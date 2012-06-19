// JScript File


var requestObject = null;

function getProductBalloonInfo(productID, speakStyle)
{
	
		var url = "/Ajax_Scripts/server/GetProductBalloonInfo.asp?prodID=" + productID;
		var myResult = HTTPRequest(url, speakStyle);
}


function createRequestObject() {
	//Try the various types of XML Request, to find one that works with the current browser
	var returnValue = null;
	try	{
		returnValue = new ActiveXObject("Msxml2.XMLHTTP")
	} catch(e) {
		try {
			returnValue = new ActiveXObject("Microsoft.XMLHTTP")
		} catch(e2)	{
			returnValue = null
		}
	}
	if(!returnValue && typeof XMLHttpRequest!="undefined") {
		returnValue = new XMLHttpRequest()
	}
	return returnValue
}

function HTTPRequest(requestUri, speakStyle) {
	//Cancel any outstanding requests, create a new one then execute it
	if(requestObject && requestObject.readyState!=0) requestObject.abort()
	requestObject = createRequestObject();
	
	if (createRequestObject) {
		//Use date/time to ensure the page is not cached
		var cacheDate = new Date();
		cacheDate = cacheDate.toString();
		requestObject.open("GET", requestUri + "&__XMLHTTPCacheDate=" + cacheDate, true); //GET/POST/HEAD, URI, always True for async
		requestObject.onreadystatechange = function() {
			if(requestObject.readyState==4 && requestObject.responseText) {
			var response = requestObject.responseText;
			var respArray = response.split('|');
			var productName = respArray[0];
			var productDescription = respArray[1];
			var productPrice = respArray[2];
			var smallImagePath = respArray[3];
			overlib(createOverLibHtml(productName, formatCurrency(productPrice), productDescription, smallImagePath, speakStyle))		
			}
		}
		requestObject.send(null); //POST body=null for GET
	}
}

function HTTPRequestJavaScript(requestUri) {
	//Cancel any outstanding requests, create a new one then execute it
	if(requestObject && requestObject.readyState!=0) requestObject.abort()
	requestObject = createRequestObject();
	
	if (createRequestObject) {
		//Use date/time to ensure the page is not cached
		var cacheDate = new Date();
		cacheDate = cacheDate.toString();
		requestObject.open("GET", requestUri + "&__XMLHTTPCacheDate=" + cacheDate, true); //GET/POST/HEAD, URI, always True for async
		requestObject.onreadystatechange = function() {
			if(requestObject.readyState==4 && requestObject.responseText) {
				try {
					eval(requestObject.responseText);
				} catch(e) {
					alert("Server side async callback caused an error: " + e.message + "\nResponse Text: " + requestObject.responseText);
				}
			}
		}
		requestObject.send(null); //POST body=null for GET
	}
}

function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + '$' + num + '.' + cents);
}

function _getProductBalloonInfo(productID, speakStyle)
{
	if (requestSent == false)
	{
		xmlHTTP = getXmlHttpObject();
		var url = "/Ajax_Scripts/server/GetProductBalloonInfo.asp?prodID=" + productID;
		xmlHTTP.open("GET", url, true);
		xmlHTTP.send(null);
		requestSent = true;
		window.setTimeout("getProductBalloonInfo(0)", 200);
	}
	else
	{
		if (xmlHTTP.readyState == 4)
		{
			var response = xmlHTTP.responseText;
			var respArray = response.split('|');
			var productName = respArray[0];
			var productDescription = respArray[1];
			var productPrice = respArray[2];
			var smallImagePath = respArray[3];
			alert(response);
			return createOverLibHtml(productName, productPrice, productDescription, smallImagePath, speakStyle)		
		}
		else
		{
			window.setTimeout("getProductBalloonInfo(0)", 200);
		}
	}
}


function getXmlHttpObject()
{
	var xmlHTTP = null;
	
	try
	{
		// For Fire-fox, Opera, Safari
		xmlHTTP = new XMLHttpRequest();	
	}
	catch(e)
	{
		// Internet explorer
		try
		{
			xmlHTTP = new ActiveXObject("Msmxl2.XMLHTTP");
		}
		catch(e)
		{
			xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	return xmlHTTP;
}

	// Turn off OverLib's background making it transparent
	ol_bgcolor = "";
	ol_fgcolor = "";
	var SPEAK_STYLE_TOP = 1;		// Default
	var SPEAK_STYLE_LEFT = 2;
	var SPEAK_STYLE_RIGHT = 3;
	
	function createOverLibHtml(productName, productPrice, productDescription, thumbnail, speak_style)
	{
		var topClass = "rollover-top";			// Default styles
		var bottomClass = "rollover-bottom";
		var rolloverClass = "rollover";
	
		if (speak_style == SPEAK_STYLE_LEFT)
		{
			topClass = "rollover-top-left";
			bottomClass = "rollover-bottom-left";
			rolloverClass = "rollover-left";
		}
		else if (speak_style == SPEAK_STYLE_RIGHT)
		{
			topClass = "rollover-top-right";		
			bottomClass = "rollover-bottom-right";
			rolloverClass = "rollover-right";
		}	
	
		var html = "<table width='400px' cellpadding='4' cellspacing='0' class='" + rolloverClass + "' style='background:transparent;'>" +
			"<tr class='" + topClass + "'><td>&nbsp;</td></tr>" +
			"<tr class='rollover-middle'><td valign='top'><span class='rollover-thumbnail'><img src='<%= CDNPath %>" + thumbnail + "' alt='' align='left' /></span>" + 
			"<font class='rollover-name'>" + productName + "</font><br /><font class='rollover-price'>" + productPrice + "</font><br /><br />" +
			"<font class='rollover-desc'>" + productDescription + "</font></td></tr>" +
			"<tr class='" + bottomClass + "'><td>&nbsp;</td></tr></table>";
		return html;			
	}
	
	function createOverLibTextHtml(textMessage)
	{
		var html="<table width='201' cellpadding='0' cellspacing='0' border='0' class='rollover-small' style='background:transparent;'>" +
			"<tr class='rollover-top-small'><td valign='absbottom'>&nbsp;</td></tr>" +
			"<tr class='rollover-middle-small'><td valign='top' class='rollover-desc-small'>" + textMessage + "</td></tr>" +
			"<tr class='rollover-bottom-small'><td valign='top'>&nbsp;</td></tr></table>";
		return html;
	}
