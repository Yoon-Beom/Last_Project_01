<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
<title>기업 회원가입창</title>
<script type="text/javascript">
window.onload=function(){
	var membership = document.membership;
	var now = new Date().getFullYear();	// 현재 날짜 및 시간

	for(var y=1940;y<=now;y++){
		membership.member_yy.add(new Option((y)+'년'));
	}
	for(var m=1;m<=12;m++){
		membership.member_mm.add(new Option((m)+'월'));
	}
	for(var d=1;d<=31;d++){
		membership.member_dd.add(new Option((d)+'일'));
		}
	}
</script>
<style type="text/css">
img{
width: 50px;
position: absolute;
left:70%;
top:25px;
}

input{
width:300px;
height: 35px;
text-align: "center";
}
.id_check{
width:70px;
height:28px;
font-size: 13px;
}
#box1{
width:80%;
height:1600px;
position :relative;
top:50px;
margin:auto;
background-color: white;
}
.text1{

font-size:20px;
margin-top:20px;
text-align: left;
padding-left: 36%;
}
.gender{
width: auto;
height: auto;
}
#account{
font-size: 13px;
color:red;
}
</style>
</head>
<body>
<form method="post" name="membership">
 <header class="masthead">
 <div class="container">
<div id="box1">
<h1>기업회원 회원가입 <img src="${pageContext.request.contextPath}/resources/assets/img/icon2.png"></h1>
<div class="text1">아이디 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
 <input type="button" value="중복 확인" class="id_check"></div>
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
</select>년
<select name="member_mm">
<option value="#">월 선택</option>
</select>월
<select name="member_dd">
<option value="#">일 선택</option>
</select>일
<div class="text1">성별</div>
<input type="radio" class = "gender" name="gender" name="남성">남성
<input type="radio" class = "gender" name="gender" name="여성" style="margin-left: 100px;">여성
<div class="text1">연락처&nbsp;&nbsp;<a id="account">-빼고 숫자만 입력해주세요</a></div>
<input type="text" name="member_phone">
<div class="text1">이메일</div>
<input type="email" name="member_email">
<div class="text1">주소</div>
<input type="text" name="member_address"><br><br>
<div class="text1">[ 매장정보 입력 ]</div><br><br>
<div class="text1">매장명</div>
<input type="text" name="shop_name">
<div class="text1">대표자 성명</div>
<input type="text" name="shop_ceo">
<div class="text1">매장 주소</div>
<input type="text" name="shop_address">
<div class="text1">매장 전화</div>
<input type="text" name="shop_phone">
<div class="text1">사업자 등록번호</div>
<input type="text" name="shop_code">

<br><br><input type="submit" value="가입하기">
</div>
</div>
</header>
</form>
</body>
</html>