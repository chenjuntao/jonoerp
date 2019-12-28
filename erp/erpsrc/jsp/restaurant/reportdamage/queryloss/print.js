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
	dojo.empty(id);
	
	var table, tr, th, td, content, c,thead,tbody,tfoot;
	table = document.createElement("table");
	thead = document.createElement("thead");
	tr = document.createElement("tr");
	
	for (c in grid.columns) {
		if("reason" == grid.columns[c].field){
			continue;
		}
		
		if("expired_time" == grid.columns[c].field){
			continue;
		}
		if("item_category" == grid.columns[c].field){
			continue;
		}
		
		if("none" == grid.columns[c].field){
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
			
			if("reason" == grid.columns[c].field){
				continue;
			}
			if("expired_time" == grid.columns[c].field){
				continue;
			}
			if("item_category" == grid.columns[c].field){
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

	LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_空白练习");
	LODOP.ADD_PRINT_TEXT(23,166,155,32,g_companyName);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,44,85,20,"报损仓库：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,129,150,20,isEmpty(storage)?"_":storage);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(119,262,85,20,"报损日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(119,345,100,20,isEmpty(lTime)?"_":lTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,45,85,20,"订单编号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,130,150,20,isEmpty(formId)?"_":formId);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"Italic",1);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(119,44,85,20,"报损人：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(119,129,108,20,isEmpty(lossMan)?"_":lossMan);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(144,44,85,20,"审核人：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(144,129,108,20,isEmpty(auditor)?"_":auditor);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(144,262,85,20,"审核日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(144,346,100,20,isEmpty(aTime)?"_":aTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(119,458,77,20,"备注：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(119,535,166,20,isEmpty(formnote)?"_":formnote);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(35,507,136,20,"第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
	LODOP.ADD_PRINT_TEXT(23,318,112,32,"原料报损单");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,319,90,20,"报损部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,409,292,20,isEmpty(lossBranch)?"_":lossBranch);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(35,417,91,20,"总页号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TABLE(196,14,667,280,"(超文本21表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
	LODOP.ADD_PRINT_TEXT(10,670,79,20,isEmpty(fStatus)?"_":fStatus);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_SHAPE(2,4,670,78,26,0,1,"#161616");


	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	var strBodyStyle="<style> table {font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} " +
			".print-rownumber,.print-item_id,.print-item_dimension ,.print-item_specification," +
			"{text-align: center;} .print-item_specification,.print-item_dimension {width: 60px;} " +
			".print-item_name{width: 120px;} .print-rownumber{width: 30px;}.print-item_id{width: 60px;}" +
			".print-item_count,.print-pay_amt,.print-storage_count,.print-unit_price " +
			"{text-align: right; width: 70px;}  </style>";
	var strFormHtml=strBodyStyle+"<body>"+document.getElementById("grid_print").innerHTML+"</body>";
	
	LODOP.ADD_PRINT_TABLE(176,17,720,280,strFormHtml);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
};
