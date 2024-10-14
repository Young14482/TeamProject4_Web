// 작성자 : 이나겸

// 페이지가 로드될 때 서버로부터 데이터를 가져오기 위한 함수 호출

let clothdialog = document.querySelector(".clothInfoDialog");




let sizeDialog = document.querySelector(".sizeListDialog");


function sizedialogOpen() {
   clothRegistration();
   sizeDialog.showModal();
}

let Name = clothdialog.querySelector("#clothName");// 상품명
let Brand = clothdialog.querySelector("#clothBrand");// 상품브랜드
let Price = clothdialog.querySelector("#clothPrice");// 상품 가격

let color = clothdialog.querySelector("#comboColor"); // 옷 색상
let s = clothdialog.querySelector("#comboS"); // 옷 종류
let season = clothdialog.querySelector("#comboSeason"); // 옷 시즌
let usage1 = clothdialog.querySelector("#comboUsage1"); // 옷 사용처 1
let usage2 = clothdialog.querySelector("#comboUsage2"); // 옷 사용처 2
let usage3 = clothdialog.querySelector("#comboUsage3"); // 옷 사용처 3
// 최초값 보관
function dialogOpen() {
   initialColorValue = color.value;
   initialSValue = s.value;
   initialSeasonValue = season.value;
   initialUsage1Value = usage1.value;
   initialUsage2Value = usage2.value;
   initialUsage3Value = usage3.value;
   clothdialog.showModal();
}
// 값 초기화
function sizedialogClose() {
   color.value = initialColorValue;
   s.value = initialSValue;
   season.value = initialSeasonValue;
   usage1.value = initialUsage1Value;
   usage2.value = initialUsage2Value;
   usage3.value = initialUsage3Value;

   inputBye();
   sizeDialog.close();
}
// 값 초기화 
function dialogClose() {
   color.value = initialColorValue;
   s.value = initialSValue;
   season.value = initialSeasonValue;
   usage1.value = initialUsage1Value;
   usage2.value = initialUsage2Value;
   usage3.value = initialUsage3Value;

   inputBye();
   clothdialog.close();
}

let maxSize = clothdialog.querySelector("#clothmaxSize");// 상품 최대 사이즈
let minSize = clothdialog.querySelector("#clothminSize");// 상품 최소 사이즈
let clothEx = clothdialog.querySelector("#clothExplanation");// 상품 설명
let gender = clothdialog.querySelector("#clothGender");// 상품 권장 성별

function inputBye() {
   Name.value = "";
   Brand.value = "";
   Price.value = "";
   maxSize.value = "";
   minSize.value = "";
   clothEx.value = "";
   gender.value = "";

   mBlock.style.display = "block";
   mBlock.style.display = "block";
   lBlock.style.display = "block";
   xlBlock.style.display = "block";
   xxlBlock.style.display = "block";
}
function clothEnroll() {
   let count = 0;
   if (Name.value <= 0) {
      alert("상품명을 작성해주세요");
      Name.value = "";
      return;
   } else {
      count++;
   }
   if (Brand.value <= 0) {
      alert("브랜드를 작성해주세요");
      Brand.value = "";
      return;
   } else {
      count++;
   }
   if (Price.value <= 0) {
      alert("상품가격을 작성해주세요");
      Price.value = "";
      return;
   } else {
      count++;
   }
   if (color.value === "0") {
      alert("옷의 색상을 선택해주세요");
      return;
   } else {
      count++;
   }
   if (s.value === "0") {
      alert("옷의 종류를 선택해주세요");
      return;
   } else {
      count++;
   }
   if (season.value === "0") {
      alert("옷의 시즌을 선택해주세요");
      return;
   } else {
      count++;
   }
   if (usage1.value === "0") {
      alert("옷의 사용처1을 선택해주세요");
      return;
   } else {
      count++;
   }

   if (usage1.value === usage2.value || usage1.value === usage3.value) {
      alert("옷의 사용처는 같은 선택할 수 없습니다.");
      return;
   }

   if (maxSize.value <= 0) {
      alert("상품의 최대사이즈를 작성해주세요");
      maxSize.value = "";
      return;
   } else {
      count++;
   }
   if (minSize.value <= 0) {
      alert("상품의 최소사이즈를 작성해주세요");
      minSize.value = "";
      return;
   } else {
      count++;
   }
   if (clothEx.value <= 0) {
      alert("상품설명을 작성해주세요");
      clothEx.value = "";
      return;
   } else {
      count++;
   }
   if (gender.value <= 0) {
      alert("권장 성별을 작성해주세요");
      gender.value = "";
      return;
   } else {
      count++;
   }
   if (count > 10) {
      sizedialogOpen();
   }
}


let Ssize = sizeDialog.querySelector("#sSize");
let Msize = sizeDialog.querySelector("#mSize");
let Lsize = sizeDialog.querySelector("#lSize");
let XLsize = sizeDialog.querySelector("#xlSize");
let XXLsize = sizeDialog.querySelector("#xxlSize");

let sBlock = sizeDialog.querySelector("#divS");
let mBlock = sizeDialog.querySelector("#divM");
let lBlock = sizeDialog.querySelector("#divL");
let xlBlock = sizeDialog.querySelector("#divXL");
let xxlBlock = sizeDialog.querySelector("#divXXL");

function clothRegistration() {
   let min = parseInt(minSize.value, 10);
   let max = parseInt(maxSize.value, 10);
   if (min === 5) {
      sBlock.style.display = "none";
      mBlock.style.display = "none";
      lBlock.style.display = "none";
      xlBlock.style.display = "none";
   } else if (min === 4) {
      sBlock.style.display = "none";
      mBlock.style.display = "none";
      lBlock.style.display = "none";
   } else if (min === 3) {
      sBlock.style.display = "none";
      mBlock.style.display = "none";
   } else if (min === 2) {
      sBlock.style.display = "none";
   }

   if (max === 4) {
      xxlBlock.style.display = "none";
   } else if (max === 3) {
      xlBlock.style.display = "none";
      xxlBlock.style.display = "none";
   } else if (max === 2) {
      lBlock.style.display = "none";
      xlBlock.style.display = "none";
      xxlBlock.style.display = "none";
   } else if (max === 1) {
      mBlock.style.display = "none";
      lBlock.style.display = "none";
      xlBlock.style.display = "none";
      xxlBlock.style.display = "none";
   }

}

function clothRegister() {
   let formData = {
      "Cloth": {
         "cloth_name": Name.value,
         "cloth_brand": Brand.value,
         "cloth_price": Price.value,
         "cloth_min_size": minSize.value,
         "cloth_max_size": maxSize.value,
         "cloth_gender": gender.value,
         "cloth_explanation": clothEx.value,

         "cloth_size_s": Ssize.value,
         "cloth_size_m": Msize.value,
         "cloth_size_l": Lsize.value,
         "cloth_size_xl": XLsize.value,
         "cloth_size_xxl": XXLsize.value
      },
      "Categorys": {
         "season_category": season.value,
         "color_category": color.value,
         "s_category": s.value,
         "usage_category1": usage1.value,
         "usage_category2": usage2.value,
         "usage_category3": usage3.value
      }

   };
   let str = JSON.stringify(formData);


   let url = "/productManage";
   return fetch(url, {
      method: "put",
      body: str
   }).then((resp) => {
      if (resp.status === 200) {
         alert("성공적으로 등록되었습니다.");
         sizedialogClose();
         dialogClose();
         window.location("http://localhost:8080/manage");
      } else if(resp.status === 420){
         alert("실패하였습니다.");
      } else {
      }
   });
}