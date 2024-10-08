<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./login/Login.css">
</head>
<body>

	<div class="login-page">
		<form id="loginForm" method="POST">
			<div class="login">
				<label for="Id">아이디</label> <input type="text" id="Id" name="Id"
					required>
			</div>

			<div class="login">
				<label for="Password">비밀번호</label> <input type="password" id="pw"
					name="pw" required>
			</div>
			<button class="login-btn" type="submit">로그인</button>
		</form>

		<div class="login-modify">
			<button>회원가입</button>
			<button id="search">아이디/비밀번호찾기</button>
			<button class="cansel" type="button">취소</button>
		</div>
	</div>
	<!-- 아이디 비밀번호 뭐찾을지 물어보는 다이얼 -->
	<dialog class="dialog">
	<h3>찾고자 하는 버튼을 클릭하세요</h3>
	<div class="dialogBtn">
		<button class="idSearch" type="button">아이디 찾기</button>
		<button class="pwSearch" type="button" style="margin-left: 15px">비밀번호
			찾기</button>
		<button class="dialogClose" type="button" style="margin-left: 15px">종료</button>
	</div>
	</dialog>
	<!-- 아이디찾기 다이얼 -->
	<dialog class="id-Dialog">
	<div>
		<label>이름<input type="text" class="userName"></label> <label>생년월일<input
			type="text" class="userBirth"></label>
		<button type="button" class="idBtn">아이디 찾기</button>
		<button class="dialogClose" type="button" style="margin-left: 15px">종료</button>
	</div>
	</dialog>

	<dialog class="searchOkId">
	<h3>아이디</h3>
	<h5 class="searchId"></h5>
	<button class="dialogClose" type="button" style="margin-left: 15px">종료</button>
	</dialog>

	<!-- 비밀번호 변경 다이얼 -->
	<dialog class="pw-Dialog">
	<div>
		<label>아이디<input type="text" class="userId"></label> <label>이름<input
			type="text" class="userName"></label>
		<button type="button" class="PwBtn">비밀번호 찾기</button>
		<button class="dialogClose" type="button" style="margin-left: 15px">종료</button>
	</div>
	</dialog>

	<dialog class="PwChang-Dialog">
		<div class="login">
			<label for="password">비밀번호</label> <input type="password"
				id="password" name="password" required>
		</div>
		<div class="login">
			<label for="passwordConfirm">비밀번호 확인</label> <input type="password"
				id="passwordConfirm" name="passwordConfirm" required disabled>
			<span id="passwordMessage" class="message"></span>
		</div>
		<button class="ChangPassWord" type="button" style="margin-left: 15px">비밀번호변경</button>
		<button class="dialogClose" type="button" style="margin-left: 15px">종료</button>
	</dialog>





</body>
<script src="./login/login.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
</html>