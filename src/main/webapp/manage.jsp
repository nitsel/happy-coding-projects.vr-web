<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="p" class="org.felix.db.PilotStudy" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Pilot Study</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
</head>

<body>
	<div class="entry">
		<h2>User Study - Administration</h2>

		<ul class="admin">
			<li><label>User Study:</label><a href="./userStudy"
				target="_blank">~/VR-web/userStudy</a></li>
			<li><label>Post Study:</label><a href="./post.jsp"
				target="_blank">~/VR-web/post.jsp</a></li>
			<li><label style="color: blue;">Wills:</label><a href="./userStudy?action=willProgress"
				target="_blank">~/VR-web/userStudy?action=willProgress</a></li>
			<li><label style="color: blue;">Users:</label><a href="./userStudy?action=userProgress"
				target="_blank">~/VR-web/userStudy?action=userProgress</a></li>
			<li><label style="color: blue;">Envs:</label><a href="./userStudy?action=envProgress"
				target="_blank">~/VR-web/userStudy?action=envProgress</a></li>
			<li><label style="color: blue;">Ratings:</label><a href="./userStudy?action=ratingProgress"
				target="_blank">~/VR-web/userStudy?action=ratingProgress</a></li>
			<li><label style="color: blue;">Sample T-shirt:</label><a href="./userStudy?action=info&teeId=VOLT018"
				target="_blank">~/VR-web/userStudy?action=info&teeId=VOLT018</a>[log in first]</li>
			<li><label>Pilot Study:</label><a
				href="./userStudy?action=pilot" target="_blank">~/VR-web/userStudy?action=pilot</a></li>
			<li><label>Pilot Study:</label><a
				href="./userStudy?action=pilotProgress" target="_blank">~/VR-web/userStudy?action=pilotProgress</a></li>
		</ul>

		<h2>Debug Operations</h2>
		<ul class="admin">
			<li><label>Clear Users' Data:</label><a
				href="./userStudy?action=clearDB" target="_blank"
				onclick="return confirm('Please double check if you really want to clear all the data base. Harmful!');">~/VR-web/userStudy?action=clearDB</a>
				&nbsp;&nbsp;(including 'users', 'ratings', 'envs');</li>
			<li><label>Clear Pilot Studies:</label><a
				href="./userStudy?action=clearPilots" target="_blank"
				onclick="return confirm('Please double check if you really want to clear all the pilot study data. Harmful!');">~/VR-web/userStudy?action=clearPilots</a></li>
			<li><label>Clear Virtual Ratings:</label><a
				href="./userStudy?action=clearRatings" target="_blank"
				onclick="return confirm('Please double check if you really want to clear all the rating data. Harmful!');">~/VR-web/userStudy?action=clearRatings</a></li>
		</ul>
	</div>
</body>
</html>
