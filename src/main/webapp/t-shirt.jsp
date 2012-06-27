<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="tee" class="org.felix.db.Tee80s" scope="request" />
<jsp:useBean id="rating" class="org.felix.db.Tee80sRating"
	scope="request" />

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
		var rated;
		function getRating(selector){
			var stars=$(selector);
			var rating=0;
			for(var i=0; i<stars.length; i++)
			{
				if(stars[i].checked){
					rating=stars[i].value;
					break;
				}
			}
			return rating; // if not rated, return 0. 
		}
		
		function submit_rating(){
			var overall_rating = getRating('input[name="star1"]');
			var rating2 = getRating('input[name="star12"]');
			var rating3 = getRating('input[name="star13"]');
			var rating4 = getRating('input[name="star14"]');
			var rating5 = getRating('input[name="star15"]');
			var rating6 = getRating('input[name="star16"]');
			var rating7 = getRating('input[name="star17"]');
			var rating8 = getRating('input[name="star18"]');
			var rating9 = getRating('input[name="star19"]');
			var rating10 = getRating('input[name="star110"]');
			var rating11 = getRating('input[name="star111"]');
			var rating12 = getRating('input[name="star112"]');
			
			if(overall_rating==0 || rating2==0 ||rating3==0 ||rating4==0 ||rating5==0 ||rating6==0 ||rating7==0 ||rating8==0 ||rating9==0 ||rating10==0 ||rating11==0 ||rating12==0) {
				alert("Rate the t-shirt before submitting your rating!");
				return false;
			}
			
			var toggle=false;
			if(toggle)
			{
				$('#result').html("Your rating "+rating+" is saved!");
			}else
			{
			    //var url = "./t-shirt?action=rating&rating="+rating+"&teeId=${tee.id }&comments="+$("#comments").val();
			    var url = "./userStudy?action=rating";
				$.post(url, {rating: overall_rating, teeId: "${tee.id}", comments: $("#comments").val()}, function(data){
						$('#result').html("&nbsp;"+data);
				});
				
				rated=true;
			}
			return false;
		}
		
		function check_rating()
		{
			if(!rated)
			{
				alert('Please rate this t-shirt first before proceeding to the next.');
				return false;
			}
			return true;
		}
		
		function init()
		{
			var rate=${rating.rating};
			if(rate>0){
				$('input[name="star1"]').rating('select', rate+'');
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
		<p>
			<strong>User Study (<span style="color: red;">${sessionScope.userId }</span>) Current Progress: </strong> 
				<span class="pages">
				<c:forEach var="progress" begin="1" end="${sessionScope.maxProgress}">
					<c:if test="${progress<=sessionScope.progress }">
						<a href="./userStudy?action=info&teeId=${sessionScope.vTees[progress] }">
					</c:if>
					<c:if test="${progress==sessionScope.progress+1 }">
						<a href="./userStudy?action=info&survey=next" onclick="return check_rating()">
					</c:if>
						<span
						<c:choose>
							<c:when test="${progress<=sessionScope.progress }">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress+1 }">class="block-rating"</c:when>
							<c:otherwise>class="block-gray"</c:otherwise>
						</c:choose>>${progress}</span>
					<c:if test="${progress<=sessionScope.progress+1 }">
						</a>
					</c:if>
				</c:forEach> 
				</span>
		</p>
		<h2>T-Shirt Info</h2>
		<img alt="T-shirt Image" src="./Htmls/${tee.image }"
			class="float-image" />
		<ul class="ul-properties">
			<li><span>Name: </span><a style="text-transform: capitalize"
				name="#">${tee.name}</a></li>
			<li><span>Category:</span>${tee.category }</li>
			<li><span>Price:</span>${tee.price }</li>
			<li><span>Faboric Details:</span>
				<ul>
					<li>${tee.features}</li>
				</ul></li>
		</ul>
		<ul class="ul-properties">
			<li><strong>&nbsp;&nbsp;Sizes:</strong>
				<ul>
					<li>${tee.sizes}</li>
				</ul></li>
		</ul>
		<div style="clear: both">
			<strong>Description:</strong> ${tee.description}
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
			</p>
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
		</div>
	</div>

	<div class="entry">
		<h2>Rate This T-Shirt</h2>
		<p>To what extent do you agree or disagree with each of the
			following statements? (from one star to five stars):</p>
		<ol>
			<li>Strongly Disagree</li>
			<li>Disagree</li>
			<li>Slightly Disagree or Slightly Agree</li>
			<li>Agree</li>
			<li>Strongly Agree</li>
		</ol>
		<form id="ratingForm" action="#" method="post"
			onSubmit="return submit_rating() && false;">
			<input id="page" type="hidden" value="${param.page }" />
			<table class="questions">
				<tr>
					<td>01.</td>
					<td width="80%">Overall, you like this t-shirt.</td>
					<td><input name="star1" type="radio" value="1" class="star" />
						<input name="star1" type="radio" value="2" class="star" /> <input
						name="star1" type="radio" value="3" class="star" /> <input
						name="star1" type="radio" value="4" class="star" /> <input
						name="star1" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>02.</td>
					<td width="80%">The T-Shirt has a good looking in terms of
						color, patterns, style, etc.</td>
					<td><input name="star12" type="radio" value="1" class="star" />
						<input name="star12" type="radio" value="2" class="star" /> <input
						name="star12" type="radio" value="3" class="star" /> <input
						name="star12" type="radio" value="4" class="star" /> <input
						name="star12" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>03.</td>
					<td width="80%">The t-shirt is made of good material.</td>
					<td><input name="star13" type="radio" value="1" class="star" />
						<input name="star13" type="radio" value="2" class="star" /> <input
						name="star13" type="radio" value="3" class="star" /> <input
						name="star13" type="radio" value="4" class="star" /> <input
						name="star13" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>04.</td>
					<td width="80%">The t-shirt fits you well.</td>
					<td><input name="star14" type="radio" value="1" class="star" />
						<input name="star14" type="radio" value="2" class="star" /> <input
						name="star14" type="radio" value="3" class="star" /> <input
						name="star14" type="radio" value="4" class="star" /> <input
						name="star14" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>05.</td>
					<td width="80%">The category of the t-shirt is of your favor.</td>
					<td><input name="star15" type="radio" value="1" class="star" />
						<input name="star15" type="radio" value="2" class="star" /> <input
						name="star15" type="radio" value="3" class="star" /> <input
						name="star15" type="radio" value="4" class="star" /> <input
						name="star15" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>06.</td>
					<td width="80%">The price of the t-shirt is acceptable,
						including base price, tax and shipping fees.</td>
					<td><input name="star16" type="radio" value="1" class="star" />
						<input name="star16" type="radio" value="2" class="star" /> <input
						name="star16" type="radio" value="3" class="star" /> <input
						name="star16" type="radio" value="4" class="star" /> <input
						name="star16" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>07.</td>
					<td width="80%">The brand is reputable.</td>
					<td><input name="star17" type="radio" value="1" class="star" />
						<input name="star17" type="radio" value="2" class="star" /> <input
						name="star17" type="radio" value="3" class="star" /> <input
						name="star17" type="radio" value="4" class="star" /> <input
						name="star17" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>08.</td>
					<td width="80%">The virtual store is well-designed.</td>
					<td><input name="star18" type="radio" value="1" class="star" />
						<input name="star18" type="radio" value="2" class="star" /> <input
						name="star18" type="radio" value="3" class="star" /> <input
						name="star18" type="radio" value="4" class="star" /> <input
						name="star18" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>09.</td>
					<td width="80%">The shipping is convenient.</td>
					<td><input name="star19" type="radio" value="1" class="star" />
						<input name="star19" type="radio" value="2" class="star" /> <input
						name="star19" type="radio" value="3" class="star" /> <input
						name="star19" type="radio" value="4" class="star" /> <input
						name="star19" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>10.</td>
					<td width="80%">In total, the quality of the t-shirt is good.
					</td>
					<td><input name="star110" type="radio" value="1" class="star" />
						<input name="star110" type="radio" value="2" class="star" /> <input
						name="star110" type="radio" value="3" class="star" /> <input
						name="star110" type="radio" value="4" class="star" /> <input
						name="star110" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>11.</td>
					<td width="80%">In total, you need to spend a lot to obtain
						the product in terms of price, time, effort, etc.</td>
					<td><input name="star111" type="radio" value="1" class="star" />
						<input name="star111" type="radio" value="2" class="star" /> <input
						name="star111" type="radio" value="3" class="star" /> <input
						name="star111" type="radio" value="4" class="star" /> <input
						name="star111" type="radio" value="5" class="star" /></td>
				</tr>
				<tr>
					<td>12.</td>
					<td width="80%">In total, the t-shirt is worthy purchasing.</td>
					<td><input name="star112" type="radio" value="1" class="star" />
						<input name="star112" type="radio" value="2" class="star" /> <input
						name="star112" type="radio" value="3" class="star" /> <input
						name="star112" type="radio" value="4" class="star" /> <input
						name="star112" type="radio" value="5" class="star" /></td>
				</tr>
			</table>
			<p>
				Comments&nbsp;&nbsp;(optional): <br />
				<textarea id="comments" name="comments" cols="50" rows="7">${rating.comments }</textarea>
			</p>
			<input type="submit" value="Submit Rating" /><span id="result"
				style="margin: 5px 0px 10px 20px; color: red;"></span>
		</form>
	</div>

	<div class="entry2">
		<h2>Customer Reviews</h2>
		<p>
			<strong>Reviewed by ${tee.numRating} Customers</strong> <span
				class="pages"><c:forEach var="page" begin="1"
					end="${tee.numRating/10 +1}">
					<a href="userStudy?action=info&teeId=${tee.id }&page=${page }">
						<span
						<c:choose>
						<c:when test="${requestScope.page==page }">class="block-reco"</c:when>
						<c:otherwise>class="block-rating"</c:otherwise>
					</c:choose>>${page}</span>
					</a>
				</c:forEach> </span>
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
					<li>About Me <strong>${r.tags}</strong></li>
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
						</table>
					</li>
					<c:if test="${!empty r.comments }">
						<li><span>Comments about <em>${tee.name}</em> </span><br />
							${r.comments }</li>
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

		<p>
			<strong>Reviewed by ${tee.numRating} Customers</strong> <span
				class="pages"><c:forEach var="page" begin="1"
					end="${tee.numRating/10 +1}">
					<a href="userStudy?action=info&teeId=${tee.id }&page=${page }">
						<span
						<c:choose>
						<c:when test="${requestScope.page==page }">class="block-reco"</c:when>
						<c:otherwise>class="block-rating"</c:otherwise>
					</c:choose>>${page}</span>
					</a>
				</c:forEach> </span>
		</p>
	</div>

</body>
</html>
