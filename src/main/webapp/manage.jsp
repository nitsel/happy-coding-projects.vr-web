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
			<li><label>User Study:</label><a href="http://localhost:8080/VR-web/userStudy" target="_blank">http://localhost:8080/VR-web/userStudy</a>
			<li><label>Pilot Study:</label><a href="http://localhost:8080/VR-web/userStudy?action=pilot" target="_blank">http://localhost:8080/VR-web/userStudy?action=pilot</a>
			<li><label>Clear Users' Data:</label><a href="http://localhost:8080/VR-web/userStudy?action=clearDB" target="_blank">http://localhost:8080/VR-web/userStudy?action=clearDB</a>
			<li><label>Clear Pilot Studies:</label><a href="http://localhost:8080/VR-web/userStudy?action=clearPilots" target="_blank">http://localhost:8080/VR-web/userStudy?action=clearPilots</a>
			<li><label>Clear Virtual Ratings:</label><a href="http://localhost:8080/VR-web/userStudy?action=clearRatings" target="_blank">http://localhost:8080/VR-web/userStudy?action=clearRatings</a>
		</ul>
	</div>
</body>
</html>
