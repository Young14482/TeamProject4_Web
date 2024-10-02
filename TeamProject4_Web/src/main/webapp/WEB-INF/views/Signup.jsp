<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="/login/Login.css">
<!-- 동일한 CSS 사용 -->
</head>

<body>
	<div class="login-page">
		<h2>회원가입</h2>
		<form method="PUT">
			<div class="login">
				<label for="name">이름</label> <input type="text" id="name"
					name="name" required>
			</div>

			<div class="login">
				<label for="phone">전화번호</label> <input type="text" id="phone"
					name="phone" required>
			</div>

			<div class="login">
				<label for="birthdate">생년월일</label> <input type="text"
					id="birthdate" name="birthdate" required>
			</div>

			<div class="login">
						<label for="address">주소</label> 
						<input type="text" id="address" name="address" required>
					    <input type="text" id="postcode" placeholder="우편번호" readonly>
    					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					    <input type="text" id="address" placeholder="주소" readonly><br>
					    <input type="text" id="detailAddress" placeholder="상세주소">
					
			</div>

			<div class="login">
				<label for="userId">아이디</label> <input type="text" id="userId"
					name="userId" required>
			</div>

			<div class="login">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" required>
			</div>

			<div class="login">
				<label for="passwordConfirm">비밀번호 확인</label> <input type="password"
					id="passwordConfirm" name="passwordConfirm" required disabled>
				<span id="passwordMessage" class="message"></span>
			</div>

			<div class="login-modify">
				<button id="signUpdate" type="submit">회원가입</button>
				<button type="reset">취소</button>
			</div>
		</form>
	</div>

</body>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>



<script src="/login/passwordCheck.js"></script>
</html>