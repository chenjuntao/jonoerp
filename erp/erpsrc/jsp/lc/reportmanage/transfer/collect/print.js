var LODOP; // 声明为全局变量

var printObject;

function prn1_preview(object) {
	printObject = object;
	CreateOneFormPage();
};

function createPrintTable(id) {
	dojo.empty(id);
	
	var table, tr, th, td, content,thead,tbody,tfoot;
	table = document.createElement("table");
	thead = document.createElement("thead");
	tr = document.createElement("tr");
	
	for (var name in printObject.names) {
		content = document.createTextNode(printObject.names[name]);
		th = document.createElement("th");
		th.appendChild(content);
		tr.appendChild(th);
	}
	thead.appendChild(tr);
	table.appendChild(thead);
	
	
	tbody = document.createElement("tbody");
	
	require([ "dojo/request/xhr"],function(xhr){
		xhr.post(appRoot + "/reportmanage/common/print/print.action", {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			
			var i,j, l;
			
			for (j = 0,l = data.length; j < l; j++) {
				tr = document.createElement("tr");
				for(i = 0;i < printObject.columns.length;i++){
					if(data[j].hasOwnProperty(printObject.columns[i])){
						if(data[j][ printObject.columns[i]] === undefined || data[j][ printObject.columns[i]] === null){
							content = document.createTextNode("");
						}else{
							content = document.createTextNode(data[j][ printObject.columns[i]]);
						}
						
						td = document.createElement("td");
						td.appendChild(content);
						tr.appendChild(td);
					}
				}
				tbody.appendChild(tr);
			}
			
			table.appendChild(tbody);
			dojo.byId(id).appendChild(table);
			
			
			LODOP.SET_PRINT_STYLEA(0,"TextFrame",2);
			 
			LODOP.ADD_PRINT_TEXT(20,300,134,20,"物流中心调拨汇总报表");
			LODOP.ADD_PRINT_TEXT(20,545,134,20,"总页号：第#页/共&页");
			LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
			
			var strBodyStyle="<style> table {  border-collapse: collapse; width: 100%;} table, td, th {border: 1px solid black;}</style>";
			var strFormHtml=strBodyStyle+"<body>"+document.getElementById("grid_print").innerHTML+"</body>";
			LODOP.ADD_PRINT_TABLE(58,26,667,399,strFormHtml);
			LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
			
			LODOP.PREVIEW();
		}, function(err) {
			console.log(err);
		});
	});
}

function CreateOneFormPage() {
	LODOP = getLodop(document.getElementById('LODOP_OB'), document
			.getElementById('LODOP_EM'));
	createPrintTable("grid_print");
};
