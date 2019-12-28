//创建可折叠的二级菜单项和三级菜单项
//create expendable sendory menuitem and third menuitem.
//first created by cjt, 2014.6.10.
//last modified by cjt, 2o14,7.3.

var imgMin = new Image();
imgMin.src = appRoot + "/jsp/common/img/minimize.gif";

var imgMax = new Image();
imgMax.src = appRoot + "/jsp/common/img/maximize.gif";

var imgItem = new Image();
imgItem.src = appRoot + "/jsp/common/img/arrows.gif";

function Widgets()
{
	this.divCount = 0;
	
	this.OpenText = "全部折叠";
	this.OpenAlt = "全部折叠";
	this.ClosedText = "全部展开";
	this.ClosedAlt = "全部展开";

	this.Display = "";
	this.Img = imgMin;
	this.Text = this.OpenText;
	this.Alt = this.OpenAlt;
	
	this.ToggleAll = ToggleAll;
	this.ToggleWidget = ToggleWidget;
}

var widgets = new Widgets();

function Widget(div)
{
	return div;
}

function ToggleNav()
{
	if(divCollapsedNav.style.display == "none")
	{
		divCollapsedNav.style.display = "";
		divOpenNav.style.display = "none";
		parent.frmstOuter.cols = "24,*"
		parent.frmstOuter.scrolling = "no";
		//parent.document.all ('frmToolbar').noResize = true;
	}
	else
	{
		divOpenNav.style.display = "";
		divCollapsedNav.style.display = "none";
		parent.frmstOuter.cols = "216,*"
		parent.frmstOuter.scrolling = "auto";
		//parent.document.all ('frmToolbar').noResize = false;
	}
}

function ToggleAll()
{
	var OpenCloseFunc = Widget;

	if(this.Display == "")
	{
		this.Display = "none";
		this.Img = imgMax;
		this.Text = this.ClosedText;
		this.Alt = this.ClosedAlt;
		expparent00.title = this.ClosedAlt;
		OpenCloseFunc = CloseWidget;
	}
	else
	{
		this.Display = "";
		this.Img = imgMin;
		this.Text = this.OpenText;
		this.Alt = this.OpenAlt;
		expparent00.title = this.OpenAlt;
		OpenCloseFunc = OpenWidget;
	}
	document.getElementById("exp00").src = this.Img.src;	
	document.getElementById("textExp").innerHTML = this.Text;
	document.getElementById("exp00").alt = this.Alt;
	
	//循环遍历各个可折叠二级菜单项，进行折叠/打开操作
	for (var i=0; i<this.divCount; i++) 
	{
		var divItem = document.getElementById("hideDetails" + i);
		if (divItem) 
		{
			OpenCloseFunc(divItem);
		}
	}
}

function ToggleWidget(div)
{
	if(div.style.display == "none")
	{
		OpenWidget(div);
	}
	else
	{
		CloseWidget(div);
	}
}

function OpenWidget(div)
{
	document.all(div.getAttribute("img")).src = imgMin.src;
	div.style.display = "";
	document.all(div.getAttribute("img")).alt = div.getAttribute("openAlt");
	document.all(div.getAttribute("img") + "1").title = div.getAttribute("openAlt");
	document.all(div.getAttribute("img") + "2").title = div.getAttribute("openAlt");
}

function CloseWidget(div)
{
	document.all(div.getAttribute("img")).src = imgMax.src;
	div.style.display = "none";
	document.all(div.getAttribute("img")).alt = div.getAttribute("closedAlt");
	document.all(div.getAttribute("img") + "1").title = div.getAttribute("closedAlt");
	document.all(div.getAttribute("img") + "2").title = div.getAttribute("closedAlt");
}

//var widgets = new Widgets();

//创建可折叠的二级菜单和三级菜单项
function addDetailsDiv(menuHead, detailsDict)
{
	var tbody;
	var tr;
	var td;
	var a;

	//添加可折叠菜单头-----------------------------------------------------------------------
	var tabHead = document.createElement("table");
	tabHead.setAttribute("width","100%");
	tabHead.setAttribute("cellspacing","0");
	tabHead.setAttribute("cellpadding","1");
	tabHead.setAttribute("border","0");
	tabHead.setAttribute("class","fillcolor");//for firefox
	tabHead.setAttribute("className","fillcolor");//for ie
	document.getElementById("divOpenNav").appendChild(tabHead);

	tbody = document.createElement("tbody");
	tabHead.appendChild(tbody);

	tr = document.createElement("tr");
	tbody.appendChild(tr);

	td = document.createElement("td");
	tr.appendChild(td);

	a = document.createElement("a");
	a.setAttribute("id","exp" + widgets.divCount + "1");
	a.setAttribute("title","折叠" + menuHead);
	a.setAttribute("href","javascript:ToggleWidget(hideDetails" + widgets.divCount + ");")
	td.appendChild(a);

	var p = document.createElement("p");
	p.setAttribute("class","label");//for firefox
	p.setAttribute("className","label");//for ie
	p.innerHTML = menuHead;
	a.appendChild(p);

	td = document.createElement("td");
	td.setAttribute("width","15");
	tr.appendChild(td);

	a = document.createElement("a");
	a.setAttribute("id","exp" + widgets.divCount + "2");
	a.setAttribute("title","折叠" + menuHead);
	a.setAttribute("href","javascript:ToggleWidget(hideDetails" + widgets.divCount + ");")
	td.appendChild(a);
	
	var imgHeadMin = new Image();
	imgHeadMin.id = "exp" + widgets.divCount;
	imgHeadMin.alt = "折叠" + menuHead;
	imgHeadMin.src = imgMin.src;
	imgHeadMin.width = 15;
	imgHeadMin.height = 15;
	imgHeadMin.border = 0;
	a.appendChild(imgHeadMin);

	//添加可折叠菜单项-----------------------------------------------------------------------
	var divDetails = document.createElement("div");
	divDetails.setAttribute("id", "hideDetails" + widgets.divCount);
	divDetails.setAttribute("img","exp" + widgets.divCount);
	divDetails.setAttribute("openAlt", "折叠" + menuHead);
	divDetails.setAttribute("closedAlt", "展开" + menuHead);
	divDetails.setAttribute("class","hiderScroll");//for firefox
	divDetails.setAttribute("className","hiderScroll");//for ie
	document.getElementById("divOpenNav").appendChild(divDetails);

	var tabDetails = document.createElement("table");
	tabDetails.setAttribute("width","100%");
	tabDetails.setAttribute("cellspacing","1");
	tabDetails.setAttribute("class","tableContent");//for firefox
	tabDetails.setAttribute("className","tableContent");//for ie
	divDetails.appendChild(tabDetails);

	tbody = document.createElement("tbody");
	tabDetails.appendChild(tbody);

	for (var key in detailsDict) 
	{
		tr = document.createElement("tr");
		tbody.appendChild(tr);

		td = document.createElement("td");
		td.setAttribute("class","tableContent");//for firefox
		td.setAttribute("className","tableContent");//for ie
		tr.appendChild(td);

		var img = document.createElement("img");
		img.src = imgItem.src;
		td.appendChild(img);

		a = document.createElement("a");
		a.innerHTML = key;
		a.setAttribute("href","#");
		var _function = "parent.frames['tab'].addTab('" + key + "', '" + detailsDict[key] + "');return false;";
		a.setAttribute("onclick",_function);
		td.appendChild(a);
	}

	//widgets.Add(divDetails, "Details" + widgets.divCount);
	widgets.divCount++;
}
