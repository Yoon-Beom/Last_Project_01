<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
<title>반려동물 등록</title>
<style type="text/css">

#box1{
width:80%;
height:600px;
position :relative;
top:50px;
margin:auto;
background-color: white;
font-size: 25px;
}
img{
width:260px;
}

#pet_img{
position:absolute;
top:30%;
left: 10%;
}
#pet_img input{
margin-left:5%;
}
#content1{
position: relative;
top:10%;
left: 20%;
font-size: 25px;
}
#kind{
position: relative;
top:10%;
left: 18%;
font-size: 25px;
}
#file{
font-size:15px;
}
.button{
width: 100px;
}
</style>
</head>
<body>
<form method="post" name="petAdd">
 <header class="masthead">
 <div class="container">
<div id="box1">
<h1>반려동물 등록</h1>
<div id="pet_img">
<img src="${pageContext.request.contextPath}/resources/assets/img/dog2.png"><br>
<input type="file" id="file">
</div>
<div id="content1">
이름 &nbsp;<input type="text" name="pet_name" class="text"><br><br>
나이 &nbsp;<input type="text" name="pet_age" class="text"><br><br>
</div>
<div id="kind">종류 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="pet_kind" value="소형" >소형&nbsp;&nbsp;&nbsp;
<input type="radio" name="pet_kind" value="중형">중형&nbsp;&nbsp;&nbsp;
<input type="radio" name="pet_kind" value="대형">대형
</div>
<br><br><br><br><input type="submit" value="등록" class="button">&nbsp;&nbsp;&nbsp;
<a href = "${pageContext.request.contextPath}/mypage/myPage.do"><input type="button" value="취소" class="button"></a>
</div>
</div>
</header>
</form>
</body>
</html>