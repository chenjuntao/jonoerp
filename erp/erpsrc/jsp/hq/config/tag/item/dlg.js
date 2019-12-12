var itemTagDlg = null;
function setItemTag(cateIds,bindTag,itemname) {
	var _title = "批量绑定标签";
	
	var _url = appRoot + "/hq/config/tag/settag/setTagView.action?cateIds="+cateIds+"&bindTag="+bindTag;
	
	if(bindTag=='unbind'){
		 _title = "批量解除标签";
	}else if(bindTag=='singlebind'){
		_title = '['+cateIds+']'+itemname+"   设置标签";
		_url = appRoot + "/hq/config/tag/settag/isingle/setTagView.action?cateIds="+cateIds+"&bindTag="+bindTag;
	}
	
	
	var frameId = 'ifr_tag';
	var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "450px",
			height : "320px"
		}
	
	createDialog(option, function(iDlg) {
		itemTagDlg = iDlg;
	});
}

function closeItemTagDlg(data) {
	itemTagDlg.hide();
	queryStock();
}

