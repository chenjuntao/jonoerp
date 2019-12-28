require([ "dojo", "dojo/date", "dojo/date/locale", "dojox/widget/Standby", "dojo/ready" ], function(dojo, date, locale,
		Standby, ready) {
	ready(function() {
		// 默认为不立即生效
		dojo.byId('effectType').value = '';

		addEvent();
		initGrid();

		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

		window.checkEffectTime = function() {
			var effectTime = dojo.byId('effectTime').value;
			if (effectTime == '') {
				alert('生效时间不能为空！');
				return false;
			}

			var parseOption = {
				datePattern : "yyyy-MM-dd",
				selector : "date"
			};
			var startDate = locale.parse(dojo.byId('startDate').value, parseOption);
			var effectTime = dojo.byId('effectTime').value;
			effectTime = locale.parse(effectTime, parseOption);
			var result = date.compare(effectTime, startDate, "date");
			if (result < 0) {
				alert("生效时间小于等于当前营业日期,请设置生效时间！")
				return false;
			} else if (result == 0) {
				if (confirm("确定立即生效吗？")) {
					dojo.byId('effectType').value = 'immediate';
					return true;
				} else {
					return false;
				}
			}
			return true;
		}
	});
});

var standby = null;

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});

	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

var dataStore = null;
var grid = null;
function initGrid() {
	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore", "dojo/_base/declare",
			"dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/lang", "dgrid/extensions/ColumnResizer" ], function(Observable, Memory, ObjectStore, declare,
			OnDemandGrid, selector, Selection, editor, NumberTextBox, lang, ColumnResizer) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector, editor, NumberTextBox, lang),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();

		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/hq/priceadjust/queryDetail.action?formId=" + formId;

	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				dataStore.put(row);
			});
		}, function(err) {
		});
	});
}

var numArgs = {
	style : 'width: 5em;',
	constraints : {
		min : 0,
		max : 99550,
		places : '0,3'
	},
	required : true,
	invalidMessage : '请输入不多于三位小数的数字。'
};

function getColumn(selector, editor, NumberTextBox, lang) {
	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
	}
	return [
	// selector({
	// field : 'rownumber',
	// sortable:false
	// }),
	{
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		},
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "包装因子",
		field : "itemPackager",
		sortable : false
	}, {
		label : "原价",
		field : "oldPrice",
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "新价",
		field : "newPrice",
		sortable : false
	}),{
		label : "状态",
		field : "enabled",
		align : 'center',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false,
		sortable : false
	} ];
}

function doValidate() {
	return true;
}

function doAudit() {
	if (!doValidate()) {
		return;
	}

	if (!checkEffectTime()) {
		return;
	}

	standby.show();

	grid.save().then(function(value) {
		var rows = dataStore.query();
		dojo.byId('jsonData').value = JSON.stringify(rows);

		var _url = appRoot + "/hq/priceadjust/audit/doAudit.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				standby.hide();

				if (data.msg == 'ok') {
					alert("提交成功！");
					doClose();
				} else {
					alert("请先将未启用的出品启用，或者手动从调价单中移除！");
				}
			}, function(err) {
				standby.hide();
			});
		});
	}, function(err) {
		console.log(err);
		standby.hide();
	}, function(update) {
		console.log(update);
	});
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
