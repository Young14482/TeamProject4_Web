// 로그인

let loginbtn = document.querySelector(".login-btn");

loginbtn.addEventListener("click", submitForm());

function submitForm() {
	let url = "/uesr";

	return fetch(url, {
		method: "POST"
	}).then((resp) => resp);
}





// 회원가입
function SignupUser() {
	//let url = "/signup";
	//const password = document.getElementById("password");
	//const passwordConfirm = document.getElementById("passwordConfirm");
	//const passwordMessage = document.getElementById("passwordMessage");
	
	
	
//	let form = document.querySelector("form");

	//let userJson = JSON.stringify(Object.fromEntries(new FormData(form)));

	//return fetch(url, {
//		method: "PUT"
	//	, body: userJson
	//}).then((resp) => resp.json());
}