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

function test(){
	console.dir(myStore.getData());
}

function createPrintTable(id) {
	dojo.empty(id);
	
	var table, tr, th, td, content, c,thead,tbody,tfoot;
	table = document.createElement("table");
	thead = document.createElement("thead");
	tr = document.createElement("tr");
	
	for (c in grid.columns) {
		if("none" == grid.columns[c].field){
			continue;
		}
		if("itemCategory" == grid.columns[c].field){
			continue;
		}
		if("applyCount" == grid.columns[c].field){
			continue;
		}
		if("differentCount" == grid.columns[c].field){
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

	var data = myStore.getData();
	var i = 1, j, l;
	for (j = 0,l = data.length; j < l; j++) {
		tr = document.createElement("tr");
		for (c in grid.columns) {
			if("none" == grid.columns[c].field){
				continue;
			}
			if("itemCategory" == grid.columns[c].field){
				continue;
			}
			if("applyCount" == grid.columns[c].field){
				continue;
			}
			if("differentCount" == grid.columns[c].field){
				continue;
			}
							
			if(data[j][grid.columns[c].field] === undefined){
				content = document.createTextNode("");
			}else{
				content = document.createTextNode(data[j][grid.columns[c].field]);
			}
			td = document.createElement("td");
			td.className="print-"+grid.columns[c].field;
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
	if(grid == undefined || grid == null){
		alert("请先选取一条采购单！");
		return;
	}
	
	LODOP = getLodop(document.getElementById('LODOP_OB'), document
			.getElementById('LODOP_EM'));




	LODOP.PRINT_INITA(0,0,800,600,"打印控件功能演示_Lodop功能_空白练习");
	LODOP.ADD_PRINT_TEXT(23,224,155,32,g_companyName);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,44,85,20,"调入部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,130,150,20,isEmpty(inBranch)?"_":inBranch);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,262,85,20,"确认日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,345,100,20,isEmpty(cTime)?"_":cTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,45,85,20,"订单编号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,130,150,20,isEmpty(fId)?"_":fId);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"Italic",1);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,44,85,20,"调出部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,129,170,20,isEmpty(outBranch)?"_":outBranch);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,320,90,20,"调出仓库：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,409,292,20,isEmpty(outStorage)?"_":outStorage);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,44,85,20,"确认人员：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,129,100,20,isEmpty(confirmer)?"_":confirmer);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,44,85,20,"制单人员：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,127,100,20,isEmpty(fromMaker)?"_":fromMaker);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,262,85,20,"制单日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,346,100,20,isEmpty(formTime)?"_":formTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,465,77,20,"备注：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,542,159,20,isEmpty(formNote)?"_":formNote);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(35,549,90,20,"第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
	LODOP.ADD_PRINT_TEXT(23,349,112,32,"调拨单");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,319,90,20,"调入仓库：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,409,292,20,isEmpty(inStorage)?"_":inStorage);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(35,449,90,20,"总页号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TABLE(196,4,667,280,"(超文本25表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
	LODOP.ADD_PRINT_TABLE(196,42,667,280,"(超文本26表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);






	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	var strBodyStyle="<style> table {font-family: verdana,arial," +
			"sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: " +
			"#666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	" +
			"border-style: solid; 	border-color: #666666;	background-color: #ffffff;} " +
			"th {border-width: 1px;	padding: 8px;	border-style: solid;	" +
			"border-color: #666666;}" +
			" .print-rownumber,.print-itemId,.print-itemDimension ,.print-itemSpecification," +
			"{text-align: center;} .print-itemDimension{width:60px}.print-rownumber{width:30px}" +
			".print-itemId,.print-payAmt,.print-itemSpecification {width: 70px;} .print-itemName{width: 120px;} " +
			".print-actualCount,.print-payAmt,.print-storage_count,.print-unitPrice " +
			"{text-align: right; width: 50px;}.print-payAmt,.print-unitPrice {width: 70px;}  </style>";
	var strFormHtml=strBodyStyle+"<body>"+document.getElementById("grid_print").innerHTML+"</body>";
	
	LODOP.ADD_PRINT_TABLE(196,57,667,280,strFormHtml);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);



	
};
