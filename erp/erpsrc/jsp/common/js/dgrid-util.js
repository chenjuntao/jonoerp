function hrefFmt(_text, _handler, _data) {
	var aEle = document.createElement("a");
	aEle.href = "#";
	aEle.onclick = function() {
		_handler(_data);
		return false;
	};
	aEle.innerHTML = _text;
	return aEle;
}

function hrefFmtId(_text, _handler, _data, id) {
	var aEle = hrefFmt(_text, _handler, _data);
	aEle.id = id;
	return aEle;
}

function spanFmt(_text, _id) {
	var aEle = document.createElement("span");
	aEle.id = _id;
	aEle.innerHTML = _text;
	return aEle;
}

function imageFmt(_text, _objId) {
	var aEle = document.createElement("a");
	var _url = appRoot + "/common/loadImage.action?objectId=" + _objId;
	_url += "&timestamp=" + new Date().getTime(); // 防止缓存
	aEle.href = _url;
	aEle.target = "_blank";
	aEle.innerHTML = _text;
	require([ "dojo/mouse", "dojo/on", "dojo/query", "dojo/dom", "dojo/dom-construct", "dojo/_base/window",
			"dojo/dom-attr", "dojo/dom-style" ], function(mouse, on, query, dom, domc, win, domAttr, domStyle) {
		on(aEle, mouse.enter, function(evt) {
			var $liveTip = dom.byId('livetip');
			if ($liveTip == null) {
				$liveTip = domc.create("div", {
					id : 'livetip'
				});
				domc.place($liveTip, win.body());
			}

			var html = "<img src='" + _url + "' style='width: 200px;' class='mouse-img' target='_blank' />";
			domAttr.set($liveTip, 'innerHTML', html);
			domStyle.set($liveTip, {
				'display' : 'block',
				'top' : evt.pageY + 'px',
				'left' : evt.pageX + 'px'
			});
		});
		on(aEle, mouse.leave, function(evt) {
			var $liveTip = dom.byId('livetip');
			domStyle.set($liveTip, 'display', 'none');
		});
	});
	return aEle;
}

function commonFmt(value, data, key_field, value_field) {
	var name = "";
	require([ "dojo/_base/array" ], function(array) {
		array.some(data, function(entry, i) {
			if (value == entry[key_field]) {
				name = entry[value_field];
				return true;
			}
		});
	});
	return name;
}

/**
 * 表格编辑验证某一列 _grid 表格对象引用， _field 列名, _msg 即提示信息
 */
function validateColumn(_grid, _field, _msg) {
	var store = _grid.store;
	var cols = _grid.columns;
	var _colId = '';
	for (key in cols) {
		var col = cols[key];
		if (col.field == _field) {
			_colId = key;
			break;
		}
	}
	var rows = dataStore.query();
	for (var i = 0; i < rows.length; i++) {
		var data = rows[i];
		var rowId = _grid.store.getIdentity(data);
		var cell = _grid.cell(rowId, _colId);
		if (isEmpty(cell.element)) {
			continue;
		}
		var valid = cell.element.widget.isValid();
		if (!valid) {
			_msg = _msg == undefined || _msg == null ? '输入有误！' : _msg;
			alert(_msg);
			return false;
		}
	}
	return true;
}