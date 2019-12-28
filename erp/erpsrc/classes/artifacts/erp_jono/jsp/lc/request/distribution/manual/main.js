require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initValue();
		initGrid();
	});
});

function initValue() {
	dojo.byId('branchId').value = '';
	dojo.byId('branchText').value = '';
}

var newColum = getColumn();

function returnMyBranch(rows) {
	var delArr = [];
	var remainArr = [];

	var branchId = dojo.byId('branchId').value;
	var branchIdArr = [];

	if (branchId != undefined) {
		branchIdArr = branchId.split(",");
	}

	var bLength = branchIdArr.length;
	var rLength = rows.length;

	for (var i = bLength - 1; i >= 0; i--) {
		var myBranchId = branchIdArr[i];

		for (var j = 0; j < rLength; j++) {
			var sum = 0;

			for (var k = 0; k < rLength; k++) {
				var row = rows[k];
				sum += row[myBranchId];
			}

			if (sum <= 0) {
				if (!exist(delArr, myBranchId)) {
					delArr.push(myBranchId);
				}
			} else {
				if (!exist(remainArr, myBranchId)) {
					remainArr.push(myBranchId);
				}
			}
		}
	}

	return {
		delArr : delArr,
		remainArr : remainArr
	};
}

function exist(arr, testItem) {
	for (var i = 0; i < arr.length; i++) {
		if (testItem == arr[i]) {
			return true;
		}
	}

	return false;
}

function validateGrid() {
	var branchId = dojo.byId('branchId').value;
	var branchIdArr = new Array();
	if (branchId != undefined) {
		branchIdArr = branchId.split(",");
	}
	for (var i = branchIdArr.length - 1; i >= 0; i--) {
		if (!validateColumn(grid, branchIdArr[i])) {
			return false;
		}
	}
	return true;
}

function doSubmit() {
	var receiveTime = dojo.byId('receiveTime').value;
	if (receiveTime == '') {
		alert("请选择到货时间！");
		return;
	}

	var branchIds = dojo.byId('branchId').value;

	if (branchIds == '') {
		alert('请选择门店！');
		return;
	}

	grid.save().then(function(value) {
		var rows = dataStore.query();
		if (rows.length == 0) {
			alert("请选择原料！");
			return;
		}

		if (!validateGrid()) {// 输入正确数字
			return;
		}

		var myBranch = returnMyBranch(rows);
		var delArr = myBranch.delArr;
		var remainArr = myBranch.remainArr;

		dojo.byId('branchId').value = remainArr.join(",");
		for (var i = 0; i < rows.length; i++) {
			var item = rows[i];

			for (var j = 0; j < delArr.length; j++) {
				alert(item[delArr[j]]);
				delete item[delArr[j]];
			}

			for (var j = 0; j < remainArr.length; j++) {
				item[remainArr[j]] = Math.ceil(item[remainArr[j]] / item.packagingFactor) * item.packagingFactor;
				item.sumCount += item[remainArr[j]];
			}
		}

		dojo.byId('jsonData').value = JSON.stringify(rows);

		require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
			standby.show();
			var _url = appRoot + "/lc/request/distribution/manual/doSave.action";
			_url = getUrl(_url);
			
			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				standby.hide();
				var sidLst = data.sidLst;
				alert("生成单据号为：" + sidLst + "，操作成功！");
				location.reload();// 刷新页面
			}, function(err) {
				standby.hide();
				alert("操作失败！");
			});
		});

	}, function(err) {
		console.log(err);
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/ColumnSet", "dgrid/Keyboard", "dojo/_base/array", "dgrid/editor", "dijit/form/NumberTextBox",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojox/widget/Standby", "dojo/query" ], function(
			OnDemandGrid, Observable, Memory, declare, ColumnSet, Keyboard, array, editor, NumberTextBox,
			ColumnResizer, on, keys, Standby, query) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, ColumnSet, Keyboard ]);
		grid = new CustomGrid({
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

		// TODO
		on(grid, 'keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.TAB) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});

		// 保证鼠标焦点与键盘焦点的连贯性
		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});
	});
}

var cols = [ {
	label : "单位",
	field : "itemDimension",
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable : false
}, {
	label : "类别编码",
	field : "itemCategory",
	sortable : false
}, {
	label : "包装因子",
	field : "packagingFactor",
	sortable : false
}, {
	label : "包装单位",
	field : "packagingUnit",
	sortable : false
// }, {
// label : "标准单价",
// field : "itemUnitPrice",
// sortable : false
} ];

var length = cols.length;

function gridAppend() {
	var branchId = dojo.byId('branchId').value;
	var branchIdArr = new Array();
	if (branchId != '') {
		branchIdArr = branchId.split(",");
	}
	var branchText = dojo.byId('branchText').value;
	var branchCols = new Array();
	if (branchText != undefined) {
		branchCols = branchText.split(",");
	}
	require([ "dojo/_base/array", "dgrid/editor", "dijit/form/NumberTextBox" ], function(array, editor, NumberTextBox) {
		var reverseIndex = branchCols.length - 1;
		array.forEach(branchCols, function(bCol, i) {
			cols.unshift(editor({
				label : branchCols[reverseIndex - i],
				field : branchIdArr[reverseIndex - i],
				className : 'grid-number',
				editorArgs : {
					style : 'width: 5em;text-align: right;',
					constraints : {
						min : 0,
						max : 1999999999,
						places : '0,3',
						row : this
					},
					required : true,
					invalidMessage : '请输入不多于三位小数的数字。'
				},
				sortable : false
			}, NumberTextBox));
		});
		grid.set('columnSets', getColumn());
	});
}

function loadData(data) {
	var branchId = dojo.byId('branchId').value;
	var branchIdArr = new Array();
	if (branchId != '') {
		branchIdArr = branchId.split(",");
		for (var j = 0; j < data.length; j++) {
			data[j]["rownumber"] = j + 1;

			var row = data[j];
			for (var i = 0; i < branchIdArr.length; i++) {
				row[branchIdArr[i]] = 0;
			}
			row["sumCount"] = 0;
		}
	}
	dataStore.setData(data);
	grid.set("store", dataStore);
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false,
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : "原料编码",
		field : "itemId",
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	} ] ], [ cols ] ];
}

var materialDlg = null;
function selMaterial() {
	var branchText = dojo.byId("branchText").value;
	if (branchText == undefined) {
		alert("请选择门店！");
		return;
	}
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/lc/request/distribution/manual/selmaterial/mainView.action";
		_url = getUrl(_url);
		
		var option = {
			title : "自选原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			materialDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.loadData();
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}
