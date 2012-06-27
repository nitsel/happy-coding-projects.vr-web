<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="user" class="org.felix.db.User" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Info</title>
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<script type="text/javascript" src='js/jquery.js'></script>
<script>
	function getValue(selector){
		var stars=$(selector);
		var value;
		for(var i=0; i<stars.length; i++)
		{
			if(stars[i].checked){
				value=stars[i].value;
				break;
			}
		}
		return value; // if not rated, return 0. 
	}
	
	</script>

</head>

<body>
	<div class="entry">
		<h2>User Study: All T-Shirts</h2>
		<h4>NOTE: Please Rate at least 10 T-Shirts.</h4>
		<div class="t-shirt">
			<h4>A</h4>
			<img alt="t-shirt image" src="./Htmls/84/Swinging-Sword-Voltron-Shirt.jpg"/>
		</div>
		<div class="t-shirt">
			<h4>A</h4>
			<img alt="t-shirt image" src="./Htmls/84/Swinging-Sword-Voltron-Shirt.jpg"/>
		</div>
	</div>

</body>
</html>
