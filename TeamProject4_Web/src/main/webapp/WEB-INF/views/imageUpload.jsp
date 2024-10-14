<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 업로드</title>
<style type="text/css">
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
}

body header {
	text-align: center;
	margin-bottom: 100px;
}

.main {
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 20px auto;
	max-width: 800px;
}

.main form {
	margin-left: 20px;
}

.essential {
	margin-left: 10px;
	color: red;
}

.main a {
	margin-top: 50px;
	text-align: center;
	display: block;
}
</style>
<script type="text/javascript">
    function submitFormWithParams(event, divideValue) {
        event.preventDefault(); // 기본 폼 제출 동작 막기

        const form = event.target;
        const formData = new FormData(form);
        const uploadButton = form.querySelector('button[type="submit"]');

        // 쿼리 파라미터 추가
        const queryParams = new URLSearchParams();
        queryParams.append("divide", divideValue);

        fetch(form.action + "?" + queryParams.toString(), {
            method: 'POST',
            body: formData
        })
        .then(response => response.text())
        .then(result => {
            alert("응답 결과: " + result);
            if (result.includes("성공")) { // 원하는 문장이 포함된 경우
                uploadButton.disabled = true; // 업로드 버튼 비활성화
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
</script>
</head>
<body>

	<header>
		<h1>추가한 옷에 적용할 이미지를 선택 및 업로드를 진행해주세요</h1>
		<h2>☆필수☆ 항목에는 반드시 사용할 이미지를 업로드 하여야 하며, 업로드 하지 않을 시 정상적으로 이미지가
			출력되지 않습니다.</h2>
		<h2>이미지를 선택한 후 업로드 버튼을 누른 뒤 업로드가 종료되면 해당 버튼은 비활성화되니</h2>
		<h2>신중하게 선택하여 주십시오.</h2>
	</header>
	<div class="main">
		<h3>리스트 목록 출력 이미지 :</h3>
		<form id="uploadForm1" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 1)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
		<h3 class="essential">☆필수☆</h3>
	</div>

	<div class="main">
		<h3>메인 출력 이미지1 :</h3>
		<form id="uploadForm2" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 2)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
		<h3 class="essential">☆필수☆</h3>
	</div>

	<div class="main">
		<h3>메인 출력 이미지2 :</h3>
		<form id="uploadForm3" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 3)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
		<h3 class="essential">☆필수☆</h3>
	</div>

	<div class="main">
		<h3>메인 출력 이미지3 :</h3>
		<form id="uploadForm4" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 4)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
	</div>

	<div class="main">
		<h3>세부 내용 이미지1 :</h3>
		<form id="uploadForm5" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 5)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
		<h3 class="essential">☆필수☆</h3>
	</div>

	<div class="main">
		<h3>세부 내용 이미지2 :</h3>
		<form id="uploadForm6" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 6)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
	</div>

	<div class="main">
		<h3>세부 내용 이미지3 :</h3>
		<form id="uploadForm7" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 7)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
	</div>

	<div class="main">
		<h3>세부 내용 이미지4 :</h3>
		<form id="uploadForm8" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 8)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
	</div>

	<div class="main">
		<h3>세부 내용 이미지5 :</h3>
		<form id="uploadForm9" action="uploadImage" method="post"
			enctype="multipart/form-data"
			onsubmit="submitFormWithParams(event, 9)">
			<input type="file" name="file" />
			<button type="submit">업로드</button>
		</form>
	</div>

	<div class="main">
		<a href="/manage"> 이미지 업로드 종료 (메인 관리자 페이지로 이동)</a>
	</div>

</body>
</html>




