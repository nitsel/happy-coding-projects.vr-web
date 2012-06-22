<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="tee" class="org.felix.db.Tee80s" scope="request" />
<jsp:useBean id="rating" class="org.felix.db.Tee80sRating" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>T-Shirt Details</title>
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
<script type="text/javascript" src='js/jquery.scrollTo-min.js'></script>
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
			    var url = "./t-shirt?action=rating&rating="+rating+"&teeId=${tee.id }&comments="+$("#comments").val();
				$.get(url, function(data){
						$(resultSelector).html("&nbsp;"+data);
				});
			}
			return false;
		}
		
		function init()
		{
			var rate=${rating.rating};
			if(rate>0){
				$('#ratingForm>input').rating('select', rate+'');
			}			
			
			var averageSelector = 'form#averageForm>input';
			var r=Math.floor(${tee.avgRating}/0.5)*0.5;
			$(averageSelector).rating('enable');
			$(averageSelector).rating('select', r+'');
			$(averageSelector).rating('disable');
			
			var averageSelector2 = 'form#averageForm2>input';
			$(averageSelector2).rating('enable');
			$(averageSelector2).rating('select', r+'');
			$(averageSelector2).rating('disable');
			
			var page = $("#page").val();
			if(page>0){	$.scrollTo('.entry2', 0, {elasout:'elasout'} );	}
		}
		
		$(document).ready(init);
	</script>

</head>

<body>
	<div class="entry">
		<div class="float-info">
			<h2>T-Shirt Info</h2>
			<ul class="ul-properties">
				<li><span>Name: </span><a style="text-transform: capitalize"
					name="#">${tee.name}</a>
				</li>
				<li><span>Price:</span>${tee.price }</li>
				<li><span>Faboric Details:</span>
					<ul>
						<li>${tee.features}</li>
					</ul></li>
				<li><span>Description:</strong> ${tee.description}
				</li>
			</ul>
		</div>
		<div class="float-rating">
			<h2>Rate This T-Shirt</h2>
			<div id="result" style="margin: 5px 0px 10px 0px;"></div>
			<form id="ratingForm" action="#" method="get"
				onSubmit="return submit_rating()">
				<input name="star1" type="radio" value="1" class="star" title="Very poor" />
				<input name="star1" type="radio" value="2" class="star" title="Poor" />
				<input name="star1" type="radio" value="3" class="star" title="Fine" />
				<input name="star1" type="radio" value="4" class="star" title="Good" />
				<input name="star1" type="radio" value="5" class="star"
					title="Very Good" /><br />
				<input id="page"  type="hidden" value="${param.page }" />
				<p>
					Comments&nbsp;&nbsp;(optional):
					<textarea id="comments" name="comments" cols="43" rows="5">${rating.comments }</textarea>
				</p>
				<input type="submit" value="Submit Rating" />
			</form>
		</div>
	</div>

	<div class="entry">
		<h2>Overall Review</h2>
		<div> 
			<div class="averageRating">Average Rating:</div>
			<div class="stars">
				<form id="averageForm2">
					<input name="star3" type="radio" class="star {split:2}" value="0.5"
						disabled="disabled" /> <input name="star3" type="radio"
						class="star {split:2}" value="1" disabled="disabled" /> <input
						name="star3" type="radio" class="star {split:2}" value="1.5"
						disabled="disabled" /> <input name="star3" type="radio"
						class="star {split:2}" value="2" disabled="disabled" /> <input
						name="star3" type="radio" class="star {split:2}" value="2.5"
						disabled="disabled" /> <input name="star3" type="radio"
						class="star {split:2}" value="3" disabled="disabled" /> <input
						name="star3" type="radio" class="star {split:2}" value="3.5"
						disabled="disabled" /> <input name="star3" type="radio"
						class="star {split:2}" value="4" disabled="disabled" /> <input
						name="star3" type="radio" class="star {split:2}" value="4.5"
						disabled="disabled" /> <input name="star3" type="radio"
						class="star {split:2}" value="5" disabled="disabled" />
				</form>
				<span class="block-rating">${tee.avgRating}</span>(Based on
				${tee.numRating} Reviews)
			</div>
			<p>
				<span class="block-reco">${tee.recommendation}</span>of respondents
				would recommend this to a friend.
				<table>
					<tr>
						<th>Pros</th>
						<th>Cons</th>
						<th>Best Uses</th>
					</tr>
					<tr>
						<td>${tee.pros }</td>
						<td>${tee.cons }</td>
						<td>${tee.bestUses }</td>
					</tr>
				</table>
				<ul class="ul-properties">
					<c:if test="${!empty tee.reviewerProfile}">
						<li><span>Reviewer Profile:</span>${tee.reviewerProfile}</li>
					</c:if>
					<c:if test="${!empty tee.gift }">
						<li><span>Is it a gift?:</span>${tee.gift}</li>
					</c:if>
				</ul>
			</p>
		</div>
	</div>

	<div class="entry2">
		<h2>Customer Reviews</h2>
		<p><strong>Reviewed by ${tee.numRating} Customers</strong>
			<span class="pages"><c:forEach var="page" begin="1" end="${tee.numRating/10 +1}">
				<a href="t-shirt?action=info&teeId=${tee.id }&page=${page }">
				<span
					<c:choose>
						<c:when test="${requestScope.page==page }">class="block-reco"</c:when>
						<c:otherwise>class="block-rating"</c:otherwise>
					</c:choose>
				>${page}</span></a>
			</c:forEach></span>
		</p>

		<c:forEach var="r" items="${requestScope.reviews}">
			<div class="entry">
				<div>
					<form action="#">
						<input name="star4" type="radio" class="star" value="1"
							disabled="disabled"
							<c:if test="${r.rating==1}">checked="checked"</c:if> /> <input
							name="star4" type="radio" class="star" value="2"
							disabled="disabled"
							<c:if test="${r.rating==2}">checked="checked"</c:if> /> <input
							name="star4" type="radio" class="star" value="3"
							disabled="disabled"
							<c:if test="${r.rating==3}">checked="checked"</c:if> /> <input
							name="star4" type="radio" class="star" value="4"
							disabled="disabled"
							<c:if test="${r.rating==4}">checked="checked"</c:if> /> <input
							name="star4" type="radio" class="star" value="5"
							disabled="disabled"
							<c:if test="${r.rating==5}">checked="checked"</c:if> />
					</form>
					<span class="block-rating">${r.rating}</span> <strong>${r.title}</strong><span
						class="vDate">${r.vDate }</span> <br />
				</div>
				<ul class="ul-properties">
					<li>By <strong>${r.userName}</strong> from <strong>${r.userLocation}</strong>
					</li>
					<li>About Me <strong>${r.tags}</strong>
					</li>
					<li>
						<table>
							<tr>
								<th>Pros</th>
								<th>Cons</th>
								<th>Best Uses</th>
							</tr>
							<tr>
								<td>${r.pros }</td>
								<td>${r.cons }</td>
								<td>${r.bestUses }</td>
							</tr>
						</table></li>
					<c:if test="${!empty r.comments }">
						<li><span>Comments about <em>${tee.name}</em>
						</span><br /> ${r.comments }</li>
					</c:if>
					<c:if test="${!empty r.services }">
						<li><span>Service and delivery comments</span><br />
							${r.services }</li>
					</c:if>
					<c:if test="${!empty r.fit }">
						<li><span>Fit:</span>${r.fit}</li>
					</c:if>
					<c:if test="${!empty r.length }">
						<li><span>Length:</span>${r.length}</li>
					</c:if>
					<li><span>Bottom Line:</span>${r.recommendation}</li>
				</ul>
			</div>
		</c:forEach>
		
		<p><strong>Reviewed by ${tee.numRating} Customers</strong>
			<span class="pages"><c:forEach var="page" begin="1" end="${tee.numRating/10 +1}">
				<a href="t-shirt?action=info&teeId=${tee.id }&page=${page }">
				<span
					<c:choose>
						<c:when test="${requestScope.page==page }">class="block-reco"</c:when>
						<c:otherwise>class="block-rating"</c:otherwise>
					</c:choose>
				>${page}</span></a>
			</c:forEach></span>
		</p>
	</div>

</body>
</html>
