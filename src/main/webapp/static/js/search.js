// 각각 계절, 색깔, 활동, 성별이 클릭되었을 때 작동하는 함수들
function selectSeason(season) {
	document.getElementById('seasonInput').value = season;
	clearSelection('season');
	document.getElementById('season' + season).classList.add('selected');
}

function selectColor(color) {
	document.getElementById('colorInput').value = color;
	clearSelection('color');
	document.getElementById('color' + color).classList.add('selected');
}

function selectUsage(usage) {
	document.getElementById('usageInput').value = usage;
	clearSelection('usage');
	document.getElementById('usage' + usage).classList.add('selected');
}

function selectGender(gender) {
	document.getElementById('genderInput').value = gender;
	clearSelection('gender');
	document.getElementById('gender' + gender).classList.add('selected');
}

///////////////////////////////////////////////////////////////////////////////
// 클릭 해제시 작동하는 함수
function clearSelection(type) {
	var elements = document.getElementsByClassName(type);
	for (var i = 0; i < elements.length; i++) {
		elements[i].classList.remove('selected');
	}
}

function submitForms() {
	const selectedPrice = document.querySelector('.price-options input:checked');
	const minPrice = document.getElementById('minPrice').value;
	const maxPrice = document.getElementById('maxPrice').value;
	const allPriceSelected = document.querySelector('input[name="priceOption"][value="all"]').checked;
	
	// 모든 항목이 선택되었는지 확인
	if (document.getElementById('seasonInput').value === "" ||
		document.getElementById('colorInput').value === "" ||
		document.getElementById('usageInput').value === "" ||
		document.getElementById('genderInput').value === "" ||
		(!selectedPrice && !allPriceSelected && (minPrice === "" || maxPrice === ""))) {
		alert("모든 항목을 선택해주세요.");
		return false; // 폼 제출 중단
	}

	if (allPriceSelected) {
		document.getElementById('priceInput').value = `0~999999`;
	} else if (selectedPrice) {
		document.getElementById('priceInput').value = `${selectedPrice.dataset.min}~${selectedPrice.dataset.max}`;
	} else if (minPrice && maxPrice) {
		if (parseInt(minPrice) > parseInt(maxPrice)) {
			alert("최소 금액이 최대 금액보다 클 수 없습니다. 다시 입력해주세요."); // 경고 메시지
			return; // 폼 제출 중단
		}
		document.getElementById('priceInput').value = `${minPrice}~${maxPrice}`;
	}

	document.getElementById('combinedForm').submit();
}

function activatePriceRange() {
	document.querySelectorAll('.price-options input').forEach(input => {
		input.disabled = false; // 가격대 선택 옵션 활성화
	});
	document.getElementById('minPrice').disabled = true;
	document.getElementById('maxPrice').disabled = true;
	document.querySelector('input[name="priceOption"][value="range"]').checked = true; // 가격대 선택 라디오 버튼 체크
}

function activateCustomPrice() {
	document.querySelectorAll('.price-options input').forEach(input => {
		input.disabled = true; // 가격대 선택 옵션 비활성화
		input.checked = false; // 라디오 버튼 선택 초기화
	});
	document.getElementById('minPrice').disabled = false;
	document.getElementById('maxPrice').disabled = false;
	document.querySelector('input[name="priceOption"][value="custom"]').checked = true; // 직접 입력 라디오 버튼 체크
}

function activateAllPrice() {
	document.querySelectorAll('.price-options input').forEach(input => {
		input.disabled = true; // 가격대 선택 옵션 비활성화
		input.checked = false; // 라디오 버튼 선택 초기화
	});
	document.getElementById('minPrice').disabled = true;
	document.getElementById('maxPrice').disabled = true;
	document.querySelector('input[name="priceOption"][value="all"]').checked = true; // 모든 가격 라디오 버튼 체크
}
