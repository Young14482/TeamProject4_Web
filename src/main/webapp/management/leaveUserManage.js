// 작성자 : 이나겸

// 회원 계정 복구 버튼 이벤트 리스너
document.addEventListener("DOMContentLoaded", function() {
	// 회원 계정 복구 버튼 읽어오기
	const changeLeaveButton = document.querySelectorAll(".btnChangeLeave");

	changeLeaveButton.forEach(button => {
		button.addEventListener("click", function() {
			// 아이디 가져오기
			const userId = button.getAttribute('data-user-id');

			// Ajax 요청 보내기
			fetch('/leaveUserManage', {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({
					user_id: userId,
					action: 'changeLeave' // action 구분을 위함
				})
			})
				.then(response => {
					if (response.ok) {
						alert('회원 계정이 성공적으로 복구되었습니다.');
						
						// 계정 복구된 회원을 <table>에서 삭제
						const userRow = button.closest('tr');
						userRow.remove();
						
						// 탈퇴 회원 수 업데이트 (계정 복구된 회원 수 만큼 -1)
						// 탈퇴 회원 수를 포함하는 div의 id가 leaveUserCount
						const leaveUserCountElement = document.getElementById("leaveUserCount");
						// 현재 탈퇴 회원 수 가져오기
						let leaveUserCount = parseInt(leaveUserCountElement.innerText.replace(/\D/g, ''));
						// 탈퇴 회원 수 업데이트
						leaveUserCountElement.innerText = `탈퇴 회원수 : ${leaveUserCount - 1}명`;

					} else {
						alert('회원 계정 복구에 실패했습니다.');
					}
				})
				.catch(error => {
					console.error('Error:', error);
					alert('회원 계정 복구 중 오류가 발생했습니다.');
				});
		});
	});
});