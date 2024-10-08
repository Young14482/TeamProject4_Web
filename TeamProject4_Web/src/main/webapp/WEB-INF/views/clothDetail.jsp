<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>옷 상세 페이지</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
.image-container {
	position: relative;
	width: 500px;
	height: 700px;
	overflow: hidden;
	border-radius: 10px;
	margin-top: -122px; /* margin: 200px auto; 상단 여백을 200px로 설정하고, 중앙 정렬 */
	margin-left: 100px;
}

.image-container img {
	position: absolute;
	width: 500px;
	height: 700px;
	object-fit: cover;
	border-radius: 10px;
	opacity: 0;
	transition: opacity 1s;
}

.image-container img.active {
	opacity: 1;
}

.arrow {
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	font-size: 2em;
	color: #333;
	background-color: rgba(255, 255, 255, 0.2);
	border: none;
	cursor: pointer;
	z-index: 1;
	transition: background-color 0.3s;
}

.arrow:hover {
	background-color: rgba(200, 200, 200, 0.7);
}

.arrow-left {
	left: 10px;
}

.arrow-right {
	right: 10px;
}

.review {
	display: flex;
	gap: 20px;
	border-top: 1px solid #000; /* 위쪽 테두리 */
	border-bottom: 1px solid #000; /* 아래쪽 테두리 */
	margin-bottom: 10px;
	margin-top: 10px
}

footer {
	border-top: 1px solid #ddd;
	padding: 10px;
	margin-top: 20px;
	margin: 10px;
}

.button-container button {
	z-index: 10; /* 버튼이 다른 요소 위에 있도록 설정 */
	pointer-events: auto; /* 클릭 이벤트를 받을 수 있도록 설정 */
}

button {
	padding: 10px 15px;
	margin-right: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	background-color: #007bff;
	color: white;
	font-size: 16px;
}

button:hover {
	background-color: #0056b3;
}

.review-img {
	width: 24px;
	height: 24px;
	vertical-align: bottom;
}

.good-or-bad {
	display: none;
}

.detail {
	margin-top: 100px;
    text-align: center; /* 가운데 정렬 */
}

.detailimage {
    display: block;
    margin: 0 auto; /* 이미지를 가운데 정렬 */
}
</style>


</head>
<body>
<div class="image-container">
    <button class="arrow arrow-left">&lt;</button>
    <c:if test="${not empty chooseCloth.main_image1}">
        <img class="image active" src="data:image/png;base64,${chooseCloth.main_image1}" alt="${chooseCloth.cloth_name}">
    </c:if>
    <c:if test="${not empty chooseCloth.main_image2}">
        <img class="image" src="data:image/png;base64,${chooseCloth.main_image2}" alt="${chooseCloth.cloth_name}">
    </c:if>
    <c:if test="${not empty chooseCloth.main_image3}">
        <img class="image" src="data:image/png;base64,${chooseCloth.main_image3}" alt="${chooseCloth.cloth_name}">
    </c:if>
    <button class="arrow arrow-right">&gt;</button>
</div>


	<div class="detail">
		<c:if test="${not empty chooseCloth.explanation_image1}">
        <img class="detailimage" src="data:image/png;base64,${chooseCloth.explanation_image1}" alt="${chooseCloth.cloth_name}">
   		</c:if>
   		<c:if test="${not empty chooseCloth.explanation_image2}">
        <img class="detailimage" src="data:image/png;base64,${chooseCloth.explanation_image2}" alt="${chooseCloth.cloth_name}">
   		</c:if>
   		<c:if test="${not empty chooseCloth.explanation_image3}">
        <img class="detailimage" src="data:image/png;base64,${chooseCloth.explanation_image3}" alt="${chooseCloth.cloth_name}">
   		</c:if>
   		<c:if test="${not empty chooseCloth.explanation_image4}">
        <img class="detailimage" src="data:image/png;base64,${chooseCloth.explanation_image4}" alt="${chooseCloth.cloth_name}">
   		</c:if>
   		<c:if test="${not empty chooseCloth.explanation_image5}">
        <img class="detailimage" src="data:image/png;base64,${chooseCloth.explanation_image5}" alt="${chooseCloth.cloth_name}">
   		</c:if>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
		<p>설명: ${chooseCloth.cloth_explanation}</p>
	</div>

	<footer>
		<div class="button-container">
			<button id="good-button">좋아요 댓글 보기</button>
			<button id="bad-button">싫어요 댓글 보기</button>
			<button id="all-button">모두 보기</button>
		</div>

		<c:forEach var="review" items="${reviewList}">
			<div class="review">
				<p>아이디: ${review.user_ID}</p>
				<p>댓글 내용: ${review.reviewDetail}</p>
				<p class="good-or-bad">${review.good_or_bad}</p>
				<c:choose>
					<c:when test="${review.good_or_bad == 'good'}">
						<img src="data:image/png;base64,<%=session.getAttribute("good")%>"
							alt="좋아요" width="24" height="24" style="margin-top: 15px;">
					</c:when>
					<c:when test="${review.good_or_bad == 'bad'}">
						<img src="data:image/png;base64,<%=session.getAttribute("bad")%>"
							alt="싫어요" width="24" height="24" style="margin-top: 15px;">
					</c:when>
				</c:choose>
			</div>
		</c:forEach>


	</footer>
</body>

<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        let currentIndex = 0;
        const images = document.querySelectorAll(".image-container img");
        const totalImages = images.length;

        function showNextImage() {
            images[currentIndex].classList.remove("active");
            currentIndex = (currentIndex + 1) % totalImages;
            images[currentIndex].classList.add("active");
        }

        function showPreviousImage() {
            images[currentIndex].classList.remove("active");
            currentIndex = (currentIndex - 1 + totalImages) % totalImages;
            images[currentIndex].classList.add("active");
        }

        document.querySelector(".arrow-right").addEventListener("click", showNextImage);
        document.querySelector(".arrow-left").addEventListener("click", showPreviousImage);

        setInterval(showNextImage, 3000); // 3초마다 이미지 변경

        document.getElementById("good-button").addEventListener("click", function() {
            filterReviews('good');
        });

        document.getElementById("bad-button").addEventListener("click", function() {
            filterReviews('bad');
        });

        document.getElementById("all-button").addEventListener("click", function() {
            filterReviews('all');
        });

        function filterReviews(criteria) {
            const reviews = document.querySelectorAll(".review");
            reviews.forEach(review => {
                if (criteria === 'all' || review.querySelector(".good-or-bad").textContent === criteria) {
                    review.style.display = 'flex';
                } else {
                    review.style.display = 'none';
                }
            });
        }
    });
</script>

</html>

