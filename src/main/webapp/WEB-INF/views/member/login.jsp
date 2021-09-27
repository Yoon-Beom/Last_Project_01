<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/styles.css" />
<style type="text/css">
#box {
	margin: auto;
	padding-top: 20px;
	font-size: 30px;
}

input {
	size: 20%;
}

h1 {
	padding-top: 30px;
	font-size: 50px;
	padding-bottom: 50px;
}

.text {
	padding-top: 15px;
	padding-bottom: 15px;
}

.button {
	margin-top: 50px;
	margin-left: 50px;
	width: 200px;
	height: 50px;
	font-size: 20px;
}
</style>
<c:choose>
	<c:when test="${result=='loginFailed'}">
	  <script>
	    window.onload=function(){
	      alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
	    }
	  </script>
	</c:when>
</c:choose>  
</head>
<body>
	<form name="membership" method="post"  action="${contextPath}/member/login.do">
		<header class="masthead">
			<div id="box">
				<h1>로그인</h1>
				<div class="text">
					&nbsp;아이디&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="member_id"><br>
				</div>
				<div class="text">
					비밀번호 &nbsp; <input type="password" name="member_pwd"><br>
				</div>
				<input type="submit" value="로그인" class="button"> 
				<a href="${contextPath}/member/membership.do">
					<input type="button" value="회원가입" class="button">
				</a>

			</div>
		</header>
	</form>
</body>
</html>