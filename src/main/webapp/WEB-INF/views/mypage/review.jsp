<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function review_delete(reviewNO){
	   
    if(!confirm("리뷰를 삭제하시겠습니까?")){
       alert("취소되었습니다.");
    } else {   
       location.href="${pageContext.request.contextPath}/mypage/deleteReview.do?review_NO="+reviewNO;
       
    }
 }; 

</script>
<style type="text/css">
.title {
	border-bottom: 3px solid gray;
}

.td {
	border: 1px solid gray;
}

table {
	width: 80%;
	margin: auto;
	margin-top: 20px;
}

#page {
	font-size: 25px;
	padding: 10px;
	padding-top: 20px;
}

#page li {
	list-style: none;
	float: center;
	padding: 6px;
	display: inline;
}
#button{
position: absolute;
top:60px;
right: 110px;
}
</style>
<title>내가 쓴 리뷰</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css" />
</head>
<body>
	<header class="masthead">
		<div class="container">
			<div id="box">
				<br>
				<table>
					<tr>
						<td class="title" colspan="6">
							<h2>내가 쓴 리뷰</h2><a href="${contextPath }/mypage/myPage.do"><input type="button" value="뒤로가기" id="button"></a>
						</td>
					</tr>
					<tr>
						<td>
							<br>
						</td>
					</tr>
					<tr>
						<td class="td" width="7%">번호</td>
						<td class="td" width="21%">매장명</td>
						<td class="td" width="30%">리뷰내용</td>
						<td class="td" width="18%">반려견 이름</td>
						<td class="td" width="21%">작성 날짜</td>
						<td class="td" width="7%">삭제</td>
					</tr>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="5">
									작성된 글이 없습니다.
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="review" items="${list}">
								<tr>
									<td class="td">${review.rnum }</td>
									<td class="td">${review.shop_name }</td>
									<td class="td" style="word-break:break-all;">${review.review_content }</td>
									<td class="td">${review.pet_name }</td>
									<td class="td">${review.review_Date }</td>
									<td class="td"><input type="button" value="삭제" onclick="review_delete(${review.review_NO })"></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<tr>
						<td>
							<br>
						</td>
					</tr>
				</table>
				<div id="page">
					<c:if test="${pageMaker.prev}">
						<li><a href="review.do${pageMaker.makeQuery(pageMaker.startPage - 1)}" style="text-decoration: none;">이전</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						<c:choose>
							<c:when test="${page==idx }">
								<li><a href="review.do${pageMaker.makeQuery(idx)}" style="color: #F8863E;">${idx}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="review.do${pageMaker.makeQuery(idx)}" style="text-decoration: none;">${idx}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li><a href="review.do${pageMaker.makeQuery(pageMaker.endPage + 1)}" style="text-decoration: none;">다음</a></li>
					</c:if>

				</div>

			</div>
		</div>
	</header>
</body>
</html>