<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.logo {
	text-align: center;
}

.link {
	font-size: 30px;
	display: flex;
	justify-content: center; /* 중앙 배치 */
	gap: 150px; /* 요소들 사이에 20px 간격 */
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
	top: 0;
	z-index: 1000;
	transition: top 0.5s;
}
</style>
</head>
<body>
	<header>
		<nav>
			<div class="logo">
				<a href="./main" class="hyperLink"><h1>Web Project 홈페이지</h1></a>
			</div>
			<div class="link">
				<c:if test="${not empty sessionScope.userId}">
					<p>환영합니다, ${ userId }님!</p>
				</c:if>
				<c:if test="${empty sessionScope.userId}">
					<a href="./user">로그인</a>
					<a href="./signup">회원가입</a>
				</c:if>
				<a href="./search">내게 맞는 옷 찾기</a>
				<a href="#">신상품</a>
				<a href="/softSearch">검색</a>
			</div>
		</nav>
	</header>
</body>
<script>

window.onscroll = function() {
    var header = document.querySelector('header');
    if (window.scrollY > 100) { // 스크롤이 100px 이상일 때
        header.style.top = '-150px'; // 헤더 숨기기
    } else {
        header.style.top = '0'; // 헤더 보이기
    }
};
</script>
</html>
