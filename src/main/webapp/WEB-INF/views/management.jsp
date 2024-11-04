<!-- 작성자 : 이나겸 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
	height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

header {
	background-color: #333;
	color: #fff;
	padding: 10px 0;
	width: 100%;
}

nav .logo {
	text-align: center;
}

main {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-grow: 1;
	width: 100%;
}

main ul {
	list-style: none;
	padding: 0;
	margin: 0;
	background-color: #fff;
}

main ul li {
	display: block;
	border-bottom: 1px solid #ddd;
}

main ul li a {
	display: block;
	color: #333;
	text-decoration: none;
	padding: 10px 20px;
	text-align: center;
}

main ul li a:hover {
	background-color: #f4f4f4;
}
</style>
</head>
<body>
	<header>
		<nav>
			<div class="logo">
				<h1>회사명 정하면 변경</h1>
			</div>
		</nav>
	</header>
	<main>
		<ul class="link">
			<li><a href="#">총 매출</a></li>
			<li><a href="#">최근 소비자 구매 현황</a></li>
			<li><a href="#">제품 입출고 현황</a></li>
			<li><a href="#">상품 추가</a></li>
		</ul>
	</main>
</body>
</html>