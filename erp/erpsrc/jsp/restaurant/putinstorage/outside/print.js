var LODOP; // 声明为全局变量

var printColumns, printData;

function prn1_preview(columns, data) {
	printColumns = {};
	printData = {};

	printColumns = columns;
	printData = data;

	CreateOneFormPage();
	LODOP.SET_PRINT_PAGESIZE(2, 0, 0, "");
	LODOP.SET_PREVIEW_WINDOW(0, 0, 0, 0, 0, "");
	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED", 1);// 横向时的正向显示
	LODOP.PREVIEW();
};

function prn1_print(columns, data) {
	printColumns = {};
	printData = {};

	printColumns = columns;
	printData = data;

	CreateOneFormPage();
	LODOP.SET_PRINT_PAGESIZE(2, 0, 0, "");
	LODOP.SET_PREVIEW_WINDOW(0, 0, 0, 0, 0, "");
	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED", 1);// 横向时的正向显示
	LODOP.PRINT();
};

function myDesign(columns, data) {
	printColumns = {};
	printData = {};

	printColumns = columns;
	printData = data;

	CreateOneFormPage();
	LODOP.SET_PRINT_PAGESIZE(2, 0, 0, "");
	LODOP.SET_PREVIEW_WINDOW(0, 0, 0, 0, 0, "");
	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED", 1);// 横向时的正向显示
	LODOP.PRINT_DESIGN();
};

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
		if ("itemSpecification" == grid.columns[c].field) {
			continue;
		}
		if ("itemUnitPrice" == grid.columns[c].field) {
			continue;
		}
		if ("payAmt" == grid.columns[c].field) {
			continue;
		}
		if ("receivePrice" == grid.columns[c].field) {
			continue;
		}
		if ("receiveAmt" == grid.columns[c].field) {
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
			if ("itemCategory" == grid.columns[c].field) {
				continue;
			}
			if ("itemSpecification" == grid.columns[c].field) {
				continue;
			}

			if ("itemUnitPrice" == grid.columns[c].field) {
				continue;
			}
			if ("payAmt" == grid.columns[c].field) {
				continue;
			}
			if ("receivePrice" == grid.columns[c].field) {
				continue;
			}
			if ("receiveAmt" == grid.columns[c].field) {
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
		alert("请先选取一条入库单！");
		return;
	}
	var l_times = dojo.byId('l_times').innerHTML;
	LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));

	LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_空白练习");
	LODOP.SET_PRINT_PAGESIZE(2, 0, 0, "");
	LODOP.ADD_PRINT_TEXT(7, 93, 155, 32, g_companyName);
	LODOP.SET_PRINT_STYLEA(0, "FontName", "黑体");
	LODOP.SET_PRINT_STYLEA(0, "FontSize", 16);
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 2);
	LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, 428, 85, 20, "入库单编号：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, 500, 100, 20, isEmpty(formId) ? "_" : formId);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, -2, 75, 20, "入库部门：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, 73, 122, 20, isEmpty(inputDepartment) ? "_" : inputDepartment);
	LODOP.SET_PRINT_STYLEA(0, "Bold", 1);
	LODOP.SET_PRINT_STYLEA(0, "Italic", 1);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, 201, 68, 20, "供应商：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, 266, 152, 20, isEmpty(provider) ? "_" : provider);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, 609, 85, 20, "入库时间：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(38, 683, 72, 20, isEmpty(inputTime) ? "_" : inputTime);
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(390, 45, 55, 25, "装箱人:");
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
	LODOP.ADD_PRINT_TEXT(388, 76, 120, 25, "_");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
	LODOP.ADD_PRINT_TEXT(387, 273, 55, 25, "送货人:");
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
	LODOP.ADD_PRINT_TEXT(387, 320, 120, 25, "_");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
	LODOP.ADD_PRINT_TEXT(387, 513, 55, 25, "收货人:");
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
	LODOP.ADD_PRINT_TEXT(387, 567, 120, 25, "_");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 1);
	LODOP.ADD_PRINT_TEXT(12, 434, 136, 20, "第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);
	LODOP.SET_PRINT_STYLEA(0, "SpacePatch", 1);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 2);
	LODOP.ADD_PRINT_TEXT(7, 245, 112, 32, "入库单");
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
	LODOP.ADD_PRINT_TEXT(12, 522, 70, 20, "打印次数：");
	LODOP.SET_PRINT_STYLEA(0, "Alignment", 3);
	LODOP.SET_PRINT_STYLEA(0, "ItemType", 1);
	LODOP.ADD_PRINT_TEXT(12, 583, 55, 20, isEmpty(l_times) ? "_" : l_times);
	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0, "TextFrame", 2);

	var strBodyStyle = "<style> table {font-family: verdana,arial,sans-serif;font-size:10px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} "
			+ ".print-itemDimension,.print-itemSpecification,.print-itemId{text-align: center;width: 95px;}.print-itemName{width: 160px;}.print-rownumber,.print-itemDimension{width: 45px;text-align: center;}"
			+ ".print-orderCount,.print-receivedCount,.print-receiveCount,.print-differentCount,.print-itemUnitPrice,.print-payAmt,.print-receivePrice,.print-receiveAmt{text-align: right;width: 65px;}.print-receivedCount{width:75px;}</style>";
	var strFormHtml = strBodyStyle + "<body>" + document.getElementById("grid_print").innerHTML + "</body>";

	// LODOP.ADD_PRINT_TABLE(70, 15, 980, 300, strFormHtml);
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.ADD_PRINT_TABLE(65, 2, 980, 300, strFormHtml);
	LODOP.SET_PRINT_STYLEA(0, "ItemName", "mytable");
	LODOP.SET_PRINT_STYLEA(0, "Vorient", 3);
	LODOP.SET_PRINT_STYLEA(10, "LinkedItem", "mytable");
	LODOP.SET_PRINT_STYLEA(11, "LinkedItem", "mytable");
	LODOP.SET_PRINT_STYLEA(12, "LinkedItem", "mytable");
	LODOP.SET_PRINT_STYLEA(13, "LinkedItem", "mytable");
	LODOP.SET_PRINT_STYLEA(14, "LinkedItem", "mytable");
	LODOP.SET_PRINT_STYLEA(15, "LinkedItem", "mytable");

};
