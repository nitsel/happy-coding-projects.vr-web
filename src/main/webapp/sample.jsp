<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Product Page</title>
	<link rel="shortcut icon" href="img/users.ico" />
	<link rel="stylesheet" type="text/css" href="css/main.css" />
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
				alert("Rate the book before submitting your rating!");
				return false;
			}
			
			var toggle=false;
			if(toggle)
			{
				$(resultSelector).html("Your rating "+rating+" is saved!");
			}else
			{
				//AJAX code: submit rating to server action
				//ajax_submit(rating);
				$.get("./rating.do?action=submit", {uId: userId, pId:productId, rating:rating}, function(data){
					$(resultSelector).html("&nbsp;"+data);
				});
			}							
			return false;
		}
		
		function init()
		{
			//$('#testform>input[value="3"]').checked="checked";
			var averageSelector = 'form#averageForm>input';
			var r=Math.floor(3.7/0.5)*0.5;
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
<div id="basic">
	<h2>Book Info</h2>
	<div class="float-image">
		<img src="img/image.jpg" width="100%" height="100%"/>
	</div>
	<div class="float-info">
		<p style="font-size: 26px;"><a href="preview.jsp?bookId=Gv4pCVyoUVYC">Introduction to Linear Algebra</a>, Fourth Edition<br/>
		<small>[Hardcover]</small></p>
		<p style="margin-bottom: 10px;"><b>Author(s):</b> Gilbert Strang</p>		 
		<div class="averageRating">Average Rating:</div>
		<div>
			<form id="averageForm">
				<input name="star2" type="radio" class="star {split:2}" value="0.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="1.0" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="1.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="2.0" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="2.5" disabled="disabled"/> 
				<input name="star2" type="radio" class="star {split:2}" value="3.0" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="3.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="4.0" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="4.5" disabled="disabled"/>
				<input name="star2" type="radio" class="star {split:2}" value="5.0" disabled="disabled"/> 
				<span style="margin:0px 0px 0px 5px;">3.7/5</span>
			</form>
		</div>
		<div><b>Price:</b> $72.15 <br/>
			<font size="4" color="#228a01"><b>In Stock</b></font>
		</div>
	</div>
	<div class="float-rating">
		<h2>Rate This Book</h2>
		<form id="ratingForm" action="#" method="get" onSubmit="return submit_rating()">
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
</div>
<div class="clear-float"/>
<hr/>

<div id="book-description">
	<h2>Book Description</h2>
	<p>Publication Date: <b>February 10, 2009</b> | ISBN-10: <b>0980232716</b> | ISBN-13: <b>978-0980232714</b> | Edition: <b>4</b></p>
	<p class="p-review">Gilbert Strang's textbooks have changed the entire approach to learning linear algebra -- away from abstract vector spaces to specific examples of the four fundamental subspaces: the column space and nullspace of A and A'.</p>
	
	<p class="p-review">Introduction to Linear Algebra, Fourth Edition includes challenge problems to complement the review problems that have been highly praised in previous editions. The basic course is followed by seven applications: differential equations, engineering, graph theory, statistics, fourier methods and the FFT, linear programming, and computer graphics.</p>
	
	<p class="p-review">Thousands of teachers in colleges and universities and now high schools are using this book, which truly explains this crucial subject.</p>
	
	<p class="p-review">Chapter 1: Introduction to Vectors; Chapter 2: Solving Linear Equations; Chapter 3: Vector Spaces and Subspaces; Chapter 4: Orthogonality; Chapter 5: Determinants; Chapter 6: Eigenvalues and Eigenvectors; Chapter 7: Linear Transformations; Chapter 8: Applications; Chapter 9: Numerical Linear Algebra; Chapter 10: Complex Vectors and Matrices; Solutions to Selected Exercises; Final Exam. Matrix Factorizations. Conceptual Questions for Review. Glossary: A Dictionary for Linear Algebra Index Teaching Codes Linear Algebra in a Nutshell.</p>
</div>
<hr/>

<div id="editor-reviews">
	<h2>About the Author</h2>
	<h4>Biography</h4>
	<div class="float-image2">
	<img src="img/noimage.png" width="100%" heigh="100%" />
	</div>
	<p class="p-review">Gilbert Strang is Professor of Mathematics at the Massachusetts Institute of Technology and an Honorary Fellow of Balliol College. He was an undergraduate at MIT and a Rhodes Scholar at Oxford. His doctorate was from UCLA and since then he has taught at MIT. He has been a Sloan Fellow and a Fairchild Scholar and is a Fellow of the American Academy of Arts and Sciences. Professor Strang has published a monograph with George Fix, "An Analysis of the Finite Element Method", and has authored six widely used textbooks. He served as President of SIAM during 1999 and 2000 and he is Chair of the U.S. National Committee on Mathematics for 2003-2004.</p>
</div>
<div class="clear-float"/>
<hr/>

<div id="details">
	<h2>Product Details</h2>
	<ul class="ul-list">
		<li><b>Hardcover:</b> 584 pages</li>
		<li><b>Publisher:</b> Wellesley Cambridge Press; 4 edition (February 10, 2009)</li>
		<li><b>Language:</b> English</li>
		<li><b>ISBN-10:</b> 0980232716</li>
		<li><b>ISBN-13:</b> 978-0980232714</li>
		<li><b>Product Dimensions:</b> 9.2 x 7.7 x 1.3 inches</li>
		<li><b>Shipping Weight:</b> 2.6 pounds</li>
		<li><b>Average Customer Review:</b> 3.7 out of 5 stars  See all reviews</li>
		<li><b>Amazon Best Sellers Rank:</b> #19,847 in Books</li>
	</ul>
</div>
<hr/>

<div id="reviews">
	<h2>Customer Reviews</h2>
	<div>	 
		<div class="averageRating">Average Rating (3.7/5):</div>
		<div>
			<form id="averageForm2">
			<input name="star3" type="radio" class="star {split:2}" value="0.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="1.0" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="1.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="2.0" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="2.5" disabled="disabled"/> 
			<input name="star3" type="radio" class="star {split:2}" value="3.0" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="3.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="4.0" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="4.5" disabled="disabled"/>
			<input name="star3" type="radio" class="star {split:2}" value="5.0" disabled="disabled"/> 
			</form>
		</div>
		<div class="clear-float"/>
	</div>
	<div class="review">
		<div>
			<input name="star4" type="radio" class="star" value="1" disabled="disabled"/>
			<input name="star4" type="radio" class="star" value="2" disabled="disabled"/>
			<input name="star4" type="radio" class="star" value="3" disabled="disabled" checked="checked"/>
			<input name="star4" type="radio" class="star" value="4" disabled="disabled"/>
			<input name="star4" type="radio" class="star" value="5" disabled="disabled"/> 
			&nbsp;&nbsp;<b>Opinion of a 35-year veteran professor</b>&nbsp;&nbsp;April 14, 2009 <br/>
			By Geoge W. Cobb<br/>
			Format: Hardcover
		</div>
		<div>
			<p>I write as a 35-year veteran teacher of mathematics and statistics, at Mount Holyoke College. This semester I am teaching two sections of linear algebra, from Gilbert Strang's Introduction to Linear Algebra, 4th edition. I understand that I'm one of the first, perhaps the very first, to teach from this edition, scooping even the author himself, whose spring semester at MIT began a week after Mount Holyoke's.</p>
			<p>In choosing a book for my course, I reviewed more than a dozen choices. In what follows, I'll try to set out why, looking back on the first two-thirds of the semester, I'm firmly convinced that I chose the right book to teach from. But first, here's an excerpt from an e-mail I sent the author a few weeks ago:</p>
			<p>I've admired your book ever since the first edition came out, but in our department we have to wait in line to teach linear algebra, and this is my first chance to teach from your book. It's hard to put into words how much I'm enjoying it.</p>
			<p>In 35 years, I've nearly always ended up feeling deeply disappointed with almost any textbook I've tried to teach from. However I've had the good fortune to find two books I really admire. Yours is one of those two inspiring books. Thanks to you, I'm having a blast!</p>
			<p>Enthusiasm aside, I'll start the substance of my review with four questions, aimed both at students and at teachers. These questions highlight the features I find inspiring - but they are not merely rhetorical: I've tried to formulate questions that should be helpful to anyone trying to decide whether Strang's Introduction to Linear Algebra is the right choice for them. In each instance, although my own answer is a resounding "Yes to choice one!" I can imagine teachers and readers whose preference would go the other way.
</p><p>
* Do you want a book that puts a top priority on the substantive content of linear algebra as a subject in its own right, or a book that uses linear algebra as vehicle for teaching formal proofs?
</p><p>
* Do you want a book whose exercises are imaginative, minimize unnecessary computation, challenge the reader to think about core concepts, and anticipate the content to come, or do you want exercises that closely track the pattern of worked examples du jour, with multiple instances of each type?
</p><p>
* Do you want a book that works hard and thoughtfully to communicate the ideas that unite linear algebra, or do you want a book that has thinned and linearized the content in order to make teaching and learning go more smoothly?
</p><p>
* Do you want a book written by a mathematician with a lifetime experience using linear algebra to understand important, authentic, applied problems, a former president of the Society for Industrial and Applied Mathematics, or do you want a book shaped mainly by the esthetics of pure mathematicians with only a weak, theoretical connection to how linear algebra is used in the natural and social sciences?
</p><p>
To get more technical:
</p><p>
The order of topics in a linear algebra course is often a good indicator of the author's orientation. If linear algebra is trotted out mainly as a show horse, a way to exhibit the sleek beauty of well-groomed mathematics, you won't find the singular value decomposition (SVD) in the index. Dot products, projections, and (horrors!) least squares will come late in the book. Abstract vector spaces, properties of linear transformations, change of basis, and isomorphisms of n-spaces will be prominent.
</p><p>
Alternatively, if linear algebra is recognized and harnessed as a powerful draft horse - and we know from the Budweiser Clydesdales that horses doing real work can compel esthetic admiration - we should expect least squares early, and expect attention to the SVD, as in Strang's book. Strang calls the SVD "a highlight of linear algebra"., and it is. A best-selling competitor doesn't even list the SVD in its index.
</p><p>
Finally, you can tell a thinned version of linear algebra from the real thing by the attention to the duality between algebra and geometry. Intellectually thinner books spend more time on algorithmically-based arithmetic and algebra. These algorithmically oriented books are mere cognitive comb-overs. The more substantial books work systematically to develop in parallel the reader's algebraic skills and geometric intuition.
</p><p>
The contrast stands out in sharpest relief when it comes to eigen-stuff. The deep books, like Strang's, emphasize geometry: does multiplication by A change the direction of x? The comb-overs emphasize Cayley-Hamilton and computation.
</p><p>
In conclusion, I'll borrow from the opening lines of Anna Karenina: Traditional linear algebra books are all alike. Each profound linear algebra book is profound in its own way. At the advanced level, we have a handful of profound linear algebra books, but at this point, at the introductory level, I know of only one: Gilbert Strang's Introduction to Linear Algebra.
</p>
			<p>George Cobb, <br/>
			Robert L. Rooke Professor of Mathematics and Statistics,<br/>
			Mount Holyoke College<br/>
			Vice President, American Statistical Association, 2005-2007<br/>
			Lifetime Achievement Award in Statistics Education, 2005<br/>
			National Research Council,<br/>
			Committee on Applied and Theoretical Statistics, 1997-2000</p>
		</div>
	</div>
</div>

<div class="review2">
		<div>
			<input name="star5" type="radio" class="star" value="1" disabled="disabled"/>
			<input name="star5" type="radio" class="star" value="2" disabled="disabled"/>
			<input name="star5" type="radio" class="star" value="3" disabled="disabled"/>
			<input name="star5" type="radio" class="star" value="4" disabled="disabled" checked="checked"/>
			<input name="star5" type="radio" class="star" value="5" disabled="disabled"/> 
			&nbsp;&nbsp;<b>Great for the Newcomer, Probably too Slow for Familiar</b>&nbsp;&nbsp;November 27, 2010 <br/>
			By jdg<br/>
			Format: Hardcover
		</div>
		<div>
			<p>I bought this book (the 3rd edition of it) my sophomore year as an undergraduate engineer. I read a couple of sections and then got distracted and didn't pick the book up again until my first year as a graduate student. Before reading this book, my experience with linear algebra had been modest (much to the fault of my undergraduate curriculum), but I soon realized how important linear algebra is to an engineer.</p>
			<p>This book was wonderful! I read nearly the entire thing over the course of a month (working a large number of the problems), and since then have referenced it often. The chapter on Eigenvalues, Linear Transformations, and Applications are extremely useful (in the 3rd edition 6,7, and 8). Strang's style is refreshing in the world of dry math books; he really gives you the intuition and excitement behind the math. I find this invaluable as an engineer.</p>
			<p>There is a downside to this: the book is wordy for a math book and the key results scattered throughout the text. For this reason I would highly recommend this book for someone without much background in linear algebra, but probably would not recommend it to someone looking for a refresher--a more succinct book would probably be more appropriate. I would also not recommend this book for someone interested in formal mathematics: the book claims informality, and it certainly is informal. That being said, most of the essential proofs are there in spirt, just not set down formally like many other math texts I have used.</p>
			<p>I gave the book 5 stars because, although it isn't for everybody (no book can be), it is exactly what it claims to be: an INTRODUCTION to linear algebra, and an excellent one at that.</p>
		</div>
	</div>
</div>

</body>
</html>
