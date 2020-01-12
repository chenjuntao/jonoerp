var workOrderGrid = new WorkOrderGrid('workOrderGrid');

function WorkOrderGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.selectArr = [];
	
	this.query = function(_query) {
		this.grid.set('query', getWorkOrderQuery());
	};
	
	this.selectAll = function(){
		instance.grid.selectAll();
	};

	this.init = function(_query) {
		var _url = appRoot
				+ "/centralfactory/productionDemand/workOrder/create/doOrderWorkQuery.action";
		_url = getUrl(_url);
		
		require([ 
		          "dojo/_base/array",
		          "dgrid/selector",
		          "dojo/_base/declare",
		          "dgrid/OnDemandGrid",
				  "custom/store/Server", 
				  "dojo/store/Observable",
				  "dojo/store/Cache",
				  "dgrid/Selection", 
				  "dojo/store/Memory" ,
				  "dgrid/extensions/ColumnResizer"
				 ],
				function(arrayUtil,selector, declare, OnDemandGrid, Server, Observable,
						Cache, Selection, Memory,ColumnResizer) {
					var myStore = Observable(Cache(Server({
						sync : true,
						target : _url,
						query : function(query, options) {
							return Server.prototype.query.call(this, query,
									options);
						}
					}), Memory()));

					var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
					instance.grid = new CustomGrid({
						store : myStore,
						columns : instance.getColumn(selector),
						cellNavigation : false,
						allowSelectAll : true,
						loadingMessage : '加载中...',
						noDataMessage : "无数据！"
					}, gridId);
					
					instance.grid.on("dgrid-deselect", function(event){
				        var rows = event.rows;
				        arrayUtil.forEach(rows,function(item){
				        	instance.selectArr.pop(item.data); 
				        });
			        	
					});
					
					instance.grid.on("dgrid-select", function(event){
				        var rows = event.rows;
				        arrayUtil.forEach(rows,function(item){
				        	instance.selectArr.push(item.data); 
				        });
			        	
					});
					
					instance.grid.startup();
				});
	};

	this.getColumn = function(selector) {
		return [  selector({
			label : "",
			field : 'rownumber',
			sortable:false
		}),{
			label : "序号",
			field : "rownumber",
			sortable:false
		}, {
			label : "单据编号",
			field : "formId",
			sortable:false
		}, {
			label : "商品编码",
			field : "itemId",
			sortable:false
		}, {
			label : "商品名称",
			field : "itemName",
			sortable:false
		}, {
			label : "单位",
			field : "itemDimension",
			sortable:false
		}, {
			label : "规格",
			field : "itemSpecification",
			sortable:false
		}, {
			label : "生产数量",
			field : "itemCount",
			sortable:false
		}, {
			label : "生产车间",
			field : "workshop",
			sortable:false
		}, {
			label : "生产周期",
			field : "productionCycle",
			sortable:false
		}, {
			label : "备注说明",
			field : "formNote",
			sortable:false
		},{
			label : '生产工单生成',
			field : 'operate',
			renderCell : function(object, data) {
				return hrefFmt("生成", doCreate, object);
			},
			sortable:false
		},{
			label:'',
			field:'none',
			sortable:false
		} ];
	};
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("生产工单正在生成中！")
		return false;
	}
	
	if(status == "未审核"){
		alert("生产工单已生成！")
		return false;
	}
	
	return true;
}

function doCreate(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	
	var _url = appRoot
			+ "/centralfactory/productionDemand/workOrder/create/doCreate.action?formId="
			+ row.formId
			+"&formNote=" + row.formNote
			+"&arrangementFormId=" + dojo.byId('arrangementFormId').value
			+"&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab(row.formId+"生产工单生成", _url);
}
