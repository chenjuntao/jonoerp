init();

var grid;

function customExport(){
	updateCopy(getExportArgs());
	exportXls(); 
}

function doQuery(){
	dojo.byId('append').value = getAppendValue();
	
	grid.filter.refresh();
}


function show(){
	dijit.byId('customDialog').show();
}

function hide(){
	dijit.byId('customDialog').hide();
}

function getAppendValue(){
	var userName = dojo.byId('userName').value.trim();;
	
	var appendValue = " AND ( u.USER_ID like '%" + userName + "%'  OR   u.USER_NAME like '%" + userName + "%') ";
	
	return appendValue;
}

function initCondition(){
	dojo.byId('inputJsonString').value = "";
	
	dojo.byId('append').value = getAppendValue();
}

function init() {
	require([ 
	          "dojo/dom", 
	          "dojo/request/xhr", 
	          "dojo/_base/array",
	          "dojo/dom-form",
	          "dojo/parser",
	          "dijit/form/Select",
	          "dojo/data/ObjectStore",
	          "dojo/store/Memory",
	          "dijit/form/FilteringSelect",
	          "dijit/form/Button",
	          "dijit/Dialog",
	          "dojo/domReady!" 
	       ], function(dom, xhr, array,	domForm,parser,Select,ObjectStore,Memory,FilteringSelect) {
		  
		parser.parse();
		
		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}
		
		initCondition();
		    
		initGrid();
		createColumnSelection();
		
		
	});
}

var columns,names;

var getExportArgs = function(){
		columns = [],names = [];
	for(var i = 0; i < c.length; i++){
		if(c[i].checked){
			columns.push(c[i].id);
			names.push(c[i].name);
		}
	}
	
	return {
		columns: columns,
		names : names
	};
};

var c = [], createColumnSelection = function (){
	if(grid){
		var columns = grid.columns(), i = 0, label, tr, td;
		for(; i < columns.length; i++){
			if(i%2 == 0){
				tr = dojo.create("tr");
				dojo.byId('columnSelection').appendChild(tr);
			}
			td = dojo.create("td");
			c[i] = new dijit.form.CheckBox({
				id: columns[i].id,
				checked: true,
				name: columns[i].name()
			});
			c[i].startup();
			td.appendChild(c[i].domNode);
			label = dojo.create("label", {innerHTML: columns[i].name() + "  "});
			td.appendChild(label);
			tr.appendChild(td);
		}
	}
};

function initGrid() {
	var _url = appRoot + "/reportmanage/common/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "gridx/Grid", "gridx/core/model/cache/Async",
			"gridx/modules/Pagination",
			"gridx/modules/pagination/PaginationBar", "gridx/modules/Filter",
			"gridx/modules/filter/FilterBar", "custom/store/Server",
			"gridx/modules/NestedSort",
			"gridx/modules/VirtualVScroller", "gridx/modules/ColumnResizer",
			"gridx/modules/ColumnLock", "dojo/domReady!" ], function(dom, Grid,
			Cache, Pagination, PaginationBar, Filter, FilterBar, Server,
			NestedSort, VirtualVScroller, ColumnResizer) {
		var store = Server({
			idProperty : 'rownumber',
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		});

		grid = new Grid({
			id : 'dataGrid',
			store : store,
			cacheClass : Cache,
			paginationBarShowRange : true,
			paginationInitialPageSize : 25,
			pageSize : 25,
			sortInitialOrder: [{colId: "branch_id", descending: false},{colId: "user_id", descending: false}],
			filterServerMode : true,
			filterSetupQuery : function(expr) {
				dom.byId("inputJsonString").value = JSON.stringify(expr);
				return {
					query : {}
				};
			},
			structure : [
							{
								id : "rownumber",
								field : "rownumber",
								name : "序号",
								filterable : false,
								sortable : false,
								decorator : function(cellData, rowId, rowIndex) {
									if('total' == cellData){
										return '合计';
									}
									return cellData;
								}
							},
							{
								id : "branch_id",
								field : "branch_id",
								name : "门店编码",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							},
							{
								id : "branch_name",
								field : "branch_name",
								name : "门店名称",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							} ,{
								id : "user_id",
								field : "user_id",
								name : "用户编码",
								autoComplete : false,
								sortable : false,
								filterable : false
							},
							{
								id : "user_name",
								field : "user_name",
								name : "用户名称",
								sortable : false,
								autoComplete : false,
								filterable : false
							},
							{
								id : "role_name",
								field : "role_name",
								name : "角色名称",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							},
							{
								id : "email",
								field : "email",
								name : "电子邮件",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							},
							{
								id : "telephone",
								field : "telephone",
								name : "手机号",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							},
							{
								id : "gender",
								field : "gender",
								name : "性别",
								autoComplete : false,
								sortable : false,
								dataType : 'enum',
								enumOptions : [{label:'男',value:'男'},{label:'女',value:'女'}],
							}],
			modules : [ Pagination, PaginationBar, Filter, FilterBar,
			            NestedSort, VirtualVScroller,
					ColumnResizer ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
