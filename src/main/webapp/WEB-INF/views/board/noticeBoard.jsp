<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%request.setCharacterEncoding("utf-8"); %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">

#menu{
position:absolute;
top:140px;
left:5%;
width: 15%;
height: 600px;
background-color: #e6e6e6;
font-size: 30px;

}
#wrting{
text-align:right;
position: absolute;
right:60px;
top:50px;
}
#boardmain{
position:relative;
top:52px;
left:14.8%;
width:88%;
height:600px;
text-align:"center";
background-color: white;

}
.mn{
text-decoration: none;
height: 80px;

}
#boardtable{
border: 1px solid gray;
width: 90%;
margin:auto;
}
#boardhead{
padding-top:30px;
text-align: left;
padding-left:6%;
width: 70%;
height: 60px;
}
.td{
height:35px;
border-bottom: 1px solid gray;
}
#page{
font-size: 25px;
padding:10px;
padding-top:20px;
}
#boardmenu{
height: 50px;
background-color: #e6e6e6;
}
#title{
color: inherit;
}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
</head>
<body>
 <header class="masthead">
 <div class="container">
 <div id="menu">
 <br>
 <a href ="${pageContext.request.contextPath}/board/freeBoard.do?board_code=1" class="mn">자유게시판</a><br>
 <a href ="${pageContext.request.contextPath}/board/qnaBoard.do?board_code=2" class="mn">QnA</a><br>
 <a href ="${pageContext.request.contextPath}/board/noticeBoard.do?board_code=3" class="mn">공지사항</a><br>
 </div>
  <br>
 <div id="boardmain">
 <div id="boardhead">
 <input type="text" style="width:280px; height:30px;" placeholder="작성자를 입력해 주세요">
 <input type="button" value="검색">&nbsp;&nbsp;&nbsp;
  <select><option value="#">나열순</option></select>
   </div>
 <br>
  <table id="boardtable">
  <tr id="boardmenu">
  <td width="7%" class="td">번호</td>
  <td width="30%" class="td">제목</td>
  <td width="14%" class="td">작성자</td>
  <td width="20%" class="td">작성일</td>
  <td width="14%" class="td">조회수</td>
  <td width="14%" class="td">좋아요</td>
  </tr>
 
  <c:forEach var="i" begin="1" end="9">
   
    <tr>
    
  <td width="7%" id="board_NO" class="td">1</td>
  <td width="30%" id="board_title" class="td"> 
  <a href="${pageContext.request.contextPath}/board/noticeContent.do" id="title">가입 이벤트 안내</a></td>
  <td width="14%" id="board_name" class="td">홍길동</td>
  <td width="20%" id="board_Date" class="td">2021-09-19</td>
  <td width="14%" id="board_score" class="td">2</td>
  <td width="14%" id="board_score" class="td">14</td>

  </tr>
  </c:forEach>
  
  </table>
  <div id="page">
  <p>< 1 2 3 4 5 6 7 8 9 ></p>
  </div>
 </div>
 </div>
 </header>
</body>
</html>