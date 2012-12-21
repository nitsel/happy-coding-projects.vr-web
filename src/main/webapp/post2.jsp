<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Questionnaire - Willingness Survey</title>
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="shortcut icon" href="img/users.ico" />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript">
	function turn(msg) {
		if (msg == 'yes') {
			$('textarea[name=yesReasons]').attr("disabled", "");
			$('textarea[name=confidence]').attr("disabled", "");
			$('textarea[name=noReasons]').attr("disabled", "disabled");
			$('textarea[name=conditions]').attr("disabled", "disabled");
		} else if (msg == 'no') {
			$('textarea[name=yesReasons]').attr("disabled", "disabled");
			$('textarea[name=confidence]').attr("disabled", "disabled");
			$('textarea[name=noReasons]').attr("disabled", "");
			$('textarea[name=conditions]').attr("disabled", "");
		}
	}

	$(document).ready(function() {
		$('textarea[name=yesReasons]').attr("disabled", "disabled");
		$('textarea[name=confidence]').attr("disabled", "disabled");
		$('textarea[name=noReasons]').attr("disabled", "disabled");
		$('textarea[name=conditions]').attr("disabled", "disabled");
	});

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
		var sel = getValue('input[name=willingness]');
		if (!sel) {
			alert("Please answer question 1 before submission.");
			return false;
		}

		if (sel == 'yes') {
			if (!$('textarea[name="yesReasons"]').val()) {
				alert("please give your reasons for willing to rate.");
				return false;
			}
			if (!$('textarea[name="confidence"]').val()) {
				alert("please give your reasons for whether to rate with confidences.");
				return false;
			}
		} else if (sel == 'no') {
			if (!$('textarea[name=noReasons]').val()) {
				alert("please give your reasons for willing to rate.");
				return false;
			}
			if (!$('textarea[name=conditions]').val()) {
				alert("please give your conditions under which you are willing to rate.");
				return false;
			}
		}

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
		<h4>NOTE: All information will be kept confidential and only for
			research purposes.</h4>
		<p>
			Based on the information and actions that you can obtain and perform
			in two environments, <br /> if both environments enable you to rate
			the t-shirts towards their quality and performance, regardless of or
			before purchasing the real t-shirts,
		</p>

		<form action="./userStudy?action=willingness" method="post"
			class="userForm" onsubmit="return validate()">
			<ol>
				<li><label>Are you willing to rate the t-shirt of your
						interest or interacted with</label> <input type="radio" name="willingness"
					value="yes" onclick="turn('yes')" />Yes <input type="radio"
					name="willingness" value="no" onclick="turn('no')" />No<br /></li>
				<li><label>If yes (you are willing to rate), it is
						because: <br /> (please also indicate which environment (web site or
						virtual store) you prefer to rate t-shirts and state your reasons)
				</label><br /> <textarea rows="5" cols="50" name="yesReasons"></textarea><br />
					<label>Besides ratings, would you like to indicate how
						confident you are in your ratings? State your reasons.</label><br /> <textarea
						rows="5" cols="50" name="confidence"></textarea></li>
				<li><label>If no (you are unwilling to rate), it is
						because: </label><br /> <textarea rows="5" cols="50" name="noReasons"></textarea><br />
					<label>In what conditions, you will rate the t-shirts?
						(e.g. any rewards, payoff, benefits, mood, etc.)</label><br /> <textarea
						rows="5" cols="50" name="conditions"></textarea></li>
				<li><label>You are</label><br /> <input type="radio"
					name="gender" value="Male" />Male<br />
					<input type="radio" name="gender" value="Female" />Female<br />
				</li>
				<li><label>How old are you?</label><br /> <input type="radio"
					name="age" value="Below 20" />Below 20<br /> <input type="radio"
					name="age" value="20 - 29" />20 - 29<br /> <input type="radio"
					name="age" value="30 - 39" />30 - 39<br /> <input type="radio"
					name="age" value="40 - 49" />40 - 49<br /> <input type="radio"
					name="age" value="50 - 59" />50 - 59<br /> <input type="radio"
					name="age" value="60 or Above" />60 or Above<br /></li>
				<li><label>What is your educational level?</label><br /> <input
					type="radio" name="education" value="Some College" />Some College<br />
					<input type="radio" name="education" value="College" />College
					(associate degree)<br /> <input type="radio" name="education"
					value="Bachelor" />University (Bachelor degree)<br /> <input
					type="radio" name="education" value="Master" />Master degree<br />
					<input type="radio" name="education" value="Doctoral" />Doctoral
					degree<br /></li>
				<li><label>You are</label><br /> <input type="radio"
					name="job" value="student" />A
					Student, from <input type="text" name="job1" class="underline_box" />
					(e.g. School of Computer Engineering, or SCE for short)<br /> <input
					type="radio" name="job" value="staff"
					<c:if test="${fn:substring(user.job, 0, fn:indexOf(user.job,'::')) eq 'staff' }">checked</c:if> />A
					Staff, the area you are working on is <input type="text"
					name="job2" class="underline_box" /><br />
					<input type="radio" name="job" value="others" />Others: <input
					type="text" name="job3" class="underline_box" /><br /></li>
				<li><label>About how often do you shop online?</label><br /> <input
					type="radio" name="shoppingExperience" value="Very frequently" />Every
					day or every other day<br /> <input type="radio"
					name="shoppingExperience" value="Frequently" />Once a week<br />
					<input type="radio" name="shoppingExperience" value="Seldom" />Once
					a month<br /> <input type="radio" name="shoppingExperience"
					value="Rarely" />Once a quarter<br /> <input type="radio"
					name="shoppingExperience" value="Almost Never" />Once a year or
					less<br /> <input type="radio" name="shoppingExperience"
					value="Never" />Never<br /></li>
				<li><label>When did you first use 3D environment?</label><br />
					<input type="radio" name="vrExperience" value="No" />This is my first time<br />
					<input type="radio" name="vrExperience" value="less than 1 month" />Last month<br /> <input type="radio" name="vrExperience"
					value="less than 3 months" />Last quarter<br /> <input
					type="radio" name="vrExperience" value="less than 6 months" />Last half a year<br /> <input type="radio" name="vrExperience"
					value="less than 1 year" />Last year<br /> <input
					type="radio" name="vrExperience" value="couple of years" />More than two years ago<br /></li>
			</ol>
			<input type="hidden" name="particiapted" value="no"> <input
				type="submit" value="Submit Answers" class="submit">
		</form>
	</div>

</body>
</html>
