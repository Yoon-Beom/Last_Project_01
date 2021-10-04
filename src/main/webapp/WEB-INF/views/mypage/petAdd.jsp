<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>   
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
<script type="text/javascript">
window.onload=function(){
	$('#pet_preview').hide();
}
function readURL(input) {
    if (input.files && input.files[0]) {
    	$('#pet_preview').show();
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#pet_preview').attr('src', e.target.result);
	        $('textarea').css("rows","15","cols","40%");
        }
       reader.readAsDataURL(input.files[0]);
    }
};
</script>
</head>
<body>
<form method="post" name="petAdd" action="${contextPath}/mypage/petAdd.do" enctype="multipart/form-data">
 <header class="masthead">
 <div class="container">
<div id="box1">
<h1>반려동물 등록</h1>
<div id="pet_img">
<img  id="pet_preview" src="#" width=300 height=300/><br>
<input type="file" id="file" name="pet_image" onchange="readURL(this);">
</div>
<div id="content1">
<input type="hidden" name="member_id" value="${member.member_id }">
이름 &nbsp;<input type="text" name="pet_name" class="text"><br><br>
나이 &nbsp;<input type="text" name="pet_age" class="text">살<br><br>
</div>
<div id="kind">종류 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="pet_scale" value="소형" >소형&nbsp;&nbsp;&nbsp;
<input type="radio" name="pet_scale" value="중형">중형&nbsp;&nbsp;&nbsp;
<input type="radio" name="pet_scale" value="대형">대형
</div>
<br><br><br><br><input type="submit" value="등록" class="button">&nbsp;&nbsp;&nbsp;
<a href = "${pageContext.request.contextPath}/mypage/myPage.do"><input type="button" value="취소" class="button"></a>
</div>
</div>
</header>
</form>
</body>
</html>
