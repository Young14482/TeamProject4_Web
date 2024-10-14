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
// 유저 아이디
let userId;

let faddressElement = document.getElementById('addressValue');
// 다이얼
let moDialog = document.querySelector("#modifyDialog");
// 다이얼 안에 주소칸
let dialogPostcode = moDialog.querySelector("#postcode");
let dialogAddress = moDialog.querySelector("#address");
let dialogDetailAddress = moDialog.querySelector("#detailAddress");



function openModifyDialog() {
	let faddress = faddressElement.innerText || faddressElement.textContent

	let addressParts = faddress.split(/\s+/);  // 공백을 기준으로 문자열을 분할

	if (addressParts.length >= 3) {  // addressParts가 null이 아닌 경우에만 실행
		let postcodeValue = addressParts[0];           // 우편번호
		let addressValue = addressParts.slice(1, -1).join(" "); // 기본 주소
		let detailAddressValue = addressParts[addressParts.length - 1]; // 상세 주소

		// 다이얼로그 내의 주소 칸에 값 설정
		dialogPostcode.value = postcodeValue;
		dialogAddress.value = addressValue;
		dialogDetailAddress.value = detailAddressValue;
	} else {
		alert("아아아");
	}
	document.getElementById('modifyDialog').style.display = 'block';
}

function closeModifyDialog() {
	document.getElementById('modifyDialog').style.display = 'none';
}

function saveChanges() {
	let name = moDialog.querySelector('#nameInput').value;
	let phone = moDialog.querySelector('#phoneInput').value;
	let fullAddress;
	if (detailAddress.value.length > 0) {
		// 유저 풀주소
		fullAddress = `${dialogPostcode.value} ${dialogAddress.value} ${dialogDetailAddress.value}`;
	} else {
		alert("주소를 다시 입력하세요");
		return;
	}

	let formData = {
		"id": userId,
		"phone": phone,
		"name": name,
		"address": fullAddress
	};
	let str = JSON.stringify(formData);

	let url = "/modify";
	return fetch(url, {
		method: "post",
		body: str
	}).then((resp) => {
		if (resp.status === 414) {
			alert("정보 변경 에러");
		} else {
			alert("정보 변경 성공");
			closeModifyDialog();
		}
	})



}

function closePage() {
	window.location.href = "/main";
}



// 비밀번호 변경을 위한 다이얼
let PwDialog = document.querySelector(".pwChangeDialog");
// 신규비밀번호가 둘이 서로 맞는지 비교
let PwMess = PwDialog.querySelector(".message");
// 다이얼 안에 기존 비밀번호 input
let oldPwInput = PwDialog.querySelector(".oldPw");
// 다이얼 안에 신규 비밀번호 input
let newPwInput = PwDialog.querySelector(".newPw");
// 다이얼 안에 신규 비밀번호 확인 input
let newPwCheckInput = PwDialog.querySelector(".newPwCheck");
// 다이얼 안에 버튼 실행
let dialogPwBtn = PwDialog.querySelector(".PwChangBtn");
// 작성할떄마다 비밀번호 체크
newPwCheckInput.addEventListener("input", passworedCheck);
// 비밀번호 변경 버튼누르면 다이얼 생성
document.querySelector(".PwBtn").addEventListener("click", DialogModal);
function DialogModal() {
	PwDialog.showModal();
}

function DialogClose() {
	PwDialog.close();
}
// 비밀번호 비교 함수
function passworedCheck() {
	if (newPwInput.value === newPwCheckInput.value) {
		PwMess.value = "가능"
		PwMess.innerText = "가능"
	} else {
		PwMess.value = "불가능"
		PwMess.innerText = "불가능"
	}
}

// 비밀번호 변경 누르면 액션
dialogPwBtn.addEventListener("click", PwChanger);

function PwChanger() {
	const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
	if (!passwordRegex.test(oldPwInput.value)) {
		alert("비밀번호는 대문자,특수문자,숫자 각1개이상과 8자 이상");
		return;
	}
	let pwTemp = CryptoJS.SHA256(oldPwInput.value).toString();

	let formData = {
		"pw": pwTemp
	};
	let str = JSON.stringify(formData);

	url = "/user";
	return fetch(url, {
		method: "post",
		body: str
	}).then((resp) => {
		if (resp.status === 200) {
			if (PwMess.value === "가능") {
				const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
				if (!passwordRegex.test(newPwInput.value)) {
					alert("비밀번호는 대문자,특수문자,숫자 각1개이상과 8자 이상");
					return;
				}
				let pwTemp1 = CryptoJS.SHA256(newPwInput.value).toString();
				let formData = {
					"pw": pwTemp1
				};
				let str = JSON.stringify(formData);

				url = "/user/pw";
				return fetch(url, {
					method: "post",
					body: str
				}).then((resp) => {
					if (resp.status === 200) {
						DialogClose();
						alert("비밀번호 변경 완료")
					} else if (resp.status === 414) {
						alert("비민번호 변경 실패")
					}
				})



			} else if (PwMess.value === "불가능") {
				alert("비밀번호 확인하세요");
				return;
			} else {
				return;
			}

		} else if (resp.status === 403) {
			alert("비밀번호가 올바르지 않습니다.");
		}
	})
}
function newPasswordCheck() {
	// 다이얼 안에 신규 비밀번호 input
	// let newPwInput = PwDialog.querySelector(".newPw");
	// 다이얼 안에 신규 비밀번호 확인 input
	// let newPwCheckInput = PwDialog.querySelector(".newPwCheck");
	if (PwMess.value === "가능") {
		const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
		if (!passwordRegex.test(newPwInput.value)) {
			alert("비밀번호는 대문자,특수문자,숫자 각1개이상과 8자 이상");
			oldPwInput.value = "";
			newPwInput.value = "";
			newPwCheckInput.value = "";
			return;
		}
		let pwTemp = CryptoJS.SHA256(newPwInput.value).toString();
		let formData = {
			"pw": pwTemp
		};
		let str = JSON.stringify(formData);

		url = "/user/pw";
		return fetch(url, {
			method: "post",
			body: str
		}).then((resp) => {
			if (resp.status === 200) {
				alert("비밀번호 변경 완료")
				oldPwInput.value = "";
				newPwInput.value = "";
				newPwCheckInput.value = "";
				PwDialog.close();
			} else if (resp.status === 414) {
				alert("비민번호 변경 실패")
				oldPwInput.value = "";
				newPwInput.value = "";
				newPwCheckInput.value = "";
				PwDialog.close();
			}
		})
	} else if (PwMess.value === "불가능") {
		oldPwInput.value = "";
		newPwInput.value = "";
		newPwCheckInput.value = "";
		alert("비밀번호 확인하세요");
		return;
	}
}

let leaveDialog = document.querySelector(".leaveUserDialog");

// 회원탈퇴 
function leaveUser() {
	// 탈퇴회원 검증 다이얼 생성
	leaveDialog.showModal();
}
// 회원탈퇴 다이얼 안에 '종료' 버튼을 누르면 다이얼 종료
function closeLeaveDialog() {
	leaveDialog.close();
}
// 회원탈퇴를 위해 유저가 비밀번호를 입력한값
let passwordCh = leaveDialog.querySelector(".leaveUserPw");
// 유저의 의사를 하번더 묻는 다이얼
let userCheckLeave = document.querySelector(".leaveUserCheckDialog");

function leaveUserPwTest() {
	if (passwordCh.value.length >= 0) {
		let pwTemp = CryptoJS.SHA256(passwordCh.value).toString();
		let formData = {
			"pw": pwTemp
		};
		let str = JSON.stringify(formData);
		let url = "/userLeave";
		fetch(url, {
			method: "post",
			body: str
		}).then((resp) => {
			if (resp.status === 200) {
				userCheckLeave.showModal();
			} else if (resp, status === 403) {
				alert("비밀번호가 올바르지 않습니다.")
				passwordCh.value = "";
			}
		})
	} else {
		// 비밀번호를 입력하지 않고 버튼을 눌렀을때
		alert("비밀번호를 입력하여 주세요");
		passwordCh.value = "";
	}
}
function deleteUser() {
	let url = "/userLeave";
	fetch(url, {
		method: "put"
	}).then((resp) => {
		if (resp.status === 200) {
			alert("정상적으로 탈퇴되었습니다.");
			window.location.href = "/main";
		} else if (resp.status === 419) {
			alert("탈퇴되지 않았습니다.");
		}
	})
}
function userCheckLeaveClose() {
	userCheckLeave.close();
}