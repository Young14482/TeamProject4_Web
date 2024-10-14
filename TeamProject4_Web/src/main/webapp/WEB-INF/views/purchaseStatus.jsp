<!-- 작성자 : 이나겸 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 태그 라이브러리 중 jstl의 core -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 태그 라이브러리 중 jstl의 fmt -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>소비자 구매 현황 페이지</title>

<style type="text/css">
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
	height: 100vh;
	display: flex;
	flex-direction: column;
}

header {
	background-color: #333;
	color: #fff;
	padding: 10px 0;
	width: 100%;
	height: 155px;
	position: fixed; /* header를 고정 */
	top: 0;
	left: 0;
	z-index: 1000; /* 다른 내용 위에 고정되도록 z-index 추가 */
}

nav .logoAndText {
    display: flex;
    flex-direction: column; /* 세로 정렬 */
    align-items: center; /* 가운데 정렬 */
}

.logoAndTitle {
    display: flex;
    align-items: center; /* 이미지와 h1을 수직 중앙 정렬 */
    justify-content: center; /* 로고와 h1을 수평 중앙 정렬 */
}

.logo-img {
    width: 50px;
    height: auto;
    margin-right: 10px;
}

main {
	display: flex;
	flex-direction: column;
	align-items: center; /* 수평 중앙 정렬 */
	width: 100%;
	padding-top: 200px; /* header 높이와 여유 공간을 줌 */
	overflow-y: auto; /* 내용이 많아지면 스크롤이 생기도록 설정 */
}

h3 {
	white-space: nowrap; /* 개행이 일어나지 않도록 */
}

.panel {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 93%;
	margin-bottom: 20px; /* 패널 사이 간격 */
}

.panel h3 {
	text-align: left;
	font-size: 20px;
	margin-bottom: 10px;
}

.panel2 {
	display: flex;
	justify-content: flex-end;
	width: 100%;
	align-items: center;
}

.panel3 {
	display: flex;
	justify-content: space-between;
	width: 100%;
}

.panel4 {
	style ="display: flex;
	justify-content: space-between;
	align-items: center;
	"
}

#searchPanel, #priceSearchPanel {
	display: flex; /* Flexbox 활성화 */
	justify-content: flex-end; /* 요소들을 오른쪽으로 정렬 */
	align-items: center; /* 요소들을 수직 중앙 정렬 */
	margin-bottom: 10px; /* 패널 사이 간격 */
}

#searchPanel table, #priceSearchPanel table {
	border: none; /* 테두리 제거 */
	margin: 0; /* 기본 마진 제거 */
	padding: 0;
}

#searchPanel td, #priceSearchPanel td {
	border: none; /* 칸의 테두리 제거 */
	padding: 2px;
	margin: 0; /* 칸 간의 여백 제거 */
}

#searchPanel select, #searchPanel input[type="text"], #searchPanel input[type="submit"],
	#priceSearchPanel input[type="number"], #priceSearchPanel input[type="submit"]
	{
	border-radius: 5px; /* 둥글게 설정 */
	border: 1px solid #ddd; /* 테두리 색상 설정 */
	height: 30px; /* 높이 설정 */
	padding: 0 8px; /* 좌우 패딩 */
	margin: 0; /* 기본 마진 제거 */
	box-sizing: border-box; /* 패딩과 테두리를 포함한 총 높이와 너비 계산 */
}

#searchPanel input[type="submit"], #priceSearchPanel input[type="submit"]
	{
	background-color: #f2f2f2; /* 버튼 배경 색상 */
	color: #000; /* 버튼 텍스트 색상 */
	cursor: pointer; /* 커서 모양 변경 */
}

/* 버튼에 마우스 호버 시 효과 */
#searchPanel input[type="submit"]:hover, #priceSearchPanel input[type="submit"]:hover
	{
	background-color: #e0e0e0; /* 호버 시 버튼 색상 변경 */
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
}

table, th, td {
	border: 1px solid #ddd; /* 표의 테두리 색상 */
}

th, td {
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

.panel:hover { /* panel에 마우스 호버 시 그림자 확대 */
	box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
}
</style>
</head>
<body>
	<header>
		<nav>
			<div class="logoAndText">
				<div class="logoAndTitle">
					<div class="logo">
						<img src="/static/image/logo/logo.png" alt="로고 이미지" class="logo-img">
					</div>
					<h1>
						<a href="/main"
							style="color: #fff; text-decoration: none; font-size: 1.5em;">
							NO MORE SHINSA</a>
					</h1>
				</div>
				<h2>소비자 구매 현황</h2>
			</div>
		</nav>
	</header>
	<main>
		<div class="panel">
			<div class="panel3">
				<div style="display: block;">
					<h3>소비자 별 구매 현황</h3>
					<div id="purchaseUserCount">
						구매 회원수 :
						<%=request.getAttribute("purchaseUserCount")%>명
					</div>
				</div>
				<div class="panel2">
					<div class="panel4">
						<div id="searchPanel">
							<form id="searchForm" method="GET" action="searchPurchase">
								<table>
									<tr>
										<td><select id="searchField" class="searchOption"
											name="searchPurchaseField">
												<option value="purchaseUserId">회원아이디</option>
												<option value="purchaseUserName">회원명</option>
										</select></td>
										<td><input type="text" id="searchText"
											class="searchOption" placeholder="검색어 입력"
											name="searchPurchaseText" maxlength="100"></td>
										<td><input type="submit" value="검색"></td>
									</tr>
								</table>
							</form>
						</div>
						<div id="priceSearchPanel">
							<form id="priceSearchForm" method="GET" action="searchPurchase">
								<table>
									<tr>
										<td><label for="minPurchasePrice">구매금액</label></td>
										<td><input type="number" id="minPrice"
											name="minPurchasePrice" placeholder="최소 금액 입력" min="0"
											step="100000"></td>
										<td><label for="maxPurchasePrice"> ~ </label></td>
										<td><input type="number" id="maxPrice"
											name="maxPurchasePrice" placeholder="최대 금액 입력" min="0"
											step="100000"></td>
										<td><input type="submit" value="검색"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th>no</th>
						<th>아이디</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>배송지</th>
						<th>회원등급</th>
						<th>총 구매금액</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty purchaseStatusList}">
						<!-- 리스트가 비어있을 경우 : 판매 내역이 없을 경우 -->
						<tr>
							<td colspan="7">구매 현황 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="purchase" items="${purchaseStatusList}">
						<tr>
							<td>${purchase.no}</td>
							<td>${purchase.user_id}</td>
							<td>${purchase.user_name}</td>
							<td>${purchase.user_phone}</td>
							<td>${purchase.user_address}</td>
							<td>${purchase.user_grade}</td>
							<!-- fmt:formatNumber 이용해서 금액을 나타내는 숫자에 1000단위로 쉼표 추가 -->
							<td><fmt:formatNumber value="${purchase.user_useMoney}"
									type="number" pattern="#,##0" />원</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>