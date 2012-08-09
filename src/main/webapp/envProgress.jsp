<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Environments</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
</head>

<body>
	<div class="entry">
		<h2>User Study Progress - Environments Overview</h2>
		<h3>Monitor the current progress of user study</h3>
		<c:if test="${!empty requestScope.records }">
			<table style="width: 100%;">
				<tr>
					<th>ID</th>
					<th>Environment</th>
					<th>Confidence</th>
					<th>Virtual Presence</th>
					<th>Comfort</th>
					<th>Reasons</th>
					<th>Date</th>
				</tr>
			<c:forEach var="record" items="${requestScope.records }">
				<tr>
					<td>${record.userId }</td>
					<td>${record.environment }</td>
					<td>${record.confidence }</td>
					<td>${record.presence }</td>
					<td>${record.comfort }</td>
					<td>${record.reasons }</td>
					<td>${record.cDate }</td>
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
