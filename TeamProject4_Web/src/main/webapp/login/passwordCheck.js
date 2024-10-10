let password = document.getElementById("password");
let passwordConfirm = document.getElementById("passwordConfirm");
let passwordMessage = document.getElementById("passwordMessage");

let userIdChecking = document.querySelector(".IdCheckText");

let IdCheckBtn = document.querySelector(".IdCheck");

IdCheckBtn.addEventListener("click", IdCheckBtnEvent);

function IdCheckBtnEvent() {
	let Id = document.getElementById("userId");

	let formData = {
		"id": Id.value,
	};
	// 필드로 만들어 놓은 객체를 JSON만든다.
	let str = JSON.stringify(formData);
	let url = "/signup";
	return fetch(url, {
		method: "put",
		body: str
	}).then((resp) => {
		if (resp.status === 200) {
			userIdChecking.innerText = "불가능";
		} else if (resp.status === 414) {
			console.log("Asdf");
			userIdChecking.innerText= "가능";
		}
	});
}








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

// 생년월일 6자리 확인
function ChangDate(date) {
	const year = parseInt(date.substring(0, 2));
	const month = date.substring(2, 4);
	const day = date.substring(4, 6);

	// 연도를 2000년대인지 1900년대인지 판단
	const fullYear = year <= 30 ? `20${year}` : `19${year}`;

	// 11자리 날짜 형식으로 반환
	return `${fullYear}-${month}-${day}`;
}



function signupCheck() {
	// 유저 이름
	let name = document.getElementById("name");
	// 유저 이름 조건식
	const expNameText = /^[가-힣]+$/;
	if (!expNameText.test(name.value)) {
		alert("이름은 한글로 작성해주세요");
		name.value = '';
		return;
	}
	// 유저 전화번호
	let phone = document.getElementById("phone");
	const expHpText = /^\d{11}$/;
	if (!expHpText.test(phone.value)) {
		alert("전화번호를 확인해주세요");
		phone.value = '';
		return;
	}
	// 유저 생년월일
	let birthdate = document.getElementById("birthdate");
	// 유저 생년월일 조건식
	const expBrirthText = /^(?:\d{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;

	//const expBrirthText = /^\d{6}$/;
	if (!expBrirthText.test(birthdate.value)) {
		alert("생년월일은 숫자 6자리");
		birthdate.value = '';
		return;
	}
	// 생년월일 변환
	let birthday = ChangDate(birthdate.value);
	console.log(birthday);

	// 유저가 선택한 값
	let genderSelect = document.querySelector('input[name="gender"]:checked');
	let gender;
	if (genderSelect) {
		if (genderSelect.value === '남') {
			gender = "남자";
		} else if (genderSelect.value === '여') {
			gender = "여자";
		}
	} else {
		alert("성별은 선택해 주세요");
		return;
	}
	// 유저 주소
	let postcode = document.getElementById("postcode");
	let address = document.getElementById("address");
	let detailAddress = document.getElementById("detailAddress");
	// 유저 풀주소
	let fulllAddress = `${postcode.value} ${address.value} ${detailAddress.value}`;

	// 유저 아이디
	let userId = document.getElementById("userId");
	// 유저 아이디 조건식
	const expIdText = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{4,20}$/
	// 조건에 맞는지 확인
	if (!expIdText.test(userId.value)) {
		alert('아이디는 4자 이상 20자 이하의 대소문자로 시작하는 조합입니다');
		userId.value = '';
		return;
	}
	// 유저가 입력한 비밀번호
	let userPw = document.getElementById("password");
	// 유저 비밀번호 조건식
	const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
	if (!passwordRegex.test(userPw.value)) {
		alert("비밀번호는 대문자,특수문자,숫자 각1개이상과 8자 이상");
		userPw.value = ""; // 잘못 입력헀을 경우 값을 초기화
		return;
	}
	// 비밀번호 sha256으로 암호화 작성
	let pwTemp = sha256(userPw.value);

	// 필드를 만드는거
	let formData = {
		"id": userId.value,
		"pw": pwTemp,
		"phone": phone.value,
		"name": name.value,
		"birth": birthday,
		"gender": gender,
		"address": fulllAddress,
		"grade": '일반',
		"useMoney": 0
	};
	// 필드로 만들어 놓은 객체를 JSON만든다.
	let str = JSON.stringify(formData);
	//객체들을 모아서 로 만들어서 json으로 변환
	//Json변환한걸 전송 >> 
	//받은걸 읽어서 User로 만들고 회원가입 DB에 insert

	let url = "/signup";
	//TODO 애니메이션 하나 만들기 > > 시작 


	return fetch(url, {
		method: "post",
		body: str
	}).then((resp) => {
		//TODO  애니메이션 종료
		return resp.json();
	}).then((obj) => {
		alert("회원가입 완료되었습니다.");
		window.location = "http://localhost:8080/main";
	}).catch((e) => {
		alert("회원가입에 실패...");
	});
}




// 뒤로가기 버튼
let cansel = document.querySelector(".cansel")
cansel.addEventListener("click", CanselBtn)
// 뒤로가기
function CanselBtn() {
	window.location = "http://localhost:8080/main"
}










