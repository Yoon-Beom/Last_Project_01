<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
      <%request.setCharacterEncoding("utf-8"); %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.title{
border-bottom: 3px solid gray;
}
.td{
border:1px solid gray;
}
table{
width: 80%;
margin:auto;
margin-top:20px;
}
</style>
<title>내가 쓴 리뷰</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
</head>
<body>
 <header class="masthead">
 <div class="container">
 <div id="box">
 <br>
<table>
<tr>
<td class="title" colspan="5"><h2>내가 쓴 리뷰</h2></td>
</tr>
<tr><td><br></td></tr>
<tr>
<td class="td" width="7%">번호</td>
<td class="td" width="21%">매장명</td>
<td class="td" width="30%">리뷰내용</td>
<td class="td" width="18%">반려견 이름</td>
<td class="td" width="21%">작성 날짜</td>

</tr>
<c:forEach var="i" begin="1" end="10">
<tr>
<td class="td">1</td>
<td class="td" >A 매장</td>
<td class="td" >넘 좋았어요</td>
<td class="td">홍뽀삐</td>
<td class="td">09.23.21</td>
</tr>
</c:forEach>
<tr><td><br></td></tr>
<tr>
<td colspan="5" style="font-size: 20px;">
< 1 2 3 4 5 >
</td>
</tr>
</table>
 </div>
 </div>
 </header>
</body>
</html>