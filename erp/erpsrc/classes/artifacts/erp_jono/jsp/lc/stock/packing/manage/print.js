var LODOP; // 声明为全局变量

var printColumns ,printData;

function prn1_preview(columns,data) {
	
	printColumns = {};
	printData = {};
	
	printColumns = columns;
	printData = data;
	
	CreateOneFormPage();
	LODOP.PREVIEW();
};

function myDesign(columns,data) {
	printColumns = {};
	printData = {};
	
	printColumns = columns;
	printData = data;
	
	CreateOneFormPage();
	LODOP.PRINT_DESIGN();		
};

function createPrintTable(id) {
	require([ "dojo/_base/array" ], function(array) {

		//补全数据
		array.forEach(printData, function(outItem) {
			array.forEach(printColumns, function(inItem) {
				if(!outItem.hasOwnProperty(inItem.field)){
					outItem[inItem.field] = "";
				}
			});
		});
	
		dojo.empty(id);
		
		var table, tr, th, td, content,thead,tbody,tfoot;
		table = document.createElement("table");
		thead = document.createElement("thead");
		tr = document.createElement("tr");
		
		for (var index in printColumns) {
			content = document.createTextNode(printColumns[index].label);
			th = document.createElement("th");
			th.appendChild(content);
			tr.appendChild(th);
		}
		
		thead.appendChild(tr);
		table.appendChild(thead);
		
		tbody = document.createElement("tbody");
		
		var i,j, l;
		
		for (j = 0,l = printData.length; j < l; j++) {
			tr = document.createElement("tr");
			for(i = 0;i <printColumns.length;i++){
				var field = printColumns[i].field;
				
				if(printData[j].hasOwnProperty(field)){
					var value = printData[j][field];
					
					if(isEmpty(value) && isNaN(value)){
						value = "";
					}
					
					content = document.createTextNode(value);
					
					td = document.createElement("td");
					td.appendChild(content);
					tr.appendChild(td);
				}
			}
			tbody.appendChild(tr);
		}
		
		table.appendChild(tbody);
		dojo.byId(id).appendChild(table);
		
		var l_formTime = dojo.byId('l_formTime').innerHTML;
		var l_formId = dojo.byId('l_formId').innerHTML;
		var l_formMaker = dojo.byId('l_formMaker').innerHTML;
		var l_formNote = dojo.byId('l_formNote').innerHTML;
		

		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_空白练习");
		LODOP.ADD_PRINT_TEXT(23,166,155,32,g_companyName);
		LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(86,262,85,20,"制单日期：");
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(86,345,100,20,isEmpty(l_formTime)?"_":l_formTime );
		LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
		LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(60,45,85,20,"订单编号：");
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(60,130,150,20,isEmpty(l_formId)?"_":l_formId );
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_PRINT_STYLEA(0,"Italic",1);
		LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
		LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(86,44,85,20,"制单人：");
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(86,129,108,20, isEmpty(l_formMaker)?"_":l_formMaker);
		LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
		LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(86,458,77,20,"备注：");
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(86,535,166,20,isEmpty(l_formNote)?"_":l_formNote);
		LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
		LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(35,507,136,20,"第#页/共&页");
		LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
		LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
		LODOP.ADD_PRINT_TEXT(23,318,112,32,"捡货单");
		LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TEXT(35,417,91,20,"总页号：");
		LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.ADD_PRINT_TABLE(144,43,667,399,"(超文本15表格的HTML代码内容)");
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);

		
		var strBodyStyle="<style> table {  border-collapse: collapse; width: 100%;} table, td, th {border: 1px solid black;}</style>";
		var strFormHtml=strBodyStyle+"<body>"+document.getElementById("grid_print").innerHTML+"</body>";
		LODOP.ADD_PRINT_TABLE(144,43,667,399,strFormHtml);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
	
	});

}

function CreateOneFormPage() {
	LODOP = getLodop(document.getElementById('LODOP_OB'), document
			.getElementById('LODOP_EM'));
	createPrintTable("grid_print");
};
