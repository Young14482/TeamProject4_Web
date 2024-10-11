<!-- 작성자 : 이나겸 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차단 회원 관리 페이지</title>
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
	width: 95%;
	margin-bottom: 20px; /* 패널 사이 간격 */
}

.panel h3 {
	text-align: left;
	font-size: 20px;
	margin-bottom: 10px;
}

#searchPanel {
	display: flex; /* Flexbox 활성화 */
	justify-content: flex-start; /* 요소들을 왼쪽으로 정렬 */
	align-items: center; /* 요소들을 수직 중앙 정렬 */
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

/* panel에 마우스 호버 시 그림자 확대 */
.panel:hover {
	box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
}
</style>
</head>
<body>
	<header>
		<nav>
			<div class="logo">
				<h1>Web Project 홈페이지</h1>
				<h2>회원 관리</h2>
			</div>
		</nav>
	</header>
	<main>
		<div class="panel">
			<h3>차단 회원 목록</h3>
			<div class="panel2">
				<div
					style="display: flex; justify-content: space-between; align-items: center;">
					<div id="userCount">
						총 회원수 :
						<%=request.getAttribute("blockUserCount")%>명
					</div>
					<div id="searchPanel">
						<form id="searchForm" method="GET" action="/searchBlockUser">
							<table>
								<tr>
									<td>
										<select id="searchField" class="searchOption"
												name="searchBlockField">
											<option value="BlockUserId">회원아이디</option>
											<option value="BlockUserGrade">회원등급</option>
											<option value="BlockUserGender">성별</option>
										</select>
									</td>
									<td><input type="text" id="searchText"
										class="searchOption" placeholder="검색어 입력" name="searchBlockText"
										maxlength="100"></td>
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
						<th>아이디</th>
						<th>이름</th>
						<th>성별</th>
						<th>생년월일</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>회원등급</th>
					</tr>
				</thead>
				<tbody>
					<!-- forEach로 blackList 순회해서 표에 차단된 회원을 다 나타낼수 있도록 함 -->
					<c:forEach var="user" items="${blackList}">
						<tr>
							<td>${user.no}</td>
							<td>${user.user_id}</td>
							<td>${user.user_name}</td>
							<td>${user.user_gender}</td>
							<td>${user.user_birth}</td>
							<td>${user.user_phone}</td>
							<td>${user.user_address}</td>
							<td>${user.user_grade}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>