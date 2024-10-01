<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>메인 홈페이지</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

header {
	background-color: #333;
	color: #fff;
	padding: 10px 0;
}

nav ul {
	list-style: none;
	padding: 0;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

nav ul li {
	display: inline;
}

nav ul li a {
	color: #fff;
	text-decoration: none;
	padding: 10px 20px;
}

.logo {
	text-align: center;
}

.search {
	text-align: right;
	padding: 10px;
}

main {
	display: flex;
	justify-content: space-around;
	padding: 20px;
}

section {
	flex: 1;
	margin: 10px;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

section img {
	max-width: 100%;
	height: auto;
}

footer {
	text-align: center;
	padding: 20px;
	background-color: #333;
	color: #fff;
}

.link {
	font-size: 30px;
}

.info {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 700px;
}

</style>
</head>
<body>
	<header>
		<nav>

			<div class="logo">
				<h1>회사명 정하면 변경</h1>
			</div>
			<ul class="link">
				<li><a href="#">로그인</a></li>
				<li><a href="#">회원가입</a></li>
				<li><a href="./search">내게 맞는 옷 찾기</a></li>
				<li><a href="#">신상품</a></li>
				<li><a href="#">메뉴</a></li>
			</ul>

			<div class="search">
				<input type="text" placeholder="Search...">
			</div>
		</nav>
	</header>
	<main>
		<section class="left">
			<img src="model.jpg" alt="Model">
		</section>
		<section class="middle">
			<h2>피피스&세트</h2>
			<img src="data:image/png;base64,<%= session.getAttribute("image1") %>" alt="Base64 Image">

		</section>
		<section class="right">
			<h2>SALE 50%</h2>
			<img src="discount_item.jpg" alt="Discount Item">
		</section>
	</main>
	<footer class=info>
		<p>전화번호 : 010 - 1234 - 1234</p>
		<p>이메일 : angus1208@naver.com</p>
	</footer>
</body>
</html>
