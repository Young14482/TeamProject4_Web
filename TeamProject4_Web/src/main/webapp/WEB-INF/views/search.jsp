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
			<img src="data:image/png;base64,<%=session.getAttribute("봄")%>" alt="봄">
			<p>봄</p>
		</div>
		<div id="season2" class="season" onclick="selectSeason('2')">
			<img src="data:image/png;base64,<%=session.getAttribute("여름")%>" alt="여름">
			<p>여름</p>
		</div>
		<div id="season3" class="season" onclick="selectSeason('3')">
			<img src="data:image/png;base64,<%=session.getAttribute("가을")%>" alt="가을">
			<p>가을</p>
		</div>
		<div id="season4" class="season" onclick="selectSeason('4')">
			<img src="data:image/png;base64,<%=session.getAttribute("겨울")%>" alt="겨울">
			<p>겨울</p>
		</div>
		<div id="season5" class="season" onclick="selectSeason('5')">
			<p>모든계절</p>
		</div>
	</div>

	<form id="combinedForm" action="${pageContext.request.contextPath}/search" method="post">
		<input type="hidden" id="seasonInput" name="season" value="">
		<input type="hidden" id="elementInput" name="element" value="">
		<button type="button" onclick="submitForms()">제출</button>
	</form>
</body>
</html>
