<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러 이미지 업로드</title>

</head>
<body>
	<p>목록에 출력할 이미지를 선택하세요 (필수)</p>
    <form id="uploadForm" action="uploadImages" method="post" enctype="multipart/form-data">
        <input type="file" name="files" multiple />
        <a href="./uploadImage?divide=1">확인</a> <!-- 하이퍼 링크로 변경 -->
    </form>
</body>
</html>


