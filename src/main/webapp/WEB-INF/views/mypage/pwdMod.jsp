<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css" />
<title>회원정보 수정</title>
<style type="text/css">
input {
	width: 300px;
	height: 35px;
	text-align: "center";
}

#box1 {
	width: 80%;
	height: 100%;
	padding: 0 0 40px 0;
	position: relative;
	top: 50px;
	margin: auto;
	background-color: white;
}

.text1 {
	font-size: 20px;
	margin-top: 20px;
	text-align: left;
	padding-left: 36%;
}
.text2 {
	width: 70px;
	text-align: "center";
}	

.gender {
	width: auto;
	height: auto;
}

#account {
	font-size: 13px;
	color: red;
}

.button {
	width: 50px;
}

.pwdcheck {
	position: relative;
	left: 3%;
}
</style>
<script type="text/javascript">

	// 전화번호 자동 포커스 이동
	$(function() {		
		$("input[name=member_phone1]").on("propertychange change keyup paste input", function(e) {
			if($(this).val().length == 3) {
				$("input[name=member_phone2]").focus();
			}
		});
		
		$("input[name=member_phone2]").on("propertychange change keyup paste input", function(e) {
			if($(this).val().length == 4) {
				$("input[name=member_phone3]").focus();
			} else if (e.keyCode == 8 || e.which == 8) {
				if($(this).val().length == 0) {
					$("input[name=member_phone1]").focus();
				}
			}
		});
		
		$("input[name=member_phone3]").on("propertychange change keyup paste input", function(e) {
			if (e.keyCode == 8 || e.which == 8) {
				if($(this).val().length == 0) {
					$("input[name=member_phone2]").focus();
				}
			}
		});
	});
</script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	//주소찾기
	function findAddr(){
		new daum.Postcode({
	        oncomplete: function(data) {
	        		        	
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
	            var jibunAddr = data.jibunAddress; // 지번 주소 변수
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.membership.member_post.value = data.zonecode;
	            
	            if(roadAddr !== ''){
	                document.membership.member_addr.value = roadAddr;
	            } else if(jibunAddr !== ''){
	                document.membership.member_addr.value = jibunAddr;
	            }
	            document.membership.member_detailAddr.focus();
	        }
	    }).open();
	}
	
	function submitBtn() {		
		var frm = document.membership;
		
		if(frm.member_name.value == "") {
			alert("이름을 입력하세요.");
			frm.member_name.focus();
		} else if(frm.member_phone1.value == "" || frm.member_phone2.value == "" || frm.member_phone3.value == "") {
			alert("연락처를 입력하세요.");
			frm.member_phone1.value = null;
			frm.member_phone2.value = null;
			frm.member_phone3.value = null;
			frm.member_phone1.focus();
		} else if(frm.member_email.value == "") {
			alert("이메일을 입력하세요.");
			frm.member_email.focus();			
		} else if(frm.member_post.value == "" || frm.member_addr.value == "") {
			alert("주소를 입력하세요.");
			frm.member_post.focus();			
		} else if(frm.member_detailAddr.value == "") {
			alert("상세 주소를 입력하세요.");
			frm.member_post.focus();	
		} else {
			frm.action = '${contextPath}/mypage/update.do';
			frm.method = 'post';
			frm.submit();
		}
	}
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" ></script>
</head>
<body>
	<header class="masthead">
		<form method="post" name="membership"
			action="${contextPath}/mypage/update.do">
			<div class="container">
				<div id="box1">
					<h1>회원 정보 수정</h1>
					<input type="hidden" name=member_birth value="${member.member_birth }">
					<div class="text1">아이디</div>
					<input type="text" name="member_id" value="${member.member_id }" style="border: none;" readonly >
					<div class="text1">이름</div>
					<input type="text" name="member_name" value="${member.member_name }">
					<div class="text1">주소</div>
					<input name="member_post" type="text" readonly onclick="findAddr()" value="${fn:split(member.member_address,',')[0]}"> <br>
					<input name="member_addr" type="text" readonly  onclick="findAddr()" value="${fn:split(member.member_address,',')[1]}" > <br> 
					<input name="member_detailAddr" type="text" value="${fn:split(member.member_address,',')[2]}">
					<div class="text1">이메일</div>
					<input type="email" name="member_email" value="${member.member_email }">
					<div class="text1">
						연락처&nbsp;&nbsp;<a id="account"></a>
					</div>
					<input type="number" class="text2" name="member_phone1"  value="${fn:substring(member.member_phone,0,3)}">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="number" class="text2"name="member_phone2"   value="${fn:substring(member.member_phone,4,8)}">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="number" class="text2"name="member_phone3"   value="${fn:substring(member.member_phone,9,13)}">
		
					<%-- <div class="text1">비밀번호</div>
					<input type="password" name="member_pwd" value="${member.member_pwd}">
					<div class="text1">비밀번호 확인</div>
					<div class="pwdcheck">
						<input type="password" name="member_pwd1" value="${member.member_pwd}"> &nbsp;&nbsp;
						<input type="button" value="확인" class="button">
					</div> --%>
					<br> <br> <br> <input type="button" value="수정" onclick="submitBtn()"
						class="button">&nbsp;&nbsp;&nbsp; 
						<a href="${pageContext.request.contextPath}/mypage/myPage.do">
						<input type="button" value="취소" class="button"></a>
				</div>
			</div>
	</header>
	</form>
</body>
</html>