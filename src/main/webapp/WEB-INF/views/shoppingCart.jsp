<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>쇼핑 카트</title>
<link rel="stylesheet" type="text/css"
	href="./static/css/shoppingCart.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"> </script>
<script>
	const userId = '<%=(String) session.getAttribute("userId")%>';
</script>
<style type="text/css">
.image {
	width: 50px;
	height: 70px;
}

.logoAndTitle {
    display: flex;
    align-items: center; /* 이미지와 h1을 수직 중앙 정렬 */
    justify-content: center; /* 로고와 h1을 수평 중앙 정렬 */
    font-size: 0.75em;
    margin-top: -5px;
    margin-left: -20px;
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
	gap: 70px; /* 요소들 사이에 20px 간격 */
}

.link a {
	color: white; /* 링크 색깔을 하얀색으로 */
	text-decoration: none; /* 밑줄 제거 */
	margin-top: -5px;
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
				<a href="./search">☆내게 맞는 옷 찾기☆</a> <a href="/softSearch">검색</a>
			</div>
		</nav>
	</header>
	
	<div class="all">
	<div class="info">
		<h1>장바구니</h1>
	</div>
	<c:choose>
		<c:when test="${not empty shoppingCartList}">
			<table>
				<thead>
					<tr>
						<th>선택</th>
						<th>상품명</th>
						<th>수량</th>
						<th>가격</th>
						<th>총 금액</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${shoppingCartList}">
						<tr>
							<td><input type="checkbox" name="selectedItems"
								value="${item.cloth_num}"></td>
							<td class="product-info">
							<img class="image" src="/static/image/cloth/cloth${item.cloth_num}/옷${item.cloth_num}.png" alt="${item.cloth_name}" class="product-image">
								${item.cloth_name}</td>
							<td>${item.shoppingcart_count}</td>
							<td>${item.cloth_price}원</td>
							<td><c:set var="itemTotal"
									value="${item.shoppingcart_count * item.cloth_price}" />
								${itemTotal}원</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="total">합계</td>
						<td class="total total-price"><c:set var="totalPrice"
								value="0" /> <c:forEach var="item" items="${shoppingCartList}">
								<c:set var="itemTotal"
									value="${item.shoppingcart_count * item.cloth_price}" />
								<c:set var="totalPrice" value="${totalPrice + itemTotal}" />
							</c:forEach> ${totalPrice}원</td>
					</tr>
					<tr>
						<td colspan="4" class="total">배송비</td>
						<td class="total">3500원</td>
						<!-- 고정 배송비 -->
					</tr>
					<tr>
						<td colspan="4" class="total">총 합계</td>
						<td class="total final-total-price" id="finalTotalPrice"><c:set
								var="finalTotalPrice" value="${totalPrice + 3500}"
								scope="session" /> ${totalPrice}원 + 3500원 = ${finalTotalPrice}원
							<input type="hidden" id="ftp" value="${finalTotalPrice}">
						</td>
					</tr>
				</tfoot>
			</table>
		</c:when>
		<c:otherwise>
			<div class="empty-message">장바구니가 비어있습니다</div>
		</c:otherwise>
	</c:choose>
	<div class="buttons-container">
		<div>
			<button data-action="select-delete">선택삭제</button>
			<button data-action="delete-all">모두삭제</button>
		</div>
		<button data-action="order">주문하기</button>
	</div>
	<dialog class="payment-dialog">
	<h2>구매 창</h2>
	<p>
		총 결제 금액:
		<%=session.getAttribute("finalTotalPrice")%>원
	</p>
	<button onclick="requestPay()">결제하기</button>
	<button class="cancel-payment-button">취소</button>
	</dialog>
	</div>
	
</body>
<script
	src="${pageContext.request.contextPath}/static/js/shoppingCart.js"></script>
</html>
