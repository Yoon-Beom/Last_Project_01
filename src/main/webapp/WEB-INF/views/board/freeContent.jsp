<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%request.setCharacterEncoding("utf-8"); %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글내용</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
<script type="text/javascript">

window.onload=function(){
	$('#tr_btn_modify').hide();
	$('#i_imageFileName').hide();
};

function fn_enable(obj){
	
	if(${not empty board.board_image && board.board_image!='null' }){
		document.getElementById("i_imageFileName").disabled=false;
		document.getElementById("tr_file_upload").style.display="block";
	};
	 document.getElementById("tr_btn_modify").style.display="block";
	 document.getElementById("board_content").disabled=false;
	 document.getElementById("board_title").disabled=false;
	 document.getElementById("delete").style.display="none";
	 document.getElementById("mod").style.display="none";
	 $('.comment').hide();
	 $('#i_imageFileName').show();
	 $('#tr_btn_modify').show();
};

function fn_remove_article(url,board_NO){
	 var form = document.createElement("form");
	 form.setAttribute("method", "post");
	 form.setAttribute("action", url);
    var articleNOInput = document.createElement("input");
    articleNOInput.setAttribute("type","hidden");
    articleNOInput.setAttribute("name","board_NO");
    articleNOInput.setAttribute("value", board_NO);
	 
    form.appendChild(articleNOInput);
    document.body.appendChild(form);
    form.submit();

};
function fn_modify_article(obj){
	 obj.action="${contextPath}/board/modArticle.do?board_NO="+${board.board_NO};
	 obj.submit();
};
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
};
 function addComment(obj){
	 if(${member.member_NO==null}){
		 location.href="${contextPath}/login.do";
	 }else{
	document.getElementById("comment_name").disabled=false;
	 obj.action="${contextPath}/board/addcomment.do?board_NO="+${board.board_NO};
	 obj.submit();
	 }
 };
 function remove_comment(obj){
		document.getElementById("comment_name").disabled=false;
		 obj.action="${contextPath}/board/removecomment.do?board_NO="+${board.board_NO};
		 obj.submit();
	 };

</script>
<style type="text/css">
#parent{
display: table;
overflow: auto;
margin-top:20px;
}
#menu{
left:5%;
width: 18%;
background-color: #e6e6e6;
font-size: 30px;
margin-bottom: 5%;
padding-bottom: 5%;
display: table-cell;
}
.wrting{
text-align:right;
position: absolute;
right:200px;
top:150px;
}
#boardmain{
width:88%;
text-align:"center";
background-color: white;
margin-bottom: 5%;
padding-bottom: 5%;
display: table-cell;
}

.comment{
width: 90%;
margin:auto;
border-bottom: 1px solid gray;
margin:auto;
}
.td{
border: 1px solid gray;
border-top::none;
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
td{
height:35px;
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
#board_content{
height: 350px;
}
textarea {
	border:0;
	
}
textarea:focus{
outline: none;
}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
</head>
<body>
 <header class="masthead">
 <div class="container">
<br>
<div id="parent">
 <div id="menu">
 <br>
 <a href ="${pageContext.request.contextPath}/board/freeBoard.do?board_code=1" class="mn">자유게시판</a><br>
 <a href ="${pageContext.request.contextPath}/board/qnaBoard.do?board_code=2" class="mn">QnA</a><br>
 <a href ="${pageContext.request.contextPath}/board/noticeBoard.do?board_code=3" class="mn">공지사항</a><br>
 </div>

 <div id="boardmain">
 <div id="boardhead">
 조회수: 99
   </div>
   <div class="wrting" id="tr_btn">
   <c:if test="${board.board_name == member.member_name }">
    <a href = "${pageContext.request.contextPath}/board/removeArticle.do?board_NO=${board.board_NO }">
  <input type="button" value="삭제하기" id="delete" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${board.board_NO})">
  </a>
   <input type="button" value="수정하기" id="mod" onClick="javascript:fn_enable(this.form)" >
  
   <input type="submit" value="수정반영하기" id="tr_btn_modify"  onClick="javascript:fn_modify_article(frmBoard)" >
   </c:if>
   <c:if test="${board.board_name != member.member_name }">
   <a href ="${pageContext.request.contextPath}/board/freeBoard.do?board_code=1">
  <input type="button" value="뒤로가기" >
  </a>
   </c:if>
   </div>
  <br>
  <form name="frmBoard" method="post" enctype="multipart/form-data">
  <table id="boardtable">
  <tr id="boardmenu">
  <td width="10%" class="td">제목</td>
  <td  width="60%" class="td">
  <input type="hidden" name="board_NO" value="${board.board_NO }">
  <input type="text" name="board_title" id="board_title" style="resize:none;width: 100%;height:100%;padding: 0;border-width: 0;font-size: 20px;overflow: auto;" value="${board.board_title }" disabled />
  </td>
  </tr>
  
    <tr> 
  <td colspan="2" width="85%" class="td">내용</td>
  </tr>
  
  <c:choose>
  <c:when test="${not empty board.board_image && board.board_image!='null' }">
   <tr id="tr_file_upload"> 
   <td>
   <input  type= "hidden"  name="originalFileName" value="${board.board_image }" id="board_image"/>
		    <img src="${contextPath}/download.do?board_NO=${board.board_NO}&board_image=${board.board_image}" id="preview" width="300" height="200px" />
		    <input  type="file"  name="board_image" id="i_imageFileName"   disabled   onchange="readURL(this);"   />
		    </td>
		    
  <td >
  <textarea rows="15" cols="40%" style="resize:none;" name="board_content" id="board_content" disabled>${board.board_content }</textarea>
  <br></td>
  </tr>
  </c:when>
  <c:otherwise>
  <tr> 
  <td colspan="2" class="td">
  <textarea rows="15" cols="100%" style="resize:none;" name="board_content" id="board_content" disabled>${board.board_content }</textarea>
  <br></td>
  </tr>
  </c:otherwise>
  </c:choose>
  </table>
   </form>
<div style="text-align:'center';">
<a style="font-size:30px;">♥</a>&nbsp;&nbsp;&nbsp; 
20
</div>
<form method="post" action="${contextPath}/board/addcomment.do" name="comment" enctype="multipart/form-data">
 <table class="comment">
 <tr>
 <td width="10%" id="member_name" class="td" >
 <input type="text" style="resize:none;width: 100%;height:100%;padding: 0;border-width: 0;text-align:center;overflow: auto;" id="comment_name" name="member_name" value="${member.member_name }" disabled/>
 <td width="30%" class="td"><textarea rows="2" cols="80%" placeholder="댓글을 입력하세요." style="resize:none;overflow: auto;" name="comment_content"></textarea>
 
 <td class="td"><input type="button" value="저장" onclick="addComment(this.form)"></td>
 </tr>
 <tr>
 <td colspan="3" class="td"> 댓글</td>
 </tr>
 <c:forEach var="comment" items="${commentList}">
 <tr>
 <td width="20%" class="td">${comment.member_name }</td>
 <c:choose>
 <c:when test="${member.member_NO == comment.member_NO }">
  <td width="50%" class="td">${comment.comment_content }</td>
 <td class="td" width="10%"><input type="hidden" name="comment_NO" value="${comment.comment_NO }">
 <input type="button" value="삭제" onclick="remove_comment(this.form)"></td>
 </c:when>
 <c:otherwise>
  <td colspan="2" width="50%" class="td">${comment.comment_content }</td>
 </c:otherwise>
 </c:choose>
 </tr>
 </c:forEach>
 </table>
 </form>
 </div>
 </div>
 </div>
 </header>
</body>
</html>