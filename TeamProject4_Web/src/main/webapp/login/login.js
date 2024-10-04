// 로그인

let loginbtn = document.querySelector("#loginForm");

loginbtn.addEventListener("submit", submitForm);

function submitForm(e) {
	e.preventDefault();

	let url = "/user";

	let id = document.querySelector("#Id");
	
	let pw = document.querySelector("#pw");
	let pwTemp = sha256(pw.value);
	
	let formData = {
		"id": id.value,
		"pw": pwTemp
	};
	let str = JSON.stringify(formData);

	return fetch(url, {
		method: "POST",
		body: str
	}).then((resp) => {
		if (resp.status === 405) {
			alert("가입되어있는 회원이 아닙니다.");
		} else if (resp.status === 403) {
			alert("비밀번호가 올바르지 않습니다.");
		} else {
			window.location = "http://localhost:8080/main";
		}
	});
}
// 뒤로가기 버튼
let cansel = document.querySelector(".cansel")
cansel.addEventListener("click", CanselBtn);
// 뒤로가기
function CanselBtn() {
	window.location = "http://localhost:8080/main";
}
// 아이디/비밀번호 액션
let searchbtn = document.getElementById("search");

searchbtn.addEventListener("click", searchs);

let dialog = document.querySelector(".dialog");
function searchs(){
	dialog.showModal();
}



























