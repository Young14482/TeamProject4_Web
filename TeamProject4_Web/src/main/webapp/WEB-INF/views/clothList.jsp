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

.clothLink {
	color: black;
	font-size: 20px;
	text-decoration: none;
}
</style>
</head>
<body>
	<%
	List<Cloth> searchCloth = (List<Cloth>) session.getAttribute("searchCloth");
	boolean print = false;
	List<Cloth> list;
	if (searchCloth == null) {
		list = (List<Cloth>) session.getAttribute("allCloth");
	} else {
		list = searchCloth;
	}
	
	if (list.size() > 0 && list != null) {
		print = true;
	%>
	<h1 class="title">Item List</h1>
	<%
	}
	%>
	<div class="main">
		<%
		for (int i = 0; i < list.size(); i++) {
			Cloth cloth = list.get(i);
			String base64 = cloth.getList_image();
			if (cloth.getCloth_explanation() != null) {
				String explanation = cloth.getCloth_explanation().replace("\\n", "\n");
				String[] arrStr = explanation.split("\n");
		%>
		<div class="card">
			<a href="detailPage?clothNum=<%=cloth.getCloth_num()%>">
				<img class="image" src="/static/image/cloth/옷<%=cloth.getCloth_num()%>.png" alt="">
			</a>
			<label class="clothName">
				<a href="detailPage?clothNum=<%=cloth.getCloth_num()%>" class="clothLink"><%=cloth.getCloth_name()%></a>
			</label>
			<label><%="브랜드 : " + cloth.getCloth_brand()%></label>
			<label><%="가격 : " + cloth.getCloth_price() + "원"%></label>
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
		}
		if (print == false) {
		%>
		<h2>검색 결과가 없습니다</h2>
		<%
		}
		%>
	</div>
</body>
</html>
