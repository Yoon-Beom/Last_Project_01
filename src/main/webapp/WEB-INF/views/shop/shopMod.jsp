<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("utf-8");
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css" />
<style type="text/css">
#box1 {
	width: 80%;
	height: 2000px;
	position: relative;
	top: 50px;
	margin: auto;
	background-color: white;
}

table {
	width: 70%;
	margin: auto;
}

#petimage {
	width: 120px;
}

img {
	width: 120px;
}

td {
	text-align: left;
}

.td {
	border: 1px solid gray;
	text-align: center;
}

p {
	font-size: xx-large;
	font-weight: bolder;
	display: inline;
}
.button {
	width: 50px;
}

.title {
	border-bottom: 3px solid gray;
}
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 수정 페이지</title>
</head>
<body>
	<header class="masthead">
		<form method="post" name="shopInfo" action="${contextPath}/shop/shopUpdate.do">
			<div class="container">
				<div id="box1">
				<h1>매장 정보 수정</h1>
<table>
	<tr>
		<td class="td" width="20%" colspan="2">매장명</td>
		<td class="td" width="20%" colspan="2">에이매장</td>
	</tr>
	<tr>
		<td class="td" width="20%" colspan="2">사업자등록번호</td>
		<td class="td" width="20%" colspan="2">111-1111-1111</td>
	</tr>
	<tr>
		<td class="td" width="20%" colspan="2">매장 대표 사진</td>
		<td class="td" width="20%" colspan="2"><img	src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
	</tr>
	<tr>		
		<td class="td" width="7%">운영시간</td>
		<td class="td" width="10%"> 11:00~16:00</td>
		<td class="td" width="7%" rowspan="3">매장소개</td>
		<td class="td" width="10%" rowspan="3"> 어쩌고 저쩌구</td>
	</tr>	
	<tr>		
		<td class="td" width="7%">주소</td>
		<td class="td" width="10%"> 경기도 구리시</td>
	<tr>	
		<td class="td" width="7%">☎</td>
		<td class="td" width="10%"> 010-111-1111</td>
	</tr>
</table>
 <br>
<table>	
	<tr>	
		<td class="td" width="7%">매장 서브 사진</td>
		<td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
		<td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
	</tr>
	<tr>
		<td>
		<br>
		</td>
	</tr>
	<tr>	
		<td class="td" width="7%">매장 서브 사진</td>
		<td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
		<td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
	</tr>
</table>

		<br> <br> <br> <input type="button" value="수정" onclick="submitBtn()"
						class="button">&nbsp;&nbsp;&nbsp; 
						<a href="${pageContext.request.contextPath}/shop/shopMyPage.do">
						<input type="button" value="취소" class="button"></a>
	</div>
			</div>
	</header>
	</form>
</body>
</html>