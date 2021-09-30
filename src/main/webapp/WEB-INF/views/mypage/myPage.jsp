<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
     <%request.setCharacterEncoding("utf-8"); %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#box1{
width:80%;
height:2000px;
position :relative;
top:50px;
margin:auto;
background-color: white;
}
table{
width: 70%;
margin:auto;
}

img{
width: 120px;
}
td{
text-align: left;
}
.td{
border: 1px solid gray;
text-align: center;
}
p{
font-size:xx-large;
font-weight: bolder;
display: inline;
}
.button{
position: relative;
left: 500px;
}
.button1{
position: relative;
left: 480px;
}
.title{
border-bottom: 3px solid gray;
}
</style>
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
</head>
<body>
 <header class="masthead">
 <div class="container">
 <div id="box1">
 <br>
 <form name="frmMember" merthod="get" action="${contextPath }" enctype="application/x-www-form-urlencoded">
<table>
<tr>
<td class="title" colspan="2"><p>회원 정보</p><a href="${pageContext.request.contextPath}/mypage/pwdCheck.do"><input type="button" value="수정하기" class="button"></a></td>
<tr>
<tr><td><br></td></tr>
<tr>
<td width="7%">이름 : </td> <td width="21%">${member.member_name }</td>
</tr>
<tr>
<td >생년월일 : </td><td>${member.member_birth }</td>
</tr>
<tr>
<td>주소 : </td><td>${member.member_address }</td>
</tr>
<tr>
<td>핸드폰 번호 : </td><td>${member.member_phone }</td>
</tr>
<tr>
<td>이메일 : </td><td>${member.member_email }</td>
</tr>
</table>
</form>
<br><br>
<table>
<tr>
<td class="title" colspan="4"><p>반려동물</p>
<a href="${pageContext.request.contextPath}/mypage/petAdd.do"> 
<input type="button" value="등록하기" class="button"></a></td>
<tr>
<tr><td><br></td></tr>
<tr>
<td rowspan="3"><img src="${pageContext.request.contextPath}/resources/assets/img/dog2.png"></td>
 <td >이름 : </td> <td>홍뽀삐</td>
</tr>
<tr>
<td >나이 : </td><td>2살</td>
</tr>
<tr>
<td>종류 : </td><td>대형견/중형견/소형견</td>
<td><input type="button"value="수정"><input type="button"value="삭제"></td>
</tr>
<tr><td><br></td></tr>
<tr>
<td rowspan="3"><img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
 <td >이름 : </td> <td>홍삐삐</td>
</tr>
<tr>
<td >나이 : </td><td>2살</td>
</tr>
<tr>
<td>종류 : </td><td>대형견/중형견/소형견</td>
<td><input type="button"value="수정"><input type="button"value="삭제"></td>
</tr>
</table>
<br><br>
<table>
<tr>
<td class="title" colspan="6"><h2>최근 예약 내역</h2></td>
</tr>
<tr><td><br></td></tr>
<tr>
<td class="td" width="7%">번호</td>
<td class="td" width="21%">매장명</td>
<td class="td" width="18%">예약시간</td>
<td class="td" width="21%">예약 날짜</td>
<td class="td" width="21%">반려견 이름</td>
<td class="td" width="18%">취소하기</td>
</tr>
<c:forEach var="i" begin="1" end="3">
<tr>
<td class="td" width="7%">1</td>
<td class="td" width="21%">A 매장</td>
<td class="td" width="18%">17시 30분</td>
<td class="td" width="21%">09.23.21</td>
<td class="td" width="21%">홍뽀삐</td>
<td class="td" width="18%"><input type="button" value="취소하기"></td>
</tr>
</c:forEach>
</table>
<br><br>
<table>
<tr>
<td class="title" colspan="7"><h2>관심매장</h2></td>
</tr>
<tr><td><br></td></tr>
<tr>
<td class="td" width="30%">사진</td>
<td class="td" width="21%">매장명</td>
<td class="td" width="18%">매장 위치</td>
<td class="td" width="18%">매장 운영 시간</td>
<td class="td" width="7%">별점</td>
<td class="td" width="10%">관심</td>
<td class="td" width="21%">예약하기</td>
</tr>
<c:forEach var="i" begin="1" end="3">
<tr>
<td class="td" width="30%"><img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
<td class="td" width="21%">A 매장</td>
<td class="td" width="18%">경기도 남양주시</td>
<td class="td" width="18%">11:00~18:00</td>
<td class="td" width="7%">5</td>
<td class="td" width="10%" style="font-size:30px;">♥</td>
<td class="td" width="21%"><input type="button" value="예약하기"></td>
</tr>
</c:forEach>
</table>
<br><br>
<table>
<tr>
<td class="title" colspan="5"><p>내가 쓴 리뷰</p>
<a href = "${pageContext.request.contextPath}/mypage/review.do">
<input type="button" value="더보기" class="button1"></a></td>
<tr>
<tr><td><br></td></tr>
<tr>
<td class="td" width="7%">번호</td>
<td class="td" width="21%">매장명</td>
<td class="td" width="30%">리뷰 내용</td>
<td class="td" width="18%">반려견 이름</td>
<td class="td" width="21%">작성날짜</td>

</tr>
<c:forEach var="i" begin="1" end="3">
<tr>
<td class="td">1</td>
<td class="td">A매장</td>
<td class="td">넘 좋았어요</td>
<td class="td">홍뽀삐</td>
<td class="td">09.23.21</td>
</tr>
</c:forEach>
</table>
<br><br>
<table>
<tr>
<td class="title" colspan="5"><p>방문 내역</p>
<a href = "${pageContext.request.contextPath}/mypage/visit.do">
<input type="button" value="더보기" class="button"></a></td>
<tr>
<tr><td><br></td></tr>
<tr>
<td class="td" width="7%">번호</td>
<td class="td" width="21%">매장명</td>
<td class="td" width="21%">반려견 이름</td>
<td class="td" width="21%">방문 날짜</td>
<td class="td" width="25%">리뷰쓰기</td>

</tr>
<c:forEach var="i" begin="1" end="3">
<tr>
<td class="td">1</td>
<td class="td">A매장</td>
<td class="td">홍뽀삐</td>
<td class="td">09.23.21</td>
<td class="td">
<a href = "${pageContext.request.contextPath}/mypage/reviewWrite.do">
<input type="button" value="리뷰쓰기"></a></td>
</tr>
</c:forEach>
</table>
 </div>
 </div>
 </header>
</body>
</html>