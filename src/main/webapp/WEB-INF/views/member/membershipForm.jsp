<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/styles.css" />
<title>회원가입창</title>
<script type="text/javascript">
	window.onload = function selec() {
		var membership = document.membership;
		var now = new Date().getFullYear(); // 현재 날짜 및 시간

		for (var y = 1940; y <= now; y++) {
			membership.member_yy.add(new Option((y)));
		}
		for (var m = 1; m <= 12; m++) {
			membership.member_mm.add(new Option((m)));
		}
		for (var d = 1; d <= 31; d++) {
			membership.member_dd.add(new Option((d)));
		}
	};

	
</script>
<style type="text/css">
img {
	width: 50px;
	position: absolute;
	left: 70%;
	top: 25px;
}

input {
	width: 300px;
	height: 35px;
	text-align: "center";
}

.id_check {
	width: 70px;
	height: 28px;
	font-size: 13px;
}

#box1 {
	width: 80%;
	height: 1100px;
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

.gender {
	width: auto;
	height: auto;
}

#account {
	font-size: 13px;
	color: red;
}
</style>
</head>
<body>
	<form method="post" name="membership"
		action="${contextPath}/member/addMember.do">
		<header class="masthead">
			<div class="container">
				<div id="box1">
					<h1>
						개인회원 회원가입 <img
							src="${contextPath}/resources/assets/img/icon.png">
					</h1>
					<div class="text1">
						아이디
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="button" value="중복 확인" class="id_check">
					</div>
					<input type="text" name="member_id">
					<div class="text1">비밀번호</div>
					<input type="password" name="member_pwd">
					<div class="text1">비밀번호 확인</div>
					<input type="password" name="member_pwd1">
					<div class="text1">이름</div>
					<input type="text" name="member_name">
					<div class="text1">생년월일</div>
					<select name="member_yy">
						<option value="#">년도 선택</option>
					</select>년 <select name="member_mm" id="member_mm">
						<option value="#">월 선택</option>
					</select>월 <select name="member_dd">
						<option value="#">일 선택</option>
					</select>일
					<div class="text1">성별</div>
					<input type="radio" class="gender" name="member_gender" value="m">남성
					<input type="radio" class="gender" name="member_gender" value="f" style="margin-left: 100px;">여성
					<div class="text1">
						연락처&nbsp;&nbsp;<a id="account">숫자만 입력해주세요</a>
					</div>
					<input type="text" name="member_phone">
					<div class="text1">이메일</div>
					<input type="email" name="member_email">
					<div class="text1">주소</div>
					<input type="text" name="member_address"><br> <br>
					<br> <input type="submit" value="가입하기" >
				</div>
			</div>
		</header>
	</form>
</body>
</html>