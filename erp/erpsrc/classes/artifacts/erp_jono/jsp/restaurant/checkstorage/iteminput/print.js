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

function createPrintTable(id) {
	$(id).empty();
	
	var table, tr, th, td, content,thead,tbody,tfoot;
	table = document.createElement("table");
	thead = document.createElement("thead");
	tr = document.createElement("tr");
	
	for (var c in colHeaders) {
		content = document.createTextNode(colHeaders[c]);
		th = document.createElement("th");
		th.appendChild(content);
		tr.appendChild(th);
	}
	thead.appendChild(tr);
	table.appendChild(thead);
	
	tbody = document.createElement("tbody");

	var data = $('#dataGrid').handsontable('getInstance').getData();
	var i = 1, j, l;
	for (j = 0,l = data.length; j < l; j++) {
		tr = document.createElement("tr");
		for (c in colHeaders) {
			if(c==5){
				content = document.createTextNode("");
			}else{
				if(data[j][c] === undefined){
					content = document.createTextNode("");
				}else{
					content = document.createTextNode(data[j][c]);
				}
			}
			
			td = document.createElement("td");
			td.className="print-"+columns[c].data;
			td.appendChild(content);
			tr.appendChild(td);
		}
		tbody.appendChild(tr);
		i++;
	}
	
	table.appendChild(tbody);
	document.getElementById(id).appendChild(table);
}

function isEmpty(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop))
			return false;
	}
	return true;
}


function CreateOneFormPage() {
	LODOP = getLodop(document.getElementById('LODOP_OB'), document
			.getElementById('LODOP_EM'));

	var branchId = document.getElementById('branchId')[0].label;
	var formId = document.getElementById('checkId').value;
	var checkBatchId = document.getElementById('checkBatchId').value;

	LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_空白练习");
	LODOP.ADD_PRINT_TEXT(23,308,110,32,"盘点清单");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(75,23,60,20,"餐厅：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(35,507,136,20,"第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
	LODOP.ADD_PRINT_TEXT(75,253,75,20,"盘点批次：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(35,417,91,20,"总页号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TABLE(106,21,720,475,"(超文本6表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
	LODOP.ADD_PRINT_TEXT(75,477,90,20,"盘点清单：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(75,83,150,20,isEmpty(branchId)?"_":branchId);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(75,328,130,20,isEmpty(checkBatchId)?"_":checkBatchId);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(75,567,130,20,isEmpty(formId)?"_":formId);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	
	createPrintTable("grid_print");
	
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	var strBodyStyle="<style> table {font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} " +
			".print-rownumber,.print-itemId,.print-itemDimension,.print-checkCount ," +
			"{text-align: center;} .print-itemSpecification{width: 100px;} " +
			".print-itemName{width: 150px;} .print-rownumber{width: 40px;}.print-itemId,.print-itemDimension{width: 55px;}" +
			".print-checkCount{text-align: right; width: 100px;}  </style>";
	var strFormHtml=strBodyStyle+"<body>"+document.getElementById("grid_print").innerHTML+"</body>";
	
	LODOP.ADD_PRINT_TABLE(106,21,720,475,strFormHtml);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
	
};
