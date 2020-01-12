require([ "dojo/window","dojo/dom","dojo/domReady!" ], function(win, dom) {
	var vs = win.getBox();
	var gridHeight = vs.h - 200;
	var grid = dom.byId("detailGrid");
	grid.style.height = gridHeight + "px";
});