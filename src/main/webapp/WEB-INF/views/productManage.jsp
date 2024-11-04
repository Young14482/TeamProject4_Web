<!-- 작성자 : 이나겸, 이진석 -->

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
<link rel="stylesheet" type="text/css"
	href="./management/productManage.css">
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

.panel {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 93%;
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

.panel3 {
	display: flex;
	flex-direction: column; /* 수직 배치 */
	align-items: flex-end; /* 오른쪽 정렬 */
}

.addButton {
	width: auto; /* 버튼의 너비는 내용에 맞게 자동 설정 */
}

#searchPanel {
	display: flex; /* Flexbox 활성화 */
	justify-content: flex-start; /* 요소들을 왼쪽으로 정렬 */
	align-items: center; /* 요소들을 수직 중앙 정렬 */
	margin-bottom: 5px; /* 검색 패널과 버튼 사이 간격 */
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
				<h2>상품 관리</h2>
			</div>
		</nav>
	</header>
	<main>
		<div class="panel">
			<div class="panel-header">
				<div class="panel2">
					<h3>상품 목록</h3>
					<div>
						총 상품 개수 :
						<%=request.getAttribute("allClothCount")%>개
					</div>
				</div>
				<div class="panel3">
					<div id="searchPanel">
						<form id="searchForm" method="GET" action="/searchProduct">
							<table>
								<tr>
									<td><select id="searchField" class="searchOption"
										name="searchProductField">
											<option value="productName">상품명</option>
											<option value="productBrand">브랜드</option>
									</select></td>
									<td><input type="text" id="searchText"
										class="searchOption" placeholder="검색어 입력"
										name="searchProductText" maxlength="100"></td>
									<td><input type="submit" value="검색"></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="addButton">
						<button onclick="dialogOpen(this)">상품추가</button>
					</div>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th rowspan="2">no</th>
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
					<c:if test="${empty allClothList}">
						<!-- 리스트가 비어있을 경우 : 판매 내역이 없을 경우 -->
						<tr>
							<td colspan="14">상품이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="cloth" items="${allClothList}">
						<tr class="${ cloth.no }">
							<td>${cloth.no}</td>
							<td class="no" style="display: none">${cloth.cloth_num}</td>
							<td data-colname="상품명" class="n" onclick="mDialogOpen(event)">${cloth.cloth_name}</td>
							<td data-colname="브랜드" class="b" onclick="mDialogOpen(event)">${cloth.cloth_brand}</td>
							<td data-valprice="${cloth.cloth_price}" data-colname="가격"
								class="p" onclick="mDialogOpen(event)">
								<!-- fmt:formatNumber 이용해서 금액을 나타내는 숫자에 1000단위로 쉼표 추가 --> <fmt:formatNumber
									value="${cloth.cloth_price}" type="number" pattern="#,##0" />원
							</td>
							<td data-colname="성별구분" class="g" onclick="mDialogOpen(event)">${cloth.cloth_gender}</td>
							<td data-colname="S 사이즈 갯수" class="s"
								onclick="mDialogOpen(event)">${cloth.cloth_size_s}</td>
							<td data-colname="M 사이즈 갯수" class="m"
								onclick="mDialogOpen(event)">${cloth.cloth_size_m}</td>
							<td data-colname="L 사이즈 갯수" class="l"
								onclick="mDialogOpen(event)">${cloth.cloth_size_l}</td>
							<td data-colname="XL 사이즈 갯수" class="xl"
								onclick="mDialogOpen(event)">${cloth.cloth_size_xl}</td>
							<td data-colname="XXL 사이즈 갯수" class="xxl"
								onclick="mDialogOpen(event)">${cloth.cloth_size_xxl}</td>
							<td><button onclick="mlcDialogOpen(event)">수정</button></td>
							<td><button onclick="deleteDialogOpen(event)">삭제</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<dialog class="deletLastCheckDialog">
		<h3>상품 수정 하시겠습니까?</h3>
		<button onclick="deleteCloth()">네</button>
		<button onclick="deleteDialogClose()">아니요</button>
		</dialog>

		<dialog class="modifyLastCheckDialog">
		<h3>상품 수정 하시겠습니까?</h3>
		<button onclick="modifyClothUpdate()">네</button>
		<button onclick="mlcDialogClose()">아니요</button>
		</dialog>

		<dialog class="modifyDialog">
		<h2 class="colName">상품명</h2>
		<div>
			<label><input type="text" class="innerText"></label>
		</div>
		<br>
		<button onclick="modifyClothinput(event)">수정</button>
		<button onclick="mDialogClose()">종료</button>
		</dialog>

		<dialog class="sizeListDialog">
		<h3>등록 상품 갯수 작성</h3>
		<div id="divS">
			<label>S사이즈<input type="number" id="sSize"></label>
		</div>
		<div id="divM">
			<label>M사이즈<input type="number" id="mSize"></label>
		</div>
		<div id="divL">
			<label>L사이즈<input type="number" id="lSize"></label>
		</div>
		<div id="divXL">
			<label>XL사이즈<input type="number" id="xlSize"></label>
		</div>
		<div id="divXXL">
			<label>XXL사이즈<input type="number" id="xxlSize"></label>
		</div>
		<button onclick="clothRegister()">상품 등록 등록</button>
		<button onclick="sizedialogClose()">종료</button>
		</dialog>

		<dialog class="clothInfoDialog">
		<h3>상품 등록</h3>
		<div>
			<label>상품명 <input type="text" id="clothName"></label>
		</div>
		<div>
			<label>브랜드<input type="text" id="clothBrand"></label>
		</div>
		<div>
			<label>상품가격<input type="number" id="clothPrice"></label>
		</div>

		<select id="comboGender">
			<option value="0">권장 성별</option>
			<c:forEach var="gender" items="${gender}">
				<option value=${ gender }>${ gender }</option>
			</c:forEach>
		</select> <br>
		<select id="comboColor">
			<option value="0">옷의 색상</option>
			<c:forEach var="color" items="${color}">
				<option value=${ color.color_num }>${ color.color_name }</option>
			</c:forEach>
		</select> <select id="comboS">
			<option value="0">옷의 종류</option>
			<c:forEach var="sList" items="${ sList }">
				<option value=${ sList.category_num }>${ sList.category_name }</option>
			</c:forEach>
		</select> <select id="comboSeason">
			<option value="0">옷의 시즌</option>
			<c:forEach var="season" items="${ season }">
				<option value=${ season.season_num }>${ season.season_name }
				</option>
			</c:forEach>
		</select> <br>
		<select id="comboUsage1">
			<option value="0">옷의 사용처1</option>
			<c:forEach var="usage" items="${ usage }">
				<option value=${ usage.usage_num }>${ usage.usage_name }</option>
			</c:forEach>
		</select> <select id="comboUsage2">
			<option value="0">옷의 사용처2</option>
			<c:forEach var="usage" items="${ usage }">
				<option value=${ usage.usage_num }>${ usage.usage_name }</option>
			</c:forEach>
		</select> <select id="comboUsage3">
			<option value="0">옷의 사용처3</option>
			<c:forEach var="usage" items="${ usage }">
				<option value=${ usage.usage_num }>${ usage.usage_name }</option>
			</c:forEach>
		</select> <br>
		<select id="comboMinSize">
			<option value="0">최소 사이즈</option>
			<c:forEach var="size" items="${size}">
				<option value=${ size.key}>${ size.value }</option>
			</c:forEach>
		</select> <select id="comboMaxSize">
			<option value="0">최대 사이즈</option>
			<c:forEach var="size" items="${size}">
				<option value=${ size.key}>${ size.value }</option>
			</c:forEach>
		</select>

		<div>
			<label>상품 설명<textarea id="clothExplanation" rows="4"
					cols="50"></textarea></label>
		</div>
		<button onclick="clothEnroll()">상품 갯수 등록</button>
		<button onclick="dialogClose()">종료</button>
		</dialog>
	</main>
</body>
<!-- productManage.js 파일과 연결함 -->
<script src="./management/productManage.js"></script>
</html>