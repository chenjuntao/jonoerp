var LODOP; // 声明为全局变量
function prn1_preview() {
	CreateOneFormPage();
	LODOP.PREVIEW();
};
function prn1_print() {
	CreateOneFormPage();
	LODOP.PRINT();
};
function myDesign() {
	CreateOneFormPage();
	LODOP.PRINT_DESIGN();
};

function test() {
	console.dir(dataStore.getData());
}

function createPrintTable(id) {
	dojo.empty(id);

	var table, tr, th, td, content, c, thead, tbody, tfoot;
	table = document.createElement("table");
	thead = document.createElement("thead");
	tr = document.createElement("tr");
	for (c in grid.columns) {
		if ("none" == grid.columns[c].field) {
			continue;
		}

		if ("returnReason" == grid.columns[c].field) {
			continue;
		}
		if ("returnPayAmt" == grid.columns[c].field) {
			continue;
		}

		if ("itemUnitPrice" == grid.columns[c].field) {
			continue;
		}
		if ("itemCategory" == grid.columns[c].field) {
			continue;
		}

		content = document.createTextNode(grid.columns[c].label);
		th = document.createElement("th");
		th.appendChild(content);
		tr.appendChild(th);
	}
	thead.appendChild(tr);
	table.appendChild(thead);

	tbody = document.createElement("tbody");

	var data = dataStore.getData();
	
	var i = 1, j, l;
	for (j = 0, l = data.length; j < l; j++) {
		if(data[j]["returnCount"]==0){
			continue;
		}
		
		tr = document.createElement("tr");
		for (c in grid.columns) {
			if ("none" == grid.columns[c].field) {
				continue;
			}

			if ("returnReason" == grid.columns[c].field) {
				continue;
			}
			if ("returnPayAmt" == grid.columns[c].field) {
				continue;
			}

			if ("itemUnitPrice" == grid.columns[c].field) {
				continue;
			}
			if ("itemCategory" == grid.columns[c].field) {
				continue;
			}

			if (data[j][grid.columns[c].field] === undefined) {
				content = document.createTextNode("");
			} else {
				content = document.createTextNode(data[j][grid.columns[c].field]);
			}
			td = document.createElement("td");
			td.className = "print-" + grid.columns[c].field;
			td.appendChild(content);
			tr.appendChild(td);
		}
		tbody.appendChild(tr);
		i++;
	}

	table.appendChild(tbody);
	dojo.byId(id).appendChild(table);
}

function CreateOneFormPage() {
	if (grid == undefined || grid == null) {
		alert("请先选取一条采购单！");
		return;
	}

	LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));

	LODOP.PRINT_INIT("0");
	LODOP.ADD_PRINT_TEXT(23, 206, 155, 32, g_companyName);
	LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
	LODOP.SET_PRINT_STYLEA(0, "FontSize", 16);
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
	LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(95, 35, 95, 20, "配送单编号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(95, 123, 150, 20, isEmpty(sformId) ? "_" : sformId);
	LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
	LODOP.SET_PRINT_STYLEA(0, "Italic", 1);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(145, 253, 85, 20, "订货部门：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(145, 336, 120, 20, isEmpty(requester) ? "_" : requester);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(70, 36, 95, 20, "退货单据编号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(70, 124, 150, 20, isEmpty(rformId) ? "_" : rformId);
	LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
	LODOP.SET_PRINT_STYLEA(0, "Italic", 1);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 35, 95, 20, "配送日期：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 123, 150, 20, isEmpty(receiveTime) ? "_" : receiveTime);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 311, 90, 20, "订货地址：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 410, 292, 20, isEmpty(requestAddress) ? "_" : requestAddress);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(145, 475, 77, 20, "入库人员：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(145, 552, 140, 20, isEmpty(inputer) ? "_" : inputer);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(145, 35, 95, 20, "配送日期：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(145, 123, 100, 20, isEmpty(receiveTime) ? "_" : receiveTime);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(170, 35, 95, 20, "入库日期：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(170, 121, 100, 20, isEmpty(inputTime) ? "_" : inputTime);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(170, 253, 85, 20, "退货人员：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(170, 336, 120, 20, isEmpty(returner) ? "_" : returner);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(170, 475, 77, 20, "退货日期：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(170, 552, 140, 20, isEmpty(returnTime) ? "_" : returnTime);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(29, 545, 93, 20, "第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 2);
	LODOP.ADD_PRINT_TEXT(23, 358, 112, 32, "配送退货单");
	LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
	LODOP.SET_PRINT_STYLEA(0, "FontSize", 15);
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(95, 310, 90, 20, "配送部门：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(95, 400, 292, 20, isEmpty(providerId) ? "_" : providerId);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(29, 455, 90, 20, "总页号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TABLE(196, 34, 667, 280, "(超文本27表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_TABLE(196, 34, 667, 280, "(超文本28表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_TEXT(14, 651, 95, 22, isEmpty(formStatus) ? "_" : formStatus);
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
	LODOP.ADD_PRINT_SHAPE(2, 7, 649, 100, 27, 0, 1, "#191919");
	LODOP.ADD_PRINT_TEXT(70, 310, 90, 20, "备注：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(70, 400, 292, 20, isEmpty(formNote) ? "_" : formNote);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TABLE(196, 34, 667, 280, "(超文本33表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);

	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	var strBodyStyle = "<style> table {font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} "
			+ ".print-itemSpecification,.print-rownumber,.print-itemId,.print-itemDimension {text-align: center;} "
			+ ".print-itemCategory,.print-itemSpecification,.print-itemDimension {width: 50px;} "
			+ ".print-itemName{width: 140px;} .print-itemCount,.print-itemUnitPrice,.print-payAmt {text-align: right; width: 50px;}  "
			+ ".print-shippingCount, .print-deliveryCount, .print-receiveCount ,.print-returnCount{text-align: right; width: 45px;} </style>";
	var strFormHtml = strBodyStyle + "<body>" + document.getElementById("grid_print").innerHTML + "</body>";

	LODOP.ADD_PRINT_TABLE(196, 55, 667, 280, strFormHtml);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);

};
