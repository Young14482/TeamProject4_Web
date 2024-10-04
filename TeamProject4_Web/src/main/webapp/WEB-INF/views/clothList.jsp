<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@ page import="material.Cloth"%>
<%@ page import="image.Image"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>옷 목록</title>
<style type="text/css">
.main {
	display: flex;
	flex-wrap: wrap; /* 줄 바꿈을 허용 */
	justify-content: center; /* 중앙 정렬 */
}

.card {
	display: flex;
	flex-direction: column;
	align-items: center; /* 중앙 정렬을 원할 경우 */
	margin: 20px; /* 이미지 사이에 10px 간격 추가 */
	width: 350px;
}

.image {
	width: 350px;
	height: 500px;
}

.title {
	text-align: center; /* 텍스트를 가운데 정렬 */
}

.clothName {
	font-size: 17px;
	font-weight: bold;
}
</style>
</head>
<body>
	<%
	List<Cloth> searchCloth = (List<Cloth>) session.getAttribute("searchCloth");
	int count = 0;
	List<Cloth> list;
	if (searchCloth == null) {
		list = (List<Cloth>) session.getAttribute("allCloth");
	} else {
		list = searchCloth;
	}
	// 세션에 저장된 모든 속성 이름을 가져옵니다.
	for (int i = 0; i < list.size(); i++) {
		Cloth cloth = list.get(i);
		String base64 = cloth.getBase64Data();
		String explanation = cloth.getCloth_explanation().replace("\\n", "\n");
		String[] arrStr = explanation.split("\n");
		count++;
	}
	if (count > 0) {
	%>
	<h1 class="title">Item List</h1>
	<%
	}
	%>
	<div class="main">
		<%
		for (int i = 0; i < list.size(); i++) {
			Cloth cloth = list.get(i);
			String base64 = cloth.getBase64Data();
			String explanation = cloth.getCloth_explanation().replace("\\n", "\n");
			String[] arrStr = explanation.split("\n");
		%>
		<div class="card">
			<img class="image" src="data:image/png;base64,<%=base64%>"
				alt="<%=cloth%>"> <label class="clothName"><%=cloth.getCloth_name()%></label>
			<label><%="브랜드 : " + cloth.getCloth_brand()%></label> <label><%="가격 : " + cloth.getCloth_price() + "원"%></label>

			<%
			for (int j = 0; j < arrStr.length; j++) {
			%>
			<label><%=arrStr[j]%></label>
			<%
			}
			%>
		</div>
		<%
		}
		
		if (count == 0) {
			%>
			<h2>검색 결과가 없습니다</h2>
			<% 
		}
		%>
	</div>
</body>
</html>
