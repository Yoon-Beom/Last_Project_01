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
#box1{
width:80%;
height:600px;
position :relative;
top:50px;
margin:auto;
background-color: white;
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
</style>
<title>방문 내역</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
</head>
<body>
 <header class="masthead">
 <div class="container">
 <div id="box1">
 <br>
<table>
<tr>
<td class="title" colspan="5"><h2>방문 내역</h2></td>
</tr>
<tr><td><br></td></tr>
<tr>
<td class="td" width="7%">번호</td>
<td class="td" width="21%">매장명</td>
<td class="td" width="18%">반려견 이름</td>
<td class="td" width="21%">방문날짜</td>
<td class="td" width="21%">리뷰쓰기</td>

</tr>
<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="5">작성된 글이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="reserve" items="${list}">
								<tr>
									<td class="td">${reserve.rnum }</td>
									<td class="td" style="word-break: break-all;">${reserve.shop_name }</td>
									<td class="td">${reserve.pet_name }</td>
									<td class="td">${reserve.reserve_Date }</td>
									<td class="td"><a href="${pageContext.request.contextPath}/mypage/reviewWrite.do?shop_NO=${reserve.shop_NO}">
									<input type="button" value="리뷰쓰기">
							</a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<tr>
						<td><br></td>
					</tr>
				</table>
				<div id="page">
					<c:if test="${pageMaker.prev}">
						<li><a href="visit.do${pageMaker.makeQuery(pageMaker.startPage - 1)}" style="text-decoration: none;">이전</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						<c:choose>
							<c:when test="${page==idx }">
								<li><a href="visit.do${pageMaker.makeQuery(idx)}" style="color: #F8863E;">${idx}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="visit.do${pageMaker.makeQuery(idx)}" style="text-decoration: none;">${idx}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li><a href="visit.do${pageMaker.makeQuery(pageMaker.endPage + 1)}" style="text-decoration: none;">다음</a></li>
					</c:if>

				</div>

			</div>
		</div>
	</header>
</body>
</html>