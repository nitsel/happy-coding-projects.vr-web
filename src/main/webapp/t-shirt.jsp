<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="tee" class="org.felix.db.Tee" scope="request" />
<jsp:useBean id="rating" class="org.felix.db.VirtualRating"
	scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - T-Shirt</title>
<link rel="shortcut icon" href="img/users.ico" />
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
			var rating;
			for(var i=0; i<stars.length; i++)
			{
				if(stars[i].checked){
					rating=stars[i].value;
					break;
				}
			}
			return rating;
		}
		
		function submit_rating(){
			var overall = getRating('input[name="overall"]');
			var appearance = getRating('input[name="appearance"]');
			var material = getRating('input[name="material"]');
			var fit = getRating('input[name="fit"]');
			var category = getRating('input[name="category"]');
			var price = getRating('input[name="price"]');
			var brand = getRating('input[name="brand"]');
			var store = getRating('input[name="store"]');
			var shipping = getRating('input[name="shipping"]');
			var quality = getRating('input[name="quality"]');
			var cost = getRating('input[name="cost"]');
			var value = getRating('input[name="value"]');
			
			if(!overall ||!appearance ||!material ||!fit ||!category ||!price ||!brand ||!store ||!shipping ||!quality ||!cost ||!value) {
				alert("Rate the t-shirt before submitting your rating!");
				return false;
			}
			
		    var url = "./userStudy?action=rating";
			$.post(url, 
					{overall: overall, 
					 appearance: appearance, 
					 material: material,
					 fit: fit,
					 category: category,
					 price: price,
					 brand: brand,
					 store: store,
					 shipping: shipping,
					 quality: quality,
					 cost: cost,
					 value: value,
					 teeId: "${tee.id}", 
					 comments: $("#comments").val()
					}, 
				function(data){
					$('#result').html("&nbsp;"+data);
				}
			);
			
			rated=true;
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
		
		// scale image proportionally
		var flag=false; 
		function DrawImage(ImgD,iwidth,iheight){ 
		    var image=new Image(); 
		    image.src=ImgD.src; 
		    if(image.width>0 && image.height>0){ 
		    flag=true; 
		    if(image.width/image.height>= iwidth/iheight){ 
		        if(image.width>iwidth){   
		        ImgD.width=iwidth; 
		        ImgD.height=(image.height*iwidth)/image.width; 
		        }else{ 
		        ImgD.width=image.width;   
		        ImgD.height=image.height; 
		        } 
		        ImgD.alt=image.width+"×"+image.height; 
		        } 
			else{ 
		        if(image.height>iheight){   
		        ImgD.height=iheight; 
		        ImgD.width=(image.width*iheight)/image.height;         
		        }else{ 
		        ImgD.width=image.width;   
		        ImgD.height=image.height; 
		        } 
		        ImgD.alt=image.width+"×"+image.height; 
		        } 
		    } 
		} 
		
		function init()
		{
			var rate=${rating.overall};
			var website=${sessionScope.environment eq 'web site'};
			if(rate){$(
				'input[name="overall"]').rating('select', rate+'');
				if(website) rated=true;
			}
			
			if((rate=${rating.appearance}))$('input[name="appearance"]').rating('select', rate+'');
			if((rate=${rating.material}))$('input[name="material"]').rating('select', rate+'');
			if((rate=${rating.fit}))$('input[name="fit"]').rating('select', rate+'');
			if((rate=${rating.category}))$('input[name="category"]').rating('select', rate+'');
			if((rate=${rating.price}))$('input[name="price"]').rating('select', rate+'');
			if((rate=${rating.brand}))$('input[name="brand"]').rating('select', rate+'');
			if((rate=${rating.store}))$('input[name="store"]').rating('select', rate+'');
			if((rate=${rating.shipping}))$('input[name="shipping"]').rating('select', rate+'');
			if((rate=${rating.quality}))$('input[name="quality"]').rating('select', rate+'');
			if((rate=${rating.cost}))$('input[name="cost"]').rating('select', rate+'');
			if((rate=${rating.value}))$('input[name="value"]').rating('select', rate+'');
			
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
		<%-- 	<p>Session.progress=${sessionScope.progress }, Session.vrProgress=${sessionScope.vrProgress}, Session.step=${sessionScope.step }</p> --%>
		<p>
			<strong>Current Progress (<span style="color: red;">${sessionScope.userId
					}@${sessionScope.environment }</span>)
			</strong> <span class="pages"> <c:forEach var="progress" begin="1"
					end="${sessionScope.maxProgress+1}">
					<c:if test="${sessionScope.environment eq 'web site' }">
						<c:choose>
							<c:when test="${progress<=sessionScope.progress }">
								<a
									href="./userStudy?action=info&teeId=${sessionScope.vTees[progress] }">
							</c:when>
							<c:when
								test="${sessionScope.progress==sessionScope.maxProgress }">
								<a href="./userStudy?action=env" onclick="return check_rating()"
									title="To Last Part of User Study">
							</c:when>
							<c:when test="${progress==sessionScope.progress+1}">
								<a href="./userStudy?action=info&survey=next"
									onclick="return check_rating()">
							</c:when>
						</c:choose>
					</c:if>
					<c:if
						test="${progress==sessionScope.maxProgress+1 
							&& sessionScope.vrProgress>=sessionScope.maxProgress
							&& sessionScope.environment eq 'virtual reality' }">
						<a href="./userStudy?action=env" onclick="return check_rating()"
							title="To Last Part of User Study">
					</c:if>
					<span
						<c:choose>
							<c:when test="${progress<sessionScope.progress 
											&& sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress 
											&& sessionScope.step>=2
											&& sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress && sessionScope.environment eq 'web site'}">class="block-red"</c:when>
							<c:when test="${progress==sessionScope.progress+1 
											&& sessionScope.step==3
											&& sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress+1 
											&& sessionScope.step==2
											&& sessionScope.environment eq 'web site'}">class="block-red"</c:when>
							<c:when test="${progress==sessionScope.progress+1 && sessionScope.environment eq 'web site'}">class="block-rating"</c:when>
							<c:when test="${progress<sessionScope.vrProgress && sessionScope.environment eq 'virtual reality'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.vrProgress && sessionScope.environment eq 'virtual reality'}">class="block-red"</c:when>
							<c:when test="${progress==sessionScope.vrProgress+1 && sessionScope.environment eq 'virtual reality'}">class="block-rating"</c:when>
							<c:otherwise>class="block-gray"</c:otherwise>
						</c:choose>>
						<c:choose>
							<c:when test="${progress==sessionScope.maxProgress+1 }">>></c:when>
							<c:otherwise>${progress}</c:otherwise>
						</c:choose>
					</span>
					<c:if
						test="${progress<=sessionScope.progress+1 && sessionScope.environment eq 'web site'}">
						</a>
					</c:if>
					<c:if
						test="${progress==sessionScope.maxProgress+1 
							&& sessionScope.vrProgress>=sessionScope.maxProgress
							&& sessionScope.environment eq 'virtual reality' }">
						</a>
					</c:if>
				</c:forEach>
			</span>
		</p>
		<h2>T-Shirt Info</h2>
		<img alt="T-shirt Image" src="./Htmls/${tee.image }"
			class="float-image" onload="javascript:DrawImage(this, 200, 280)" />
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
			following statements?
		<ul class="stars-list">
			<li style="list-style-image: url(img/star1.PNG);">Strongly
				Disagree</li>
			<li style="list-style-image: url(img/star2.PNG)">Disagree</li>
			<li style="list-style-image: url(img/star3.PNG)">Slightly
				Disagree or Slightly Agree</li>
			<li style="list-style-image: url(img/star4.PNG)">Agree</li>
			<li style="list-style-image: url(img/star5.PNG)">Strongly Agree</li>
		</ul>
		</p>
		<form id="ratingForm" action="#" method="post"
			onsubmit="return submit_rating()">
			<input id="page" type="hidden" value="${param.page }" />
			<table class="questions">
				<tr>
					<td>01.</td>
					<td width="80%">Overall, you like this t-shirt.</td>
					<td><input name="overall" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="overall" type="radio"
						value="2" class="star" title="Disagree" /> <input name="overall"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="overall" type="radio" value="4" class="star" title="Agree" />
						<input name="overall" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>02.</td>
					<td width="80%">This t-shirt has a good looking <br /> in
						terms of color, patterns, style, etc.
					</td>
					<td><input name="appearance" type="radio" value="1"
						class="star" title="Strongly Disagree" /> <input
						name="appearance" type="radio" value="2" class="star"
						title="Disagree" /> <input name="appearance" type="radio"
						value="3" class="star" title="Slightly Disagree or Slightly Agree" />
						<input name="appearance" type="radio" value="4" class="star"
						title="Agree" /> <input name="appearance" type="radio" value="5"
						class="star" title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>03.</td>
					<td width="80%">This t-shirt is made of good material.</td>
					<td><input name="material" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="material" type="radio"
						value="2" class="star" title="Disagree" /> <input name="material"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="material" type="radio" value="4" class="star" title="Agree" />
						<input name="material" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>04.</td>
					<td width="80%">This t-shirt fits you well.</td>
					<td><input name="fit" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="fit" type="radio"
						value="2" class="star" title="Disagree" /> <input name="fit"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input name="fit"
						type="radio" value="4" class="star" title="Agree" /> <input
						name="fit" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>05.</td>
					<td width="80%">The category of this t-shirt is of your favor.</td>
					<td><input name="category" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="category" type="radio"
						value="2" class="star" title="Disagree" /> <input name="category"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="category" type="radio" value="4" class="star" title="Agree" />
						<input name="category" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>06.</td>
					<td width="80%">The price of this t-shirt is acceptable, <br />
						including base price, tax and shipping fees.
					</td>
					<td><input name="price" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="price" type="radio"
						value="2" class="star" title="Disagree" /> <input name="price"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="price" type="radio" value="4" class="star" title="Agree" />
						<input name="price" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>07.</td>
					<td width="80%">The brand is reputable.</td>
					<td><input name="brand" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="brand" type="radio"
						value="2" class="star" title="Disagree" /> <input name="brand"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="brand" type="radio" value="4" class="star" title="Agree" />
						<input name="brand" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>08.</td>
					<td width="80%">The web site (or virtual store) is well-designed.</td>
					<td><input name="store" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="store" type="radio"
						value="2" class="star" title="Disagree" /> <input name="store"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="store" type="radio" value="4" class="star" title="Agree" />
						<input name="store" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>09.</td>
					<td width="80%">The shipping is convenient.</td>
					<td><input name="shipping" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="shipping" type="radio"
						value="2" class="star" title="Disagree" /> <input name="shipping"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="shipping" type="radio" value="4" class="star" title="Agree" />
						<input name="shipping" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>10.</td>
					<td width="80%">In total, the quality of this t-shirt is good.
					</td>
					<td><input name="quality" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="quality" type="radio"
						value="2" class="star" title="Disagree" /> <input name="quality"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="quality" type="radio" value="4" class="star" title="Agree" />
						<input name="quality" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>11.</td>
					<td width="80%">In total, you need to spend a lot to obtain
						this t-shirt <br /> in terms of price, time, effort, etc.
					</td>
					<td><input name="cost" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="cost" type="radio"
						value="2" class="star" title="Disagree" /> <input name="cost"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input name="cost"
						type="radio" value="4" class="star" title="Agree" /> <input
						name="cost" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
				<tr>
					<td>12.</td>
					<td width="80%">In total, this t-shirt is worthy purchasing.</td>
					<td><input name="value" type="radio" value="1" class="star"
						title="Strongly Disagree" /> <input name="value" type="radio"
						value="2" class="star" title="Disagree" /> <input name="value"
						type="radio" value="3" class="star"
						title="Slightly Disagree or Slightly Agree" /> <input
						name="value" type="radio" value="4" class="star" title="Agree" />
						<input name="value" type="radio" value="5" class="star"
						title="Strongly Agree" /></td>
				</tr>
			</table>
			<p>
				Comments&nbsp;&nbsp;(optional): <br />
				<textarea id="comments" name="comments" cols="50" rows="7">${rating.comments }</textarea>
			</p>
			<p>
			<c:out value="${requsetScope.rating }"></c:out>
				<input type="submit" value="Submit Ratings" class="submit" 
				<c:if test="${(sessionScope.environment=='virtual reality') && (!empty rating.cDate) }">disabled="disabled"</c:if>/>
				<span id="result" style="margin: 5px 0px 10px 20px; color: red;"></span>
				<span class="pages"> <c:forEach var="progress" begin="1"
						end="${sessionScope.maxProgress+1}">
						<c:if test="${sessionScope.environment eq 'web site' }">
							<c:choose>
								<c:when test="${progress<=sessionScope.progress }">
									<a
										href="./userStudy?action=info&teeId=${sessionScope.vTees[progress] }">
								</c:when>
								<c:when
									test="${sessionScope.progress==sessionScope.maxProgress }">
									<a href="./userStudy?action=env"
										onclick="return check_rating()"
										title="To Last Part of User Study">
								</c:when>
								<c:when test="${progress==sessionScope.progress+1}">
									<a href="./userStudy?action=info&survey=next"
										onclick="return check_rating()">
								</c:when>
							</c:choose>
						</c:if>
						<c:if
							test="${progress==sessionScope.maxProgress+1 
							&& sessionScope.vrProgress>=sessionScope.maxProgress
							&& sessionScope.environment eq 'virtual reality' }">
							<a href="./userStudy?action=env" onclick="return check_rating()"
								title="To Last Part of User Study">
						</c:if>
						<span
							<c:choose>
							<c:when test="${progress<sessionScope.progress 
											&& sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress 
											&& sessionScope.step>=2
											&& sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress && sessionScope.environment eq 'web site'}">class="block-red"</c:when>
							<c:when test="${progress==sessionScope.progress+1 
											&& sessionScope.step==3
											&& sessionScope.environment eq 'web site'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.progress+1 
											&& sessionScope.step==2
											&& sessionScope.environment eq 'web site'}">class="block-red"</c:when>
							<c:when test="${progress==sessionScope.progress+1 && sessionScope.environment eq 'web site'}">class="block-rating"</c:when>
							<c:when test="${progress<sessionScope.vrProgress && sessionScope.environment eq 'virtual reality'}">class="block-reco"</c:when>
							<c:when test="${progress==sessionScope.vrProgress && sessionScope.environment eq 'virtual reality'}">class="block-red"</c:when>
							<c:when test="${progress==sessionScope.vrProgress+1 && sessionScope.environment eq 'virtual reality'}">class="block-rating"</c:when>
							<c:otherwise>class="block-gray"</c:otherwise>
						</c:choose>>
							<c:choose>
								<c:when test="${progress==sessionScope.maxProgress+1 }">>></c:when>
								<c:otherwise>${progress}</c:otherwise>
							</c:choose>
						</span>
						<c:if
							test="${progress<=sessionScope.progress+1 && sessionScope.environment eq 'web site'}">
							</a>
						</c:if>
						<c:if
							test="${progress==sessionScope.maxProgress+1 
							&& sessionScope.vrProgress>=sessionScope.maxProgress
							&& sessionScope.environment eq 'virtual reality' }">
							</a>
						</c:if>
					</c:forEach>
				</span>
			</p>
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
