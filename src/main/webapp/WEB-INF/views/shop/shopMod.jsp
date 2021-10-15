<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("utf-8");
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css" />
<style type="text/css">
#box1 {
   width: 80%;
   height: 100%;
   padding: 0 0 40px 0;
   position: relative;
   top: 50px;
   margin: auto;
   background-color: white;
}

table {
   width: 70%;
   margin: auto;
}

#petimage {
   width: 120px;
}

img {
   width: 120px;
}

td {
   text-align: left;
}

.td {
   border: 1px solid gray;
   text-align: center;
}

p {
   font-size: xx-large;
   font-weight: bolder;
   display: inline;
}
.button {
   width: 50px;
}

.title {
   border-bottom: 3px solid gray;
}
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 수정 페이지</title>
</head>
<body>
   <header class="masthead">
      <form method="post" name="shopInfo" action="${contextPath}/shop/shopUpdate.do">
         <div class="container">
            <div id="box1">
            <h1>매장 정보 수정</h1>
   				<table>               
                  <tr>
                     <td class="td" width="20%" colspan="4">매장 대표 사진</td>
                   </tr>
                   <tr>
                     <td class="td" width="20%" colspan="4"><img   src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
                   </tr>
                   <tr>      
                     <td class="td" width="7%">매장명</td>
                     <td class="td" width="10%"><input type="text" name ="shop_name" value="${shop.SHOP_NAME}" ></td>
                     <td class="td" width="7%" >사업자등록번호</td>
                     <td class="td" width="10%" ><input type="text" name ="shop_code" value="${shop.SHOP_CODE }" ></td>
                  </tr>   
                  <tr>      
                     <td class="td" width="7%">오픈 시간</td>
                     <td class="td" width="10%"> ${shop.SHOP_OPEN_TIME }</td>
                     <td class="td" width="7%" >마감시간</td>
                     <td class="td" width="10%" >${shop.SHOP_CLOSE_TIME }</td>
                  </tr>   
                  <tr>      
                     <td class="td" width="7%">주소</td>
                     <td class="td" width="10%">${shop.SHOP_ADDRESS }</td>
                     <td class="td" width="7%">☎</td>
                     <td class="td" width="10%"> ${shop.SHOP_PHONE }</td>
                  </tr>
                  <tr>
                     <td class="td" width="20%" colspan="4">매장 소개</td>
                  </tr>
                  <tr>
                     <td class="td" width="20%" colspan="4">매장 소개내용<br><br></td>
                  </tr>
                  <tr>   
                     <td class="td" width="7%" colspan="4">매장 서브 사진</td>
                  </tr>
                  <tr>   
                     <td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
                     <td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
                     <td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
                     <td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
                  </tr>
            
            </table>

      <br> <br> <br> <input type="button" value="수정" onclick="submitBtn()" class="button">&nbsp;&nbsp;&nbsp; 
                  <input type="hidden" value="${member.member_NO }">
                  <a href="${pageContext.request.contextPath}/shop/shopMyPage.do">
                  <input type="button" value="취소" class="button"></a>
      </form>
   </div>
         </div>
   </header>
</body>
</html>