<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="env" class="org.felix.db.Environment" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Environment</title>
<link rel="shortcut icon" href="img/users.ico" />
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
		var confidence = getValue('input[name="confidence"]');
		if(!confidence)
		{
			alert("Please select your confidence about your ratings. ");	
			return false;
		}
		var presence = getValue('input[name="presence"]');
		if(!presence)
		{
			alert("Please select your feelings about the environment. ");	
			return false;
		}
		var comfort = getValue('input[name="comfort"]');
		if(!comfort)
		{
			alert("Please select your comfort for giving ratings in the tested environment. ");	
			return false;
		}
		
		var reasons = $('textarea[name="reasons"]').val();
		if(!reasons)
		{
			alert("Please state your reasons for your selection in Q13. ");	
			return false;
		}
		
		return true;
	}
		
	</script>

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
				<c:if test="${progress==sessionScope.maxProgress+1 && sessionScope.environment eq 'virtual reality' }">
						<a href="./userStudy?action=env" onclick="return check_rating()" title="To Last Part of User Study">
				</c:if>
					<span
						<c:choose>
							<c:when test="${progress<=sessionScope.progress && sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress+1 
											&& sessionScope.step==3
											&& sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress+1 && sessionScope.environment eq 'web site'}">class="block-red"</c:when>
							<c:when test="${progress<=sessionScope.vrProgress && sessionScope.environment eq 'virtual reality'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.vrProgress+1 && sessionScope.environment eq 'virtual reality'}">class="block-red"</c:when>
							<c:otherwise>class="block-gray"</c:otherwise>
						</c:choose>>
						<c:choose>
							<c:when test="${progress==sessionScope.maxProgress+1 }">>></c:when>
							<c:otherwise>${progress}</c:otherwise>
						</c:choose> </span>
					<c:if test="${progress<=sessionScope.progress+1 && sessionScope.environment eq 'web site'}">
						</a>
					</c:if>
					<c:if test="${sessionScope.progress==sessionScope.maxProgress && sessionScope.environment eq 'virtual reality' }">
						</a>
				</c:if>
				</c:forEach> 
			</span>
		</p>
		
		
		<h2>Rate and Comment on the Environment</h2>
		<p>To what extent do you agree or disagree with each of the
			following statements? (from one star to five stars)</p>
		<ol>
			<li>Strongly Disagree</li>
			<li>Disagree</li>
			<li>Slightly Disagree or Slightly Agree</li>
			<li>Agree</li>
			<li>Strongly Agree</li>
		</ol>
		<form action="./userStudy?action=env_sub" method="post" class="userForm" onsubmit="return validate()">
			<table class="questions">
				<tr>
					<td>13.</td>
					<td width="80%">You are confident about your ratings.</td>
					<td><input name="confidence" type="radio" value="1" class="star" 
							<c:if test="${env.confidence==1 }">checked</c:if>
						/>
						<input name="confidence" type="radio" value="2" class="star" 
							<c:if test="${env.confidence==2 }">checked</c:if>
						/> 
						<input name="confidence" type="radio" value="3" class="star" 
							<c:if test="${env.confidence==3 }">checked</c:if>
						/> 
						<input name="confidence" type="radio" value="4" class="star" 
							<c:if test="${env.confidence==4 }">checked</c:if>
						/> 
						<input name="confidence" type="radio" value="5" class="star" 
							<c:if test="${env.confidence==5 }">checked</c:if>
						/>
					</td>
				</tr>
				<tr>
					<td>14.</td>
					<td width="80%">It feels the same that inspecting the t-shirt 
					in the environment is just as if you were in a real store and
					had a real t-shirt in hand. </td>
					<td><input name="presence" type="radio" value="1" class="star" 
							<c:if test="${env.presence==1 }">checked</c:if>
						/>
						<input name="presence" type="radio" value="2" class="star" 
							<c:if test="${env.presence==2 }">checked</c:if>
						/> 
						<input name="presence" type="radio" value="3" class="star" 
							<c:if test="${env.presence==3 }">checked</c:if>
						/> 
						<input name="presence" type="radio" value="4" class="star" 
							<c:if test="${env.presence==4 }">checked</c:if>
						/> 
						<input name="presence" type="radio" value="5" class="star" 
							<c:if test="${env.presence==5 }">checked</c:if>
						/>
					</td>
				</tr>
				<tr>
					<td>15.</td>
					<td width="80%">You are comfortable to give ratings in the tested environment.</td>
					<td><input name="comfort" type="radio" value="1" class="star" 
							<c:if test="${env.comfort==1 }">checked</c:if>
						/>
						<input name="comfort" type="radio" value="2" class="star" 
							<c:if test="${env.comfort==2 }">checked</c:if>
						/> 
						<input name="comfort" type="radio" value="3" class="star" 
							<c:if test="${env.comfort==3 }">checked</c:if>
						/> 
						<input name="comfort" type="radio" value="4" class="star" 
							<c:if test="${env.comfort==4 }">checked</c:if>
						/> 
						<input name="comfort" type="radio" value="5" class="star" 
							<c:if test="${env.comfort==5 }">checked</c:if>
						/>
					</td>
				</tr>
				<tr>
					<td>16.</td>
					<td width="80%">You are (not) confident in your ratings (Q13) because </td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3">
						<textarea name="reasons" cols="50" rows="7">${env.reasons }</textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="Complete User Study" class="submit2">
		</form>
	</div>

</body>
</html>
