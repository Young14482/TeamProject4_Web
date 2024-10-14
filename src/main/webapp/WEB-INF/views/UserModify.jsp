<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보 수정</title>
<link rel="stylesheet" type="text/css" href="/login/UserModify.css">
</head>
<body>
	<div class="modify-page">
		<h2>회원정보 정보</h2>
		<div class="modify">
			<label>이름: <span id="nameValue"><%=request.getAttribute("userName")%></span></label>
			<label>전화번호: <span id="phoneValue"><%=request.getAttribute("userPhone")%></span></label>
			<label>주소: <span id="addressValue"><%=request.getAttribute("userAddress")%></span></label>
			<label>아이디: <span id="idValue"><%=request.getAttribute("userId")%></span></label>
			<label>회원 등급: <span id="gradeValue"><%=request.getAttribute("userGrade")%></span></label>
		</div>
		<div class="modify-buttons">
			<button onclick="openModifyDialog()">정보 수정</button>
			<button class="PwBtn">비밀번호 변경</button>
			<button onclick="leaveUser()">회원 탈퇴</button>
			<button onclick="closePage()">종료</button>
		</div>
	</div>

	<dialog class="leaveUserDialog">
	<div>
		<label>비밀번호<input type="password" class="leaveUserPw"></label>
	</div>
	<button onclick="leaveUserPwTest()">회원 탈퇴</button>
	<button onclick="closeLeaveDialog()">종료</button>
	</dialog>

	<dialog class="leaveUserCheckDialog">
	<div>
		<h3>탈퇴 하시겠습니까?</h3>
	</div>
	<button onclick="deleteUser()">회원 탈퇴</button>
	<button onclick="userCheckLeaveClose()">종료</button>
	</dialog>



	<dialog class="pwChangeDialog">
	<div>
		<div>
			<label>기존 비밀번호<input type="password" class="oldPw"></label>
		</div>
		<div>
			<label>신규 비밀번호<input type="password" class="newPw"></label>
		</div>
		<div>
			<label>신규 비밀번호 확인<input type="password" class="newPwCheck"></label>
		</div>
		<span class="message"></span>
	</div>
	<button class="PwChangBtn">비밀번호 변경</button>
	<button onclick="DialogClose()">종료</button>
	</dialog>




	<div id="modifyDialog" class="dialog" style="display: none;">
		<h3>정보 수정하기</h3>
		<label>이름: <input type="text" id="nameInput"
			value="<%=request.getAttribute("userName")%>"></label> <label>전화번호:
			<input type="text" id="phoneInput"
			value="<%=request.getAttribute("userPhone")%>">
		</label>
		<div class="login">
			<label for="address">주소</label> <input type="text" id="postcode"
				placeholder="우편번호" readonly> <input type="button"
				onclick="execDaumPostcode()" value="우편번호 찾기"><br> <input
				type="text" id="address" placeholder="주소" readonly><br>
			<input type="text" id="detailAddress" placeholder="상세주소">
		</div>
		<div class="dialog-buttons">
			<button onclick="saveChanges()">저장</button>
			<button onclick="closeModifyDialog()">취소</button>
		</div>
	</div>
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
<script src="/login/UserModify.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</html>