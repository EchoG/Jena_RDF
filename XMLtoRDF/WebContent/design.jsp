<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Parse XML</title>
<link rel="stylesheet" type="text/css" media="screen" href="resources/aux/css/design.css">
</head>
<body id="css-zen-garden">
<div class="page-wrapper">

	<section class="intro" id="zen-intro">
		<header role="banner">
		</header>

		<!--input city name-->
		<div class="summary" id="zen-summary" role="article">
		<form action="./CoauthorServlet" method="get">
			Author Name: <input type="text" id="cityname" name="author">
			<input type="submit" value="submit" value="search" >
		</form>
		</div>

		<!--show coauthors-->
		<div class="preamble" id="zen-preamble" role="article">
			<h3>Coauthor</h3>
			<hr></hr>
			<div id="coauthor">
			<input type="hidden" name="oldData" value="${coauthors}"/>
			<p>${coauthors}</p>
			</div>
			
		</div>
	</section>

	<div class="main supporting" id="zen-supporting" role="main">
	
		<!--show data from publications-->
		<div class="explanation" id="zen-explanation" role="article"  style="height:200px">
			<h3>Title of Corresponding Publications</h3>
			<hr></hr>
			<input type="hidden" name="oldData" value="${publications_title}"/>
			<p>${publications}</p>
			
		</div>
	</div>


	<aside class="sidebar" role="complementary">
		<div class="wrapper">

			<div class="design-selection" id="design-selection">
				<h3 class="select">Select:</h3>
				<nav role="navigation">
					<ul>
					<li>
						<font size="3">COAUTHOR</font>
					</li>					
					<li>
						<font size="3">PUBLICATION</font>
					</li>									
					</ul>
				</nav>
			</div>
		</div>
	</aside>


</div>
</body>
</html>