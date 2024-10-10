<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 페이지</title>
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

.container {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    gap: 50px;
    position: fixed; /* 화면에 고정 */
    top: 410px; /* 중앙에 위치하도록 조정 */
    left: 80%; /* 중앙에 위치하도록 조정 */
    transform: translate(-50%, -50%); /* 중앙에 위치하도록 조정 */
}

.image {
    width: 400px;
    height: 500px;
    object-fit: cover;
    border-radius: 10px;
}

.info {
    width: 300px;
}

.info h1 {
    font-size: 24px;
    margin-bottom: 20px;
}

.info p {
    font-size: 18px;
    margin-bottom: 10px;
}

button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    margin-top: 10px;
}

button:hover {
    background-color: #0056b3;
}

.main {
    position: relative; /* 상대 위치 조정 */
    top: 300px; /* 200px 아래로 이동 */
}

.icon {
	display: flex;
	gap: 50px;
}

.clothDetail {
	width: 700px;
}
</style>
<script type="text/javascript">
    function redirectToPurchase() {
        var userId = '<%= session.getAttribute("userId") %>';
        if (userId) {
            window.location.href = '/user';
        } else {
            window.location.href = '/user';
        }
    }

    function redirectToCart() {
        var userId = '<%= session.getAttribute("userId") %>';
        if (userId) {
            window.location.href = '/user';
        } else {
            window.location.href = '/user';
        }
    }
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/mainBar.jsp"></jsp:include>

    <div class="main">
        <div class=clothDetail>
            <jsp:include page="/WEB-INF/views/clothDetail.jsp"></jsp:include>
        </div>

        <div class="container">
            <div class="info">
                <h1>제품 상세 정보</h1>
                <p>이름: ${chooseCloth.cloth_name}</p>
                <p>가격: ${chooseCloth.cloth_price}</p>
                <p>설명: ${chooseCloth.cloth_explanation}</p>
                <button onclick="redirectToPurchase()">바로구매</button>
                <button onclick="redirectToCart()">장바구니</button>
                <div class="icon">
                	<p><img src="data:image/png;base64,<%=session.getAttribute("good")%>"
							alt="좋아요" width="24" height="24" style="vertical-align: bottom;"> ${chooseCloth.cloth_good}</p>
              		<p><img src="data:image/png;base64,<%=session.getAttribute("bad")%>"
							alt="좋아요" width="24" height="24" style="vertical-align: bottom;"> ${chooseCloth.cloth_bad}</p>
                </div>
                
            </div>
        </div>
    </div>

</body>
</html>

