<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>옷 상세 페이지</title>
</head>
<body>
	<div>
		<img class="image"
			src="data:image/png;base64,${ chooseCloth.main_image1 }"
			alt="${ chooseCloth.cloth_name }">
	</div>

	<div>
		<p>이름: ${chooseCloth.cloth_name}</p>
		<p>가격: ${chooseCloth.cloth_price}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
	</div>
</body>
</html>