<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색?</title>
<link rel="stylesheet" type="text/css" href="./static/css/search.css">
<script src="./static/js/search.js"></script>
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
			<img src="data:image/png;base64,<%=session.getAttribute("사계절")%>"
				alt="사계절">
			<p>모든계절</p>
		</div>
		<div id="season6" class="season" onclick="selectSeason('6')">
			<p>상관없음</p>
		</div>
	</div>

	<h1>어떤 색상을 원하시나요?</h1>
	<div class="color-container">
		<div id="color1" class="color" onclick="selectColor('1')">
			<img src="data:image/png;base64,<%=session.getAttribute("빨강")%>"
				alt="빨강">
			<p>빨강 계열</p>
		</div>
		<div id="color2" class="color" onclick="selectColor('2')">
			<img src="data:image/png;base64,<%=session.getAttribute("노랑")%>"
				alt="노랑">
			<p>노랑 계열</p>
		</div>
		<div id="color3" class="color" onclick="selectColor('3')">
			<img src="data:image/png;base64,<%=session.getAttribute("파랑")%>"
				alt="파랑">
			<p>파랑 계열</p>
		</div>
		<div id="color4" class="color" onclick="selectColor('4')">
			<img src="data:image/png;base64,<%=session.getAttribute("초록")%>"
				alt="초록">
			<p>초록 계열</p>
		</div>
		<div id="color5" class="color" onclick="selectColor('5')">
			<img src="data:image/png;base64,<%=session.getAttribute("갈색")%>"
				alt="갈색">
			<p>갈색 계열</p>
		</div>
		<div id="color6" class="color" onclick="selectColor('6')">
			<img src="data:image/png;base64,<%=session.getAttribute("무채색")%>"
				alt="무채색">
			<p>무채색</p>
		</div>
		<div id="color7" class="color" onclick="selectColor('7')">
			<p>모두선택</p>
		</div>
	</div>

	<h1>언제 입으실 건가요?</h1>
	<div class="usage-container">
		<div id="usage1" class="usage" onclick="selectUsage('1')">
			<img src="data:image/png;base64,<%=session.getAttribute("운동")%>"
				alt="운동">
			<p>운동</p>
		</div>
		<div id="usage2" class="usage" onclick="selectUsage('2')">
			<img src="data:image/png;base64,<%=session.getAttribute("경사")%>"
				alt="경사">
			<p>경사</p>
		</div>
		<div id="usage3" class="usage" onclick="selectUsage('3')">
			<img src="data:image/png;base64,<%=session.getAttribute("캠핑")%>"
				alt="캠핑">
			<p>캠핑</p>
		</div>
		<div id="usage4" class="usage" onclick="selectUsage('4')">
			<img src="data:image/png;base64,<%=session.getAttribute("등산")%>"
				alt="등산">
			<p>등산</p>
		</div>
		<div id="usage5" class="usage" onclick="selectUsage('5')">
			<img src="data:image/png;base64,<%=session.getAttribute("비즈니스")%>"
				alt="비즈니스">
			<p>비즈니스</p>
		</div>
		<div id="usage6" class="usage" onclick="selectUsage('6')">
			<img src="data:image/png;base64,<%=session.getAttribute("일상")%>"
				alt="일상">
			<p>일상</p>
		</div>
		<div id="usage7" class="usage" onclick="selectUsage('7')">
			<img src="data:image/png;base64,<%=session.getAttribute("실내")%>"
				alt="실내">
			<p>실내</p>
		</div>
		<div id="usage8" class="usage" onclick="selectUsage('8')">
			<p>모두선택</p>
		</div>
	</div>

	<!-- 가격대 설정 영역 -->
	<h1>가격대를 설정해주세요</h1>
	<div class="price-container">
		<div id="priceRange" class="price-range"
			onclick="activatePriceRange()">
			<label><input type="radio" name="priceOption" value="range">
				가격대 선택</label>
			<div class="price-options">
				<label><input type="radio" name="price" value="7000 이하"
					data-min="0" data-max="7000" disabled> 7천원 이하</label> <label><input
					type="radio" name="price" value="7000-14000" data-min="7000"
					data-max="14000" disabled> 7천원~1만 4천원</label> <label><input
					type="radio" name="price" value="14000-21000" data-min="14000"
					data-max="21000" disabled> 1만 4천원~2만 1천원</label> <label><input
					type="radio" name="price" value="21000-28000" data-min="21000"
					data-max="28000" disabled> 2만 1천원~2만 8천원</label> <label><input
					type="radio" name="price" value="28000 이상" data-min="28000"
					data-max="999999" disabled> 2만 8천원 이상</label>
			</div>
		</div>
		<div id="customPrice" class="custom-price"
			onclick="activateCustomPrice()">
			<label class="custom-price-label"><input type="radio"
				name="priceOption" value="custom"> 직접 입력</label>
			<div class="custom-price-inputs">
				<input type="number" id="minPrice" name="minPrice"
					placeholder="최소 금액" disabled required> ~ <input
					type="number" id="maxPrice" name="maxPrice" placeholder="최대 금액"
					disabled required>
			</div>
		</div>
		<div id="allPrice" class="all-price" onclick="activateAllPrice()">
			<label><input type="radio" name="priceOption" value="all">
				모든 가격</label>
		</div>
	</div>


	<form id="combinedForm"
		action="${pageContext.request.contextPath}/search" method="post">
		<input type="hidden" id="seasonInput" name="season" value="">
		<input type="hidden" id="colorInput" name="color" value=""> <input
			type="hidden" id="usageInput" name="usage" value=""> <input
			type="hidden" id="priceInput" name="price" value="">
		<button type="button" id="submitBtn" onclick="submitForms()">제출</button>
	</form>
</body>
</html>
