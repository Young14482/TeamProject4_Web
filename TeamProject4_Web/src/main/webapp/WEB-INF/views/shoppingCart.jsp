<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>쇼핑 카트</title>
<link rel="stylesheet" type="text/css" href="./static/css/shoppingCart.css">
<script src="${pageContext.request.contextPath}/static/js/shoppingCart.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/mainBar.jsp"></jsp:include>
    <div class="info">
        <h1>쇼핑 카트</h1>
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
                            <td><input type="checkbox" name="selectedItems" value="${item.cloth_num}"></td>
                            <td class="product-info">
                                <img src="data:image/jpeg;base64,${item.list_Image}" alt="${item.cloth_name}" class="product-image">
                                ${item.cloth_name}
                            </td>
                            <td>${item.shoppingcart_count}</td>
                            <td>${item.cloth_price}원</td>
                            <td>
                                <c:set var="itemTotal" value="${item.shoppingcart_count * item.cloth_price}" />
                                ${itemTotal}원
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4" class="total">합계</td>
                        <td class="total total-price">
                            <c:set var="totalPrice" value="0" />
                            <c:forEach var="item" items="${shoppingCartList}">
                                <c:set var="itemTotal" value="${item.shoppingcart_count * item.cloth_price}" />
                                <c:set var="totalPrice" value="${totalPrice + itemTotal}" />
                            </c:forEach>
                            ${totalPrice}원
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" class="total">배송비</td>
                        <td class="total">3500원</td> <!-- 고정 배송비 -->
                    </tr>
                    <tr>
                        <td colspan="4" class="total">총 합계</td>
                        <td class="total final-total-price">
                            <c:set var="finalTotalPrice" value="${totalPrice + 3500}" />
                            ${totalPrice}원 + 3500원 = ${finalTotalPrice}원
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
</body>
</html>
