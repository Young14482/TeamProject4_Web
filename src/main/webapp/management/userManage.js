// 작성자 : 이나겸

// 한 아이디로 배송지를 여러개 가지고있을 경우 모든 배송지를 보여주는 함수
// 상세보기 버튼을 누르면 다이얼로그가 뜨고 모든 배송지가 보여짐
function showDetails(userId) {
	// Fetch 요청을 통해 해당 user_id의 모든 배송지를 가져옴
	fetch('/userManage', {
		method: 'POST',
		headers: {
			// POST 데이터의 content type
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: new URLSearchParams({
			userId: userId
		})
	})
		.then(response => response.json()) // 응답을 JSON으로 파싱
		.then(data => {
			// 다이얼로그에 모든 배송지를 표시
			let addresses = data;
			let dialogContent = '<ul>';
			addresses.forEach(function(address) {
				dialogContent += '<li>' + address + '</li>';
			});
			dialogContent += '</ul>';
			$('#deliveryDialog').html(dialogContent);
			$('#deliveryDialog').dialog('open');
		})
		.catch(error => {
			console.error('Error:', error);
		});
}

$(document).ready(function() {
	$('#deliveryDialog').dialog({
		autoOpen: false,
		modal: true,
		width: 400,
		height: 300,
		title: '배송지 목록'
	});
});

// 회원 등급 변경 버튼 이벤트 리스너
document.addEventListener("DOMContentLoaded", function() {
	// 변경 버튼 읽어오기
	const changeButtons = document.querySelectorAll(".btnChangeGrade");

	changeButtons.forEach(button => {
		button.addEventListener("click", function() {
			// 아이디 가져오기
			const userId = button.getAttribute('data-user-id');
			// 드롭박스에서 선택한 등급 가져오기
			const newGrade = button.closest('td').querySelector('.gradeSelect').value;

			// 요청 데이터 구성
			const requestData = {
				userId: userId,
				userGrade: newGrade // 변경할 등급
			};

			// Ajax 요청 보내기
			fetch("/userManage", {
				method: "PUT",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify({
					user_id: userId,
					user_grade: newGrade, // 여기에서 newGrade 사용
					action: 'changeGrade' // action 구분을 위함
				})
			})
				.then(response => {
					if (response.ok) {
						alert('등급이 성공적으로 변경되었습니다.');
						// 변경된 등급을 테이블에 즉시 반영
						// .querySelector('td:nth-child(9)') : 등급 칸
						// 가입 회원 목록 표의 칸 수가 달라지면 자식요소 숫자도 꼭 바꿔주기
						const gradeCell = button.closest('tr').querySelector('td:nth-child(9)');
						// 변경된 등급으로 업데이트
						gradeCell.textContent = newGrade;

					} else {
						alert('등급 변경에 실패했습니다.');
					}
				})
				.catch(error => {
					console.error("Error:", error);
					alert("회원 등급 수정 중 오류 발생");
				});
		});
	});
});

// 회원 차단 버튼 이벤트 리스너
document.addEventListener("DOMContentLoaded", function() {
	// 차단 버튼 읽어오기
	const blockButtons = document.querySelectorAll(".btnUserBlock");

	blockButtons.forEach(button => {
		button.addEventListener("click", function() {
			// 아이디 가져오기
			const userId = button.getAttribute('data-user-id');

			// Ajax 요청 보내기
			fetch('/userManage', {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({
					user_id: userId,
					action: 'blockUser' // action 구분을 위함
				})
			})
				.then(response => {
					if (response.ok) {
						alert('회원이 성공적으로 차단되었습니다.');

						// 차단된 회원을 <table>에서 삭제
						const userRow = button.closest('tr');
						userRow.remove();

						// 총 회원 수 업데이트 (차단된 회원 수만큼 -1)
						// 총 회원 수를 포함하는 div의 id가 userCount
						const userCountElement = document.getElementById("userCount");
						// 현재 총 회원 수 가져오기
						let userCount = parseInt(userCountElement.innerText.replace(/\D/g, ''));
						// 회원 수 업데이트
						userCountElement.innerText = `총 회원수 : ${userCount - 1}명`;

					} else {
						alert('회원 차단에 실패했습니다.');
					}
				})
				.catch(error => {
					console.error('Error:', error);
					alert('회원 차단 중 오류가 발생했습니다.');
				});
		});
	});
});