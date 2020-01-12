var LODOP; // 声明为全局变量
var printColumns, printData;
function prn1_preview() {
	CreateOneFormPage();
	LODOP.PREVIEW();
};

function prn1_print(columns, data) {
	printColumns = {};
	printData = {};

	printColumns = columns;
	printData = data;

	CreateOneFormPage();
	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED", 1);// 横向时的正向显示
	LODOP.PRINT();
};

function myDesign() {
	CreateOneFormPage();
	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED", 1);// 横向时的正向显示
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
		if ("itemCategory" == grid.columns[c].field) {
			continue;
		}

		if ("workOrderId" == grid.columns[c].field) {
			continue;
		}

		if ("workshop" == grid.columns[c].field) {
			continue;
		}

		if ("produceTime" == grid.columns[c].field) {
			content = document.createTextNode('实际主料领料数');
		} else {
			if ("completeTime" == grid.columns[c].field) {
				content = document.createTextNode('实际生产数');
			} else {
				content = document.createTextNode(grid.columns[c].label);
			}
		}

		th = document.createElement("th");
		th.appendChild(content);
		tr.appendChild(th);
	}
	thead.appendChild(tr);
	table.appendChild(thead);

	tbody = document.createElement("tbody");

	// var data = dataStore.data;
	var i = 1, j, l;
	var data = printData;
	for (j = 0, l = data.length; j < l; j++) {
		tr = document.createElement("tr");
		for (c in grid.columns) {
			if ("itemCategory" == grid.columns[c].field) {
				continue;
			}

			if ("workOrderId" == grid.columns[c].field) {
				continue;
			}

			if ("workshop" == grid.columns[c].field) {
				continue;
			}

			var value = data[j][grid.columns[c].field];

			if (isEmpty(value) && isNaN(value)) {
				value = "";
			}
			if ("produceTime" == grid.columns[c].field) {
				value = "";
			}

			if ("completeTime" == grid.columns[c].field) {
				value = "";
			}

			content = document.createTextNode(value);

			td = document.createElement("td");
			// td.className = "print-" + field;
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
		alert("请先选取一条生产计划单！");
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
	LODOP.ADD_PRINT_TEXT(72, 59, 79, 25, "单据编号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(72, 133, 133, 25, isEmpty(formId) ? "_" : formId);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(72, 540, 80, 25, "生产车间：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(71, 614, 135, 25, isEmpty(workshop) ? "_" : workshop);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(72, 311, 100, 25, "制单时间：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(72, 406, 110, 25, isEmpty(formTime) ? "_" : formTime);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TABLE(196, 15, 728, 300, "(超文本16表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_TABLE(107, 15, 728, 242, "(超文本17表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);

	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	var strBodyStyle = "<style> table {font-family: verdana,arial,sans-serif;font-size:10px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} "
			+ ".print-rownumber,.print-itemDimension2,.print-itemSpecification,.print-itemId{text-align: center;}"
			+ ".print-shippingCount,.print-deliveryCount,.print-receiveCount,.print-antiauditReceiveCount,.print-itemUnitPrice,.print-payAmt,.print-antiauditPayAmt,.print-grossCount{text-align: right;}"
			+ ".print-itemUnitPrice,.print-payAmt,.print-note,.print-itemDimension2,.print-itemSpecification,.print-productionCycle,.print-storageCount,.print-produceCount2{width: 65px;},.print-itemName,.print-mainName{width: 110px;},.print-grossCount,.print-completeTime,.print-produceTime{width: 80px;}</style>";
	var strFormHtml = strBodyStyle + "<body>" + document.getElementById("grid_print").innerHTML + "</body>";

	LODOP.ADD_PRINT_TABLE(100, 50, 728, 300, strFormHtml);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);

};
