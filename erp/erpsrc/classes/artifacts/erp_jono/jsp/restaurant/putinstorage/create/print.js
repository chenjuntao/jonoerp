var LODOP = null; // 声明为全局变量

require([ "dojo/dom","dojo/domReady!" ],
		function(dom) {
	LODOP = getLodop(document.getElementById('LODOP_OB'),dom.byId('LODOP_EM'));
})

var flag = 1;

function prn1_preview() {
	flag = 1;
	
	CreateOneFormPage();
	
	if(flag){
		LODOP.PREVIEW();
	}
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
		if("expiredTime" == grid.columns[c].field){
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
	
	var data = grid.store.data;
	var i = 1, j, l;
	for (j = 0,l = data.length; j < l; j++) {
		tr = document.createElement("tr");
		for (c in grid.columns) {
			if("expiredTime" == grid.columns[c].field){
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
	if(isEmpty(grid)){
		alert("请先选取一条采购单！");
		flag = 0;
		return;
	}
	

	LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_空白练习");
	LODOP.ADD_PRINT_TEXT(23,97,155,32,g_companyName);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,15,85,20,"供应商编码：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,100,150,20,isEmpty(printHeader.providerId)?"_":printHeader.providerId);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,233,85,20,"到货日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,316,100,20,isEmpty(printHeader.receiveTime)?"_":printHeader.receiveTime); 
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,16,85,20,"订单编号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,101,150,20,isEmpty(printHeader.formId)?"_":printHeader.formId); 
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"Italic",1);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,15,85,20,"收货部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,100,170,20,isEmpty(printHeader.receiver)?"_":printHeader.receiver); 
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,291,90,20,"收货地址：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,380,292,20,isEmpty(printHeader.receiveAddress)?"_":printHeader.receiveAddress); 
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,455,77,20,"订货人：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,532,140,20,isEmpty(printHeader.formMaker)?"_":printHeader.formMaker); 
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,15,85,20,"订货日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,100,100,20,isEmpty(printHeader.formTime)?"_":printHeader.formTime); 
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,15,85,20,"审核人：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,98,100,20,isEmpty(printHeader.auditor)?"_":printHeader.auditor);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,233,85,20,"审核日期：\n");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,317,100,20,isEmpty(printHeader.auditTime)?"_":printHeader.auditTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,455,77,20,"备注：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,532,140,20,isEmpty(printHeader.formNote)?"_":printHeader.formNote);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(69,380,292,20,"第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
	LODOP.ADD_PRINT_TEXT(23,251,100,32,"直配订单");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(23,351,300,32,"["+ printHeader.receiver +"]");
	LODOP.SET_PRINT_STYLEA(0,"FontName","楷体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(506,20,55,20,"供应商:");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(506,66,110,20,"________________");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(506,184,55,20,"收货员:");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(506,231,110,20,"________________");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(506,355,55,20,"防损员:");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(506,402,110,20,"________________");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(506,523,63,20,"值班经理:");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(506,577,110,20,"________________");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(526,48,689,86,"1、请供应商在本订单送货日期前送货，过期无效（特殊情况须提前通知订货人）；送货前请先电话预约，以便优先安排收货。\n2、请供应商于送货前仔细核对商品品名、规格、数量，无误方可送货。\n3、本订单“审核人”栏空白无效。");	
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.SET_PRINT_STYLEA(0,"LineSpacing",-1);
	LODOP.SET_PRINT_STYLEA(0,"LetterSpacing",-1);
	LODOP.ADD_PRINT_TEXT(532,19,39,30,"注：");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(95,290,90,20,"供应商名称：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,380,292,20,isEmpty(printHeader.provider)?"_":printHeader.provider);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(69,290,90,20,"总页号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TABLE(196,14,667,280,"(超文本38表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);

	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	var strBodyStyle="<style> table {font-family: verdana,arial,sans-serif;font-size:11px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} .print-rownumber,.print-itemId,.print-itemDimension {text-align: center;} .print-itemCategory,.print-itemSpecification,.print-itemDimension {width: 50px;} .print-itemName{width: 120px;} .print-itemCount,.print-itemUnitPrice,.print-payAmt {text-align: right; width: 50px;}  </style>";
	var strFormHtml=strBodyStyle+"<body>"+document.getElementById("grid_print").innerHTML+"</body>";
	
	LODOP.ADD_PRINT_TABLE(196,14,667,280,strFormHtml);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);



	
};
