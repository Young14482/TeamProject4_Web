// 작성자 : 이진석

// 페이지가 로드될 때 서버로부터 데이터를 가져오기 위한 함수 호출

let clothdialog = document.querySelector(".clothInfoDialog");
let sizeDialog = document.querySelector(".sizeListDialog");

function sizedialogOpen() {
	clothRegistration();
	sizeDialog.showModal();
}

// 상품 추가 다이얼에 들어가있는 value들
let Name = clothdialog.querySelector("#clothName");// 상품명
let Brand = clothdialog.querySelector("#clothBrand");// 상품브랜드
let Price = clothdialog.querySelector("#clothPrice");// 상품 가격

let color = clothdialog.querySelector("#comboColor"); // 옷 색상
let s = clothdialog.querySelector("#comboS"); // 옷 종류
let season = clothdialog.querySelector("#comboSeason"); // 옷 시즌
let usage1 = clothdialog.querySelector("#comboUsage1"); // 옷 사용처 1
let usage2 = clothdialog.querySelector("#comboUsage2"); // 옷 사용처 2
let usage3 = clothdialog.querySelector("#comboUsage3"); // 옷 사용처 3

let maxSizeCombo = clothdialog.querySelector("#comboMaxSize"); // 상품 최대 사이즈 콤보박스
let minSizeCombo = clothdialog.querySelector("#comboMinSize"); // 상품 최소 사이즈 콤보박스
let clothEx = clothdialog.querySelector("#clothExplanation"); // 상품 설명
let genderCombo = clothdialog.querySelector("#comboGender"); // 상품 권장 성별 콤보박스// 최초값 보관 보관하면서 open

function dialogOpen() {
	initialColorValue = color.value;
	initialSValue = s.value;
	initialSeasonValue = season.value;
	initialUsage1Value = usage1.value;
	initialUsage2Value = usage2.value;
	initialUsage3Value = usage3.value;
	initialMaxSizeValue = maxSizeCombo.value;
	initialMinSizeValue = minSizeCombo.value;
	initialGenderValue = genderCombo.value;
	clothdialog.showModal();
}

// 값 초기화 수정한다고 눌려놓은값들 최초값으로 넣으면서 close
function sizedialogClose() {
	color.value = initialColorValue;
	s.value = initialSValue;
	season.value = initialSeasonValue;
	usage1.value = initialUsage1Value;
	usage2.value = initialUsage2Value;
	usage3.value = initialUsage3Value;

	inputBye(); // input값들 초기화
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
	maxSizeCombo.value = initialMaxSizeValue;
	minSizeCombo.value = initialMinSizeValue;
	genderCombo.value = initialGenderValue;

	inputBye();
	clothdialog.close();
}

// 상품 수정 액션
let dialogModify = document.querySelector(".modifyDialog");
let target;

// 다이얼 오픈 액션
function mDialogOpen(e) {
	target = e.target;
	let targetText = target.innerText;
	let targetColname = target.getAttribute('data-colname');

	dialogModify.querySelector(".colName").innerText = targetColname;
	dialogModify.querySelector(".innerText").value = targetText;

	dialogModify.showModal();
}

// 다이얼 종료 액션
function mDialogClose() {
	dialogModify.querySelector(".colName").innerText = "상품명";
	dialogModify.querySelector(".innerText").value = "";

	dialogModify.close();
}

// 다이얼 안에 있는 수정버튼 액션
function modifyClothinput(e) {
	let dialogText = dialogModify.querySelector(".innerText").value;
	target.innerText = dialogText;
	mDialogClose();
}

// input값 초기화 하는 함수
function inputBye() {
	Name.value = "";
	Brand.value = "";
	Price.value = "";
	clothEx.value = "";

	// size 관련 블록 초기화
	mBlock.style.display = "block";
	lBlock.style.display = "block";
	xlBlock.style.display = "block";
	xxlBlock.style.display = "block";
}

// 추가할 때 값을 재대로 입력했는지 검증
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

	if (maxSizeCombo.value <= 0) {
		alert("상품의 최대사이즈를 작성해주세요");
		return;
	} else {
		count++;
	}
	if (minSizeCombo.value <= 0) {
		alert("상품의 최소사이즈를 작성해주세요");
		return;
	} else {
		count++;
	}
	
	if (maxSizeCombo.value === minSizeCombo.value) {
		alert("상품의 최대,최소 사이즈는 같으면 안됩니다.");
		return;
	} else if (maxSizeCombo.value < minSizeCombo.value) {
		alert("상품의 최대 사이즈는 최소사이즈보다 작을수 없습니다.");
		return;
	}
	
	if (clothEx.value <= 0) {
		alert("상품설명을 작성해주세요");
		clothEx.value = "";
		return;
	} else {
		count++;
	}
	if (genderCombo.value <= 0) {
		alert("권장 성별을 작성해주세요");
		return;
	} else {
		count++;
	}
	if (count > 10) {
		sizedialogOpen();
	}
}
// 기본 값(input)
let Ssize = sizeDialog.querySelector("#sSize");
let Msize = sizeDialog.querySelector("#mSize");
let Lsize = sizeDialog.querySelector("#lSize");
let XLsize = sizeDialog.querySelector("#xlSize");
let XXLsize = sizeDialog.querySelector("#xxlSize");
// input의 div(안보여 주기 위해서)
let sBlock = sizeDialog.querySelector("#divS");
let mBlock = sizeDialog.querySelector("#divM");
let lBlock = sizeDialog.querySelector("#divL");
let xlBlock = sizeDialog.querySelector("#divXL");
let xxlBlock = sizeDialog.querySelector("#divXXL");
// 사이즈 선택에 따라 보여주는 값
function clothRegistration() {
	let min = parseInt(minSizeCombo.value, 10);
	let max = parseInt(maxSizeCombo.value, 10);
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

// 상품 최종 추가 함수
function clothRegister() {
	let formData = {
		"Cloth": {


			"cloth_name": Name.value,
			"cloth_brand": Brand.value,
			"cloth_price": Price.value,
			"cloth_min_size": minSizeCombo.value,
			"cloth_max_size": maxSizeCombo.value,
			"cloth_gender": genderCombo.value,
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
		method: "post",
		body: str
	}).then((resp) => {
		if (resp.status === 200) {
			alert("성공적으로 등록되었습니다.");
			sizedialogClose();
			dialogClose();
			window.location.href = "/uploadImage";
		} else if (resp.status === 420) {
			alert("실패하였습니다.");
		} else {
		}
	});
}

// 수정 한번더 물어보기
let mLastCheckDialog = document.querySelector(".modifyLastCheckDialog");
// 클래스 리스트 들고 오는방법
let mother;
function mlcDialogOpen(e) {
	mother = e.target.closest("tr");

	mLastCheckDialog.showModal();
}
function mlcDialogClose() {
	mLastCheckDialog.close();
}
// 최종 수정 함수
function modifyClothUpdate() {
	let priceValue = parseInt(mother.querySelector(".p").getAttribute('data-valprice'));
	let formData = {
		"cloth_num": mother.querySelector(".no").innerText,
		"cloth_name": mother.querySelector(".n").innerText,
		"cloth_brand": mother.querySelector(".b").innerText,
		"cloth_price": priceValue,
		"cloth_gender": mother.querySelector(".g").innerText,

		"cloth_size_s": mother.querySelector(".s").innerText,
		"cloth_size_m": mother.querySelector(".m").innerText,
		"cloth_size_l": mother.querySelector(".l").innerText,
		"cloth_size_xl": mother.querySelector(".xl").innerText,
		"cloth_size_xxl": mother.querySelector(".xxl").innerText
	};
	let str = JSON.stringify(formData);
	let url = "/productManage";

	return fetch(url, {
		method: "put",
		body: str
	}).then((resp) => {
		if (resp.status === 200) {
			mlcDialogClose();
			alert("정상적으로 변경 되었습니다.");
		} else if (resp.status === 418) {
			alert("변경되지 않았습니다.");
		}
	});
}



let deleteDialog = document.querySelector(".deletLastCheckDialog");
function deleteDialogOpen(e) {
	mother = e.target.closest("tr");
	console.log(mother.querySelector(".no").innerText);
	deleteDialog.showModal();
}
function deleteDialogClose() {
	deleteDialog.close();
}

// 삭제 
function deleteCloth() {
	console.log(mother.querySelector(".no").innerText);
	let formData = {
		"cloth_num": mother.querySelector(".no").innerText
	};
	let str = JSON.stringify(formData);
	let url = "/productManage";

	return fetch(url, {
		method: "delete",
		body: str
	}).then((resp) => {
		if (resp.status === 200) {
			mother.style.display = "none";
			mlcDialogClose();
			deleteDialogClose();
			alert("정상적으로 삭제 되었습니다,");
		} else if (resp.status === 418) {
			alert("삭제 실패했습니다.");
		}
	});
}