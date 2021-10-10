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
<title>비밀번호 수정창</title>
<style type="text/css">
#box{
margin:auto;
padding-top:200px;
font-size: 30px;
}

</style>
<script type="text/javascript">

//submit
function submitBtn() {		
	var frm = document.pwdcheck;
	
	if(frm.member_pwd.value == "") {
		alert("새 비밀번호를 입력하세요.");
		frm.member_pwd.focus();
	} else if(frm.member_pwd2.value == "") {
		alert("새 비밀번호 확인을 입력하세요.");
		frm.member_pwd2.focus();
	} else if(frm.pwdCheck.value == "pwdUnmatch") {
		alert("새 비밀번호가 일치하지 않습니다.");
		frm.member_pwd.focus();
	} else {
		frm.action = '${contextPath}/mypage/checkPwd.do';
		frm.method = 'post';
		frm.submit();
	}
}
// 비밀번호 유효성 검사
// 비밀번호 8자리 이상
// 숫자 포함	
// 영대 문자 포함	
// 영소 문자 포함	
// 특수문자 포함	
// 공백 X	
// 같은 문자 4번 반복 X	
// 아이디 포함 X	
// 한글 X
function chkPW() {
	var id = document.pwdcheck.member_id.value;
	var pwd = document.pwdcheck.member_pwd.value;
	var pwd1 = document.pwdcheck.member_pwd2.value;
	var pwdNow = document.pwdcheck.pwdNow.value;
	
	var pwdtext = document.getElementById('pwdtext');
	var pwdCheck = document.pwdcheck.pwdCheck;
	
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	
	pwdCheck.value = 'pwdUnmatch';
	
	if(pwdNow ==''){
		pwdtext.innerHTML = '현재 비밀번호를 입력하세요.';
		document.pwdcheck.pwdNow.focus();
	} else if(pwd == '') {
		pwdtext.innerHTML = '새 비밀번호를 입력하세요.';
		document.pwdcheck.member_pwd.focus();
	} else if (false === reg.test(pwd)) {
		pwdtext.innerHTML = '새 비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.';
		document.pwdcheck.member_pwd.focus();
	} else if (/(\w)\1\1\1/.test(pwd)) {
		pwdtext.innerHTML = '같은 문자를 4번 이상 사용하실 수 없습니다.';
		document.pwdcheck.member_pwd.focus();
	} else if (pwd.search(id) > -1) {
		pwdtext.innerHTML = '새 비밀번호에 아이디가 포함되었습니다.';
		document.pwdcheck.member_pwd.focus();
	} else if (pwd.search(/\s/) != -1) {
		pwdtext.innerHTML = '새 비밀번호는 공백 없이 입력해주세요.';
		document.pwdcheck.member_pwd.focus();
	} else if (hangulcheck.test(pwd)) {
		pwdtext.innerHTML = '새 비밀번호에 한글을 사용 할 수 없습니다.';
		document.pwdcheck.member_pwd.focus();
	} else if (pwd1 == '') {
		pwdtext.innerHTML = '새 비밀번호 확인을 입력하세요.';
		document.pwdcheck.member_pwd2.focus();
	} else if(pwd != pwd1) {
		pwdtext.innerHTML = '새 비밀번호가 일치하지 않습니다.';
		document.pwdcheck.member_pwd.focus();
	} else {
		pwdtext.innerHTML = '';
		pwdCheck.value = 'pwdmatch';
	}
	
}
</script>
<style>
 p {
 	font-size: 18px;
 	color: red;
 }
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
<c:choose>

	<c:when test="${result == 'pwdCheckFail'}">
	  <script>
	  window.onload=function(){
	      alert("현재 비밀번호가 틀립니다.");
	    }
	  </script>
	</c:when>
	
</c:choose>
</head>
<body>
 <header class="masthead">
 <form name="pwdcheck">
 <div id="box"><input type="hidden" name="member_id" value="${member.member_id }">
 <h1>비밀번호 수정</h1>
<p id="pwdtext"></p>
 <br>
 <div>현재 비밀번호 :   &nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="pwdNow"></div>
  <br>
 <div>새 비밀번호 : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="member_pwd"onchange="chkPW()"></div>
  <br>
 <div>새 비밀번호 확인 : &nbsp;<input type="password" name="member_pwd2" onchange="chkPW()"></div>
 	<input type="hidden" name="pwdCheck" value="pwdUnmatch">
<br><br><input type="button" value="암호변경" onclick="submitBtn()">&nbsp;&nbsp;<a href = "${pageContext.request.contextPath}/mypage/myPage.do"><input type="button" value="취소"></a>
 </div>
 </form>
 </header>
</body>
</html>