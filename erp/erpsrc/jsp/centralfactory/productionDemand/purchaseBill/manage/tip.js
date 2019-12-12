function refFmt(refIds, _itemId) {
	if (refIds == undefined) {
		return null;
	}
	var idArr = refIds.split(",");
	var spanEle = document.createElement("span");
	var aEle = document.createElement("a");
	aEle.href = "#";
	var firstId = idArr[0];
	aEle.onclick = function() {
		scanRef(firstId, _itemId);
		return false;
	};
	aEle.innerHTML = firstId;
	spanEle.appendChild(aEle);
	if (idArr.length > 1) {
		var moreEle = document.createElement("span");
		moreEle.style = "padding-left: 3px;";
		moreEle.innerHTML = "更多";
		spanEle.appendChild(moreEle);
	}

	require([ "dojo/mouse", "dojo/on", "dojo/query", "dojo/dom", "dojo/dom-construct", "dojo/_base/window",
			"dojo/dom-attr", "dojo/dom-style" ], function(mouse, on, query, dom, domc, win, domAttr, domStyle) {
		var $liveTip = dom.byId('livetip');
		if ($liveTip == null) {
			$liveTip = domc.create("div", {
				id : 'livetip'
			});
			domc.place($liveTip, win.body());
		}
		on(spanEle, mouse.enter, function(evt) {
			hrefTip.cancelClose();

			domAttr.set($liveTip, 'innerHTML', '');// 清空内容
			for (var i = 0; i < idArr.length; i++) {
				var refId = idArr[i];
				var refLink = document.createElement("a");
				refLink.href = "#";
				refLink.onclick = function() {
					scanRef(refId, _itemId);
					return false;
				};
				refLink.style = "padding: 3px;";
				refLink.innerHTML = refId;
				$liveTip.appendChild(refLink);
			}
			domStyle.set($liveTip, {
				'display' : 'block',
				'top' : evt.pageY + 'px',
				'left' : evt.pageX + 'px'
			});
		});
		on($liveTip, mouse.enter, function(evt) {
			hrefTip.cancelClose();
		});
		on(spanEle, mouse.leave, function(evt) {
			hrefTip.delayClose();
		});
		on($liveTip, mouse.leave, function(evt) {
			hrefTip.delayClose();
		});
	});
	return spanEle;
}

var hrefTip = new HrefTip();

function HrefTip() {
	var timeout = 500;
	var closetimer = 0;
	var instance = this;

	// go close timer
	this.delayClose = function() {
		closetimer = window.setTimeout(instance.closeTip, timeout);
	}
	// close showed layer
	this.closeTip = function() {
		var $liveTip = document.getElementById('livetip');
		$liveTip.style.display = 'none';
	}

	// cancel close timer
	this.cancelClose = function() {
		if (closetimer) {
			window.clearTimeout(closetimer);
			closetimer = null;
		}
	}

	// close layer when click-out
	// document.onclick = mclose;
}
