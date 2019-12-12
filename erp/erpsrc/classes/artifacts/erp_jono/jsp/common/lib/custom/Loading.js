define("custom/Loading", [ "dojo/_base/declare", "dojo/_base/window", "dojo/dom-construct", "dojo/dom-attr",
		"dojo/dom-style", "dojo/dom-geometry" ], function(declare, win, domc, domAttr, domStyle, domGeom) {
	return declare(null, {
		loadingMessage : "<span class='dijitContentPaneLoading'>"
				+ "<span class='dijitInline dijitIconLoading'></span>" + '正在处理，请稍待。。。' + "</span>",
		show : function(_eleId) {
			console.log(this.loadingMessage)
			if (_eleId == undefined) {
				_eleId = win.body();
			}
			var $div = domc.create("div", {});
			var $msgDiv = domc.create("div", {
				innerHTML : this.loadingMessage
			});
			domc.place($msgDiv, $div);
			domStyle.set($div, {
				"opacity" : 0.5,
				"filter" : "alpha(opacity=30)",
				"position" : "absolute",
				"z-index" : "300",
				"left" : "0px",
				"top" : "0px",
				"text-align" : "center",
				"background" : "#ccc",
				"width" : '100%',
				"height" : "100%"
			});
			domc.place($div, _eleId);
			// 放到最后设置高度才有作用
			domStyle.set($msgDiv, {
				"z-index" : "301",
				"margin-top" : (domStyle.get(_eleId, 'height') - domGeom.position($msgDiv).h) / 2 + 'px'
			});
		}
	});
});
