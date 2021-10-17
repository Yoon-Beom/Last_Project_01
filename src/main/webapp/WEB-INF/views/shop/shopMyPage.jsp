<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, java.text.*, java.io.*"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!--<c:set var="s" value="${shopList[0]}"/>
<c:set var="sd" value="${shopDList[0]}"/>-->
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
	display: inline;
}

.button {
	position: relative;
	left: 500px;
}

.button1 {
	position: relative;
	left: 480px;
}
.button2 {
	position: relative;
	left: 520px;
}

.title {
	border-bottom: 3px solid gray;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<c:choose>

	<c:when test="${result == 'pwdChange'}">
	  <script>
	  window.onload=function(){
	      alert("비밀번호가 수정되었습니다.");
	    }
	  </script>
	</c:when>
	
</c:choose>
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
							<td class="title" colspan="2"><p>회원 정보</p> 
							<a href="${pageContext.request.contextPath}/mypage/pwdCheckMem.do">
							<input type="button" value="수정하기" class="button"></a></td>
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td width="7%">이름 :</td>
							<td width="21%">${member.member_name }</td>
						</tr>
						<tr>
							<td>생년월일 :</td>
							<td>${member.member_birth }</td>
						</tr>
						<tr>
							<td>주소 :</td>
							<td>${member.member_address }</td>
						</tr>
						<tr>
							<td>핸드폰 번호 :</td>
							<td>${member.member_phone }</td>
						</tr>
						<tr>
							<td>이메일 :</td>
							<td>${member.member_email }</td>
						</tr>
					</table>
				</form>

				<br> <br>
				
				<table>
					<tr>
						<td class="title" colspan="2"><p>보안 설정</p> 
						</td>
					</tr>
					<tr>
						<td>
							<br>
						</td>
					</tr>
					<tr>
						<td width="7%">비밀번호</td>
						<td width="21%">
						<a href="${pageContext.request.contextPath}/mypage/pwdCheckPwd.do">
						<input type="button" value="암호변경">
						</a>
						</td>
					</tr>
				</table>
				
				<br> <br>
				<form action="${pageContext.request.contextPath}/shop/shopMod.do" enctype="multipart/form-data">
				<table>
					<tr>
						<td class="title" colspan="4"><p>매장정보</p>
						<input type="hidden" value="${member.member_NO }">
						<input type="submit" value="수정하기" class="button">
						</td>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					
					<tr>
						<td class="td" width="20%">관심</td>
						<td class="td" width="25%">${shop.shopDetailVO.shop_heartScore}</td>
						<td class="td" width="20%">예약수</td>
						<td class="td" width="25%">${shop.shopDetailVO.shop_reserveScore }</td>
					</tr>
					<tr>
						<td class="td" width="20%">평점</td>
						<td class="td" width="25%">${shop.shopDetailVO.shop_starScore }</td>
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
                     <td class="td" width="7%">매장명</td>
                     <td class="td" width="10%">${shop.shop_name}</td>
                     <td class="td" width="7%" >사업자등록번호</td>
                     <td class="td" width="10%" >${shop.shop_code}</td>
                  </tr>   
                  <tr>      
                     <td class="td" width="7%">오픈 시간</td>
                     <td class="td" width="10%"> ${shop.shopDetailVO.shop_open_time}</td>
                     <td class="td" width="7%" >마감시간</td>
                     <td class="td" width="10%" >${shop.shopDetailVO.shop_close_time}</td>
                  </tr>   
                  <tr>      
                     <td class="td" width="7%">주소</td>
                     <td class="td" width="10%">${shop.shop_address}</td>
                     <td class="td" width="7%">☎</td>
                     <td class="td" width="10%"> ${shop.shopDetailVO.shop_phone}</td>
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
			</form>

				<br> <br>
				<table>
					<tr>
						<td class="title" colspan="9"><p>최근 예약 내역</p>
						</td>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="td" width="5%">번호</td>
						<td class="td" width="12%">예약 날짜</td>
						<td class="td" width="12%">예약시간</td>
						<td class="td" width="10%">고객명</td>
						<td class="td" width="12%">반려견 이름</td>
						<td class="td" width="8%">크기</td>
						<td class="td" width="8%">미용</td>
						<td class="td" width="5%">목욕</td>
						<td class="td" width="10%">예약취소</td>
					</tr>
					<c:forEach var="i" begin="1" end="3">
						<tr>
							<td class="td" width="5%">1</td>
							<td class="td" width="12%">09.23.21</td>
							<td class="td" width="12%">17시 30분</td>
							<td class="td" width="10%">콩콩콩</td>
							<td class="td" width="12%">홍뽀삐</td>
							<td class="td" width="8%">소형</td>
							<td class="td" width="8%">전체</td>
							<td class="td" width="5%">o</td>
							<td class="td" width="10%"><input type="button" value="취소하기"></td>
						</tr>
					</c:forEach>
				</table>
				
				<br> <br>
				<table>
					<tr>
						<td class="title" colspan="8"><p>고객 기록</p> 
						<input type="button" value="더보기" class="button2"></td>
					<tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="td" width="5%">번호</td>
						<td class="td" width="12%">예약 날짜</td>
						<td class="td" width="12%">예약시간</td>
						<td class="td" width="10%">고객명</td>
						<td class="td" width="12%">반려견 이름</td>
						<td class="td" width="8%">크기</td>
						<td class="td" width="8%">미용</td>
						<td class="td" width="5%">목욕</td>

					</tr>
					<c:forEach var="i" begin="1" end="3">
						<tr>
							<td class="td" width="5%">1</td>
							<td class="td" width="12%">09.23.21</td>
							<td class="td" width="12%">17시 30분</td>
							<td class="td" width="10%">콩콩콩</td>
							<td class="td" width="12%">홍뽀삐</td>
							<td class="td" width="8%">소형</td>
							<td class="td" width="8%">전체</td>
							<td class="td" width="5%">o</td>
						</tr>
					</c:forEach>
				</table>
				<br> <br>
				<table>
					<tr>
						<td class="title" colspan="5"><p>리뷰 내역</p><a
							href="${pageContext.request.contextPath}/mypage/visit.do"> <input
								type="button" value="더보기" class="button2"></a></td>
					<tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="td" width="7%">번호</td>
						<td class="td" width="10%">고객명</td>
						<td class="td" width="20%">반려견 이름</td>
						<td class="td" width="30%">리뷰 내용</td>
						<td class="td" width="20%">작성 날짜</td>

					</tr>
					<c:forEach var="i" begin="1" end="3">
						<tr>
							<td class="td">1</td>
							<td class="td">콩콩콩</td>
							<td class="td">콩뽀삐</td>
							<td class="td">넘 좋았어요.</td>
							<td class="td">09.15.21</td>
						</tr>
					</c:forEach>
				</table>
				
				<br> <br>		
				<table>
					
					<tr>
						<td width="7%">
							<a href="${pageContext.request.contextPath}/mypage/membershipCancle.do">
							<input type="button" value="회원탈퇴">
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<script type="text/javascript">
			
		</script>		
	</header>
</body>
</html>