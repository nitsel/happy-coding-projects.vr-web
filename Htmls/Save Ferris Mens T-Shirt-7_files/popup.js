<!--
function showDimensions(product_id) {
newwin = window.open('GarmentDimensions.asp?product_id=' + product_id,'newwin','toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=400,height=400');
newwin.focus();
}

function openWin450x200(page) {
window.open(page, 'newWin', 'width=450,height=200,scrollbars=yes');
}

function openWin630x400(page) {
window.open(page, 'newWin', 'width=630,height=400,scrollbars=yes');
}

function openWin630x800(page) {
window.open(page, 'newWin', 'width=630,height=800,scrollbars=yes');
} 

function popupKM(URL,wWidth,wHeight)
{
myWindow = window.open(URL, 'newWin', 'toolbar=no,scrollbars=yes,location=no,statusbar=no,menubar=no,resizable=yes,width='+wWidth+',height='+wHeight+',left=50,top=50');
}

// onChange="openDir( this.form )"
function openDir( form ) { 

	var newIndex = form.fieldname.selectedIndex; 

	if ( newIndex == 0 ) { 

		alert( "Please select a location!" ); 

	} else { 

		cururl = form.fieldname.options[ newIndex ].value; 

		window.location.assign( cururl ); 

	} 

} 

//-->