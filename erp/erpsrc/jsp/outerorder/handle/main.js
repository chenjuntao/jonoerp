require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		var query = getQuery();
		orderGrid.init(query);
	});
});

function doQuery() {
	query();
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value,
		initStatus : "已发货,已收货,总部已对账",
		status : dojo.byId('status').value
	}
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在操作中！")
		return false;
	}
	
	if (status == '外部订货方对账已确认') {
		alert("外部订货方对账已确认！")
		return false;
	}
	
	if (status == '已付款') {
		alert("外部订货方已付款！")
		return false;
	}
	
	return true;
}

function query() {
	var _url = appRoot + "/outerorder/handle/query.action";
	_url = getUrl(_url);
	
	require([ 
	          "dojo/_base/lang",
	          "dojo/request/xhr", 
	          "dojo/store/Observable" ,
	          "dojo/store/Memory"
	        ], function(lang,xhr,Observable,Memory) {
		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(
				function(data) {
					var myStore =  Observable(new Memory({
						data : data,
						idProperty : 'form_id'
					}));
					
					orderGrid.set('store',myStore);

				}, function(err) {
					return null;
					// Handle the error condition
				});
	})
}
