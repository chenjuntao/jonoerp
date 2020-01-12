var grid;

require([ 
          "dgrid/OnDemandGrid",
          "dojo/store/Observable",
          "dojo/store/Cache", 
          "dojo/store/Memory",
          "dojo/request/xhr",
          "custom/store/Server", 
          "dojo/_base/declare",
          "dgrid/Keyboard", 
          "dgrid/extensions/Pagination",
          "dojo/parser",
          "dojo/domReady!"
      ],function(OnDemandGrid, Observable, Cache, Memory, xhr, Server, declare, Keyboard,Pagination,parser) {
			parser.parse();
			var _url = appRoot + "/setting/rolesearch/searchRole.action";
			_url = getUrl(_url);
			
			xhr(_url, {
				handleAs : 'json'
			}).then(function(data) {
				dataStore = new Memory({
					data : data,
					idProperty : 'rownumber'
				});

				var CustomGrid = declare([ OnDemandGrid, Keyboard,Pagination ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : getColumn(),
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
			}, function(err) {
				alert("load error");
			})
		});

var cols = [ {
	label : "",
	field : "none",
	sortable : false
} ];

function getColumn() {
	return [{
		label : "序号",
		field : "rownumber",
		sortable:false,
	}, 
	{
		label : "角色编码",
		field : "roleId",
		className:"text-center",
		sortable:false,
	},{
		label : "角色名称",
		field : "roleName",
		sortable:false,
		className:"text-center"
	}, {
		label : "用户名",
		field : "userName",
		sortable:false,
		className:"text-center"
	}, {
		label : "用户Id",
		field : "userId",
		sortable:false,
		className:"text-center"
	}, {
		label : "根角色",
		field : "rootName",
		className:"text-center",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}];}



function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	var _url = appRoot + "/setting/rolesearch/searchRole.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
			}
		}).then(function(data) {
			fillData(cols, data);
		}, function(err) {
			errorHandler(err);
		});
	});

}

