<%@page import="java.util.List"%>
<%@page import="material.Cloth"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 페이지</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"> </script>
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
<%-- function redirectToPurchase() {
    var userId = '<%= session.getAttribute("userId") %>';
    var chooseCloth = '<%= session.getAttribute("chooseCloth") %>';
    var clothNum = '<%= ((Cloth) session.getAttribute("chooseCloth")).getCloth_num() %>'; // Cloth 객체의 num 값 가져오기
    
    if (userId !== 'null') {
        fetch('/shoppingCart', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ user_Id: userId, cloth_num: clothNum, shoppingcart_count: 1 })
        })
        .then(response => {
            if (response.ok) {
                alert('구매 완료. 결제 목록 페이지로 이동합니다.');
                window.location.href = '/userPayment';
            } else {
                alert('요청에 실패했습니다.');
            }
        })
        .catch(error => console.error('Error:', error));
    } else {
        window.location.href = '/user';
    }
} --%>


    function redirectToCart() {
        var userId = '<%= session.getAttribute("userId") %>';
        var clothNum = document.getElementById("clothNum").value;
        if (userId) {
            alert("상품을 장바구니에 담았습니다.");
            window.location.href = '/add?userId=' + userId + '&cloth_num=' + clothNum;
        } else {
            window.location.href = '/user';
        }
    }
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/mainBar.jsp"></jsp:include>

    <div class="main">
        <div class="clothDetail">
            <jsp:include page="/WEB-INF/views/clothDetail.jsp"></jsp:include>
        </div>

        <div class="container">
            <div class="info">
                <h1>제품 상세 정보</h1>
                <input type="hidden" id="clothNum" value="${chooseCloth.cloth_num}" />
                <p>옷 번호: ${chooseCloth.cloth_num}</p>
                <p>이름: ${chooseCloth.cloth_name}</p>
              	<p>가격: ${chooseCloth.cloth_price}</p>
				<%
				Cloth chooseCloth = (Cloth) session.getAttribute("chooseCloth");
				String explanation = chooseCloth.getCloth_explanation();
				String result = explanation.replaceAll("\\\\n", "").replaceAll("\\\\r", ""); // \\n과 \\r을 제거
				%>
				<p>설명: <%= result %></p>

                <button id="pay" onclick="redirectToPurchase()">바로구매</button>
                <button id="shoppingcart" onclick="redirectToCart()">장바구니</button>
                <div class="icon">
                    <p><img src="/static/image/엄지위로척.png" alt="좋아요" width="24" height="24" style="vertical-align: bottom;">${chooseCloth.cloth_good}</p>
                    <p><img src="/static/image/엄지아래로척.png" alt="좋아요" width="24" height="24" style="vertical-align: bottom;">${chooseCloth.cloth_bad}</p>
                </div>
            </div>
        </div>
    </div>
    <dialog class="payment-dialog">
	<h2>구매 창</h2>
	<p>
		<%
			Cloth cloth = (Cloth) session.getAttribute("chooseCloth");
			session.setAttribute("finalTotalPrice", (cloth.getCloth_price() + 3500));
		%>
		총 결제 금액: ${finalTotalPrice}
		(${chooseCloth.cloth_price}원 + 배송비: 3500원)
		
	</p>
	<button onclick="requestPay()">결제하기</button>
	<button class="cancel-payment-button">취소</button>
	</dialog>
</body>

<script type="text/javascript">

const paymentDialog = document.querySelector('.payment-dialog');
const payButton = document.querySelector('#pay');
const shopping = document.querySelector('#shoppingcart');

function allButtonDisable() {
	payButton.disabled = true;
	shopping.disabled = true;
}
function allButtonAble() {
	payButton.disabled = false;
	shopping.disabled = false;
}

function redirectToPurchase() {
	showPaymentDialog();
	allButtonDisable();
}

function showPaymentDialog() {
	//const finalTotalPrice = document.querySelector('#ftp').value; // hidden input에서 가격 가져오기

	// 다이얼로그 열기
	paymentDialog.show();

	// 취소 버튼 이벤트
	paymentDialog.querySelector('.cancel-payment-button').addEventListener('click', function() {
		paymentDialog.close();
		allButtonAble();
	});
}

var userId = '<%= session.getAttribute("userId") %>';
var chooseClothPrice = parseInt('<%= ((Integer) session.getAttribute("finalTotalPrice")) %>');
var clothNum = '<%= ((Cloth) session.getAttribute("chooseCloth")).getCloth_num() %>'; // Cloth 객체의 num 값 가져오기

function requestPay() {
    IMP.init('imp08323214');
    fetch('/shoppingCart', {
        method: 'PUT',
    }).then(response => response.json())
      .then(user => {
        IMP.request_pay({
            pg: "kakaopay",
            pay_method: "card",
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: '상품구매',
            amount: chooseClothPrice,
            buyer_tel: user.phone_num,
            buyer_addr: user.address,
        }, function(rsp) { // callback
            if (rsp.success) { // 결제 성공 시 로직
                paymentDialog.close();
                
                if (userId !== 'null') {
                    fetch('/shoppingCart', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            user_Id: userId,
                            cloth_num: clothNum,
                            shoppingcart_count: 1
                        })
                    })
                    .then(response => response.json())
					.then(data => {
						if (data.status === 'success') {
							alert('결제가 완료되었습니다!');
							window.location.href = '/userPayment'
						} else {
							alert('결제 실패!');
						}
					});
                } else {
                    window.location.href = '/user';
                }
            } else { // 결제 실패 시 로직
                alert('결제 실패!');
                allButtonAble();
                window.location.href = '/main';
            }
        });
    });
}


</script>
</html>

