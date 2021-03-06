<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%request.setCharacterEncoding("utf-8"); %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

	function fn_articleForm(isLogOn,qnaBoardWriting,login){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=qnaBoardWriting;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=login+'?action=/board/freeBoardWriting.do';
	  }
	};
	function show_board_title(boardMember,memberMember,board_NO,memberName){
		if(memberName=='admin'){
			location.href='/test/board/qnaContent.do?board_NO='+board_NO;	
		}else if(boardMember!= memberMember){
			 alert("해당 게시글 권한이 없습니다.\n"+boardMember+"\n"+memberMember);		
		}else{
			location.href='/test/board/qnaContent.do?board_NO='+board_NO;
		}	
	};
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
#page li {
list-style: none;
float: center;
padding: 6px;
display: inline;
}

</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
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
<form name="frmsearch" method="post" action="${contextPath}/board/searchQnA.do?board_code=2">
 <input type="text" style="width:280px; height:30px;" name="search" placeholder="작성자를 입력해 주세요">
 <input type="submit" value="검색">&nbsp;&nbsp;&nbsp;
  <select name="optionContent"><option value="board_name">작성자</option>
  <option value="board_title">제목</option></select>
   </form>
   </div>
   <a href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/qnaBoardWriting.do', 
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
 <c:choose>
 <c:when test="${empty list}">
 <tr>
 <td colspan="5"><h3>작성된 글이 없습니다.</h3></td>
 </tr>
 </c:when>
 <c:otherwise>
 <c:forEach var="board" items="${list}">
   
    <tr>
    
  <td width="7%" id="board_NO" class="td">${board.rnum}</td> 

   <td width="30%" id="board_title" class="td" onclick="show_board_title('${board.member_NO}','${member.member_NO}','${ board.board_NO}','${member.member_name }')">
  <a href="#" id="title" style="text-decoration:none;" >비밀글 입니다.</a></td>
  <td width="14%" id="board_name" class="td">${board.board_name }</td>
  <td width="20%" id="board_Date" class="td">${board.board_Date }</td>
  <td width="14%" id="board_score" class="td">${board.board_score}</td>

  </tr>
  </c:forEach>
 </c:otherwise>
 </c:choose>
   
  
</table>
</form>
  <div id="page">
  <ul>
  <c:choose>
  <c:when test="${search == 'AllList'}">
  <c:if test="${pageMaker.prev}">
    	<li><a href="qnaBoard.do${pageMaker.makeQuery(pageMaker.startPage - 1)}&board_code=2" style="text-decoration:none;">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<c:choose>
			<c:when test="${page==idx }">
				<li><a href="qnaBoard.do${pageMaker.makeQuery(idx)}&board_code=2" style="color: #F8863E;">${idx}</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="qnaBoard.do${pageMaker.makeQuery(idx)}&board_code=2" style="text-decoration:none;">${idx}</a></li>
			</c:otherwise>
		</c:choose>
    </c:forEach>
    
    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="qnaBoard.do${pageMaker.makeQuery(pageMaker.endPage + 1)}&board_code=2" style="text-decoration:none;">다음</a></li>
    </c:if>
  </c:when>
  <c:otherwise>
  <c:if test="${pageMaker.prev}">
    	<li><a href="searchQnA.do${pageMaker.makeSearch(pageMaker.startPage - 1)}&board_code=2" style="text-decoration:none;">이전</a></li>
    </c:if>

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	  	<c:choose>
			<c:when test="${page==idx }">
				<li><a href="searchQnA.do${pageMaker.makeSearch(idx)}&board_code=2" style="color: #F8863E;">${idx}</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="searchQnA.do${pageMaker.makeSearch(idx)}&board_code=2" style="text-decoration:none;">${idx}</a></li>
			</c:otherwise>
		</c:choose>
    </c:forEach>
    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li><a href="searchQnA.do${pageMaker.makeSearch(pageMaker.endPage + 1)}&board_code=2" style="text-decoration:none;">다음</a></li>
    </c:if>
  </c:otherwise>
  </c:choose>    
  </ul>
  </div>
 </div>
 </div>
 </header>
</body>
</html>