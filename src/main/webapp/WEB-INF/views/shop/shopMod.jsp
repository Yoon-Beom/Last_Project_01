<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css" />
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
	width: 50px;
}

.title {
	border-bottom: 3px solid gray;
}

.text2 {
	width: 60px;
	text-align: "center";
}	
.img2 {
	width: 500px;
	height: 200px;
}
</style>
<script type="text/javascript">

window.onload = function() {
	var shopInfo = document.shopInfo;
	
	for (var h = 10; h <= 20; h++) {
		shopInfo.open_time.add(new Option((h) + ':00'));
		shopInfo.close_time.add(new Option((h) + ':00'));
	}
	
	$('#preview').show();
	$('#previewNon').hide();
	$('#mainCover').show();
};
function readURL(input) {
    if (input.files && input.files[0]) {
    	$('#preview').hide();
    	$('#mainCover').hide();
    	$('#previewNon').show();
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#previewNon').attr('src', e.target.result);
        }
       reader.readAsDataURL(input.files[0]);
    }
};

//기업 주소 찾기
function shopFindAddr() {
	new daum.Postcode({
		oncomplete : function(data) {

			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var jibunAddr = data.jibunAddress; // 지번 주소 변수
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.shopInfo.shop_post.value = data.zonecode;

			if (roadAddr !== '') {
				document.shopInfo.shop_addr.value = roadAddr;
			} else if (jibunAddr !== '') {
				document.membership.shop_addr.value = jibunAddr;
			}
			document.shopInfo.shop_detailAddr.focus();
		}
	}).open();
};


</script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" ></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 수정 페이지</title>
</head>
<body>
	<header class="masthead">
		<form method="post" name="shopInfo" action="${contextPath}/shop/updateShop.do" enctype="multipart/form-data">
			<div class="container">
				<div id="box1">
					<h1>매장 정보 수정</h1>
					<table>
						<tr>
							<td class="td" width="20%" colspan="4"> 매장 대표사진</td>
						</tr>
						<tr>
							<td class="td" width="20%" colspan="4">
								<c:if test="${shop.shopDetailVO.shop_imageMain == null}">
								<img src="${pageContext.request.contextPath}/resources/assets/img/shopMain.png" id="mainCover" class="img2">
								<img src="${contextPath}/downloadShopDetail.do?shopDetail_NO=${shop.shopDetailVO.shopDetail_NO}&shop_imageMain=${shop.shopDetailVO.shop_imageMain }" id="previewNon" class="img2" />
								<br>
								<input type="file" class="file" name="shop_imageMain" onchange="readURL(this);">
								</c:if>
								<c:if test="${shop.shopDetailVO.shop_imageMain != null}">
								<img src="${contextPath}/downloadShopDetail.do?shopDetail_NO=${shop.shopDetailVO.shopDetail_NO}&shop_imageMain=${shop.shopDetailVO.shop_imageMain }" id="preview" class="img2" />
								<img src="${contextPath}/downloadShopDetail.do?shopDetail_NO=${shop.shopDetailVO.shopDetail_NO}&shop_imageMain=${shop.shopDetailVO.shop_imageMain }" id="previewNon" class="img2" />
								
							<!-- 	<img  class="img2" id="preview" src="#" /> -->
								<br>
								<input type="file" class="file" name="shop_imageMain" onchange="readURL(this);">
							</c:if>
							</td>
						</tr>
						<tr>
							<td class="td" colspan="2" width="7%">매장명</td>
							<td class="td" colspan="2"  width="10%">
								<input type="text" name="shop_name" value="${shop.shop_name}">
							</td>
						</tr>
						<tr>
							<td class="td" width="7%">대표명</td>
							<td class="td" width="10%">
								<input type="text" name="shop_ceo" value="${shop.shopDetailVO.shop_ceo}">
							</td>
							<td class="td" width="7%">사업자등록번호</td>
							<td class="td" width="10%">
								<input type="text" name="shop_code" value="${shop.shop_code}">
							</td>
						</tr>
						<tr>
							<td class="td" width="7%">오픈 시간</td>
							<td class="td" width="10%">
									<select name="open_time">
									<option value="${shop.shopDetailVO.shop_open_time}">${shop.shopDetailVO.shop_open_time}</option>
									</select>
							</td> 
							<td class="td" width="7%">마감시간</td>
							<td class="td" width="10%">
									<select name="close_time">
									<option value="${shop.shopDetailVO.shop_close_time}">${shop.shopDetailVO.shop_close_time}</option>
									</select>		
						</td>
						</tr>
						<tr>
							<td class="td" width="7%">주소</td>
							<td class="td" width="10%">
									<input name="shop_post" type="text" placeholder="우편번호" readonly onclick="shopFindAddr()" value="${fn:split(shop.shop_address,',')[0]}"> <br>
									<input name="shop_addr" type="text" placeholder="주소" readonly onclick="shopFindAddr()" value="${fn:split(shop.shop_address,',')[1]}"> <br> 
									<input name="shop_detailAddr" type="text" placeholder="상세주소" value="${fn:split(shop.shop_address,',')[2]}"><br>
							</td>
							<td class="td" width="7%">☎</td>
							<td class="td" width="10%">
								<input type="number" class="text2" name="shop_phone1"  value="${fn:substring(shop.shopDetailVO.shop_phone,0,3)}">&nbsp;-&nbsp;
								<input type="number" class="text2"name="shop_phone2"   value="${fn:substring(shop.shopDetailVO.shop_phone,4,8)}">&nbsp;-&nbsp;
								<input type="number" class="text2"name="shop_phone3"   value="${fn:substring(shop.shopDetailVO.shop_phone,9,13)}">
							
							</td>
						</tr>
						<tr>
							<td class="td" width="20%" colspan="4">매장 소개</td>
						</tr>
						<tr>
							<td class="td" width="20%" colspan="4">
								<textarea rows="8" cols="60%" style="resize:none;" name="shop_introduce" id="shop_introduce">${shop.shopDetailVO.shop_introduce }</textarea>
							</td>
						</tr>
						<tr>
							<td class="td" width="7%" colspan="4">매장 서브 사진</td>
						</tr>
						<tr>
							<td class="td" width="10%">
								<img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png">
							</td>
							<td class="td" width="10%">
								<img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png">
							</td>
							<td class="td" width="10%">
								<img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png">
							</td>
							<td class="td" width="10%">
								<img src="${pageContext.request.contextPath}/resources/assets/img/dog4.png">
							</td>
						</tr>
					</table>
					<br> <br> <br>
					<input type="submit" value="수정" class="button">
					&nbsp;&nbsp;&nbsp;
					<input type="hidden" name="member_NO" value="${member.member_NO }">
					<input type="hidden" name="shop_NO" value="${shop.shop_NO }">
					<input type="hidden" name="shopDetail_NO" value="${shop.shopDetailVO.shopDetail_NO}">
					<a href="${pageContext.request.contextPath}/shop/shopMyPage.do">
						<input type="button" value="취소" class="button">
					</a>
				</div>
			</div>
		</form>
	</header>
</body>
</html>