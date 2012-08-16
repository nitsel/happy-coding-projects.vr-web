<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!-- <meta HTTP-EQUIV="Refresh" content="5; url=./userStudy"> -->
<title>User Study - Environment</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />

</head>

<body>
	<div class="entry">
		<p>
			<strong>Current Progress (<span style="color: red;">${sessionScope.userId
					}@${sessionScope.environment }</span>) </strong> 
				
			<span class="pages"> 
				<c:forEach var="progress" begin="1" end="${sessionScope.maxProgress+1}">
				<c:if test="${sessionScope.environment eq 'web site' }">
					<c:choose>
						<c:when test="${progress<=sessionScope.progress }">
							<a href="./userStudy?action=info&teeId=${sessionScope.vTees[progress] }">
						</c:when>
						<c:when test="${sessionScope.progress==sessionScope.maxProgress }">
							<a href="./userStudy?action=env" onclick="return check_rating()"
								title="To Last Part of User Study">
						</c:when>
						<c:when test="${progress==sessionScope.progress+1}">
							<a href="./userStudy?action=info&survey=next"
								onclick="return check_rating()">
						</c:when>
					</c:choose>
				</c:if>
					<span class="block-reco">
						<c:choose>
							<c:when test="${progress==sessionScope.maxProgress+1 }">>></c:when>
							<c:otherwise>${progress}</c:otherwise>
						</c:choose> </span>
					<c:if test="${progress<=sessionScope.progress+1 && sessionScope.environment eq 'web site'}">
						</a>
					</c:if>
				</c:forEach> 
			</span>
		</p>
		
		<h2 style="color:red;">Thanks for your Great Support.</h2>
		<div>
		<p>Dear <span style="color: blue">${sessionScope.userId }</span>,</p>
		<p>			
			Thanks for your great support in this user study (@ <span style="color: blue">${sessionScope.environment }</span>).<br/> 
			To protect all the information you provided, we will keep it safe and confidential, and only use it for research purpose.<br/>
			If you have not completed the other study (@<span style="color: blue">
			<c:if test="${sessionScope.environment eq 'web site' }">virtual reality</c:if>
			<c:if test="${sessionScope.environment eq 'virtual reality' }">web site</c:if></span>), please proceed to continue user study.
		</p>
			
			Best Regards, <br/>
			Guibing Guo
		</div>
	</div>

</body>
</html>
