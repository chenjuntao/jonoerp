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

	var formText = "领料单";
	if (formId.indexOf("LL") != -1) {
		formText = "领料单";
	} else if (formId.indexOf("CL") != -1) {
		formText = "超领单";
	} else if (formId.indexOf("TL") != -1) {
		formText = "退料单";
	}

	LODOP.PRINT_INITA(0, 0, 800, 600, "打印控件功能演示_Lodop功能_空白练习");
	LODOP.ADD_PRINT_TEXT(23, 195, 155, 32, g_companyName);
	LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
	LODOP.SET_PRINT_STYLEA(0, "FontSize", 16);
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
	LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(95, 15, 107, 20, "工单编号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(95, 122, 150, 20, isEmpty(formRefId) ? "_" : formRefId);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);

	LODOP.ADD_PRINT_TEXT(95, 357, 90, 20, "商品名称：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(95, 446, 150, 20, isEmpty(itemName) ? "_" : itemName);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);

	LODOP.ADD_PRINT_TEXT(70, 16, 107, 20, "单据编号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(70, 122, 150, 20, isEmpty(fId) ? "_" : fId);
	LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
	LODOP.SET_PRINT_STYLEA(0, "Italic", 1);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 15, 107, 20, "制单人员：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 122, 150, 20, isEmpty(formMaker) ? "_" : formMaker);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 357, 90, 20, "制单日期：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(120, 446, 150, 20, isEmpty(formTime) ? "_" : formTime);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(33, 532, 90, 20, "第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 2);
	LODOP.ADD_PRINT_TEXT(23, 333, 112, 32, formText);
	LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
	LODOP.SET_PRINT_STYLEA(0, "FontSize", 15);
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(144, 15, 107, 20, "备注：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(144, 122, 260, 20, isEmpty(formNote) ? "_" : formNote);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(33, 449, 90, 20, "总页号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TABLE(163, 14, 667, 280, "(超文本27表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);

	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	var strBodyStyle = "<style> table {font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} "
			+ ".print-rownumber,.print-itemId,.print-itemDimension,.print-itemSpecification {text-align: center;} "
			+ ".print-itemCategory,.print-itemDimension {width: 50px;} .print-rownumber{width: 40px}"
			+ ".print-itemSpecification {width: 60px;} "
			+ ".print-itemCount,.print-receivedCount,.print-receiveCount{width: 80px}.print-itemName{width: 60px}"
			+ ".print-itemCount,.print-receiveCount,.print-receivedCount,.print-differentCount {text-align: right; }  </style>";
	var strFormHtml = strBodyStyle + "<body>" + document.getElementById("grid_print").innerHTML + "</body>";

	LODOP.ADD_PRINT_TABLE(163, 25, 700, 360, strFormHtml);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);

};
