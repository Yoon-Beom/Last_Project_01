<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">
#menu {
	position: absolute;
	top: 140px;
	left: 5%;
	width: 15%;
	height: 600px;
	background-color: #e6e6e6;
	font-size: 30px;
}

#wrting {
	text-align: right;
	position: absolute;
	right: 60px;
	top: 50px;
}

#boardmain {
	position: relative;
	top: 52px;
	left: 14.8%;
	width: 88%;
	height: 600px;
	text-align: "center";
	background-color: white;
}

.mn {
	text-decoration: none;
	height: 80px;
}

#boardtable {
	border: 1px solid gray;
	width: 90%;
	margin: auto;
	border-collapse: collapse;
}

#boardhead {
	padding-top: 30px;
	text-align: left;
	padding-left: 6%;
	width: 70%;
	height: 60px;
}

.td {
	height: 35px;
	border-bottom: 1px solid gray;
	border-collapse: collapse;
}

#page {
	font-size: 25px;
	padding: 10px;
	padding-top: 20px;
}

#boardmenu {
	height: 50px;
	background-color: #e6e6e6;
}

#title {
	color: inherit;
}

#page li {
	list-style: none;
	float: center;
	padding: 6px;
	display: inline;
}
</style>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/styles.css" />
</head>
<body>
	<header class="masthead">
		<div class="container">
			<div id="menu">
				<br> <a href="${contextPath}/board/freeBoard.do?board_code=1"
					class="mn">자유게시판</a><br> <a
					href="${contextPath}/board/qnaBoard.do?board_code=2" class="mn">QnA</a><br>
				<a href="${contextPath}/board/noticeBoard.do?board_code=3"
					class="mn">공지사항</a><br>
			</div>
			<br>
			<div id="boardmain">
				<div id="boardhead">
					<form name="frmsearch" method="post"
						action="${contextPath}/board/searchNotice.do?board_code=3">
						<input type="text" style="width: 280px; height: 30px;"
							name="search" placeholder="작성자를 입력해 주세요"> <input
							type="submit" value="검색">&nbsp;&nbsp;&nbsp; <select
							name="optionContent"><option value="board_name">작성자</option>
							<option value="board_title">제목</option></select>
					</form>
				</div>

				<c:if test="${member.member_id=='admin' }">
					<a href="${contextPath }/board/noticeBoardWriting.do"> <input
						type="button" value="글쓰기" id="wrting">
					</a>
				</c:if>

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

						<c:forEach var="board" items="${list}">
							<c:if test="${board.board_level=='1' }">
								<tr style="background-color: #F2F2F2; color: #EF5A5C;">

									<td width="7%" name="board_NO" class="td">${board.rnum}</td>
									<td width="30%" name="board_title" class="td"><a
										href="${contextPath}/board/noticeContent.do?board_NO=${board.board_NO}"
										id="title" style="text-decoration: none;">${board.board_title}</a></td>
									<td width="14%" name="board_name" class="td">${board.board_name}</td>
									<td width="20%" name="board_Date" class="td">${board.board_Date}</td>
									<td width="14%" name="board_score" class="td">${board.board_score}</td>


								</tr>
							</c:if>
							<c:if test="${board.board_level=='0' }">
								<tr>

									<td width="7%" name="board_NO" class="td">${board.rnum}</td>
									<td width="30%" name="board_title" class="td"><a
										href="${contextPath}/board/noticeContent.do?board_NO=${board.board_NO}"
										id="title" style="text-decoration: none;">${board.board_title}</a></td>
									<td width="14%" name="board_name" class="td">${board.board_name}</td>
									<td width="20%" name="board_Date" class="td">${board.board_Date}</td>
									<td width="14%" name="board_score" class="td">${board.board_score}</td>


								</tr>
							</c:if>
						</c:forEach>

					</table>
				</form>
				<div id="page">
					<ul>
						<c:choose>
							<c:when test="${search == 'AllList'}">
								<c:if test="${pageMaker.prev}">
									<li><a
										href="noticeBoard.do${pageMaker.makeQuery(pageMaker.startPage - 1)}&board_code=3" style="text-decoration:none;">이전</a></li>
								</c:if>

								<c:forEach begin="${pageMaker.startPage}"
									end="${pageMaker.endPage}" var="idx">
									<c:choose>
										<c:when test="${page==idx }">
											<li><a href="noticeBoard.do${pageMaker.makeQuery(idx)}&board_code=3" style="color: #F8863E;">${idx}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="noticeBoard.do${pageMaker.makeQuery(idx)}&board_code=3" style="text-decoration:none;">${idx}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
									<li><a
										href="noticeBoard.do${pageMaker.makeQuery(pageMaker.endPage + 1)}&board_code=3" style="text-decoration:none;">다음</a></li>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${pageMaker.prev}">
									<li><a
										href="searchNotice.do${pageMaker.makeSearch(pageMaker.startPage - 1)}&board_code=3" style="text-decoration:none;">이전</a></li>
								</c:if>

								<c:forEach begin="${pageMaker.startPage}"
									end="${pageMaker.endPage}" var="idx">
									<c:choose>
										<c:when test="${page==idx }">
											<li><a href="searchNotice.do${pageMaker.makeSearch(idx)}&board_code=3" style="color: #F8863E;">${idx}</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="searchNotice.do${pageMaker.makeSearch(idx)}&board_code=3" style="text-decoration:none;">${idx}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
									<li><a
										href="searchNotice.do${pageMaker.makeSearch(pageMaker.endPage + 1)}&board_code=3" style="text-decoration:none;">다음</a></li>
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