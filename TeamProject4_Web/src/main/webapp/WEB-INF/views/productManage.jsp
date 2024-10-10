<!-- 작성자 : 이나겸 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 태그 라이브러리 중 jstl의 core -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 태그 라이브러리 중 jstl의 fmt -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리 페이지</title>
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
	height: 125px;
	position: fixed; /* header를 고정 */
	top: 0;
	left: 0;
	z-index: 1000; /* 다른 내용 위에 고정되도록 z-index 추가 */
}

nav .logo {
	text-align: center;
}

main {
	display: flex;
	flex-direction: column;
	align-items: center; /* 수평 중앙 정렬 */
	width: 100%;
	padding-top: 170px; /* header 높이와 여유 공간을 줌 */
	overflow-y: auto; /* 내용이 많아지면 스크롤이 생기도록 설정 */
}

.panel {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 80%;
	margin-bottom: 20px; /* 패널 사이 간격 */
	display: flex;
	flex-direction: column;
}

.panel h3 {
	text-align: left;
	font-size: 20px;
	margin-bottom: 10px;
}

.panel-header {
	display: flex;
	justify-content: space-between; /* 좌우 공간을 최대한 넓게 */
	align-items: center; /* 수직 중앙 정렬 */
	margin-bottom: 10px; /* 제목과 버튼 사이 간격 */
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

button {
    border: 1px solid #bbb; /* 기존보다 진한 테두리 색상 */
    border-radius: 5px; /* 테두리를 둥글게 설정 */
    padding: 5px 10px;
    background-color: #f2f2f2;
    color: #000;
    cursor: pointer;
}

/* 버튼에 마우스 호버 시 효과 */
button:hover {
    background-color: #f2f2f2;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<header>
		<nav>
			<div class="logo">
				<h1>Web Project 홈페이지</h1>
				<h2>상품 관리</h2>
			</div>
		</nav>
	</header>
	<main>
		<div class="panel">
			<div class="panel-header">
				<div>
					<h3>상품 목록</h3>
					전체 품목 :
					<%=request.getAttribute("allClothCount")%>개
				</div>
				<button class="btnAdd">상품 추가</button>
			</div>
			<table>
				<thead>
					<tr>
						<th rowspan="2">상품명</th>
						<th rowspan="2">브랜드</th>
						<th rowspan="2">가격</th>
						<th rowspan="2">성별구분</th>
						<!-- 사이즈 칸을 묶음 -->
						<th colspan="5">사이즈 별 재고</th>
						<th rowspan="2">정보수정</th>
						<th rowspan="2">삭제</th>
					</tr>
					<tr>
					<!-- 묶어놓은 사이즈 별 재고 칸 밑에 나타날 사이즈들 -->
						<th>S</th>
						<th>M</th>
						<th>L</th>
						<th>XL</th>
						<th>XXL</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cloth" items="${allClothList}">
						<tr>
							<td>${cloth.cloth_name}</td>
							<td>${cloth.cloth_brand}</td>
							<!-- fmt:formatNumber 이용해서 금액을 나타내는 숫자에 1000단위로 쉼표 추가 -->
							<td><fmt:formatNumber value="${cloth.cloth_price}"
									type="number" pattern="#,##0" />원</td>
							<td>${cloth.cloth_gender}</td>
							<td>${cloth.cloth_size_s}</td>
							<td>${cloth.cloth_size_m}</td>
							<td>${cloth.cloth_size_l}</td>
							<td>${cloth.cloth_size_xl}</td>
							<td>${cloth.cloth_size_xxl}</td>
							<td><button>수정</button></td>
							<td><button>삭제</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
<!-- productManage.js 파일과 연결함 -->
<script src="/management/productManage.js"></script>
</html>