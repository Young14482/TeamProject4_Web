function selectSeason(season) {
    document.getElementById('seasonInput').value = season;
    clearSelection('season');
    document.getElementById('season' + season).classList.add('selected');
    console.log(season + " 선택됨"); // 선택된 계절을 콘솔에 출력
}

function selectColor(color) {
	document.getElementById('colorInput').value = color;
	clearSelection('color');
	document.getElementById('color' + color).classList.add('selected');
}

function clearSelection(type) {
	var elements = document.getElementsByClassName(type);
	for (var i = 0; i < elements.length; i++) {
		elements[i].classList.remove('selected');
	}
}

function submitForms() {
//	if (document.getElementById('seasonInput').value === "" || document.getElementById('elementInput').value === "") {
//		alert("모든 항목을 선택해주세요.");
//		return false;
//	}
	document.getElementById('combinedForm').submit();
}
