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
	console.dir(dataStore.getData());
}

function createPrintTable(id) {
	dojo.empty(id);
	
	var table, tr, th, td, content, c,thead,tbody,tfoot;
	table = document.createElement("table");
	thead = document.createElement("thead");
	tr = document.createElement("tr");
	for (c in grid.columns) {
		if("itemCategory" == grid.columns[c].field){
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
	for (j = 0,l = data.length; j < l; j++) {
		tr = document.createElement("tr");
		for (c in grid.columns) {
			if("itemCategory" == grid.columns[c].field){
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









	LODOP.PRINT_INIT("0");
	LODOP.ADD_PRINT_TEXT(23,206,155,32,g_companyName);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,35,95,20,"订货部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,123,130,20,isEmpty(requester)?"_":requester);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,218,85,20,"入库日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,301,120,20,isEmpty(inputTime)?"_":inputTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,36,95,20,"配送单编号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,124,130,20,isEmpty(sformId)?"_":sformId);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"Italic",1);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,35,95,20,"制单人员：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(120,123,130,20,isEmpty(formMaker)?"_":formMaker);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(121,262,90,20,"制单日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(121,351,130,20,isEmpty(formTime)?"_":formTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,417,82,20,"订货地址：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,498,232,20,isEmpty(requestAddress)?"_":requestAddress);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,35,95,20,"入库人员：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(145,123,100,20,isEmpty(inputer)?"_":inputer);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,35,95,20,"反审核人员：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,122,100,20,isEmpty(antiauditor)?"_":antiauditor);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,218,85,20,"反审核日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,301,120,20,isEmpty(antiauditTime)?"_":antiauditTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,417,82,20,"备注：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(170,498,232,20,isEmpty(formNote)?"_":formNote);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(29,572,93,20,"第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
	LODOP.ADD_PRINT_TEXT(23,358,133,32,"配送反审核单");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,262,90,20,"入库仓库：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,351,130,20,isEmpty(inStorage)?"_":inStorage);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(29,482,90,20,"总页号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TABLE(196,34,667,280,"(超文本27表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
	LODOP.ADD_PRINT_TEXT(70,262,90,20,"配送部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,351,130,20,isEmpty(provider)?"_":provider);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,490,90,20,"出库仓库：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(70,580,150,20,isEmpty(outStorage)?"_":outStorage);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,490,90,20,"反审核部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(95,580,150,20,isEmpty(antiauditBranch)?"_":antiauditBranch);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(121,490,90,20,"配送日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(121,580,150,20,isEmpty(receiveTime)?"_":receiveTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TABLE(196,55,667,300,"(超文本38表格的HTML代码内容)");
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);




	createPrintTable("grid_print");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	var strBodyStyle="<style> table {font-family: verdana,arial,sans-serif;font-size:10px;color:#333333;border-width: 1px;border-color: #666666;border-collapse: collapse;} td {border-width: 1px;	padding: 8px; 	border-style: solid; 	border-color: #666666;	background-color: #ffffff;} th {border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;} " +
			".print-rownumber,.print-itemDimension,.print-itemSpecification,.print-itemId{text-align: center;}" +
			".print-shippingCount,.print-deliveryCount,.print-receiveCount,.print-antiauditReceiveCount,.print-itemUnitPrice,.print-payAmt,.print-antiauditPayAmt{text-align: right;}" +
			".print-itemUnitPrice,.print-payAmt{width: 60px;}</style>";
	var strFormHtml=strBodyStyle+"<body>"+document.getElementById("grid_print").innerHTML+"</body>";
	
	LODOP.ADD_PRINT_TABLE(196,15,728,300,strFormHtml);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",3);



	
};
