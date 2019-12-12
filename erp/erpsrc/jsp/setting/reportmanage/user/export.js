var sheetName = '用户信息';


function updateCopy(object){
	dataCopy.columns = [];
	
	var columns = object.columns;
	
	for(var i=0,lengthI = data.columns.length; i< lengthI ; i++){
		var col1 = data.columns[i].name;
		
		for(var j=0,lengthJ = columns.length; j<lengthJ; j++){
			var col2 = columns[j];
			
		
			if(col1 == col2){
				dataCopy.columns.push(data.columns[i]);
				break;
			}
		}
	}
}

var dataCopy = {
	sheetName : sheetName,
	title : {
		text : sheetName
	},
	columns : []
};

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 50
		},{
			display : '门店编码',
			name : 'branch_id',
			align : 'left',
			width : 80
		},{
			display : '门店名称',
			name : 'branch_name',
			align : 'left',
			width :150
		},{
			display : '用户编码',
			name : 'user_id',
			align : 'left',
			width : 80
		},{
			display : '用户名称',
			name : 'user_name',
			align : 'left',
			width : 150
		},{
			display : '角色名称',
			name : 'role_name',
			align : 'left',
			width : 150
		},{
			display : '电子邮件',
			name : 'email',
			align : 'left',
			width : 120
		},{
			display : '手机号',
			name : 'telephone',
			align : 'left',
			width : 100
		},{
			display : '性别',
			name : 'gender',
			align : 'left',
			width : 60
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/common/export/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(dataCopy);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

