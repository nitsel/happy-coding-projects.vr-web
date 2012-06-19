<%@ page isELIgnored ="false" %>
<%@ page import="org.felix.db.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="tee" class="org.felix.db.Tee80s" scope="request" />
<% List<Tee80sReview> rs= (List<Tee80sReview>)request.getAttribute("reviews"); %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>T-Shirt Details</title>
	<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
	<link rel="stylesheet" type="text/css"  href='js/jquery.rating.css' />
	<script type="text/javascript" language="javascript" src='js/jquery.js'></script>	
	<script type="text/javascript" language="javascript" src='js/jquery.MetaData.js'></script>
	<script type="text/javascript" language="javascript" src='js/jquery.rating.js'></script>
	<script>
		var ratingSelector='form#ratingForm>input.star';
		var resultSelector='div#result';
		var productId, userId;
		
		function submit_rating(){
			var stars=$(ratingSelector);
			var rating;
			var rated=false;
			for(var i=0; i<stars.length; i++)
			{
				if(stars[i].checked){
					rating=stars[i].value;
					rated=true;
					break;
				}
			}
			
			if(!rated) {
				alert("Rate the t-shirt before submitting your rating!");
				return false;
			}
			
			var toggle=false;
			if(toggle)
			{
				$(resultSelector).html("Your rating "+rating+" is saved!");
			}else
			{
			    var url = "./t-shirt?action=rating&rating="+rating+"&teeId="+$("input#tid").val()+"&userId="+$("input#uid").val();
			    // alert(url);
				$.get(url, function(data){
						$(resultSelector).html("&nbsp;"+data);
				});
			}
			return false;
		}
		
		function init()
		{
			//$('#testform>input[value="3"]').checked="checked";
			var averageSelector = 'form#averageForm>input';
			var r=Math.floor(${tee.avgRating}/0.5)*0.5;
			$(averageSelector).rating('enable');
			$(averageSelector).rating('select', r+'');
			$(averageSelector).rating('disable');
			
			var averageSelector2 = 'form#averageForm2>input';
			$(averageSelector2).rating('enable');
			$(averageSelector2).rating('select', r+'');
			$(averageSelector2).rating('disable');
			
			//set default rating for you.
			//$(ratingSelector).rating('select', 3+'');
		}
		
		$(document).ready(init);
	</script>

</head>

<body>
<div class="entry">
	<div class="float-info">
		<h2>${tee.name}</h2>
		<div class="averageRating">Average Rating:</div>
		<div>
			<form id="averageForm">
				<input name="star2" type="radio" class="star {split:2}" value="0.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="1" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="1.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="2" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="2.5" disabled="disabled"/> 
				<input name="star2" type="radio" class="star {split:2}" value="30" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="3.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="4" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="4.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="5" disabled="disabled"/> 
				<span style="margin:0px 0px 0px 5px;">${tee.avgRating}/5 (Based on ${tee.numRating} Reviews)</span>
			</form>
		</div>
		<p><strong>Price:</strong> ${tee.price } </p>
		<table>
			<tr><th>Size</th><th>In Stock</th></tr>
			<tr><td>Small</td><td>Yes</td></tr>
			<tr><td>Middle</td><td>Yes</td></tr>
			<tr><td>Large</td><td>Yes</td></tr>
			<tr><td>XL</td><td>Yes</td></tr>
		</table>
	</div>
	<div class="float-rating">
		<h2>Rate This T-Shirt</h2>
		<form id="ratingForm" action="#" method="get" onSubmit="return submit_rating()">
			<input id="tid" name="teeId" type="hidden" value="${tee.id }"/>
			<input id="uid" name="userId", type="hidden" value="guoguibing"/>
			<input name="star1" type="radio" value="1" class="star" title="Very poor"/>
			<input name="star1" type="radio" value="2" class="star" title="Poor"/>
			<input name="star1" type="radio" value="3" class="star" title="Fine"/>
			<input name="star1" type="radio" value="4" class="star" title="Good"/>
			<input name="star1" type="radio" value="5" class="star" title="Very Good"/>
			<br/>
			<div id="result" style="margin:5px 0px 10px 0px;">&nbsp;</div>
			<input type="submit" value="Submit Rating"/>
		</form>
	</div>
	<div style="clear:both"></div>
</div>

<div class="entry">
	<h2>T-Shirt Description</h2>
	<p class="p-review">${tee.description}</p>
</div>

<div class="entry">
	<h2>Customer Reviews</h2>
	<div>	 
		<div class="averageRating">Average Rating (${tee.avgRating}/5):</div>
		<div>
			<form id="averageForm2">
			<input name="star3" type="radio" class="star {split:2}" value="0.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="1" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="1.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="2" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="2.5" disabled="disabled"/> 
			<input name="star3" type="radio" class="star {split:2}" value="3" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="3.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="4" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="4.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="5" disabled="disabled"/> 
			</form>
		</div>
	</div>
	<div style="clear:both"></div>
	<p>Hello: ? <c:if test="1==1"><c:out value="true"></c:out>true</c:if>, separate <c:if test="2>1">false</c:if></p>

<c:forEach var="r" items="${rs}">
	<div class="review">
		<div>
			<form action="#">
			<input name="star4" type="radio" class="star" value="1" disabled="disabled"
				<c:if test="${r.rating==1}">checked="checked"</c:if>
			 />
			<input name="star4" type="radio" class="star" value="2" disabled="disabled" <c:if test="${r.rating==2}">checked="checked"</c:if>/>
			<input name="star4" type="radio" class="star" value="3" disabled="disabled" <c:if test="${r.rating==3}">checked="checked"</c:if>/>
			<input name="star4" type="radio" class="star" value="4" disabled="disabled" <c:if test="${r.rating==4}">checked="checked"</c:if>/>
			<input name="star4" type="radio" class="star" value="5" disabled="disabled" <c:if test="${r.rating==5}">checked="checked"</c:if>/> 
			&nbsp;&nbsp; ${r.rDate } <br/>
			${r.recommendation }
			</form>
		</div>
	</div>
</c:forEach>
</div>

</body>
</html>
