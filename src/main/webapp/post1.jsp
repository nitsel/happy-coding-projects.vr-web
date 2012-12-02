<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Willingness Survey</title>
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="shortcut icon" href="img/users.ico" />
<script type="text/javascript" src='js/jquery.js'></script>
</head>

<body>
	<div class="entry">
		<p>Based on the information and actions that you can obtain and perform in two environments, <br/>
		if both environments enable you to rate the t-shirts regardless of or before purchasing the real t-shirts, </p>
		
		<form action="./userStudy?action=willingness" method="post" class="userForm"
			onsubmit="return validate()">
			<ol>
				<li><label>Are you willing to rate the t-shirt of your interest or interacted with</label><input type="radio"
					name="willingness" value="yes"
					<c:if test="${user.gender eq 'Male' }">checked</c:if> />Yes
					<input type="radio" name="will" value="no"
					<c:if test="${user.gender eq 'Female' }">checked</c:if> />No<br />
				</li>
				<li><label>If yes (you are willing to rate), it is because: </label><br/>
				<textarea rows="5" cols="50" name="yesReasons"></textarea><br/>
				<label>Besides ratings, would you like to indicate how confident you are for your ratings? State your reasons.</label><br/>
				<textarea rows="5" cols="50" name="confidence"></textarea>
				</li>
				<li><label>If no (you are unwilling to rate), it is because: </label><br/>
				<textarea rows="5" cols="50" name="noReasons"></textarea><br/>
				<label>In what conditions, you will rate the t-shirts? (e.g. any rewards, payoff, benefits, mood, etc.)</label><br/>
				<textarea rows="5" cols="50" name="conditions"></textarea>
				</li>
			</ol>
			<input type="hidden" name="particiapted" value="yes">
			<input type="submit" value="Submit Answers" class="submit">
		</form>
	</div>

</body>
</html>
