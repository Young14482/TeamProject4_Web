<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	position: fixed;
	width: 100%;
	top: 0;
	z-index: 1000;
}

nav ul {
	list-style: none;
	padding: 0;
	display: flex;
	justify-content: space-around;
	align-items: center;
	margin: 0;
}

nav ul li {
	display: inline;
}

nav ul li a {
	color: #fff;
	text-decoration: none;
	padding: 10px 20px;
}

.search {
	text-align: right;
	padding: 10px;
}

main {
	margin-top: 150px; /* 헤더 높이만큼 마진 추가 */
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

.swiper-container {
	width: 600px;
	height: 400px;
	margin: 0 auto;
}

.swiper-slide {
	text-align: center;
	font-size: 18px;
	background: #fff;
	display: flex;
	justify-content: center;
	align-items: center;
}

.slick-prev, .slick-next {
    font-size: 40px !important;
    color: black !important;
    border: none !important;
    border-radius: 50% !important;
    width: 50px !important;
    height: 50px !important;
    display: flex !important;
    justify-content: center !important;
    align-items: center !important;
    z-index: 1 !important;
}

.slick-prev:hover, .slick-next:hover {
    color: black !important;
}

.slider img {
    max-width: 100%; /* Adjust the width as needed */
    height: auto;
    margin: 0 auto; /* Center the images */
}

.logo {
	text-align: center;
}

.info {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 500px;
}

</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
</head>
<body>
	<header>
		<nav>
			<div class="logo">
				<h1>Web Project 홈페이지</h1>
			</div>
			<ul class="link">
				<c:if test="${not empty sessionScope.userId}">
					<p>환영합니다, ${ userId }님!</p>
				</c:if>
				<c:if test="${empty sessionScope.userId}">
					<li><a href="./user">로그인</a></li>
					<li><a href="./signup">회원가입</a></li>
				</c:if>
				<li><a href="./search">내게 맞는 옷 찾기</a></li>
				<li><a href="#">신상품</a></li>
				<li><a href="/TeamProject4_Web/softSearch">검색</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<section class="middle">
			<div class="slider">
				<div>
					<img src="data:image/png;base64,<%=session.getAttribute("image1")%>" alt="Slide 1">
				</div>
				<div>
					<img src="data:image/png;base64,<%=session.getAttribute("image2")%>" alt="Slide 2">
				</div>
				<div>
					<img src="data:image/png;base64,<%=session.getAttribute("image3")%>" alt="Slide 3">
				</div>
			</div>
		</section>
	</main>
	<jsp:include page="/WEB-INF/views/clothList.jsp"></jsp:include>
	<footer class="info">
		<p>전화번호 : 010 - 1234 - 1234</p>
		<p>이메일 : angus1208@naver.com</p>
		<p>홈페이지의 모든 자료는 상업적으로 이용되지 않습니다.</p>
	</footer>
	<script>
		$(document).ready(function() {
			$('.slider').slick({
				autoplay: true,
				autoplaySpeed: 2000,
				dots: true,
				prevArrow: '<button type="button" class="slick-prev"><</button>',
				nextArrow: '<button type="button" class="slick-next">></button>'
			});
		});
	</script>
</body>
</html>
