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

function print(){
	prn1_preview(getExportArgs());
}

function show(){
	dijit.byId('customDialog').show();
}

function hide(){
	dijit.byId('customDialog').hide();
}

function getAppendValue(){
	if(!isEmpty(dojo.byId("startDate").value)){
		startDate = dojo.byId("startDate").value;
	}else{
		startDate = '';
	}
	
	if(!isEmpty(dojo.byId("endDate").value)){
		endDate = dojo.byId("endDate").value;
	}else{
		endDate = '';
	}
	
	var branchCondition = dijit.byId('branchCondition').get('value');
	var storageCondition = dijit.byId('storageCondition').get('value');
	
	var outBranchCondition = dijit.byId('outBranchCondition').get('value');
	var outStorageCondition = dijit.byId('outStorageCondition').get('value');
	
	
	var appendValue = "  AND (h.IN_BRANCH_ID = '" + loginBranchId + "' or h.OUT_BRANCH_ID = '" + loginBranchId + "')";
	
	if(!isEmpty(startDate)){
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') >= '" + startDate + "' " ;
	}
	
	if(!isEmpty(endDate)){
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') <= '" + endDate + "' " 
	}
	
	if(!isEmpty(branchCondition)){
		appendValue += " AND h.IN_BRANCH_ID = '" + branchCondition+ "' "
	}
	
	if(!isEmpty(storageCondition)){
		appendValue += " AND h.IN_STORAGE_ID = '" + storageCondition+ "' "
	}
	
	if(!isEmpty(outBranchCondition)){
		appendValue += " AND h.OUT_BRANCH_ID = '" + outBranchCondition+ "' "
	}
	
	if(!isEmpty(outStorageCondition)){
		appendValue += " AND h.OUT_STORAGE_ID = '" + outStorageCondition+ "' "
	}
	
	return appendValue;
}

function initCondition(){
	dojo.byId('inputJsonString').value = "";
	dijit.byId('branchCondition').set('value',"");
	dijit.byId('storageCondition').set('value',"");
	
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
	          "dojo/domReady!" ], function(dom, xhr, array,
			domForm,parser,Select,ObjectStore,Memory,FilteringSelect) {
		  
		parser.parse();
		
		var branchType = dom.byId('branchType').value;
		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		xhr.post(appRoot + "/common/listBs.action", {
			handleAs : "json",
			data : {
				branchType1 : branchType,
				branchType2 : branchType
			}
		}).then(function(data) {
			 new FilteringSelect({
			        id: "storageCondition",
			        store: new Memory({idProperty: "storageId", data: data.storage }),
			        value:"storageId",
			        required : false,
					autoComplete : false,
			        query: {branchId: /.*/},
			        style: "width: 200px;",
			        labelAttr : 'storageName',
			        queryExpr: "*${0}*",
			        displayValueAttr:"storageName",
			        searchAttr: "storageName"
			    }, "storageCondition").startup();

			    new FilteringSelect({
			        id: "branchCondition",
			        value:"code",
			        labelAttr : 'name',
			        displayValueAttr:"name",
			        searchAttr: "name",
			        required : false,
					autoComplete : false,
					queryExpr: "*${0}*",
			        store: new Memory({ idProperty: "code", data: data.branch }),
			        style: "width: 150px;",
			        onChange: function(current){
			        	if(isEmpty(current)){
			        		dijit.byId('storageCondition').query.branchId =  /.*/;
			        	}else{
			        		dijit.byId('storageCondition').query.branchId = this.item.code || /.*/;
			        	}
			        	dijit.byId('storageCondition').set('value',"");
			        }
			    }, "branchCondition").startup();
			    
			    new FilteringSelect({
			    	id: "outStorageCondition",
			    	store: new Memory({idProperty: "storageId", data: data.storage }),
			    	value:"storageId",
			    	required : false,
			    	autoComplete : false,
			    	query: {branchId: /.*/},
			    	style: "width: 200px;",
			    	labelAttr : 'storageName',
			    	queryExpr: "*${0}*",
			    	displayValueAttr:"storageName",
			    	searchAttr: "storageName"
			    }, "outStorageCondition").startup();
			    
			    new FilteringSelect({
			    	id: "outBranchCondition",
			    	value:"code",
			    	labelAttr : 'name',
			    	displayValueAttr:"name",
			    	searchAttr: "name",
			    	required : false,
			    	autoComplete : false,
			    	queryExpr: "*${0}*",
			    	store: new Memory({ idProperty: "code", data: data.branch }),
			    	style: "width: 150px;",
			    	onChange: function(current){
			    		if(isEmpty(current)){
			    			dijit.byId('outStorageCondition').query.branchId =  /.*/;
			    		}else{
			    			dijit.byId('outStorageCondition').query.branchId = this.item.code || /.*/;
			    		}
			    		dijit.byId('outStorageCondition').set('value',"");
			    	}
			    }, "outBranchCondition").startup();
			    
			    initCondition();
			    
				initGrid();
				createColumnSelection();
		}, function(err) {
					console.log(err);
				});

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
			NestedSort, VirtualVScroller, ColumnResizer,
			ColumnLock) {
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
			columnLockCount : 3,
			paginationBarShowRange : true,
			paginationInitialPageSize : 25,
			pageSize : 25,
			sortInitialOrder: [{colId: "in_branch", descending: false},{colId: "item_id", descending: false}],
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
								id : "in_branch",
								field : "in_branch",
								name : "调入部门",
								autoComplete : false,
								sortable : false,
								filterable : false
							},
							{
								id : "in_storage",
								field : "in_storage",
								name : "调入仓库",
								autoComplete : false,
								sortable : false,
								filterable : false
							},
							{
								id : "out_branch",
								field : "out_branch",
								name : "调出部门",
								autoComplete : false,
								sortable : false,
								filterable : false
							},
							{
								id : "out_storage",
								field : "out_storage",
								name : "调出仓库",
								autoComplete : false,
								sortable : false,
								filterable : false
							},
							{
								id : "item_id",
								field : "item_id",
								name : "商品编码",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_name",
								field : "item_name",
								name : "商品名称",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_category",
								field : "item_category",
								name : "类别编码",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "category_name",
								field : "category_name",
								name : "类别名称",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_dimension",
								field : "item_dimension",
								name : "单位",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_specification",
								field : "item_specification",
								name : "规格",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "apply_count",
								field : "apply_count",
								name : "申请数量",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "actual_count",
								field : "actual_count",
								name : "实际数量",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "different_count",
								field : "different_count",
								name : "调拨差异",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "pay_amt",
								field : "pay_amt",
								name : "标准金额",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							}],
			modules : [ Pagination, PaginationBar, Filter, FilterBar,
			            NestedSort, VirtualVScroller,
					ColumnResizer, ColumnLock ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
