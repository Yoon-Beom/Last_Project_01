<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰작성</title>
<style type="text/css">

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
.file{
  width: 100%;
  padding: 0;
  border-width: 0;
}
#box1{
width:80%;
height:800px;
position :relative;
top:50px;
margin:auto;
background-color: white;
}
.button{
width: 120px;
font-size: 20px;
}
#myform fieldset{
    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    border: 0; /* 필드셋 테두리 제거 */
}
#myform input[type=radio]{
    display: none; /* 라디오박스 감춤 */
}
#myform label{
    font-size: 2em; /* 이모지 크기 */
    color: transparent; /* 기존 이모지 컬러 제거 */
    text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
}
color: transparent; /* 기존 이모지 컬러 제거 */
text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
#myform label:hover{
    text-shadow: 0 0 0 #FCCA03; /* 마우스 호버 */
}
#myform label:hover ~ label{
    text-shadow: 0 0 0 #FCCA03; /* 마우스 호버 뒤에오는 이모지들 */
}
#myform fieldset{
    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
    direction: rtl; /* 이모지 순서 반전 */
    border: 0; /* 필드셋 테두리 제거 */
}
#myform fieldset legend{
    text-align: left;
}
#myform input[type=radio]:checked ~ label{
    text-shadow: 0 0 0 #FCCA03; /* 마우스 클릭 체크 */
}
</style>
</head>
<body>
<header class="masthead">
 <div class="container">
 <div id="box1">
 <br>
 <h2>매장 방문 리뷰</h2>
 <br>
 
  <table id="boardtable">
  <tr id="boardmenu">
  <td width="20%" class="td">제목</td>
  <td id="board_title" width="80%" class="td" style="background-color: white;">
  <input type="text" name="review_title" style="resize:none;width: 100%;height:100%;padding: 0;border-width: 0;font-size: 20px;" ></input></td>
  </tr>
    <tr> 
  <td colspan="2" width="85%" class="td">내용</td>
  </tr>
   <tr> 
 <td colspan="2" id="board_content" class="td"><textarea name="review_content" rows="13" cols="100%" style="resize:none;" ></textarea></td>
  </tr>
  <tr>
  <td width="10%" class="td">이미지 첨부</td>
  <td colspan="2" width="85%" class="td"><input type="file" class="file" name="review_image">
 
  </table>
  <br>
<form name="myform" id="myform" method="post" action="./save">
    <fieldset>
    
        <input type="radio" name="review_starScore" value="5" id="rate1"><label for="rate1">⭐</label>
        <input type="radio" name="review_starScore" value="4" id="rate2"><label for="rate2">⭐</label>
        <input type="radio" name="review_starScore" value="3" id="rate3"><label for="rate3">⭐</label>
        <input type="radio" name="review_starScore" value="2" id="rate4"><label for="rate4">⭐</label>
        <input type="radio" name="review_starScore" value="1" id="rate5"><label for="rate5">⭐</label>
        별점 
    </fieldset>
</form>
<br><br>
  <input type="submit" value="등록" class="button">&nbsp;&nbsp;&nbsp;
  <a href="${pageContext.request.contextPath}/mypage/myPage.do">
  <input type="button" value="취소" class="button"></a>
 </div>
</div>
 </header>
</body>
</html>