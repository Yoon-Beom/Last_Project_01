<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css" />
<title>회원정보 수정</title>
<style type="text/css">
input {
	width: 300px;
	height: 35px;
	text-align: "center";
}

#box1 {
	width: 80%;
	height: 900px;
	position: relative;
	top: 50px;
	margin: auto;
	background-color: white;
}

.text1 {
	font-size: 20px;
	margin-top: 20px;
	text-align: left;
	padding-left: 36%;
}
.text2 {
	width: 70px;
	text-align: "center";
}	

.gender {
	width: auto;
	height: auto;
}

#account {
	font-size: 13px;
	color: red;
}

.button {
	width: 50px;
}

.pwdcheck {
	position: relative;
	left: 3%;
}
</style>
</head>
<body>
	<header class="masthead">
		<form method="post" name="membership"
			action="${contextPath}/mypage/update.do">
			<div class="container">
				<div id="box1">
					<h1>회원 정보 수정</h1>
					<input type="hidden" name=member_birth value="${member.member_birth }">
					<div class="text1">아이디</div>
					<input type="text" name="member_id" value="${member.member_id }" readonly>
					<div class="text1">이름</div>
					<input type="text" name="member_name" value="${member.member_name }">
					<div class="text1">주소</div>
					<input type="text" name="member_address" value="${member.member_address }">
					<div class="text1">이메일</div>
					<input type="email" name="member_email" value="${member.member_email }">
					<div class="text1">
						연락처&nbsp;&nbsp;<a id="account">-빼고 숫자만 입력해주세요</a>
					</div>
					<input type="text" class="text2" name="member_phone1"  value="${fn:substring(member.member_phone,0,3)}">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="text" class="text2"name="member_phone2"   value="${fn:substring(member.member_phone,4,8)}">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="text" class="text2"name="member_phone3"   value="${fn:substring(member.member_phone,9,13)}">
		
					<div class="text1">비밀번호</div>
					<input type="password" name="member_pwd" value="${member.member_pwd}">
					<div class="text1">비밀번호 확인</div>
					<div class="pwdcheck">
						<input type="password" name="member_pwd1" value="${member.member_pwd}"> &nbsp;&nbsp;
						<input type="button" value="확인" class="button">
					</div>
					<br> <br> <br> <input type="submit" value="수정"
						class="button">&nbsp;&nbsp;&nbsp; 
						<a href="${pageContext.request.contextPath}/mypage/myPage.do">
						<input type="button" value="취소" class="button"></a>
				</div>
			</div>
	</header>
	</form>
</body>
</html>