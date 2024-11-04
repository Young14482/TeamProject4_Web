<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style type="text/css">
.logoAndTitle {
    display: flex;
    align-items: center; /* 이미지와 h1을 수직 중앙 정렬 */
    justify-content: center; /* 로고와 h1을 수평 중앙 정렬 */
}

.logo-img {
    width: 50px;
    height: auto;
    margin-right: 10px;
}

.link {
	font-size: 30px;
	display: flex;
	justify-content: center; /* 중앙 배치 */
	gap: 70px; 
}

.link a {
	color: white; /* 링크 색깔을 하얀색으로 */
	text-decoration: none; /* 밑줄 제거 */
}

.logo a {
	color: white; /* 링크 색깔을 하얀색으로 */
	text-decoration: none; /* 밑줄 제거 */
}

header {
	background-color: #333;
	color: #fff;
	padding: 10px 0;
	position: fixed;
	width: 100%;
	height: 125px;
	top: 0;
	z-index: 1000;
	transition: top 0.5s;
}

.link p {
	margin-top: 0px;
}
</style>
</head>
<body>
	<header>
		<nav>
			<div class="logoAndTitle">
				<div class="logo">
					<a href="/main"> 
						<img src="/static/image/logo/logo.png" alt="로고 이미지" class="logo-img">
					</a>
				</div>
				<h1><a href="/main" style="color: #fff; text-decoration: none; font-size: 1.5em;">NO MORE SHINSA</a></h1>
			</div>
			<div class="link">
				<c:if test="${not empty sessionScope.userId}">
					<p>환영합니다, ${ userId }님!</p>
					<c:if test="${sessionScope.userId == 'admin'}">
						<a href="./manage">관리자 페이지</a>
					</c:if>
					<c:if test="${sessionScope.userId != 'admin'}">
						<a href="./modify?userId=${ userId }">내 정보 보기</a>
						<a href="./shoppingCart?userId=${ userId }">장바구니</a>
						<a href="./userPayment">결제 완료 목록</a>
					</c:if>
					<a href="/logout">로그 아웃</a>
				</c:if>
				<c:if test="${empty sessionScope.userId}">
					<a href="./user">로그인</a>
					<a href="./signup">회원가입</a>
				</c:if>
				<a href="./search">내게 맞는 옷 찾기</a> <a href="/softSearch">검색</a>
			</div>
		</nav>
	</header>
</body>
<script>
	window.onscroll = function() {
		var header = document.querySelector('header');
		if (window.scrollY > 100) { // 스크롤이 100px 이상일 때
			header.style.top = '-190px'; // 헤더 숨기기
		} else {
			header.style.top = '0'; // 헤더 보이기
		}
	};
</script>
</html>
