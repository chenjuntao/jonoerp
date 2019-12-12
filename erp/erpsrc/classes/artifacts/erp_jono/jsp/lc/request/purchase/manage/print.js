var LODOP; // 声明为全局变量

var printColumns, printData;

function prn1_preview(columns, data) {
	printColumns = {};
	printData = {};

	printColumns = columns;
	printData = data;

	CreateOneFormPage();
	LODOP.PREVIEW();
};

function prn1_print(columns, data) {
	printColumns = {};
	printData = {};

	printColumns = columns;
	printData = data;

	CreateOneFormPage();
	LODOP.PRINT();
};

function myDesign(columns, data) {
	printColumns = {};
	printData = {};

	printColumns = columns;
	printData = data;

	CreateOneFormPage();
	LODOP.PRINT_DESIGN();
};

function createPrintTable(id) {
	require(
			[ "dojo/_base/array" ],
			function(array) {

				// 补全数据
				array.forEach(printData, function(outItem) {
					array.forEach(printColumns, function(inItem) {
						if (!outItem.hasOwnProperty(inItem.field)) {
							outItem[inItem.field] = "";
						}
					});
				});

				dojo.empty(id);

				var table, tr, th, td, content, thead, tbody, tfoot;
				table = document.createElement("table");
				thead = document.createElement("thead");
				tr = document.createElement("tr");

				for ( var index in printColumns) {
					if (printColumns[index].field == 'receiver') {
						continue;
					}

					if (printColumns[index].field == 'payAmt') {
						continue;
					}

					if (printColumns[index].field == 'receivePrice') {
						continue;
					}

					if (printColumns[index].field == 'receiveAmt') {
						continue;
					}

					if (printColumns[index].field == 'receiveAddress') {
						continue;
					}

					if (printColumns[index].field == 'itemCategory') {
						continue;
					}

					if (printColumns[index].field == 'itemUnitPrice') {
						content = document.createTextNode('实收数量');
					} else if (printColumns[index].field == 'expiredTime') {
						content = document.createTextNode("备注");
					} else {
						content = document.createTextNode(printColumns[index].label);
					}

					th = document.createElement("th");
					th.appendChild(content);
					tr.appendChild(th);
				}

				thead.appendChild(tr);
				table.appendChild(thead);

				tbody = document.createElement("tbody");

				var i, j, l;

				for (j = 0, l = printData.length; j < l; j++) {
					tr = document.createElement("tr");
					for (i = 0; i < printColumns.length; i++) {
						var field = printColumns[i].field;

						if (field == 'receiver') {
							continue;
						}

						if (field == 'payAmt') {
							continue;
						}

						if (field == 'receivePrice') {
							continue;
						}

						if (field == 'receiveAmt') {
							continue;
						}

						if (field == 'receiveAddress') {
							continue;
						}

						if (field == 'itemCategory') {
							continue;
						}

						if (printData[j].hasOwnProperty(field)) {
							var value = printData[j][field];

							if (isEmpty(value) && isNaN(value)) {
								value = "";
							}

							if (field == 'expiredTime' || field == 'itemUnitPrice') {
								value = "";
							}

							content = document.createTextNode(value);

							td = document.createElement("td");
							td.className = "print-" + field;
							td.appendChild(content);
							tr.appendChild(td);
						}
					}
					tbody.appendChild(tr);
				}

				table.appendChild(tbody);
				dojo.byId(id).appendChild(table);

				var l_receiveTime = dojo.byId('l_receiveTime').innerHTML;
				var l_formTime = dojo.byId('l_formTime').innerHTML;
				var l_auditTime = dojo.byId('l_auditTime').innerHTML;

				var l_formId = dojo.byId('l_formId').innerHTML;
				var l_provider = dojo.byId('l_provider').innerHTML;
				var l_requester = dojo.byId('l_requester').innerHTML;
				var l_deliveryType = dojo.byId('l_deliveryType').innerHTML;
				var l_times = dojo.byId('l_times').innerHTML;

				LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_空白练习");
				LODOP.ADD_PRINT_TEXT(7, 93, 155, 32, g_companyName);
				LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
				LODOP.SET_PRINT_STYLEA(0, "FontSize", 16);
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
				LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(57, 396, 75, 20, "到货日期：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(57, 469, 80, 20, isEmpty(l_receiveTime) ? "_" : l_receiveTime);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(36, -2, 75, 20, "单据编号：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(36, 73, 150, 20, isEmpty(l_formId) ? "_" : l_formId);
				LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
				LODOP.SET_PRINT_STYLEA(0, "Italic", 1);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(37, 273, 68, 20, "供应商：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(37, 341, 327, 20, isEmpty(l_provider) ? "_" : l_provider);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(57, -2, 75, 20, "订货部门：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(57, 73, 168, 20, isEmpty(l_requester) ? "_" : l_requester);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(12, 434, 136, 20, "第#页/共&页");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 2);
				LODOP.ADD_PRINT_TEXT(7, 245, 112, 32, l_deliveryType + "采购单");
				LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
				LODOP.SET_PRINT_STYLEA(0, "FontSize", 15);
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(12, 374, 61, 20, "总页号：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(10, 670, 79, 20, status);
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_SHAPE(2, 4, 670, 78, 26, 0, 1, "#161616");
				LODOP.ADD_PRINT_TEXT(522, 12, 55, 20, "供应商:");
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(522, 58, 110, 20, "________________");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(522, 228, 55, 20, "收货员:");
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(522, 275, 110, 20, "________________");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(522, 442, 55, 20, "品控员:");
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(522, 489, 110, 20, "________________");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);

				LODOP.ADD_PRINT_TEXT(12, 522, 70, 20, "打印次数：");

				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(12, 583, 55, 20, l_times);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);

				var strBodyStyle = "<style> table {font-family: verdana,arial,"
						+ "sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: "
						+ "#666666;border-collapse: collapse;} td {border-width: 1px; padding:4px;"
						+ "border-style: solid; 	border-color: #666666;	background-color: #ffffff;} "
						+ "th {border-width: 1px;border-style: solid;border-color: #666666;padding:5px;}"
						+ " .print-rownumber,.print-itemId,.print-itemDimension,"
						+ "{text-align: center;} .print-rownumber,.print-itemDimension{width:50px}"
						+ ".print-payAmt,.print-itemSpecification {width: 135px;} .print-itemId,.print-receiver{width: 65px;}.print-receiver,.print-itemUnitPrice,.print-expiredTime{width: 75px;} "
						+ ".print-itemName{width: 200px;}.print-itemCount"
						+ "{text-align: right; width: 60px;}  </style>";
				var strFormHtml = strBodyStyle + "<body>" + document.getElementById("grid_print").innerHTML + "</body>";
				LODOP.ADD_PRINT_TABLE(81, 4, 720, 415, strFormHtml);

				LODOP.SET_PRINT_STYLEA(0, "ItemName", "mytable");
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
				LODOP.SET_PRINT_STYLEA(15, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(16, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(17, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(18, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(19, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(20, "LinkedItem", "mytable");
				LODOP.ADD_PRINT_TEXT(57, 551, 75, 20, "审核日期：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(57, 627, 90, 20, l_auditTime);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(57, 241, 75, 20, "订货日期：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(57, 316, 80, 20, l_formTime);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
			});
}

function createPrintTable_YK(id) {
	console.log("YK");
	require(
			[ "dojo/_base/array" ],
			function(array) {

				// 补全数据
				array.forEach(printData, function(outItem) {
					array.forEach(printColumns, function(inItem) {
						if (!outItem.hasOwnProperty(inItem.field)) {
							outItem[inItem.field] = "";
						}
					});
				});

				dojo.empty(id);

				var table, tr, th, td, content, thead, tbody, tfoot;
				table = document.createElement("table");
				thead = document.createElement("thead");
				tr = document.createElement("tr");

				for ( var index in printColumns) {
					if (printColumns[index].field == 'itemUnitPrice') {
						continue;
					}

					if (printColumns[index].field == 'payAmt') {
						continue;
					}

					if (printColumns[index].field == 'receivePrice') {
						continue;
					}

					if (printColumns[index].field == 'receiveAmt') {
						continue;
					}

					if (printColumns[index].field == 'receiveAddress') {
						continue;
					}

					if (printColumns[index].field == 'itemCategory') {
						continue;
					}

					if (printColumns[index].field == 'expiredTime') {
						content = document.createTextNode('实收数量');
					} else {
						content = document.createTextNode(printColumns[index].label);
					}

					th = document.createElement("th");
					th.appendChild(content);
					tr.appendChild(th);
				}

				thead.appendChild(tr);
				table.appendChild(thead);

				tbody = document.createElement("tbody");

				var i, j, l;

				for (j = 0, l = printData.length; j < l; j++) {
					tr = document.createElement("tr");
					for (i = 0; i < printColumns.length; i++) {
						var field = printColumns[i].field;

						if (field == 'itemUnitPrice') {
							continue;
						}

						if (field == 'payAmt') {
							continue;
						}

						if (field == 'receivePrice') {
							continue;
						}

						if (field == 'receiveAmt') {
							continue;
						}

						if (field == 'receiveAddress') {
							continue;
						}

						if (field == 'itemCategory') {
							continue;
						}

						if (printData[j].hasOwnProperty(field)) {
							var value = printData[j][field];

							if (isEmpty(value) && isNaN(value)) {
								value = "";
							}

							if (field == 'expiredTime') {
								value = "";
							}

							content = document.createTextNode(value);

							td = document.createElement("td");
							td.className = "print-" + field;
							td.appendChild(content);
							tr.appendChild(td);
						}
					}
					tbody.appendChild(tr);
				}

				table.appendChild(tbody);
				dojo.byId(id).appendChild(table);

				var l_receiveTime = dojo.byId('l_receiveTime').innerHTML;
				var l_formTime = dojo.byId('l_formTime').innerHTML;
				var l_auditTime = dojo.byId('l_auditTime').innerHTML;

				var l_formId = dojo.byId('l_formId').innerHTML;
				var l_provider = dojo.byId('l_provider').innerHTML;
				var l_requester = dojo.byId('l_requester').innerHTML;
				var l_deliveryType = dojo.byId('l_deliveryType').innerHTML;

				LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_空白练习");
				LODOP.ADD_PRINT_TEXT(7, 166, 155, 32, g_companyName);
				LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
				LODOP.SET_PRINT_STYLEA(0, "FontSize", 16);
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
				LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(65, 408, 75, 20, "到货日期：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(65, 481, 80, 20, isEmpty(l_receiveTime) ? "_" : l_receiveTime);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(39, 10, 75, 20, "单据编号：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(39, 85, 150, 20, isEmpty(l_formId) ? "_" : l_formId);
				LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
				LODOP.SET_PRINT_STYLEA(0, "Italic", 1);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(40, 285, 68, 20, "供应商：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(40, 353, 327, 20, isEmpty(l_provider) ? "_" : l_provider);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(65, 10, 75, 20, "订货部门：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(65, 85, 168, 20, isEmpty(l_requester) ? "_" : l_requester);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(12, 507, 136, 20, "第#页/共&页");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 2);
				LODOP.ADD_PRINT_TEXT(7, 318, 112, 32, l_deliveryType + "采购单");
				LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
				LODOP.SET_PRINT_STYLEA(0, "FontSize", 15);
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(12, 447, 61, 20, "总页号：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(10, 670, 79, 20, status);
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_SHAPE(2, 4, 670, 78, 26, 0, 1, "#161616");
				LODOP.ADD_PRINT_TEXT(561, 20, 55, 20, "供应商:");
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(561, 66, 110, 20, "________________");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(561, 236, 55, 20, "收货员:");
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(561, 283, 110, 20, "________________");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(561, 450, 55, 20, "品控员:");
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				LODOP.ADD_PRINT_TEXT(561, 497, 110, 20, "________________");
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
				var strBodyStyle = "<style> table {font-family: verdana,arial,"
						+ "sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: "
						+ "#666666;border-collapse: collapse;} td {border-width: 1px; padding:4px;"
						+ "border-style: solid; 	border-color: #666666;	background-color: #ffffff;} "
						+ "th {border-width: 1px;border-style: solid;border-color: #666666;padding:5px;}"
						+ " .print-rownumber,.print-itemId,.print-itemDimension,"
						+ "{text-align: center;} .print-rownumber,.print-itemDimension{width:50px}"
						+ ".print-payAmt,.print-itemSpecification,.print-receiver {width: 105px;} .print-itemId{width: 65px;}.print-expiredTime{width: 75px;} "
						+ ".print-itemName{width: 150px;}.print-itemCount"
						+ "{text-align: right; width: 60px;}  </style>";
				var strFormHtml = strBodyStyle + "<body>" + document.getElementById("grid_print").innerHTML + "</body>";
				LODOP.ADD_PRINT_TABLE(91, 17, 720, 435, strFormHtml);

				LODOP.SET_PRINT_STYLEA(0, "ItemName", "mytable");
				LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
				LODOP.SET_PRINT_STYLEA(15, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(16, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(17, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(18, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(19, "LinkedItem", "mytable");
				LODOP.SET_PRINT_STYLEA(20, "LinkedItem", "mytable");
				LODOP.ADD_PRINT_TEXT(65, 563, 75, 20, "审核日期：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(65, 639, 90, 20, l_auditTime);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(65, 253, 75, 20, "订货日期：");
				LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
				LODOP.ADD_PRINT_TEXT(65, 328, 80, 20, l_formTime);
				LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
				LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
				LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);

			});
}

function CreateOneFormPage() {
	var l_deliveryType = dojo.byId('l_deliveryType').innerHTML;

	LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));

	if ("越库" == l_deliveryType) {
		createPrintTable_YK("grid_print");
	} else {
		createPrintTable("grid_print");
	}
};
