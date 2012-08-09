<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Ratings</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
</head>

<body>
	<div class="entry">
		<h2>User Study Progress - Ratings Overview</h2>
		<h3>Monitor the current progress of user study</h3>
		<c:if test="${!empty requestScope.records }">
			<table style="width: 1000px;">
				<tr>
					<th>ID</th>
					<th>TeeID</th>
					<th>Q1</th>
					<th>Q2</th>
					<th>Q3</th>
					<th>Q4</th>
					<th>Q5</th>
					<th>Q6</th>
					<th>Q7</th>
					<th>Q8</th>
					<th>Q9</th>
					<th>Q10</th>
					<th>Q11</th>
					<th>Q12</th>
					<th>Date</th>
				</tr>
			<c:forEach var="record" items="${requestScope.records }">
				<tr>
					<td>${record.userId }</td>
					<td>${record.teeId }</td>
					<td>${record.overall }</td>
					<td>${record.appearance }</td>
					<td>${record.material }</td>
					<td>${record.fit }</td>
					<td>${record.category }</td>
					<td>${record.price }</td>
					<td>${record.brand }</td>
					<td>${record.store }</td>
					<td>${record.shipping }</td>
					<td>${record.quality }</td>
					<td>${record.cost }</td>
					<td>${record.value }</td>
					<td>${record.cDate }</td>
				</tr>
			</c:forEach>
			</table>
			<ul>
				<li>Q1: Overall Rating</li>
				<li>Q2: Appearance</li>
				<li>Q3: Material</li>
				<li>Q4: Fit</li>
				<li>Q5: Category</li>
				<li>Q6: Price</li>
				<li>Q7: Brand</li>
				<li>Q8: Store</li>
				<li>Q9: Shipping</li>
				<li>Q10: Product Quality</li>
				<li>Q11: Product Cost</li>
				<li>Q12: Product Value</li>
				<li>Date</li>
			</ul>
		</c:if>
		<c:if test="${empty requestScope.records }">
			<h3>No data is available.</h3>
		</c:if>
	</div>
</body>
</html>
