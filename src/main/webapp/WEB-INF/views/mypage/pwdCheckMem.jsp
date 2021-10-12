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
<title>회원정보수정 비밀번호 확인창</title>
<style type="text/css">
#box{
margin:auto;
padding-top:200px;
font-size: 30px;
}

</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
<c:choose>

	<c:when test="${result == 'pwdCheckFail'}">
	  <script>
	  window.onload=function(){
	      alert("비밀번호가 틀립니다.");
	    }
	  </script>
	</c:when>
</c:choose>  
</head>
<body>
 <header class="masthead">
 <form name="cancleMem" method="post"  action="${contextPath}/mypage/checkMem.do">
 <div class="container">
 <div id="box">
  <h1>회원정보 수정</h1>
비밀번호 : <input type="password" name="pwdcheck" id="pwdcheck">&nbsp;&nbsp;
<input type="submit" value="확인">
<br><br><br><a href = "${pageContext.request.contextPath}/mypage/myPage.do"><input type="button" value="취소"></a>
 </div>
 </div>
 </form>
 </header>
</body>
</html>