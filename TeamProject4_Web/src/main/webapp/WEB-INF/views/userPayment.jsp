<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제된 목록 창</title>
<style type="text/css">
body {
	margin-left: 0px;
}

.main {
	margin-top: 200px;
	margin-bottom: 100px;
}

.table {
	width: 80%;
	margin: 0 auto;
	border-collapse: collapse;
}

.table th, .table td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

.table th {
	background-color: #f4f4f4;
}

.image {
	width: 50px;
	height: 70px;
	object-fit: cover;
}

.review {
	margin-top: 10px;
}

footer.info {
	position: fixed;
	bottom: 0;
	width: 100%;
	background-color: #000;
	color: #fff;
	text-align: center;
	padding: 10px 0;
	white-space: nowrap; /* 한 줄로 표시하도록 설정 */
}

footer.info p {
	display: inline; /* p 태그를 인라인 요소로 변경 */
	margin: 0; /* p 태그의 기본 마진 제거 */
	padding: 0 5px; /* p 태그 사이에 약간의 패딩 추가 */
}
</style>
<script type="text/javascript">
    function openReviewDialog(clothNum) {
        const reviewDialog = document.getElementById("reviewDialog");
        reviewDialog.style.display = 'block';
        document.getElementById("reviewClothNum").value = clothNum;
    }

    function closeReviewDialog() {
        const reviewDialog = document.getElementById("reviewDialog");
        reviewDialog.style.display = 'none';
    }

    async function submitReview() {
        const userId = document.getElementById("userId").value;
        const clothNum = document.getElementById("reviewClothNum").value;
        const reviewContent = document.getElementById("reviewContent").value;
        const reviewRating = document.querySelector('input[name="reviewRating"]:checked').value;

        const reviewData = {
        	user_ID: userId,
        	cloth_num: clothNum,
        	reviewDetail: reviewContent,
        	good_or_bad: reviewRating
        };

        const response = await fetch('/userPayment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reviewData)
        });

        if (response.ok) {
            alert("후기가 제출되었습니다!");
            closeReviewDialog();
            location.reload(); 
        } else {
            alert("후기 제출에 실패했습니다. 다시 시도해주세요.");
        }
    }

    
    function confirmCancel(clothNum) {
        if (confirm("결제를 취소하시겠습니까?")) {
        	  fetch(`/cancelOrder?cloth_num=` + clothNum, { // 쿼리 파라미터 추가
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json'
                  }
              })
              .then(response => response.text())
              .then(result => {
                  alert('해당 품목의 결제가 취소되었습니다.');
                  location.reload(); // 페이지 새로고침
              })
              .catch(error => {
                  console.error('Error:', error);
              });
        }
    }

</script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/mainBar.jsp"></jsp:include>

	<div class="main">

		<table class="table">
			<tr>
				<th>이미지</th>
				<th>결제 날짜</th>
				<th>상품 이름</th>
				<th>사이즈</th>
				<th>수량</th>
				<th>가격</th>
				<th>총 가격</th>
				<th>배송 현황</th>
				<th>후기</th>
			</tr>
			<c:forEach var="payCloth" items="${sessionScope.payClothList}">
				<tr>
					<td><img class="image"
						src="/static/image/cloth/cloth${payCloth.cloth_num}/옷${payCloth.cloth_num}.png"
						alt=""></td>
					<td>${payCloth.payment_date}</td>
					<td>${payCloth.cloth_name}</td>
					<td><c:choose>
							<c:when test="${payCloth.cloth_size == 0}">normal</c:when>
							<c:when test="${payCloth.cloth_size == 1}">S</c:when>
							<c:when test="${payCloth.cloth_size == 2}">M</c:when>
							<c:when test="${payCloth.cloth_size == 3}">L</c:when>
							<c:when test="${payCloth.cloth_size == 4}">XL</c:when>
							<c:when test="${payCloth.cloth_size == 5}">XXL</c:when>
							<c:otherwise>Unknown</c:otherwise>
						</c:choose></td>
					<td>${payCloth.payment_count}</td>
					<td>${payCloth.cloth_price}</td>
					<td>${payCloth.cloth_price * payCloth.payment_count}</td>
					<c:choose>
						<c:when test="${payCloth.delivery_status == 0}">
							<td><button type="button"
									onclick="confirmCancel(${payCloth.cloth_num})">결제 취소</button></td>
						</c:when>
						<c:when test="${payCloth.delivery_status == 1}">
							<td>배송중</td>
						</c:when>
						<c:when test="${payCloth.delivery_status == 2}">
							<td>배송완료</td>
						</c:when>
						<c:otherwise>
							<td>상태 알 수 없음</td>
						</c:otherwise>
					</c:choose>


					<td><c:choose>
							<c:when
								test="${payCloth.delivery_status == 0 || payCloth.delivery_status == 1}">
       							후기는 배송이 완료되면 작성 가능합니다.
 							</c:when>
							<c:when test="${payCloth.write_review == 1}">
                                후기 작성완료
                            </c:when>
							<c:when test="${payCloth.write_review == 0}">
								<button onclick="openReviewDialog(${payCloth.cloth_num})">후기
									작성하기</button>
							</c:when>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>

		<div id="reviewDialog"
			style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
			<h2>후기 작성하기</h2>
			<br> <label> <input type="radio" name="reviewRating"
				value="good" checked> 좋아요
			</label> <label> <input type="radio" name="reviewRating" value="bad">
				싫어요
			</label> <br> <input class="review" type="hidden" id="reviewClothNum" />
			<input type="hidden" id="userId"
				value="<%=session.getAttribute("userId")%>" />
			<textarea id="reviewContent" rows="4" cols="50"
				placeholder="여기에 후기를 작성하세요"></textarea>

			<button onclick="submitReview()">제출</button>
			<button onclick="closeReviewDialog()">취소</button>
		</div>
	</div>

	<footer class="info">
		<p>전화번호 : 010 - 1234 - 1234</p>
		<p>이메일 : angus1208@naver.com</p>
		<p>홈페이지의 모든 자료는 상업적으로 이용되지 않습니다.</p>
	</footer>
</body>
</html>

