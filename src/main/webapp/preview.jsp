<!DOCTYPE html "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Book Preview</title>
    <link rel="stylesheet" type="text/css" href="css/html.css" />
    <link rel="stylesheet" type="text/css" href="css/preview.css" />
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
	  google.load("books", "0", {"language" : "us-EN"});
	  
	  var bookViewer, bookId;
	  var viewerCanvas='viewerCanvas';
	  
	  function handleNotFound() {
		  var html="<p class='book-not-found'>Book (id = "+bookId+") is not found in Google Books! </p>";
          document.getElementById(viewerCanvas).innerHTML = html;
      }
	  
	  function go(){
		var page=document.getElementById('page').value;
		bookViewer.goToPage(page);
		return false;
	  }
	  
	  function init() 
	  {
	    var divContainer = document.getElementById(viewerCanvas);
		divContainer.style.height=(screen.height-20)+'px';
		
        bookViewer = new google.books.DefaultViewer(divContainer);
        bookId=document.getElementById('bookId').value;
        
		bookViewer.load(bookId, handleNotFound);
      }

      google.setOnLoadCallback(init);
    </script>
  </head>
  <body>
    <input id="bookId" type="hidden" value="<%=request.getParameter("bookId")%>"/>
    <div id="interaction" class="menu">
		<ul>
			<li><a href="javascript:init()">Home</a></li>
			<li><a href="javascript:bookViewer.nextPage()">Next Page</a></li>
            <li><a href="javascript:bookViewer.previousPage()">Prev Page</a></li>
            <li><a href="javascript:bookViewer.zoomIn()">Zoom In</a></li>
            <li><a href="javascript:bookViewer.zoomOut()">Zoom Out</a></li>
			<li>
				<form action="#" name="form" onSubmit="javascript:return go()">
					<input id="page" type="text" size="3" value=""/>
					<input type="submit" value="Go" onClick="javascript:go()"/>
				</form>
			</li>
		</ul>
	</div>
	<div style="clear:both;"></div>
	<div id="viewerCanvas" class="container"></div>
  </body>
</html>