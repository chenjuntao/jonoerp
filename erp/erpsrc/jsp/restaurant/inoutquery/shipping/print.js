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

	var table, tr, th, td, content, c, thead, tbody, tfoot;
	table = document.createElement("table");
	thead = document.createElement("thead");
	tr = document.createElement("tr");

	for (c in grid.columns) {
		if ("none" == grid.columns[c].field) {
			continue;
		}
		if ("payAmt" == grid.columns[c].field) {
			continue;
		}
		if ("itemUnitPrice" == grid.columns[c].field) {
			continue;
		}
		if ("packagingFactor" == grid.columns[c].field) {
			continue;
		}
		if ("itemCategory" == grid.columns[c].field) {
			continue;
		}
		
		if ("packagingUnit" == grid.columns[c].field) {
			continue;
		}
		
		if("packagingCount" == grid.columns[c].field){
			content = document.createTextNode("实发包数");
		}else{
			content = document.createTextNode(grid.columns[c].label);
		}
		
		th = document.createElement("th");
		th.appendChild(content);
		tr.appendChild(th);
	}
	thead.appendChild(tr);
	table.appendChild(thead);

	tbody = document.createElement("tbody");

	var data = myStore.getData();
	var i = 1, j, l;
	for (j = 0, l = data.length; j < l; j++) {
		tr = document.createElement("tr");
		for (c in grid.columns) {
			if ("none" == grid.columns[c].field) {
				continue;
			}
			if ("payAmt" == grid.columns[c].field) {
				continue;
			}
			if ("itemUnitPrice" == grid.columns[c].field) {
				continue;
			}
			if ("packagingFactor" == grid.columns[c].field) {
				continue;
			}
			if ("itemCategory" == grid.columns[c].field) {
				continue;
			}

			if ("itemCategory" == grid.columns[c].field) {
				continue;
			}
			
			if ("packagingUnit" == grid.columns[c].field) {
				continue;
			}

			if (data[j][grid.columns[c].field] === undefined) {
				content = document.createTextNode("");
			} else {
				if ("deliveryCount" == grid.columns[c].field) {
					content = document.createTextNode("");
				} else {
					var textStr = data[j][grid.columns[c].field];
					//商品名称
					if ("itemName" == grid.columns[c].field && textStr.length > 9) {
						content = document.createTextNode(textStr.substring(0,8));
					}else if ("itemSpecification" == grid.columns[c].field && textStr.length > 9) {
						content = document.createTextNode(textStr.substring(0,8));
					}else{
						content = document.createTextNode(data[j][grid.columns[c].field]);
					}
				}
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
	
	LODOP.PRINT_INITA(2,-1,800,600,"打印控件功能演示_Lodop功能_空白练习");
	LODOP.ADD_PRINT_TEXT(-1,191,155,32,g_companyName);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(26,274,85,20,"配送部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(26,359,190,20,isEmpty(provider) ? "_" : provider);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(26,16,85,20,"订单编号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(26,101,170,20, isEmpty(fId) ? "_" : fId);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.SET_PRINT_STYLEA(0,"Italic",1);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(47,15,85,20,"订货部门：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(47,100,170,20,isEmpty(requester) ? "_" : requester);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(-1,531,93,20,"第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
	LODOP.ADD_PRINT_TEXT(0,326,112,32,"配送单");
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(277,19,55,20,"装箱人:");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(277,65,120,20,"_");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(277,268,55,20,"送货人:");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(277,315,120,20,"_");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(277,508,55,20,"收货人:");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(277,562,120,20,"_");
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Vorient",1);
	LODOP.ADD_PRINT_TEXT(26,551,80,20,"配送日期：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(26,631,102,20,isEmpty(receiveTime) ? "_" : receiveTime);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(-1,476,61,20,"总页号：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(47,275,85,20,"备    注：");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.ADD_PRINT_TEXT(47,358,350,20,isEmpty(formNote) ? "_" : formNote);
	LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
	LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	
	createPrintTable("grid_print");
	var strBodyStyle = "<style> table {font-family: verdana,arial,"
		+ "sans-serif;font-size:12px;color:black;border-width: 1px;border-color: "
		+ "black;border-collapse: collapse;} td {border-width: 1px;	padding: 4px; 	"
		+ "border-style: solid; 	border-color: black;	background-color: #ffffff;} "
		+ "th {border-width: 1px;	padding: 3px;	border-style: solid;	"
		+ "border-color: black;}"
		+ " .print-rownumber,.print-itemId,"
		+ "{text-align: center;} .print-rownumber,.print-itemDimension{width:38px}"
		+ ".print-itemSpecification {width: 90px;} .print-itemId {width:62px;} .print-packagingUnit ,.print-shippingCount,.print-packagingCount{width:68px;text-align: right;} "
		+ ".print-itemName{text-align: left; width: 135px;}.print-differentCount,.print-requestCount,.print-deliveryCount,.print-receiveCount "
		+ "{text-align: right; width: 50px;}  </style>";
		var strFormHtml = strBodyStyle + "<body>" + document.getElementById("grid_print").innerHTML + "</body>";
	LODOP.ADD_PRINT_TABLE(67,10,750,190,strFormHtml);
	
	LODOP.SET_PRINT_STYLEA(0,"ItemName","mytable");
	LODOP.SET_PRINT_STYLEA(10,"LinkedItem","mytable");
	LODOP.SET_PRINT_STYLEA(11,"LinkedItem","mytable");
	LODOP.SET_PRINT_STYLEA(12,"LinkedItem","mytable");
	LODOP.SET_PRINT_STYLEA(13,"LinkedItem","mytable");
	LODOP.SET_PRINT_STYLEA(14,"LinkedItem","mytable");
	LODOP.SET_PRINT_STYLEA(15,"LinkedItem","mytable");
	
	LODOP.ADD_PRINT_TEXT(0,422,52,32,dojo.byId("addFormFlag").innerHTML);
	LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
};
