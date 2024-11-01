<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.form {
    flex-direction: row;
    align-items: center;
    display: flex;
    align-content: space-around;
    justify-content: center;
    margin: 50px;
}

#userInput {
    width: 500px;
}

input[type="text"] {
    width: 300px;
    height: 40px;
    font-size: 16px;
    margin-bottom: 10px;
    margin-top: 10px;
    margin-right: 20px;
}

button {
    width: 150px;
    height: 46px;
    font-size: 16px;
}

.radio-group {
    display: flex;
    align-content: center;
    margin-left: 630px;
}

.radio-group label {
    margin-right: 20px;
}

header {
    background-color: #333;
    color: #fff;
    padding: 10px 0;
    position: fixed;
    width: 100%;
    top: 0;
    left: 0;
    z-index: 1000;
    transition: top 0.5s; /* 부드러운 전환 효과 */
    height: 120px;
}

.logo {
    text-align: center;
    color: white;
    font-family: Arial, sans-serif;
}

.info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 500px;
    font-family: Arial, sans-serif;
    margin: 0;
    background-color: #f4f4f4;
}

.link {
    display: flex;
    justify-content: center;
    list-style: none;
    padding: 0;
    height: 25px;
}

.link li {
    margin-left: 20px;
    margin-right: 20px;
}

.link a {
    color: white;
    text-decoration: none;
    font-family: Arial, sans-serif;
    font-size: 30px;
}

.search {
    padding-top: 200px; /* 헤더 높이만큼 패딩 추가 */
}

.hyperLink {
    color: white;
    text-decoration-line: none;
}

.link p {
    font-size: 30px;
    margin-top: 0px;
}
.jt {
	align-content: center;
	text-align: center;
}
</style>

</head>
<body>

    <header>
        <nav>
            <div class="logo">
                <a href="./main" class="hyperLink"><h1>Web Project 홈페이지</h1></a>
            </div>
            <ul class="link">
                <c:if test="${not empty sessionScope.userId}">
                    <li><p>환영합니다, ${ userId }님!</p></li>
                    <li><a href="/logout">로그 아웃</a></li>
                    <li><a href="./shoppingCart?userId=${ userId }">장바구니</a></li>
                    <li><a href="./userPayment">결제 완료 목록</a></li>
                </c:if>
                <c:if test="${empty sessionScope.userId}">
                    <li><a href="./user">로그인</a></li>
                    <li><a href="./signup">회원가입</a></li>
                </c:if>
                <li><a href="./search">☆내게 맞는 옷 찾기☆</a></li>
            </ul>
        </nav>
    </header>

    <div class="search">
        <form method="post">
            <div>
            <h1 class="jt">설문 조사를 바탕으로 나열한 결과 리스트</h1>
            
            </div>
            <div class="form">
                <input type="text" id="userInput" name="userInput"
                    value="<c:out value='${userInputDetail}' default=''/>">
                <button type="submit">결과 내 재검색</button>
            </div>

            <div class="radio-group">
                <label><input type="radio" name="sortOption"
                    value="highPrice"
                    <c:if test="${sessionScope.sortOption == 'highPrice'}">checked</c:if>>
                    높은 가격순으로 정렬</label> <label><input type="radio" name="sortOption"
                    value="lowPrice"
                    <c:if test="${sessionScope.sortOption == 'lowPrice'}">checked</c:if>>
                    낮은 가격순으로 정렬</label> <label><input type="radio" name="sortOption"
                    value="name"
                    <c:if test="${sessionScope.sortOption == 'name'}">checked</c:if>>
                    이름순으로 정렬</label> <label><input type="button"
                    onclick="clearRadioButtons()" value="선택 해제"></label>
            </div>

        </form>
    </div>

    <jsp:include page="/WEB-INF/views/clothList.jsp"></jsp:include>

    <footer class="info">
        <p>전화번호 : 010 - 1234 - 1234</p>
        <p>이메일 : angus1208@naver.com</p>
        <p>홈페이지의 모든 자료는 상업적으로 이용되지 않습니다.</p>
    </footer>
</body>

<script>
function toggleCheckbox(checkbox) {
    checkbox.checked = !checkbox.checked;
}

function clearRadioButtons() {
    const radioButtons = document.querySelectorAll('input[name="sortOption"]');
    radioButtons.forEach(radio => radio.checked = false);
}

function toggleCheckbox(checkbox) {
    checkbox.checked = !checkbox.checked;
}

function clearRadioButtons() {
    const radioButtons = document.querySelectorAll('input[name="sortOption"]');
    radioButtons.forEach(radio => radio.checked = false);
}

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


