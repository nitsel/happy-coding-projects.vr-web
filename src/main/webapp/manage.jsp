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
			<li><label>Pilot Study:</label><a href="http://localhost:8080/VR-web/userStudy?action=pilot">http://localhost:8080/VR-web/userStudy?action=pilot</a>
			<li><label>User Study:</label><a href="http://localhost:8080/VR-web/userStudy">http://localhost:8080/VR-web/userStudy</a>
			<li><label>Clear Users' Data:</label><a href="http://localhost:8080/VR-web/userStudy?action=clearDB">http://localhost:8080/VR-web/userStudy?action=clearDB</a>
			<li><label>Clear Users' Rating Data:</label><a href="http://localhost:8080/VR-web/userStudy?action=clearRatings">http://localhost:8080/VR-web/userStudy?action=clearRatings</a>
			<li><label>Clear Users' Pilot Study Data:</label><a href="http://localhost:8080/VR-web/userStudy?action=clearPilots">http://localhost:8080/VR-web/userStudy?action=clearPilots</a>
		</ul>
	</div>
</body>
</html>
