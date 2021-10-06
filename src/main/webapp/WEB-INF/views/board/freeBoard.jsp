<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%request.setCharacterEncoding("utf-8"); %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />

<% request.setCharacterEncoding("UTF-8"); 
	%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script>
	function fn_articleForm(isLogOn,freeBoardWriting,login){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=freeBoardWriting;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=login+'?action=/board/freeBoardWriting.do';
	  }
	}
</script>

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
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css"/>
</head>
<body>
 <header class="masthead">
 <div class="container">
 <div id="menu">
 <br>
 <a href ="${contextPath}/board/freeBoard.do?board_code=1" class="mn">자유게시판</a><br>
 <a href ="${contextPath}/board/qnaBoard.do?board_code=2" class="mn">QnA</a><br>
 <a href ="${contextPath}/board/noticeBoard.do?board_code=3" class="mn">공지사항</a><br>
 </div>
  <br>
 <div id="boardmain">
 <div id="boardhead">
<form name="frmsearch" method="post" action="${contextPath}/board/search.do?board_code=1">
 <input type="text" style="width:280px; height:30px;" name="search" placeholder="작성자를 입력해 주세요">
 <input type="submit" value="검색">&nbsp;&nbsp;&nbsp;
  <select name="optionContent"><option value="board_name">작성자</option>
  <option value="board_title">제목</option></select>
   </form>
   </div>
   
  <a href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/freeBoardWriting.do', 
                                                    '${contextPath}/login.do')">
  <input type="button" value="글쓰기" id="wrting">
  
  </a> 
  <br>
  <form>
  <table id="boardtable">
  <tr id="boardmenu">
  <td width="7%" class="td">번호</td>
  <td width="30%" class="td">제목</td>
  <td width="14%" class="td">작성자</td>
  <td width="20%" class="td">작성일</td>
  <td width="14%" class="td">조회수</td>
 
  </tr>
  
  <c:forEach var="board" items="${articlesList}">
   
    <tr>
    
  <td width="7%" name="board_NO" class="td">${board.board_NO}</td>
  <td width="30%" name="board_title" class="td">
  <a href="${contextPath}/board/freeContent.do?board_NO=${board.board_NO}" id="title">${board.board_title}</a></td>
  <td width="14%" name="board_name" class="td">${board.board_name}</td>
  <td width="20%" name="board_Date" class="td">${board.board_Date}</td>
  <td width="14%" name="board_score" class="td">${board.board_score}</td>
 

  </tr>
  </c:forEach>
  
  </table>
  </form>
  <div id="page">
  <p>< 1 2 3 4 5 6 7 8 9 ></p>
  </div>
 </div>
 </div>
 </header>
</body>
</html>