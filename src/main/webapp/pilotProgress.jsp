<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<h3>Monitor the current progress of pilot study</h3>
		<c:if test="${!empty requestScope.records }">
			<table>
				<tr>
					<th>ID</th>
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
					<th>Q13</th>
					<th>Q14</th>
					<th>Q15</th>
					<th>Date</th>
				</tr>
			<c:forEach var="record" items="${requestScope.records }">
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
					<td>${record.cDate }</td>
				</tr>
			</c:forEach>
			</table>
			<ul>
				<li>Q1: Appearance</li>
				<li>Q2: Material</li>
				<li>Q3: Fit</li>
				<li>Q4: Situation</li>
				<li>Q5: Customization</li>
				<li>Q6: High Rating</li>
				<li>Q7: Brand</li>
				<li>Q8: Store</li>
				<li>Q9: Recommendation</li>
				<li>Q10: Category</li>
				<li>Q11: Warranty</li>
				<li>Q12: Price</li>
				<li>Q13: Promotion</li>
				<li>Q14: Shipping</li>
				<li>Q15: Other Features</li>
				<li>Date</li>
			</ul>
		</c:if>
		<c:if test="${empty requestScope.records }">
			<h3>No data is available.</h3>
		</c:if>
	</div>
</body>
</html>
