<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%
   request.setCharacterEncoding("utf-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
   text-align: center;
}

.button {
   position: relative;
   left: 500px;
}

.title {
   border-bottom: 3px solid gray;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>매장페이지</title>
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/resources/css/styles.css" />
</head>
<body>
   <header class="masthead">
      <div class="container">
         <div id="box1">
            <br>
            <form name="frmMember" method="get" action="${contextPath }">            
            <table>
               <tr>
                  <td class="title" colspan="4"><p> ${shop.shop_name}  ♡</p>
                  </td>
               </tr>
               <tr>
                  <td><br></td>
               </tr>
               
               <tr>
                  <td class="td" width="20%">관심수</td>
                  <td class="td" width="25%"> ${shop.shopDetailVO.shop_heartScore}</td>
                  <td class="td" width="20%">예약수</td>
                  <td class="td" width="25%"> ${shop.shopDetailVO.shop_reserveScore}</td>
               </tr>
               <tr>
                  <td class="td" width="20%">평점</td>
                  <td class="td" width="25%"> ${shop.shopDetailVO.shop_starScore}</td>
                  <td class="td" width="20%">리뷰수</td>
                  <td class="td" width="25%">500</td>
               </tr>
               </table>
               <br>
               
               <table>
                  <tr>
                     <td class="td" width="20%" colspan="4">매장 대표 사진</td>
                   </tr>
                   <tr>
                     <td class="td" width="20%" colspan="4"><img   src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
                   </tr>
                   <tr>      
                     <td class="td" width="7%">오픈 시간</td>
                     <td class="td" width="10%">${shop.shopDetailVO.shop_open_time}</td>
                     <td class="td" width="7%" >마감시간</td>
                     <td class="td" width="10%" >${shop.shopDetailVO.shop_close_time}</td>
                  </tr>   
                  <tr>      
                     <td class="td" width="7%">주소</td>
                     <td class="td" width="10%">${shop.shop_address}</td>
                     <td class="td" width="7%">☎</td>
                     <td class="td" width="10%">${shop.shopDetailVO.shop_phone}</td>
                  </tr>
            </table>
                     <br>
                           <input type="button" value="예약하기">
                           <a href="${pageContext.request.contextPath}/shop/shopMap.do"><input type="button" value="뒤로가기"></a>
                      <br><br>
            <table>   
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

               <br>
               <table>   
                  <tr>   
                     <td class="td" width="7%" colspan="4">리뷰</td>
                  </tr>
                  <tr>   
                     <td class="td" width="10%">홍길동</td>
                     <td class="td" width="10%">★★★★★</td>
                  </tr>
                  <tr>
                     <td class="td" width="10%"> <img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
                     <td class="td" width="10%">리뷰내용</td>
                  </tr>
                  <tr>   
                     <td class="td" width="10%">홍길동</td>
                     <td class="td" width="10%">★★★★★</td>
                  </tr>
                  <tr>
                     <td class="td" width="10%" colspan="2"><br><br>리뷰내용<br><br></td>
                  </tr>
            </table>
                  <br>
                     <input type="button" value="예약하기">
                         <a href="${pageContext.request.contextPath}/shop/shopMap.do"><input type="button" value="뒤로가기"></a>
                      <br><br>
            </form>
         </div>
      </div>
   </header>
</body>
</html>