<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.nio.file.Files, java.nio.file.Paths, java.nio.file.Path, material.Cloth" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>옷 상세 페이지</title>
<style type="text/css">
.image-container {
	position: relative;
	width: 500px;
	height: 700px;
	overflow: hidden;
	border-radius: 10px;
	margin-top: -122px; 
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
	border-top: 1px solid #000; 
	border-bottom: 1px solid #000;
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
	z-index: 10;
	pointer-events: auto; 
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
	text-align: center;
}

.detailimage {
	display: block;
	margin: 0 auto; 
}
</style>


</head>
<body>
	<div class="image-container">
		<button class="arrow arrow-left">&lt;</button>
		<%
			
		Cloth cloth = (Cloth) session.getAttribute("chooseCloth");
		String folderPath = application.getRealPath("/static/image/cloth/cloth" + cloth.getCloth_num()); // 폴더 경로를 여기에 작성하세요
		
		for (int i = 1; i <= 3; i++) {
		    String fileName = i + ".png"; // 파일 이름
		    Path filePath = Paths.get(folderPath, fileName);
		    String className = (i == 1) ? "image active" : "image";

		    if (Files.exists(filePath)) {
		        %>
		        <img class="<%= className %>" src="/static/image/cloth/cloth${ chooseCloth.cloth_num }/<%= fileName %>" alt="<%= cloth.getCloth_name() %>">
		        <%
		    }
		}

		%>
		
		<button class="arrow arrow-right">&gt;</button>
	</div>


	<div class="detail">
	
		<%
		for (int i = 1; i <= 5; i++) {
		    String fileName = "1-" + i + ".png"; // 파일 이름
		    Path filePath = Paths.get(folderPath, fileName);

		    if (Files.exists(filePath)) {
		        %>
		        <img class="detailimage" src="/static/image/cloth/cloth${ chooseCloth.cloth_num }/<%= fileName %>" alt="<%= cloth.getCloth_name() %>">
		        <%
		    }
		}
		%>
		
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
						<img src="/static/image/엄지위로척.png" alt="좋아요" width="24" height="24" style="margin-top: 15px;">
					</c:when>
					<c:when test="${review.good_or_bad == 'bad'}">
						<img src="/static/image/엄지아래로척.png" alt="싫어요" width="24" height="24" style="margin-top: 15px;">
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