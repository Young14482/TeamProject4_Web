<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/ 비밀번호 찾기</title>
</head>
<body>
	<!-- 다이얼말고 display를 none으로 주고 스타일 변경할지 말지 고민 -->
<button id="kakao-login-btn">카카오 로그인</button>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
  Kakao.init('YOUR_APP_KEY'); // 본인의 앱 키로 초기화

  document.getElementById('kakao-login-btn').addEventListener('click', function() {
    Kakao.Auth.login({
      success: function(authObj) {
        // 로그인 성공 후 사용자 정보 요청
        Kakao.API.request({
          url: '/v2/user/me',
          success: function(response) {
            const kakaoAccount = response.kakao_account;
            console.log(kakaoAccount);
            
            // 가져온 정보를 회원가입 폼에 자동으로 입력
            document.getElementById('name').value = kakaoAccount.profile.nickname;
            document.getElementById('birthdate').value = kakaoAccount.birthday;
            document.querySelector(`input[name="gender"][value="${kakaoAccount.gender === 'male' ? '남' : '여'}"]`).checked = true;
            document.getElementById('phone').value = kakaoAccount.phone_number;
          },
          fail: function(error) {
            console.error(error);
          }
        });
      },
      fail: function(err) {
        console.error(err);
      }
    });
  });
</script>

	
	
</body>
</html>