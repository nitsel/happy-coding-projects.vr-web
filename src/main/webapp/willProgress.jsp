<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Willingness</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
</head>

<body>
	<div class="entry">
		<h2>User Study Progress - Willingness Overview</h2>
		<h3>Monitor the current progress of user study</h3>
		<c:if test="${!empty requestScope.records }">
			<table style="width: 100%;">
				<tr>
					<th>ID</th>
					<th>Willingness</th>
					<th>Yes, reasons</th>
					<th>Confidence</th>
					<th>No, reasons</th>
					<th>Conditions</th>
					<th>UserId</th>
				</tr>
			<c:forEach var="record" items="${requestScope.records }">
				<tr>
					<td>${record.id }</td>
					<td>${record.willingness }</td>
					<td>${record.yesReasons }</td>
					<td>${record.confidence }</td>
					<td>${record.noReasons }</td>
					<td>${record.conditions }</td>
					<td>${record.userId }</td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty requestScope.records }">
			<h3>No data is available.</h3>
		</c:if>
	</div>
</body>
</html>
