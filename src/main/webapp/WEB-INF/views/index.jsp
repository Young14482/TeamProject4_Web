<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<jsp:include page="/WEB-INF/views/mainBar.jsp"></jsp:include>
	<main>
		<section class="middle">
			<div class="slider">

				<div>
					<img src="/static/image/메인이미지1.PNG" alt="Slide 1">
				</div>
				<div>
					<img src="/static/image/메인이미지2.PNG" alt="Slide 2">
				</div>
				<div>
					<img src="/static/image/메인이미지3.PNG" alt="Slide 3">
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
		$(document)
				.ready(
						function() {
							$('.slider')
									.slick(
											{
												autoplay : true,
												autoplaySpeed : 2000,
												dots : true,
												prevArrow : '<button type="button" class="slick-prev"><</button>',
												nextArrow : '<button type="button" class="slick-next">></button>'
											});
						});
	</script>
</body>
</html>
