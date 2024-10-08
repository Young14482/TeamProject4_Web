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
	window.location = "http://localhost:8080/main";
}





