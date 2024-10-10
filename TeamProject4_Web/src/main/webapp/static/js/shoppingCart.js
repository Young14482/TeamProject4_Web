document.addEventListener("DOMContentLoaded", function() {
	const selectDeleteButton = document.querySelector('button[data-action="select-delete"]');
	const deleteAllButton = document.querySelector('button[data-action="delete-all"]');
	const orderButton = document.querySelector('button[data-action="order"]');

	/*  function updateTotals() {
		  let totalPrice = 0;
		  const rows = document.querySelectorAll('tbody tr');
		  
		  rows.forEach(row => {
			  const itemTotal = parseFloat(row.querySelector('td:nth-child(5)').textContent.replace('원', ''));
			  totalPrice += itemTotal;
		  });
		  
		  const totalPriceElement = document.querySelector('.total-price');
		  const finalTotalPriceElement = document.querySelector('.final-total-price');
	  	
		  console.log(rows.length)
	  	
		  if (totalPriceElement) {
			  totalPriceElement.textContent = `${totalPrice}원`;
		  }
		  if (finalTotalPriceElement) {
			  finalTotalPriceElement.textContent = `${totalPrice + 3500}원`;
		  }
  
		  const tableElement = document.querySelector('table');
		  const emptyMessageElement = document.querySelector('.empty-message');
		  if (rows.length === 0) { // 모든 항목이 삭제된 경우
			  if (tableElement) {
				  tableElement.style.display = 'none'; // 테이블 숨기기
			  }
			  if (emptyMessageElement) {
				  emptyMessageElement.classList.remove('hidden'); // 문구 표시
				  emptyMessageElement.style.display = 'block';
			  }
		  } else {
			  if (tableElement) {
				  tableElement.style.display = ''; // 테이블 표시
			  }
			  if (emptyMessageElement) {
				  emptyMessageElement.classList.add('hidden'); // 문구 숨기기
				  emptyMessageElement.style.display = 'none';
			  }
		  }
	  }*/

	if (selectDeleteButton) {
		selectDeleteButton.addEventListener('click', function() {
			const checkedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
			if (checkedItems.length === 0) { // 체크된 항목이 없는 경우
				alert('선택된 상품이 없습니다');
				return; // 삭제 작업을 중단
			}
			if (confirm('정말 삭제하시겠습니까?')) {
				const checkedValues = Array.from(checkedItems).map(item => item.value);

				// AJAX 요청 보내기
				fetch('/shoppingCart', {
					method: 'DELETE',
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded',
					},
					body: `selectedItems=${checkedValues.join(',')}`
				})
					.then(response => response.json())
					.then(data => {
						if (data.status === 'success') {
							checkedItems.forEach(item => {
								item.closest('tr').remove();
							});
							location.reload(); // 페이지 새로고침
						}
					});
			}
		});
	}

	if (deleteAllButton) {
		deleteAllButton.addEventListener('click', function() {
			if (confirm('모든 항목을 정말 삭제하시겠습니까?')) {
				const allItems = document.querySelectorAll('input[name="selectedItems"]');
				const allValues = Array.from(allItems).map(item => item.value);

				// AJAX 요청 보내기
				fetch('/shoppingCart', {
					method: 'DELETE',
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded',
					},
					body: `selectedItems=${allValues.join(',')}`
				})
					.then(response => response.json())
					.then(data => {
						if (data.status === 'success') {
							allItems.forEach(item => {
								item.closest('tr').remove();
							});
							location.reload(); // 페이지 새로고침
						}
					});
			}
		});
	}

	if (orderButton) {
		orderButton.addEventListener('click', function() {
			if (confirm('주문 하시겠습니까?')) {
				const allItems = document.querySelectorAll('tbody tr');
				const orderData = Array.from(allItems).map(row => {
					return {
						user_Id: userId, // user_Id 추가
						cloth_num: row.querySelector('input[name="selectedItems"]').value,
						cloth_name: row.querySelector('.product-info').textContent.trim(),
						shoppingcart_count: row.querySelector('td:nth-child(3)').textContent.trim(),
						cloth_price: row.querySelector('td:nth-child(4)').textContent.replace('원', '').trim(),
						list_Image: row.querySelector('td:nth-child(5)').textContent.trim()
					};
				});

				fetch('/shoppingCart', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json',
					},
					body: JSON.stringify(orderData)
				})
					.then(response => response.json())
					.then(data => {
						if (data.status === 'success') {
							alert('결제 화면으로 이동합니다.');
							window.location.href = '/userPayment'; // 주문 후 /payment 주소로 이동
						} else {
							alert('주문 처리에 실패했습니다.');
						}
					});
			}
		});
	}
});
