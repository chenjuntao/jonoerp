require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branch_lc : dojo.byId('branch_lc').value,
		branch_out : dojo.byId('branch_out').value,
		status : '已审核'
	}
}

var gird = null;
var myStore = null;
var myXhr = null;

 function initGrid() {
		var _url = appRoot + "/outerscan/scan/doQuery.action";
		_url = getUrl(_url);
		
		require([ 
		          "dgrid/Grid", 
		          "custom/store/Server", 
		          "dojo/store/Observable",
		          "dojo/store/Cache", 
		          "dojo/store/Memory", 
		          "dojo/_base/declare",
		          "dgrid/extensions/Pagination", 
		          "dgrid/ColumnSet",
		          "dojo/request/xhr", 
		          "dojo/domReady!" 
		        ], function(Grid,Server, Observable, Cache, Memory, declare, Pagination,ColumnSet,xhr) {
			myXhr = xhr;
			 myStore = Observable(Cache(Server({
				target : _url,
				query : function(query, options) {
					return Server.prototype.query.call(this, getQuery(), options);
				}
			}), Memory()));

			var CustomGrid = declare([ Grid, Pagination ,ColumnSet]);
			grid = new CustomGrid({
				store : myStore,
				columnSets : getColumn(),
				cellNavigation : false,
				pageSizeOptions : [ 10 ],
				loadingMessage : '加载中...'
			}, "dataGrid");

			grid.startup();
		});
};

 function updateStatus (form_id){
	myXhr.post(appRoot + "/sp/poscan/updateStatus.action", {
		handleAs : "json",
		data : {formId:form_id}
	}).then(function(data) {
	}, function(err) {
		console.log(err);
	});
}

 function getColumn() {
		return [[[
			        {
						label : "查看状态",
						field : "status",
						formatter : function(currentValue){
							return currentValue == 'Y'?'已查看': "<font color='red'>未查看</font>";
						}
					},{
						label : "单据编号",
						field : "form_id",
						renderCell : function(item, data) {
							return hrefFmt(!item.form_id ?'':item.form_id,
									doScan, item);
						}
					}]], [[
					 {
						label : "订货部门",
						field : "buyer_name"
					}, {
						label : "供应商",
						field : "provider"
					}, {
						label : "到货时间",
						field : "receive_time"
					}, {
						label : "审核日期",
						field : "audit_time"
					},{
						label : "主要采购品",
						field : "max_pay_item"
					}, {
						label : "总金额",
						field : "all_pay_amt",
						formatter:function(value,row){
							return !row.form_id ?"<b>"+ value+"</b>":value;
						}
					}, {
						label : "备注说明",
						field : "form_note"
					}]]];
		};

 function doScan(row) {
	 if(row.status == 'N'){
			updateStatus(row.form_id);
	 }
	 
	var _url = appRoot + "/outerorder/manage/scanView.action?formId="
	+ row.form_id;
	_url = getUrl(_url);
	
	addTab(row.form_id + "外部订货单查看", _url);
};
