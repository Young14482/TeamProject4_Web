<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/login/Login.css">
</head>
<body>

	<div class="login-page">
		<form id="loginForm" method="POST">
			<div class="login">
				<label for="Id">아이디</label> <input type="text" id="Id" name="Id"
					required>
			</div>

			<div class="login">
				<label for="Password">비밀번호</label> <input type="password"
					id="Password" name="Password" required>
			</div>

			<button class="login-btn" type="button" onclick="submitForm()">로그인</button>
		</form>

		<div class="login-modify">
			<button>회원가입</button>
			<button>아이디/비밀번호찾기</button>
			<button>취소</button>
		</div>
	</div>

</body>
</html>