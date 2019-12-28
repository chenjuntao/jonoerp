/**
 * @param _formId
 * @returns {hasLock, formStatus}
 */
function getCurrentStatus(_formId) {
	var result = {};
	var _url = appRoot + "/restaurant/goodsbill/query/getCurrentStatus.action?formId=" + _formId;
	$.ajax({
		type : "POST",
		async : false,
		url : _url,
		error : function(e) {
			alert(e);
		},
		success : function(data) {
			result = data;
		}
	});
	return result;
}
