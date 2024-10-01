<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색?</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/search.css">
<script src="${pageContext.request.contextPath}/static/js/search.js"></script>
</head>
<body>
	<h1>어느 계절에 입으시나요?</h1>
	<div class="season-container">
		<div id="season1" class="season" onclick="selectSeason('1')">
			<img src="images/spring.jpg" alt="봄">
			<p>봄</p>
		</div>
		<div id="season2" class="season" onclick="selectSeason('2')">
			<img src="images/summer.jpg" alt="여름">
			<p>여름</p>
		</div>
		<div id="season3" class="season" onclick="selectSeason('3')">
			<img src="images/autumn.jpg" alt="가을">
			<p>가을</p>
		</div>
		<div id="season4" class="season" onclick="selectSeason('4')">
			<img src="images/winter.jpg" alt="겨울">
			<p>겨울</p>
		</div>
		<div id="season5" class="season" onclick="selectSeason('5')">
			<img src="images/all_seasons.jpg" alt="모든계절">
			<p>모든계절</p>
		</div>
	</div>

	<h1>어떤 요소가 움직이나요?</h1>
	<div class="element-container">
		<div id="elementMoon" class="element" onclick="selectElement('Moon')">
			<img src="images/moon.jpg" alt="Moon">
			<p>Moon</p>
		</div>
		<div id="elementSun" class="element" onclick="selectElement('Sun')">
			<img src="images/sun.jpg" alt="Sun">
			<p>Sun</p>
		</div>
		<div id="elementYang" class="element" onclick="selectElement('Yang')">
			<img src="images/yang.jpg" alt="Yang">
			<p>Yang</p>
		</div>
		<div id="elementYin" class="element" onclick="selectElement('Yin')">
			<img src="images/yin.jpg" alt="Yin">
			<p>Yin</p>
		</div>
		<div id="elementEarth" class="element" onclick="selectElement('Earth')">
			<img src="images/earth.jpg" alt="Earth">
			<p>Earth</p>
		</div>
		<div id="elementMercury" class="element" onclick="selectElement('Mercury')">
			<img src="images/mercury.jpg" alt="Mercury">
			<p>Mercury</p>
		</div>
	</div>

	<form id="combinedForm" method="post">
		<input type="hidden" id="seasonInput" name="season" value="">
		<input type="hidden" id="elementInput" name="element" value="">
		<button type="button" onclick="submitForms()">제출</button>
	</form>
</body>
</html>
