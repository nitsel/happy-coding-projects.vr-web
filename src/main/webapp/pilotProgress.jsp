<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="records" class="org.felix.db.PilotStudy" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Pilot Study</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
</head>

<body>
	<div class="entry">
		<h2>Pilot Study Progress - Data View</h2>
		<h3>Manage the current progress of pilot study</h3>
		<c:if test="${!empty records }">
			<table>
				<tr>
					<th>UserID</th>
					<th>Appearance</th>
					<th>Material</th>
					<th>Fit</th>
					<th>Situation</th>
					<th>Customization</th>
					<th>High Rating</th>
					<th>Brand</th>
					<th>Store</th>
					<th>Recommendation</th>
					<th>Category</th>
					<th>Warranty</th>
					<th>Price</th>
					<th>Promotion</th>
					<th>Shipping</th>
					<th>Other Feature</th>
					<th>Comments</th>
					<th>Date</th>
				</tr>
			<c:forEach var="record" items="${records }">
				<tr>
					<td>${record.userId }</td>
					<td>${record.appearance }</td>
					<td>${record.material }</td>
					<td>${record.fit }</td>
					<td>${record.situation }</td>
					<td>${record.customization }</td>
					<td>${record.rating }</td>
					<td>${record.brand }</td>
					<td>${record.store }</td>
					<td>${record.recommendation }</td>
					<td>${record.category }</td>
					<td>${record.warranty }</td>
					<td>${record.price }</td>
					<td>${record.promotion }</td>
					<td>${record.shipping }</td>
					<td>${record.others }</td>
					<td>${record.comments }</td>
					<td>${record.cDate }</td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty records }">
			<h3>No data is available.</h3>
		</c:if>
	</div>
</body>
</html>
