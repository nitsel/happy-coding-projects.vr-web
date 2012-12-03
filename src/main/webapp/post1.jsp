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

	function validate() {
		var sel = getValue('input[name=willingness]');
		if (!sel) {
			alert("Please answer question 1 before submission.");
			return false;
		}

		if (sel == 'yes') {
			if(!$('textarea[name="yesReasons"]').val()){
				alert("please give your reasons for willing to rate.");
				return false;
			}
			if(!$('textarea[name="confidence"]').val()){
				alert("please give your reasons for whether to rate with confidences.");
				return false;
			}
		} else if (sel == 'no') {
			if(!$('textarea[name=noReasons]').val()){
				alert("please give your reasons for willing to rate.");
				return false;
			}
			if(!$('textarea[name=conditions]').val()){
				alert("please give your conditions under which you are willing to rate.");
				return false;
			}
		}

		return true;
	}
</script>
</head>

<body>
	<div class="entry">
		<p>
			Based on the information and actions that you can obtain and perform
			in two environments, <br /> if both environments enable you to rate
			the t-shirts regardless of or before purchasing the real t-shirts,
		</p>

		<form action="./userStudy?action=willingness" method="post"
			class="userForm" onsubmit="return validate()">
			<ol>
				<li><label>Are you willing to rate the t-shirt of your
						interest or interacted with</label> <input type="radio" name="willingness"
					value="yes" onclick="turn('yes')" />Yes <input type="radio"
					name="willingness" value="no" onclick="turn('no')" />No<br /></li>
				<li><label>If yes (you are willing to rate), it is
						because: </label><br /> <textarea rows="5" cols="50" name="yesReasons"></textarea><br />
					<label>Besides ratings, would you like to indicate how
						confident you are for your ratings? State your reasons.</label><br /> <textarea
						rows="5" cols="50" name="confidence"></textarea></li>
				<li><label>If no (you are unwilling to rate), it is
						because: </label><br /> <textarea rows="5" cols="50" name="noReasons"></textarea><br />
					<label>In what conditions, you will rate the t-shirts?
						(e.g. any rewards, payoff, benefits, mood, etc.)</label><br /> <textarea
						rows="5" cols="50" name="conditions"></textarea></li>
			</ol>
			<input type="hidden" name="particiapted" value="yes"> <input
				type="submit" value="Submit Answers" class="submit">
		</form>
	</div>

</body>
</html>
