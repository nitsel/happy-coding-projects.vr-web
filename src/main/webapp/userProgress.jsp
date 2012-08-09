<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Users</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
</head>

<body>
	<div class="entry">
		<h2>User Study Progress - Users Overview</h2>
		<h3>Monitor the current progress of user study</h3>
		<c:if test="${!empty requestScope.records }">
			<table style="width: 100%;">
				<tr>
					<th>ID</th>
					<th>Gender</th>
					<th>Age</th>
					<th>Education</th>
					<th>Job</th>
					<th>Shopping Experience</th>
					<th>Virtual Experience</th>
					<th>Date</th>
				</tr>
			<c:forEach var="record" items="${requestScope.records }">
				<tr>
					<td>${record.userId }</td>
					<td>${record.gender }</td>
					<td>${record.age }</td>
					<td>${record.education }</td>
					<td>${record.job }</td>
					<td>${record.shoppingExperience }</td>
					<td>${record.vrExperience }</td>
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
