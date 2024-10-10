document.addEventListener("DOMContentLoaded", function() {
    const selectDeleteButton = document.querySelector('button[data-action="select-delete"]');
    const deleteAllButton = document.querySelector('button[data-action="delete-all"]');
    
    function updateTotals() {
        let totalPrice = 0;
        const rows = document.querySelectorAll('tbody tr');
        
        rows.forEach(row => {
            const itemTotal = parseFloat(row.querySelector('td:nth-child(5)').textContent.replace('원', ''));
            totalPrice += itemTotal;
        });
        
        const totalPriceElement = document.querySelector('.total-price');
        const finalTotalPriceElement = document.querySelector('.final-total-price');
        if (totalPriceElement) {
            totalPriceElement.textContent = `${totalPrice}원`;
        }
        if (finalTotalPriceElement) {
            finalTotalPriceElement.textContent = `${totalPrice + 3500}원`;
        }

        if (rows.length === 0) {
            const tableElement = document.querySelector('table');
            const emptyMessageElement = document.querySelector('.empty-message');
            if (tableElement) {
                tableElement.style.display = 'none';
            }
            if (emptyMessageElement) {
                emptyMessageElement.style.display = 'block';
            }
        }
    }

    if (selectDeleteButton) {
        selectDeleteButton.addEventListener('click', function() {
            if (confirm('정말 삭제하시겠습니까?')) {
                const checkedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
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
                        updateTotals();
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
                        updateTotals();
                    }
                });
            }
        });
    }
});
