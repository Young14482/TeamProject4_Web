// 작성자 : 이나겸

// 페이지가 로드될 때 서버로부터 데이터를 가져오기 위한 함수 호출
reload();

// 상품 삭제하는 함수 예시
// fetch에 들어가는 URL다시 확인하기
function deleteProduct(cloth_num) {
    if (confirm("정말로 이 상품을 삭제하시겠습니까?")) {
        fetch(`/productManage?cloth_num=${cloth_num}`, {
            method: 'DELETE'
        }).then(response => {
            if (response.ok) {
                alert('삭제되었습니다.');
                location.reload(); // 삭제 후 페이지 새로고침
            } else {
                alert('삭제에 실패했습니다.');
            }
        });
    }
}