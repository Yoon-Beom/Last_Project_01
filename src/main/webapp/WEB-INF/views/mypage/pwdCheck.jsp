<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내주변</title>
<style type="text/css">
#box{
margin:auto;
padding-top:200px;
font-size: 30px;
}

</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
</head>
<body>
 <header class="masthead">
 <div class="container">
 <div id="box">
비밀번호 : <input type="password" id="pwdcheck">&nbsp;&nbsp;
<a href = "${pageContext.request.contextPath}/mypage/membershipMod.do"><input type="button" value="확인"></a>
<br><br><br><a href = "${pageContext.request.contextPath}/mypage/myPage.do"><input type="button" value="취소"></a>
 </div>
 </div>
 </header>
</body>
</html>