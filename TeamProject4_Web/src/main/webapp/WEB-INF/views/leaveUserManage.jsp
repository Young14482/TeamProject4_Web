<!-- 작성자 : 이나겸 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴 회원 관리 페이지</title>
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

button {
	border: 1px solid #ddd; /* 표와 동일한 테두리 색상 */
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

.gradeSelect {
	border-radius: 5px; /* 테두리 둥글게 설정 */
	border: 1px solid #ddd; /* 테두리 색상 설정 */
	height: 30px; /* 높이 설정 */
	padding: 0 8px; /* 좌우 패딩 */
	box-sizing: border-box; /* 패딩과 테두리를 포함한 총 높이와 너비 계산 */
}

.btnChangeGrade {
	border: 1px solid #ddd; /* 드롭박스와 동일한 테두리 색상 */
	border-radius: 5px; /* 테두리를 둥글게 설정 */
	height: 30px; /* 드롭박스와 맞추기 위한 높이 설정 */
	padding: 5px 10px; /* 패딩 추가 */
	background-color: #f2f2f2; /* 버튼 배경 색상 */
	color: #000; /* 버튼 텍스트 색상 */
	cursor: pointer; /* 커서 모양 변경 */
}

/* 버튼에 마우스 호버 시 효과 */
.btnChangeGrade:hover {
	background-color: #e0e0e0; /* 호버 시 버튼 색상 변경 */
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<!-- leaveUserManageManage.js 파일과 연결함 -->
<script src="/management/leaveUserManage.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
				<h2>탈퇴 회원 관리</h2>
			</div>
		</nav>
	</header>
	<main>
		<div class="panel">
			<h3>탈퇴 회원 목록</h3>
			<div class="panel2">
				<div
					style="display: flex; justify-content: space-between; align-items: center;">
					<div id="leaveUserCount">
						탈퇴 회원수 :
						<%=request.getAttribute("leaveUserCount")%>명
					</div>
					<div id="searchPanel">
						<form id="searchForm" method="GET" action="/searchLeaveUser">
							<table>
								<tr>
									<td><select id="searchField" class="searchOption"
										name="searchLeaveField">
											<option value="leaveUserId">회원아이디</option>
											<option value="leaveUserName">회원명</option>
									</select></td>
									<td><input type="text" id="searchText"
										class="searchOption" placeholder="검색어 입력"
										name="searchLeaveText" maxlength="100"></td>
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
						<th>전화번호</th>
						<th>회원계정복구</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty leaveList}">
						<!-- 리스트가 비어있을 경우 : 판매 내역이 없을 경우 -->
						<tr>
							<td colspan="10">탈퇴 회원이 없습니다.</td>
						</tr>
					</c:if>
					<!-- forEach로 leaveList 순회해서 표에 탈퇴 회원을 다 나타낼수 있도록 함 -->
					<c:forEach var="user" items="${leaveList}">
						<tr>
							<td>${user.no}</td>
							<td>${user.user_id}</td>
							<td>${user.user_name}</td>
							<td>${user.user_phone}</td>
							<td><button class="btnChangeLeave"
									data-user-id="${user.user_id}">회원계정복구</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>