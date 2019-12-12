var lcaCols = [ [ [ {
	label : "序号",
	field : "rownumber",
	sortable : false,
	formatter : function(field) {
		if (field == "合计") {
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "要货/配送单",
	field : "form_id",
	className : 'text-center',
	sortable : false,
	renderCell : function(object, data) {
		return hrefFmt(object.form_id, doScan, object);
	}
} ] ], [[ {
	label : "捡货单号",
	field : "form_id2",
	sortable : false,
	className:'text-center'
}, {
	label : "商品编码",
	field : "item_id",
	className:'text-center',
	sortable : false
}, {
	label : "已分配量",
	field : "mrp_count",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
} , {
	label : "",
	field : "none",
	sortable : false
}] ]];

//-------------------
var lcoRawCols = [ [ [ {
	label : "序号",
	field : "rownumber",
	sortable : false,
	formatter : function(field) {
		if (field == "合计") {
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "采购单",
	field : "form_id",
	className : 'text-center',
	sortable : false,
	renderCell : function(object, data) {
		return hrefFmt(object.form_id, doScan, object);
	}
} ] ], [[ {
	label : "商品编码",
	field : "item_id",
	className:'text-center',
	sortable : false
},{
	label : "采购量",
	field : "item_count",
	className:'text-right',
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "在途量",
	field : "mrp_count",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "已入库数",
	field : "received",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
} , {
	label : "已入库并审核单据",
	field : "infos",
	sortable : false
}] ]];

//-------------------
var lcoSemiCols = [ [ [ {
	label : "序号",
	field : "rownumber",
	sortable : false,
	formatter : function(field) {
		if (field == "合计") {
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "采购单",
	field : "form_id",
	className : 'text-center',
	sortable : false,
	renderCell : function(object, data) {
		return hrefFmt(object.form_id, doScan, object);
	}
} ] ], [[  {
	label : "出库单",
	field : "received",
	className : 'text-center',
	sortable : false,
	renderCell : function(object, data) {
		return hrefFmt(object.received, doScan, object);
	}
},{
	label : "商品编码",
	field : "item_id",
	className:'text-center',
	sortable : false
}, {
	label : "在途量",
	field : "mrp_count",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
},{
	label : "",
	field : "none",
	className:'text-center',
	sortable : false
}] ]];

//--------------------
var cfaCols = [ [ [ {
	label : "序号",
	field : "rownumber",
	sortable : false,
	formatter : function(field) {
		if (field == "合计") {
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "工单",
	field : "form_id",
	className : 'text-center',
	sortable : false,
	renderCell : function(object, data) {
		return hrefFmt(object.form_id, doScan, object);
	}
} ] ], [[ {
	label : "商品编码",
	field : "item_id",
	className:'text-center',
	sortable : false
},{
	label : "计划领料数",
	field : "item_count",
	className:'text-right',
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
} , {
	label : "已分配量",
	field : "mrp_count",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "已领料数",
	field : "received",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "已领料并审核单据",
	field : "infos",
	sortable : false,
	className:'text-right'
}]]];

//----------------
var cfoCols = [ [ [ {
	label : "序号",
	field : "rownumber",
	sortable : false,
	formatter : function(field) {
		if (field == "合计") {
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "采购单",
	field : "form_id",
	className : 'text-center',
	sortable : false,
	renderCell : function(object, data) {
		return hrefFmt(object.form_id, doScan, object);
	}
} ] ], [[ {
	label : "商品编码",
	field : "item_id",
	className:'text-center',
	sortable : false
},{
	label : "采购量",
	field : "item_count",
	className:'text-right',
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
} , {
	label : "在途量",
	field : "mrp_count",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "已入库数",
	field : "received",
	sortable : false,
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "已入库并审核单据",
	field : "infos",
	sortable : false,
	className:'text-right'
}]]];