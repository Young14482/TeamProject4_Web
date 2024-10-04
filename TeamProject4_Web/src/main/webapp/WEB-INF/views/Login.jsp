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

	<dialog class="dialog">
		<h3>찾고자 하는 버튼을 클릭하세요</h3>
		<div class="dialogBtn">
			<button class="idSearch" type="button">아이디 찾기</button>
			<button class="pwSearch" type="button" style="margin-left: 15px">비밀번호 찾기</button>
		</div>
	</dialog>

</body>
<script src="./login/login.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
</html>