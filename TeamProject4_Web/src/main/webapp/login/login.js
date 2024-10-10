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
// 아이디를 찾을지 비밀번호를 찾을지 찾는 
let dialog = document.querySelector(".dialog");
function searchs() {
   dialog.showModal();
}

let dialogCloseBtn = dialog.querySelector(".dialogClose");

dialogCloseBtn.addEventListener("click", closeDialog);
function closeDialog() {
   dialog.close();
   if (IdDialog.open) {
      IdDialog.close();
   }
   if (PwDialog.open) {
      PwDialog.close();
   }
   if (searchOkdialog.open) {
      searchOkdialog.close();
   }
   if (PwChangdialog.open) {
      PwChangdialog.close();
   }
}
// 아이디 찾기 버튼
let IdOpenBtn = dialog.querySelector(".idSearch");
IdOpenBtn.addEventListener('click', IdOpen);


// 비밀번호 찾기 버튼
let PwOpenBtn = dialog.querySelector(".pwSearch");
PwOpenBtn.addEventListener('click', PwOpen);


// 아이디 찾기 다이얼
let IdDialog = document.querySelector(".id-Dialog");
function IdOpen() {
   IdDialog.showModal();
}

// 비밀번호 변경 다이얼
let PwDialog = document.querySelector(".pw-Dialog");
function PwOpen() {
   PwDialog.showModal();
}
// 아이디 버튼 종료
IdDialog.querySelector(".dialogClose").addEventListener('click', closeDialog);
// 비밀번호 버튼 종료
PwDialog.querySelector(".dialogClose").addEventListener('click', closeDialog);


// 최종 아이디 찾기 실행
IdDialog.querySelector(".idBtn").addEventListener('click', IdSearch);
let SearchUserId;
let searchOkdialog = document.querySelector(".searchOkId");
let searchOk = searchOkdialog.querySelector(".searchId");
searchOkdialog.querySelector(".dialogClose").addEventListener('click', closeDialog);
function IdSearch() {
   let Name = IdDialog.querySelector(".userName");
   let Brith = IdDialog.querySelector(".userBirth");

   if (Name.value.length === 0) {
      Name.innerText = "";
      alert("올바른 값을 넣으세요");
      return;
   }
   if (Brith.value.length == 0) {
      Brith.innerText = "";
      alert("올바른 값을 넣으세요");
      return;
   }
   let userBrith = ChangDate(Brith.value);

   let formData = {
      "name": Name.value,
      "birth": userBrith
   };
   // 필드로 만들어 놓은 객체를 JSON만든다.
   let str = JSON.stringify(formData);

   let url = "/user/Id";
   return fetch(url, {
      method: "post",
      body: str
   }).then((resp) => {
      if (resp.status === 416) {
         throw new Error("가입되어있는 회원이 아닙니다");
      } else if (resp.status === 200) {
         return resp.text()
      }
   }).then((Object) => {
      SearchUserId = Object;
      searchOkdialog.showModal();
      searchOk.innerText = SearchUserId;
   }).catch((e) => {
      alert(e);
   })
}


// 최종 비밀번호 찾기 실행
PwDialog.querySelector(".PwBtn").addEventListener('click', PwSearch);

let PwChangdialog = document.querySelector(".PwChang-Dialog");
let faId
let faName
function PwSearch() {
   let Id = PwDialog.querySelector(".userId");
   let Name = PwDialog.querySelector(".userName");

   if (Name.value.length === 0) {
      Name.innerText = "";
      alert("올바른 값을 넣으세요");
      return;
   }
   if (Id.value.length === 0) {
      Id.innerText = "";
      alert("올바른 값을 넣으세요");
      return;
   }

   let formData = {
      "id": Id.value,
      "name": Name.value
   };
   // 필드로 만들어 놓은 객체를 JSON만든다.
   let str = JSON.stringify(formData);

   let url = "/user/pw";
   return fetch(url, {
      method: "post",
      body: str
   }).then((resp) => {
      if (resp.status === 416) {
         throw new Error("가입되어있는 회원이 아닙니다");
      } else {
         faId = Id.value;
         faName = Name.value;
         PwChangdialog.showModal();
      }
   }).catch((e) => {
      alert(e);
   })
}

let password = PwChangdialog.querySelector("#password");
let passwordConfirm = PwChangdialog.querySelector("#passwordConfirm");
let passwordMessage = PwChangdialog.querySelector("#passwordMessage");
let ChangePw = PwChangdialog.querySelector(".ChangPassWord");
PwChangdialog.querySelector(".dialogClose").addEventListener('click', closeDialog);



password.addEventListener("input", passwordLengthCheck);

function passwordLengthCheck() {
   if (password.value.length > 0) {
      passwordConfirm.removeAttribute('disabled');
   } else if (password.value.length == 0) {
      passwordConfirm.setAttribute('disabled');
   }
}

passwordConfirm.addEventListener("input", passwordCheck);

function passwordCheck() {
   if (password.value === passwordConfirm.value) {
      passwordMessage.innerText = "확인"
   } else {
      passwordMessage.innerText = "불가"
   }
}


ChangePw.addEventListener('click', passwordChang);
// 비밀번호 변경
function passwordChang() {
   const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,20}$/;
   if (!passwordRegex.test(passwordConfirm.value)) {
      alert("비밀번호는 대문자,특수문자,숫자 각1개이상과 8자 이상");
      password.value = "";
      passwordConfirm.value = ""; // 잘못 입력헀을 경우 값을 초기화
      return;
   }
   let pwTemp = sha256(passwordConfirm.value);
   let formData = {
      "id": faId,
      "name": faName,
      "pw": pwTemp
   };
   let str = JSON.stringify(formData);
   let url = "/user/pw";
   return fetch(url, {
      method: "post",
      body: str
   }).then((resp) => {
      if (resp.status === 414) {
         throw new Error("가입되어있는 회원이 아닙니다");
      } else if (resp.status === 200) {
         alert("비밀번호 변경 완료");
         window.location = "http://localhost:8080/main";
      }
   }).catch((e) => {
      alert("비밀번호 변경 실패");
   });

}


function ChangDate(date) {
   const year = parseInt(date.substring(0, 2));
   const month = date.substring(2, 4);
   const day = date.substring(4, 6);

   // 연도를 2000년대인지 1900년대인지 판단
   const fullYear = year <= 30 ? `20${year}` : `19${year}`;

   // 11자리 날짜 형식으로 반환
   return `${fullYear}-${month}-${day}`;
}
