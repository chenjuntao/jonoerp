function getHref(cellData,type) {
	return "<a href='#' onclick='doDetailScan(\"" + cellData + "\",\""+ type + "\")'><b>"
			+ cellData + "</b></a>";
}

function getHrefWithDel(cellData) {
	return "<a href='#' onclick='doDetailScanWithDel(\"" + cellData + "\")'><b>"
	+ cellData + "</b></a>";
	
}