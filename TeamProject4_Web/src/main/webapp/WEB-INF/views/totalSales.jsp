<!-- 작성자 : 이나겸 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 태그 라이브러리 중 jstl의 core -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 태그 라이브러리 중 jstl의 fmt -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>총 매출 페이지</title>
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

.totalMoney {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 90%;
	margin-bottom: 20px; /* 패널 사이 간격 */
	text-align: center;
}

.panel {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 90%;
	margin-bottom: 20px; /* 패널 사이 간격 */
}

.panel h3 {
	text-align: left;
	font-size: 20px;
	margin-bottom: 10px;
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
			<div class="logo">
				<h1>Web Project 홈페이지</h1>
				<h2>총 매출</h2>
			</div>
		</nav>
	</header>
	<main>
		<div class="totalMoney">
			<h2>
				<!-- fmt:formatNumber 이용해서 금액을 나타내는 숫자에 1000단위로 쉼표 추가 -->
				총 매출액 <fmt:formatNumber value="${totalSales}" type="number" pattern="#,##0"/>원
			</h2>
		</div>
		<div class="panel">
			<h3>판매 내역</h3>
			<table>
				<thead>
					<tr>
						<th>no</th>
						<th>상품명</th>
						<th>상품브랜드</th>
						<th>판매수량</th>
						<th>결제금액</th>
						<th>아이디</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>회원등급</th>
						<th>배송지(일단은 회원주소)</th>
						<th>결제일시</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty salesHistoryList}"> <!-- 리스트가 비어있을 경우 : 판매 내역이 없을 경우 -->
						<tr>
							<td colspan="10">판매 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="sales" items="${salesHistoryList}">
						<tr>
							<td>${sales.no}</td>
							<td>${sales.cloth_name}</td>
							<td>${sales.cloth_brand}</td>
							<td>${sales.payment_count}</td>
							<!-- fmt:formatNumber 이용해서 금액을 나타내는 숫자에 1000단위로 쉼표 추가 -->
							<td><fmt:formatNumber value="${sales.cloth_price * sales.payment_count}" type="number" pattern="#,##0"/>원</td>
							<td>${sales.user_id}</td>
							<td>${sales.user_name}</td>
							<td>${sales.user_phone}</td>
							<td>${sales.user_grade}</td>
							<td>${sales.user_address}</td>
							<td>
                                <!-- fmt:formatDate 이용해서 payment_date를 날짜와 시간 형식으로 출력 -->
                                <fmt:formatDate value="${sales.payment_date}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>