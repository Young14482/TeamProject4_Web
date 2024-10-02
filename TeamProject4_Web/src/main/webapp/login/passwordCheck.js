let password = document.getElementById("password");
let passwordConfirm = document.getElementById("passwordConfirm");
let passwordMessage = document.getElementById("passwordMessage");
// 비밀번호 입력시 비밀번호 확인 제크되는거
password.addEventListener("input", passwordLengthCheck);
function passwordLengthCheck() {
	if (password.value.length > 0) {
		passwordConfirm.removeAttribute('disabled');
	} else if (password.value.length == 0) {
		passwordConfirm.setAttribute('disabled');
	}
}
// 비밀번호확인입력하면서 비밀번호와 같은지 비교
passwordConfirm.addEventListener("input", passwordCheck);
function passwordCheck() {
	if (password.value === passwordConfirm.value) {
		passwordMessage.innerText = "확인"
	} else {
		passwordMessage.innerText = "불가"
	}
}
// 회원가입 버튼 누르면 회원가입 시키는 함수
let signUpdate = document.getElementById("signUpdate");
signUpdate.addEventListener("click", signupCheck);

// 주소 구현
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = ''; // 주소 변수
			if (data.userSelectedType === 'R') {
				addr = data.roadAddress;
			} else {
				addr = data.jibunAddress;
			}
			document.getElementById("postcode").value = data.zonecode;
			document.getElementById("address").value = addr;
			document.getElementById("detailAddress").focus();
		}
	}).open();
}


function signupCheck() {
	// 유저 이름
	let name = document.getElementById("name");
	// 유저 이름 조건식
	const expNameText = /^[가-힣]+$/;
	if (!expNameText.test(name.value)) {
		alert("이름은 한글로");
		return;
	}
	// 유저 전화번호
	let phone = document.getElementById("phone");
	const expHpText = /^\d{3}-\d{3,4}-\d{4}$/;
	if (!expHpText.test(phone.value)) {
		alert("전화번호는 '-' 포함할것 (3-3-4)");
		return;
	}
	// 유저 생년월일
	let birthdate = document.getElementById("birthdate");
	// 유저 생년월일 조건식
	const expBrirthText = /^\d{6}$/;
	if (!expBrirthText.test(birthdate.value)) {
		alert("생년월일은 숫자 6자리");
		return;
	}
	// 유저 주소
	let address = document.getElementById("address");
	// 유저 아이디
	let userId = document.getElementById("userId");
	// 유저 아이디 조건식
	const expIdText = /^[A-Za-z]{4,20}$/;
	// 조건에 맞는지 확인
	if (!expIdText.test(userId.value)) {
		alert('아이디는 4자 이상 20자 이하의 대소문자로 시작하는 조합입니다');
		return;
	}
	// 유저가 입력한 비밀번호
	let userPw = document.getElementById("password");
	// 유저 비밀번호 조건식
	const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
	if (!expPwText.test(userPw.value)) {
		alert("비밀번호는 대문자,특수문자,숫자 각1개이상과 8자 이상");
		return;
	}
	
	

	
	
	
	
	let url ="/signup";
	return fetch(url ,{
		method:"POST",
		body: json
	}).then((resp) => resp.json);
	
}