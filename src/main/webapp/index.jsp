<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="user" class="org.felix.db.User" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - User</title>
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="shortcut icon" href="img/users.ico" />
<script type="text/javascript" src='js/jquery.js'></script>
<script>
	function getValue(selector) {
		var stars = $(selector);
		var value;
		for ( var i = 0; i < stars.length; i++) {
			if (stars[i].checked) {
				value = stars[i].value;
				break;
			}
		}
		return value; // if not rated, return 0. 
	}

	function check_name() {
		var userId = $('input[name="userId"]').val();
		if (!userId) {
			alert("Please enter your UserID");
			return false;
		}

		return true;
	}

	function validate() {
		var gender = getValue('input[name="gender"]');
		if (!gender) {
			alert("Please select your gender. ");
			return false;
		}
		var age = getValue('input[name="age"]');
		if (!age) {
			alert("Please select your age. ");
			return false;
		}
		var education = getValue('input[name="education"]');
		if (!education) {
			alert("Please select your education. ");
			return false;
		}

		var job = getValue('input[name="job"]');
		if (!job) {
			alert("Please select your job. ");
			return false;
		}

		if (job == 'student') {
			var school = $('input[name="job1"]').val();
			if (!school) {
				alert("Please input your school.");
				return false;
			}
		} else if (job == 'staff') {
			var area = $('input[name="job2"]').val();
			if (!area) {
				alert("Please input your working area.");
				return false;
			}
		}

		var shoppingExperience = getValue('input[name="shoppingExperience"]');
		if (!shoppingExperience) {
			alert("Please select your shopping experience. ");
			return false;
		}

		var vrExperience = getValue('input[name="vrExperience"]');
		if (!vrExperience) {
			alert("Please select your virtual reality experience. ");
			return false;
		}

		return true;
	}
</script>

</head>

<body>
	<div class="entry">
		<h2>User Study: Some Information About You</h2>
		<h4>NOTE: All information will be kept confidential and only for
			research purpose.</h4>
		<form action="./userStudy?action=user_in" method="post"
			class="userForm" onsubmit="return check_name()">
			<label>Existing UserID:</label><input name="userId" type="text"
				value="${sessionScope.userId }" width="150px" class="underline_box" />
			<select name="environment">
				<option value="web site"
					<c:if test="${sessionScope.environment eq 'web site' }">selected="selected"</c:if>>Web
					Site</option>
				<option value="virtual reality"
					<c:if test="${sessionScope.environment eq 'virtual reality' }">selected="selected"</c:if>>Virtual
					Reality</option>
			</select> <input type="submit" value="Start User Study" class="submit" />
			<p style="display: inherit; color: red;">${requestScope.error_in
				}</p>
		</form>
		<div class="hr"></div>
		<form action="./userStudy?action=user" method="post" class="userForm"
			onsubmit="return validate()">
			<label>New UserID:</label><span style="margin: 0px 10px; color: red;">${userId
				}</span> Environment: <select name="environment">
				<option value="web site"
					<c:if test="${sessionScope.environment eq 'web site' }">selected="selected"</c:if>>Web
					Site</option>
				<option value="virtual reality"
					<c:if test="${sessionScope.environment eq 'virtual reality' }">selected="selected"</c:if>>Virtual
					Reality</option>
			</select><br /> <span style="color: red;">${requestScope.error }</span>

			<ol>
				<li><label>You are</label><br /> <input type="radio"
					name="gender" value="Male"
					<c:if test="${user.gender eq 'Male' }">checked</c:if> />Male<br />
					<input type="radio" name="gender" value="Female"
					<c:if test="${user.gender eq 'Female' }">checked</c:if> />Female<br />
				</li>
				<li><label>How old are you?</label><br /> <input type="radio"
					name="age" value="Below 20"
					<c:if test="${user.age eq 'Below 20' }">checked</c:if> />Below 20<br />
					<input type="radio" name="age" value="20 - 29"
					<c:if test="${user.age eq '20 - 29' }">checked</c:if> />20 - 29<br />
					<input type="radio" name="age" value="30 - 39"
					<c:if test="${user.age eq '30 - 39' }">checked</c:if> />30 - 39<br />
					<input type="radio" name="age" value="40 - 49"
					<c:if test="${user.age eq '40 - 49' }">checked</c:if> />40 - 49<br />
					<input type="radio" name="age" value="50 - 59"
					<c:if test="${user.age eq '50 - 59' }">checked</c:if> />50 - 59<br />
					<input type="radio" name="age" value="60 or Above"
					<c:if test="${user.age eq '60 or Above' }">checked</c:if> />60 or
					Above<br /></li>
				<li><label>What is your educational level?</label><br /> <input
					type="radio" name="education" value="Some College"
					<c:if test="${user.education eq 'Some College' }">checked</c:if> />Some
					College<br /> <input type="radio" name="education" value="College"
					<c:if test="${user.education eq 'College' }">checked</c:if> />College
					(associate degree)<br /> <input type="radio" name="education"
					value="Bachelor"
					<c:if test="${user.education eq 'Bachelor' }">checked</c:if> />University
					(Bachelor degree)<br /> <input type="radio" name="education"
					value="Master"
					<c:if test="${user.education eq 'Master' }">checked</c:if> />Master
					degree<br /> <input type="radio" name="education" value="Doctoral"
					<c:if test="${user.education eq 'Doctoral' }">checked</c:if> />Doctoral
					degree<br /></li>
				<li><label>You are</label><br /> <input type="radio"
					name="job" value="student"
					<c:if test="${fn:substring(user.job, 0, fn:indexOf(user.job,'::')) eq 'student' }">checked</c:if> />A
					Student, from <input type="text" name="job1" class="underline_box"
					<c:if test="${fn:substring(user.job, 0, fn:indexOf(user.job,'::')) eq 'student' }">
						value="${fn:substringAfter(user.job,'::') }"
					</c:if> />
					(e.g. School of Computer Engineering, or SCE for short)<br /> <input
					type="radio" name="job" value="staff"
					<c:if test="${fn:substring(user.job, 0, fn:indexOf(user.job,'::')) eq 'staff' }">checked</c:if> />A
					Staff, the area you are working on is <input type="text"
					name="job2" class="underline_box"
					<c:if test="${fn:substring(user.job, 0, fn:indexOf(user.job,'::')) eq 'staff' }">
						value="${fn:substringAfter(user.job,'::') }"
					</c:if> /><br />
					<input type="radio" name="job" value="others" />Others: <input type="text" name="job3" class="underline_box"/><br /></li>
				<li><label>On the average, you shop online</label><br /> <input
					type="radio" name="shoppingExperience" value="Very frequently"
					<c:if test="${user.shoppingExperience eq 'Very frequently' }">checked</c:if> />Very
					frequently, more than (or equal) 3 times per week<br /> <input
					type="radio" name="shoppingExperience" value="Frequently"
					<c:if test="${user.shoppingExperience eq 'Frequently' }">checked</c:if> />Frequently,
					once or twice per week<br /> <input type="radio"
					name="shoppingExperience" value="Seldom"
					<c:if test="${user.shoppingExperience eq 'Seldom' }">checked</c:if> />Seldom,
					once or twice per month<br /> <input type="radio"
					name="shoppingExperience" value="Rarely"
					<c:if test="${user.shoppingExperience eq 'Rarely' }">checked</c:if> />Rarely,
					once or twice every several months<br /> <input type="radio"
					name="shoppingExperience" value="Never"
					<c:if test="${user.shoppingExperience eq 'Never' }">checked</c:if> />Never<br />
				</li>
				<li><label>Have you used 3D virtual environment before?</label><br />
					<input type="radio" name="vrExperience" value="No"
					<c:if test="${user.vrExperience eq 'No' }">checked</c:if> />No,
					never<br /> <input type="radio" name="vrExperience"
					value="less than 1 month"
					<c:if test="${user.vrExperience eq 'less than 1 month' }">checked</c:if> />Yes,
					less than 1 month<br /> <input type="radio" name="vrExperience"
					value="less than 3 months"
					<c:if test="${user.vrExperience eq 'less than 3 months' }">checked</c:if> />Yes,
					less than 3 months<br /> <input type="radio" name="vrExperience"
					value="less than 6 months"
					<c:if test="${user.vrExperience eq 'less than 6 months' }">checked</c:if> />Yes,
					less than 6 months<br /> <input type="radio" name="vrExperience"
					value="less than 1 year"
					<c:if test="${user.vrExperience eq 'less than 1 year' }">checked</c:if> />Yes,
					less than 1 year<br /> <input type="radio" name="vrExperience"
					value="couple of years"
					<c:if test="${user.vrExperience eq 'couple of years' }">checked</c:if> />Yes,
					couple of years<br /></li>
			</ol>
			<input type="submit" value="Start User Study" class="submit">
		</form>
	</div>

</body>
</html>
