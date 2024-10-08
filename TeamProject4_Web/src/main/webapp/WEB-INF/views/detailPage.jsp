<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 페이지</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	display: flex;
	gap: 50px;
}

.image {
	width: 400px;
	height: 500px;
	object-fit: cover;
	border-radius: 10px;
}

.info {
	width: 300px;
}

.info h1 {
	font-size: 24px;
	margin-bottom: 20px;
}

.info p {
	font-size: 18px;
	margin-bottom: 10px;
}

button {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
	margin-top: 10px;
}

button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/mainBar.jsp"></jsp:include>

	
	<div>
		<jsp:include page="/WEB-INF/views/clothDetail.jsp"></jsp:include>
	</div>

	<div class="container">


		<div class="info">
			<h1>제품 상세 정보</h1>
			<p>이름: ${chooseCloth.cloth_name}</p>
			<p>가격: ${chooseCloth.cloth_price}</p>
			<p>설명: ${chooseCloth.cloth_explanation}</p>
			<button>바로구매</button>
			<button>장바구니</button>
			<p>개추 : ${chooseCloth.cloth_good}</p>
			<p>비추 : ${chooseCloth.cloth_bad}</p>
		</div>
	</div>
</body>
</html>
