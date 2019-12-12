function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
}

function doValidate() {
	var auditTime = dojo.byId('auditTime').value;
	if (auditTime.trim() == '') {
		alert("审核日期不能为空！");
		return false;
	}
	return true;
}

function validateGrid() {
	if (!validateColumn(grid, 'produceCount2')) {
		return false;
	}
	return true;
}

var grid = null;
var dataStore = null;

var doAudit = null;
require([ "dojo", "dojo/request/xhr", "dojo/dom-form", "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard",
		"dojo/store/Observable", "dojo/store/Memory", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/date/locale",
		"dijit/form/DateTextBox", "dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query",
		"dojox/widget/Standby", "dojo/ready" ],
		function(dojo, xhr, domForm, OnDemandGrid, declare, Keyboard, Observable, Memory, editor, NumberTextBox,
				locale, DateTextBox, ColumnResizer, on, keys, query, Standby, ready) {

			ready(function() {
				// 初始化遮罩层
				standby = new Standby({
					target : "billForm"
				});

				document.body.appendChild(standby.domNode);
				standby.startup();

				addEvent();
				initGrid();
			});

			function initGrid() {
				var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
				grid = new CustomGrid({
					sort : [ {
						attribute : "rownumber",
						descending : false
					} ],
					columns : getColumn(editor, NumberTextBox, DateTextBox),
					loadingMessage : '加载中...'
				}, "auditDataGrid");

				grid.startup();
				// grid.on("dgrid-datachange", function(event) {
				// var field = event.cell.column.field;
				// if (field == 'produceCount') {
				// grid.save();// very important
				// var row = dataStore.get(event.rowId);
				// row[field] = event.value;
				// dataStore.put(row);
				// } else if (field == 'completeTime') {
				// grid.save();// very important
				// var row = dataStore.get(event.rowId);
				// row[field] = locale.format(event.value, {
				// selector : 'date',
				// datePattern : 'yyyy-MM-dd'
				// });
				// dataStore.put(row);
				// }
				// });
				on(grid, 'keydown', function(e) {
					if (e.keyCode === keys.UP_ARROW) {
						Keyboard.moveFocusUp.call(grid, e);
					} else if (e.keyCode === keys.DOWN_ARROW) {
						Keyboard.moveFocusDown.call(grid, e);
					} else if (e.keyCode === keys.ENTER) {
						Keyboard.moveFocusDown.call(grid, e);
					}
				});

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

				on(grid, "dgrid-datachange", function(event) {
					var field = event.cell.column.field;
					if (field == 'produceCount2') {
						var row = dataStore.get(event.rowId);
						var deliveryFactor = parseFloat((row.produceCount / row.produceCount2).toFixed(2));
						// dgrid 视图和数据源相分离
						grid.save();// very important
						row[field] = event.value;
						row.produceCount = parseFloat((event.value * deliveryFactor).toFixed(2)); // 计算库存单位
						dataStore.put(row);
					}
				});

				loadGridData();
			}

			function loadGridData() {
				var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/queryDetail.action?formId="
						+ formId;
				_url = getUrl(_url);

				xhr.get(_url, {
					handleAs : "json"
				}).then(function(data) {
					dataStore = new Observable(new Memory({
						idProperty : "rownumber",
						data : data
					}));
					grid.set("store", dataStore);
				}, function(err) {
				});
			}

			function getColumn(editor, NumberTextBox, DateTextBox) {
				return [ {
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
					sortable : false
				}, {
					label : "单位",
					field : "itemDimension2",
					sortable : false
				}, {
					label : "规格",
					field : "itemSpecification",
					sortable : false
				}, {
					label : "主库存数量(物)",
					field : "storageCount",
					sortable : false
				}, editor({
					label : "计划生产量",
					field : "produceCount2",
					sortable : false,
					className : 'grid-number',
					editorArgs : {
						style : 'width: 5em;text-align: right;',
						constraints : {
							min : 0,
							max : 1999999999,
							places : '0,3'
						},
						required : true,
						invalidMessage : '请输入不多于三位小数的数字。'
					}
				}, NumberTextBox), {
					label : "生产车间",
					field : "workshop",
					sortable : false
				}, {
					label : "生产日期",
					field : "produceTime",
					sortable : false
				}, {
					label : "生产周期",
					field : "productionCycle",
					sortable : false
				}, editor({
					label : "完工日期",
					field : "completeTime",
					sortable : false,
					editorArgs : {
						constraints : {
							datePattern : 'yyyy-MM-dd',
							selector : 'date'
						},
						style : "width:100px;"
					}
				}, DateTextBox), {
					label : "",
					field : "none",
					sortable : false
				} ];
			}

			doAudit = function() {
				standby.show();
				if (!doValidate()) {
					return;
				}

				if (!validateGrid()) {
					return;
				}
				if (dojo.byId('preVersion').value.length == 0) {
					dojo.byId('preVersion').value = preVersion;
				}

				if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
					return;
				}

				grid.save().then(function(value) {
					var rows = dataStore.query();
					var info = '';
					var rowsSave = [];
					if (rows.length > 0) {
						var zeroItem = [];
						for (var i = 0; i < rows.length; i++) {
							if (typeof rows[i].completeTime == 'object') {
								rows[i].completeTime = locale.format(rows[i].completeTime, {
									selector : 'date',
									datePattern : 'yyyy-MM-dd'
								});
							}

							if (rows[i].completeTime < rows[i].businessDate) {
								info += '[' + rows[i].itemId + ']' + rows[i].itemName + "\n";
							}
							if (rows[i].produceCount2 == 0) {
								zeroItem.push(rows[i].itemName);
							} else {
								rows[i].payAmt = parseFloat((rows[i].orderCount * rows[i].itemUnitPrice).toFixed(2)); // 计算金额
								rowsSave.push(rows[i]);
							}
						}
						if (!isEmpty(info)) {
							info += "物料的完工日期小于制单日期！";
							alert(info);
							return;
						}
						if (rowsSave.length == 0) {
							alert("不允许所有物料的计划生产量为零！");
							return;
						}
						if (zeroItem.length > 0) {
							if (!confirm(zeroItem.join('、') + "输入为零，确定提交吗？")) {
								return;
							}
						}
					}

					dojo.byId('jsonData').value = JSON.stringify(rowsSave);

					var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/doAudit.action";
					_url = getUrl(_url);

					xhr.post(_url, {
						handleAs : "json",
						data : domForm.toObject("billForm")
					}).then(function(data) {
						if (data.msg == 'ok') {
							alert("审核成功！");
							standby.hide();
							doClose();
						} else {
							alert("操作失败！");
							standby.hide();
						}
					}, function(err) {
						alert("操作失败！");
						standby.hide();

					});
				}, function(err) {
					alert("操作失败！");
					console.log(err);
				});
			}
		});

function doClose() {
	closeTab(tabId, "doQuery");
}
