<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="p" class="org.felix.db.PilotStudy" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>User Study - Pilot Study</title>
<link rel="shortcut icon" href="img/users.ico" />
<link rel="stylesheet" type="text/css" href="css/t-shirt.css" />
<link rel="stylesheet" type="text/css" href='js/jquery.rating.css' />
<script type="text/javascript" src='js/jquery.js'></script>
<script type="text/javascript" src='js/jquery.MetaData.js'></script>
<script type="text/javascript" src='js/jquery.rating.js'></script>
<script>
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
			var userId=$('input[name="userId"]').val();
			if(!userId){
				alert('Please input the user name.');
				return false;
			}
			
			var otherFeature=$('input[name="otherFeature"]').val();
			if(otherFeature){
				var others = getRating('input[name="others"]');
				if(!others)
				{
					alert('Select your rating for \''+otherFeature+'\'');
					return false;
				}
			}
			
			var appearance = getRating('input[name="appearance"]');
			var material = getRating('input[name="material"]');
			var fit = getRating('input[name="fit"]');
			var situation = getRating('input[name="situation"]');
			var customization = getRating('input[name="customization"]');
			var rating = getRating('input[name="rating"]');
			var brand = getRating('input[name="brand"]');
			var store = getRating('input[name="store"]');
			var recommendation = getRating('input[name="recommendation"]');
			var category = getRating('input[name="category"]');
			var warranty = getRating('input[name="warranty"]');
			var price = getRating('input[name="price"]');
			var shipping = getRating('input[name="shipping"]');
			var promotion = getRating('input[name="promotion"]');
			
			if(!appearance ||!material ||!fit ||!situation ||!customization ||!rating ||!brand ||!store ||!recommendation ||!category ||!warranty ||!price ||!promotion ||!shipping ) {
				alert("Rate each question before submitting your ratings!");
				return false;
			}
			
			return true;
		}
		
		function init()
		{
			var rate;
			
			if((rate=${p.appearance}))$('input[name="appearance"]').rating('select', rate+'');
			if((rate=${p.material}))$('input[name="material"]').rating('select', rate+'');
			if((rate=${p.fit}))$('input[name="fit"]').rating('select', rate+'');
			if((rate=${p.situation}))$('input[name="situation"]').rating('select', rate+'');
			if((rate=${p.customization}))$('input[name="customization"]').rating('select', rate+'');
			if((rate=${p.rating}))$('input[name="rating"]').rating('select', rate+'');
			if((rate=${p.brand}))$('input[name="brand"]').rating('select', rate+'');
			if((rate=${p.store}))$('input[name="store"]').rating('select', rate+'');
			if((rate=${p.recommendation}))$('input[name="recommendation"]').rating('select', rate+'');
			if((rate=${p.category}))$('input[name="category"]').rating('select', rate+'');
			if((rate=${p.warranty}))$('input[name="warranty"]').rating('select', rate+'');
			if((rate=${p.price}))$('input[name="price"]').rating('select', rate+'');
			if((rate=${p.promotion}))$('input[name="promotion"]').rating('select', rate+'');
			if((rate=${p.shipping}))$('input[name="shipping"]').rating('select', rate+'');
			if((rate=${p.others}))$('input[name="others"]').rating('select', rate+'');
			
		}
		
		$(document).ready(init);
	</script>

</head>

<body>
	<div class="entry">
		<h2>Pilot Study</h2>
		<p style="width: 820px;">Please think of purchasing a t-shirt in
			virtual environments (e.g. online web sites, virtual malls), given
			that the retailers are honest and reputable. In order to evaluate the
			quality/value of the t-shirt, how important would it be to you that
			the t-shirt ... (please select one answer for each question)</p>
		<ul class="stars-list">
			<li style="list-style-image: url(img/star1.PNG);">of very little
				or no importance</li>
			<li style="list-style-image: url(img/star2.PNG)">of little
				importance</li>
			<li style="list-style-image: url(img/star3.PNG)">of moderate
				importance</li>
			<li style="list-style-image: url(img/star4.PNG)">very important</li>
			<li style="list-style-image: url(img/star5.PNG)">of utmost
				importance</li>
		</ul>
		<form id="ratingForm" action="./userStudy?action=pilot_sub"
			method="post" onsubmit="return submit_rating()">
			<p>
			<span>New User: </span>
			<input type="text" name="userId" value="${p.userId }" class="underline_box"/>
			<span id="result" style="margin: 0px 10px; color: red;">${requestScope.thanks}</span>
			</p>
			<table class="questions">
				<tr>
					<td>01.</td>
					<td width="80%">has a good looking in terms of colour, front
						and/or back patterns, etc.</td>
					<td><input name="appearance" type="radio" value="1"
						class="star" title="of very little or no importance" /> <input
						name="appearance" type="radio" value="2" class="star"
						title="of little importance" /> <input name="appearance"
						type="radio" value="3" class="star" title="of moderate importance" />
						<input name="appearance" type="radio" value="4" class="star"
						title="very important" /> <input name="appearance" type="radio"
						value="5" class="star" title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>02.</td>
					<td width="80%">is made of good material.</td>
					<td><input name="material" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="material"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="material" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="material"
						type="radio" value="4" class="star" title="very important" /> <input
						name="material" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>03.</td>
					<td width="80%">can try on and fit you well.</td>
					<td><input name="fit" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="fit"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="fit" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="fit" type="radio"
						value="4" class="star" title="very important" /> <input
						name="fit" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>04.</td>
					<td width="80%">is suitable for usage situations e.g. casual
						wear, dates, work, etc.</td>
					<td><input name="situation" type="radio" value="1"
						class="star" title="of very little or no importance" /> <input
						name="situation" type="radio" value="2" class="star"
						title="of little importance" /> <input name="situation"
						type="radio" value="3" class="star" title="of moderate importance" />
						<input name="situation" type="radio" value="4" class="star"
						title="very important" /> <input name="situation" type="radio"
						value="5" class="star" title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>05.</td>
					<td width="80%">is customizable in terms of colour, patterns,
						size, etc.</td>
					<td><input name="customization" type="radio" value="1"
						class="star" title="of very little or no importance" /> <input
						name="customization" type="radio" value="2" class="star"
						title="of little importance" /> <input name="customization"
						type="radio" value="3" class="star" title="of moderate importance" />
						<input name="customization" type="radio" value="4" class="star"
						title="very important" /> <input name="customization"
						type="radio" value="5" class="star" title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>06.</td>
					<td width="80%">is rated highly by other buyers.</td>
					<td><input name="rating" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="rating"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="rating" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="rating"
						type="radio" value="4" class="star" title="very important" /> <input
						name="rating" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>07.</td>
					<td width="80%">has a reputable brand name, <br /> e.g. (This
						brand gives me a feeling of goodwill) or <br /> (I know much
						information about this brand) or <br /> (This brand is a
						well-known brand)</td>
					<td><input name="brand" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="brand"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="brand" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="brand"
						type="radio" value="4" class="star" title="very important" /> <input
						name="brand" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>08.</td>
					<td width="80%">is sold in a well-designed web site or virtual
						store, <br /> e.g. (it is easy to navigate around) or <br /> (it
						is represented or organized well) or <br /> (it looks professional
						or trustable).</td>
					<td><input name="store" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="store"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="store" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="store"
						type="radio" value="4" class="star" title="very important" /> <input
						name="store" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>09.</td>
					<td width="80%">is highly recommended by experts or others.</td>
					<td><input name="recommendation" type="radio" value="1"
						class="star" title="of very little or no importance" /> <input
						name="recommendation" type="radio" value="2" class="star"
						title="of little importance" /> <input name="recommendation"
						type="radio" value="3" class="star" title="of moderate importance" />
						<input name="recommendation" type="radio" value="4" class="star"
						title="very important" /> <input name="recommendation"
						type="radio" value="5" class="star" title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>10.</td>
					<td width="80%">belongs to the category of your favour e.g.
						cartoon, movie, music, tv, etc.</td>
					<td><input name="category" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="category"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="category" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="category"
						type="radio" value="4" class="star" title="very important" /> <input
						name="category" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>11.</td>
					<td width="80%">has an after-sale warranty.</td>
					<td><input name="warranty" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="warranty"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="warranty" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="warranty"
						type="radio" value="4" class="star" title="very important" /> <input
						name="warranty" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>12.</td>
					<td width="80%">has an acceptable price, including base price,
						tax and shipping fees.</td>
					<td><input name="price" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="price"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="price" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="price"
						type="radio" value="4" class="star" title="very important" /> <input
						name="price" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>13.</td>
					<td width="80%">is in promotion, e.g. coupons provided.</td>
					<td><input name="promotion" type="radio" value="1"
						class="star" title="of very little or no importance" /> <input
						name="promotion" type="radio" value="2" class="star"
						title="of little importance" /> <input name="promotion"
						type="radio" value="3" class="star" title="of moderate importance" />
						<input name="promotion" type="radio" value="4" class="star"
						title="very important" /> <input name="promotion" type="radio"
						value="5" class="star" title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>14.</td>
					<td width="80%">can be shipped conveniently and shortly.</td>
					<td><input name="shipping" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="shipping"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="shipping" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="shipping"
						type="radio" value="4" class="star" title="very important" /> <input
						name="shipping" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
				<tr>
					<td>15.</td>
					<td width="80%">has other features (optional): <input
						type="text" name="otherFeature" value="${p.otherFeature }" class="underline_box"/></td>
					<td><input name="others" type="radio" value="1" class="star"
						title="of very little or no importance" /> <input name="others"
						type="radio" value="2" class="star" title="of little importance" />
						<input name="others" type="radio" value="3" class="star"
						title="of moderate importance" /> <input name="others"
						type="radio" value="4" class="star" title="very important" /> <input
						name="others" type="radio" value="5" class="star"
						title="of utmost importance" />
					</td>
				</tr>
			</table>
			<p>
				Comments&nbsp;&nbsp;(optional): <br />
				<textarea id="comments" name="comments" cols="50" rows="7"></textarea>
			</p>
			<input type="submit" value="Submit Ratings" class="submit" />
		</form>
	</div>
</body>
</html>
