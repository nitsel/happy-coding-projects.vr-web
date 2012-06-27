<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="user" class="org.felix.db.User" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Environment</title>
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
<script>
	function getValue(selector){
		var stars=$(selector);
		var value;
		for(var i=0; i<stars.length; i++)
		{
			if(stars[i].checked){
				value=stars[i].value;
				break;
			}
		}
		return value; // if not rated, return 0. 
	}
	
	function validate()
	{
		var userId = $('input[name="userId"]').val();
		if(!userId)
		{
			alert("Please input your name.");	
			return false;
		}
		
		var gender = getValue('input[name="gender"]');
		if(!gender)
		{
			alert("Please select your gender. ");	
			return false;
		}
		var age = getValue('input[name="age"]');
		if(!age)
		{
			alert("Please select your age. ");	
			return false;
		}
		var education = getValue('input[name="education"]');
		if(!education)
		{
			alert("Please select your education. ");	
			return false;
		}
		
		var job = getValue('input[name="job"]');
		if(!job)
		{
			alert("Please select your job. ");	
			return false;
		}
		
		if(job=='student')
		{
			var school = $('input[name="job1"]').val();
			if(!school)
			{
				alert("Please input your school.");
				return false;
			}
		}else if(job=='staff')
		{
			var area = $('input[name="job2"]').val();
			if(!area)
			{
				alert("Please input your working area.");
				return false;
			}
		}
		
		var shoppingExperience = getValue('input[name="shoppingExperience"]');
		if(!shoppingExperience)
		{
			alert("Please select your shopping experience. ");	
			return false;
		}
		
		var vrExperience = getValue('input[name="vrExperience"]');
		if(!vrExperience)
		{
			alert("Please select your virtual reality experience. ");	
			return false;
		}
		
		return true;
	}
		
	</script>

</head>

<body>
	<div class="entry">
		<h2>Rate and Comment on The Environment</h2>
		<p>To what extent do you agree or disagree with each of the
			following statements? (from one star to five stars):</p>
		<ol>
			<li>Strongly Disagree</li>
			<li>Disagree</li>
			<li>Slightly Disagree or Slightly Agree</li>
			<li>Agree</li>
			<li>Strongly Agree</li>
		</ol>
		<form action="./userStudy?action=env_sub" method="post" class="userForm"
			onsubmit="return validate()">
			<table class="questions">
				<tr>
					<td>13.</td>
					<td width="80%">You are confident about your ratings.</td>
					<td><input name="star1" type="radio" value="1" class="star" />
						<input name="star1" type="radio" value="2" class="star" /> 
						<input name="star1" type="radio" value="3" class="star" /> 
						<input name="star1" type="radio" value="4" class="star" /> 
						<input name="star1" type="radio" value="5" class="star" />
					</td>
				</tr>
				<tr>
					<td>14.</td>
					<td width="80%">It feels the same that inspecting the t-shirt 
					in the environment is just as if you were in a real store and
					had a real t-shirt in hand. </td>
					<td><input name="star2" type="radio" value="1" class="star" />
						<input name="star2" type="radio" value="2" class="star" /> 
						<input name="star2" type="radio" value="3" class="star" /> 
						<input name="star2" type="radio" value="4" class="star" /> 
						<input name="star2" type="radio" value="5" class="star" />
					</td>
				</tr>
				<tr>
					<td>15.</td>
					<td width="80%">You are comfortable to give ratings in the tested environment.</td>
					<td><input name="star3" type="radio" value="1" class="star" />
						<input name="star3" type="radio" value="2" class="star" /> 
						<input name="star3" type="radio" value="3" class="star" /> 
						<input name="star3" type="radio" value="4" class="star" /> 
						<input name="star3" type="radio" value="5" class="star" />
					</td>
				</tr>
				<tr>
					<td>16.</td>
					<td width="80%">You are (not) confident in your ratings (Q13) because </td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3">
						<textarea name="comments" cols="50" rows="7"></textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="Complete User Study" class="submit2">
		</form>
	</div>

</body>
</html>
