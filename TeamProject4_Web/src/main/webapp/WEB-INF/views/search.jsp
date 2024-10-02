<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색?</title>
<link rel="stylesheet" type="text/css"
	href="./static/css/search.css">
<script src="${pageContext.request.contextPath}/static/js/search.js"></script>
</head>
<body>
	<h1>어느 계절에 입으시나요?</h1>
	<div class="season-container">
		<div id="season1" class="season" onclick="selectSeason('1')">
			<img src="data:image/png;base64,<%=session.getAttribute("봄")%>"
				alt="봄">
			<p>봄</p>
		</div>
		<div id="season2" class="season" onclick="selectSeason('2')">
			<img src="data:image/png;base64,<%=session.getAttribute("여름")%>"
				alt="여름">
			<p>여름</p>
		</div>
		<div id="season3" class="season" onclick="selectSeason('3')">
			<img src="data:image/png;base64,<%=session.getAttribute("가을")%>"
				alt="가을">
			<p>가을</p>
		</div>
		<div id="season4" class="season" onclick="selectSeason('4')">
			<img src="data:image/png;base64,<%=session.getAttribute("겨울")%>"
				alt="겨울">
			<p>겨울</p>
		</div>
		<div id="season5" class="season" onclick="selectSeason('5')">
			<p>모든계절</p>
		</div>
	</div>
	
	<h1>어떤 색상을 원하시나요?</h1>
	<div class="color-container">
		<div id="color1" class="color" onclick="selectColor('1')">
			<img src="data:image/png;base64,<%=session.getAttribute("빨강")%>" alt="빨강">
			<p>빨강 계열</p>
		</div>
		<div id="color2" class="color" onclick="selectColor('2')">
			<img src="data:image/png;base64,<%=session.getAttribute("노랑")%>" alt="노랑">
			<p>노랑 계열</p>
		</div>
		<div id="color3" class="color" onclick="selectColor('3')">
			<img src="data:image/png;base64,<%=session.getAttribute("파랑")%>" alt="파랑">
			<p>파랑 계열</p>
		</div>
		<div id="color4" class="color" onclick="selectColor('4')">
			<img src="data:image/png;base64,<%=session.getAttribute("초록")%>" alt="초록">
			<p>초록 계열</p>
		</div>
		<div id="color5" class="color" onclick="selectColor('5')">
			<img src="data:image/png;base64,<%=session.getAttribute("갈색")%>" alt="갈색">
			<p>갈색 계열</p>
		</div>
		<div id="color6" class="color" onclick="selectColor('6')">
			<img src="data:image/png;base64,<%=session.getAttribute("무채색")%>" alt="무채색">
			<p>무채색</p>
		</div>
		<div id="color7" class="color" onclick="selectColor('7')">
			<p>모두선택</p>
		</div>
	</div>

	<form id="combinedForm"
		action="${pageContext.request.contextPath}/search" method="post">
		<input type="hidden" id="seasonInput" name="season" value="">
		<input type="hidden" id="colorInput" name="color" value="">
		<button type="button" id="submitBtn" onclick="submitForms()">제출</button>
	</form>
</body>
</html>
