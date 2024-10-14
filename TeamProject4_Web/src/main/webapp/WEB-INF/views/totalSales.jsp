<!-- 작성자 : 이나겸 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 태그 라이브러리 중 jstl의 core -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 태그 라이브러리 중 jstl의 fmt -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

.totalMoney {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 93%;
	margin-bottom: 20px; /* 패널 사이 간격 */
	text-align: center;
}

h3 {
	white-space: nowrap; /* 개행이 일어나지 않도록 */
}

.graphPanel {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 93%;
    margin-bottom: 20px; /* 패널 사이 간격 */
    height: 700px; 
}

#salesChart {
    width: 100% !important; /* 캔버스의 너비를 패널에 맞게 설정 */
    height: 450px !important; /* 캔버스의 높이를 줄임 */
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

#searchPanel {
	display: flex; /* Flexbox 활성화 */
	justify-content: flex-end; /* 요소들을 오른쪽으로 정렬 */
	align-items: center; /* 요소들을 수직 중앙 정렬 */
	margin-left: auto; /* 검색 창이 오른쪽으로 이동하도록 설정 */
}

#searchPanel table {
	border: none; /* 테두리 제거 */
	margin: 0; /* 기본 마진 제거 */
	padding: 0;
}

#searchPanel td {
	border: none; /* 칸의 테두리 제거 */
	padding: 2px;
	margin: 0; /* 칸 간의 여백 제거 */
}

#searchPanel select, #searchPanel input[type="text"], #searchPanel input[type="submit"]
	{
	border-radius: 5px; /* 둥글게 설정 */
	border: 1px solid #ddd; /* 테두리 색상 설정 */
	height: 30px; /* 높이 설정 */
	padding: 0 8px; /* 좌우 패딩 */
	margin: 0; /* 기본 마진 제거 */
	box-sizing: border-box; /* 패딩과 테두리를 포함한 총 높이와 너비 계산 */
}

#searchPanel input[type="submit"] {
	background-color: #f2f2f2; /* 버튼 배경 색상 */
	color: #000; /* 버튼 텍스트 색상 */
	cursor: pointer; /* 커서 모양 변경 */
}

/* 버튼에 마우스 호버 시 효과 */
#searchPanel input[type="submit"]:hover {
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
<!-- Chart.js 추가 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
	<header>
		<nav>
			<div class="logoAndText">
				<div class="logoAndTitle">
					<div class="logo">
						<img src="/static/image/logo/logo.png" alt="로고 이미지"
							class="logo-img">
					</div>
					<h1>
						<a href="/main"
							style="color: #fff; text-decoration: none; font-size: 1.5em;">
							NO MORE SHINSA</a>
					</h1>
				</div>
				<h2>판매 관리</h2>
			</div>
		</nav>
	</header>
	<main>
		<div class="totalMoney">
			<h2 id="totalSales">
				<!-- fmt:formatNumber 이용해서 금액을 나타내는 숫자에 1000단위로 쉼표 추가 -->
				총 매출액
				<fmt:formatNumber value="${totalSales}" type="number"
					pattern="#,##0" />
				원
			</h2>
		</div>
		<div class="graphPanel">
			<h3>날짜별 매출 그래프</h3>
			<!-- 그래프를 그릴 캔버스 추가 -->
			<canvas id="salesChart"></canvas>
		</div>
		<div class="panel">
			<h3>판매 내역</h3>
			<div class="panel2">
				<div class="panel3">
					<div id="salesCount">
						총 판매 건 수 :
						<%=request.getAttribute("salesHistoryCount")%>건
					</div>
					<div id="searchPanel">
						<form id="searchForm" method="GET" action="searchSales">
							<table>
								<tr>
									<td><select id="searchField" class="searchOption"
										name="searchSalesField">
											<option value="salesDate">결제일시</option>
											<option value="salesUserId">회원아이디</option>
											<option value="salesClothName">상품명</option>
											<option value="salesClothBrand">브랜드</option>
									</select></td>
									<td><input type="text" id="searchText"
										class="searchOption" placeholder="검색어 입력"
										name="searchSalesText" maxlength="100"></td>
									<td><input type="submit" value="검색"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th>no</th>
						<th>상품명</th>
						<th>상품브랜드</th>
						<th>판매수량</th>
						<th>아이디</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>회원등급</th>
						<th>배송지</th>
						<th>결제금액</th>
						<th>결제일시</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty salesHistoryList}">
						<!-- 리스트가 비어있을 경우 : 판매 내역이 없을 경우 -->
						<tr>
							<td colspan="11">판매 내역이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="sales" items="${salesHistoryList}">
						<tr>
							<td>${sales.no}</td>
							<td>${sales.cloth_name}</td>
							<td>${sales.cloth_brand}</td>
							<td>${sales.payment_count}</td>
							<td>${sales.user_id}</td>
							<td>${sales.user_name}</td>
							<td>${sales.user_phone}</td>
							<td>${sales.user_grade}</td>
							<td>${sales.user_address}</td>
							<td>
								<!-- fmt:formatNumber 이용해서 금액을 나타내는 숫자에 1000단위로 쉼표 추가 --> <fmt:formatNumber
									value="${sales.cloth_price * sales.payment_count}"
									type="number" pattern="#,##0" />원
							</td>
							<td>
								<!-- fmt:formatDate 이용해서 payment_date를 날짜와 시간 형식으로 출력 --> <fmt:formatDate
									value="${sales.payment_date}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
	<script>
    // JSP에서 날짜별 매출 데이터를 배열로 준비
    const labels = [<c:forEach var="entry" items="${salesGroupedByDate}">'${entry.key}',</c:forEach>];
    const data = [<c:forEach var="entry" items="${salesGroupedByDate}">${entry.value},</c:forEach>];

    // Chart.js를 이용해 그래프 생성
    const ctx = document.getElementById('salesChart').getContext('2d');
    const salesChart = new Chart(ctx, {
        type: 'line', // 라인 그래프
        data: {
            labels: labels, // 날짜 배열 (YYYY.MM.DD 형식)
            datasets: [{
                label: '날짜별 매출액',
                data: data, // 날짜별 매출액 데이터
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true // y축을 0에서 시작
                }
            }
        }
    });
</script>
</body>
</html>