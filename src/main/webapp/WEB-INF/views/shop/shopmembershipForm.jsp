<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css" />
<title>기업 회원가입창</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

$(document).ready(function(){
    $("#check").change(function(){
        if($("#check").is(":checked")){
            $('input[name=shop_ceo]').val($('input[name=member_name]').val());
            
            $('input[name=shop_phone1]').val($('input[name=member_phone1]').val());
            $('input[name=shop_phone2]').val($('input[name=member_phone2]').val());
            $('input[name=shop_phone3]').val($('input[name=member_phone3]').val());
            
            $('input[name=shop_post]').val($('input[name=member_post]').val());
            $('input[name=shop_addr]').val($('input[name=member_addr]').val());
            $('input[name=shop_detailAddr]').val($('input[name=member_detailAddr]').val());
        }
    });
});

// 회원 전화번호 자동 포커스 이동
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

// 매장 전화번호 자동 포커스 이동
$(function() {		
	$("input[name=shop_phone1]").on("propertychange change keyup paste input", function(e) {
		if($(this).val().length == 3) {
			$("input[name=shop_phone2]").focus();
		}
	});
	
	$("input[name=shop_phone2]").on("propertychange change keyup paste input", function(e) {
		if($(this).val().length == 4) {
			$("input[name=shop_phone3]").focus();
		} else if (e.keyCode == 8 || e.which == 8) {
			if($(this).val().length == 0) {
				$("input[name=shop_phone1]").focus();
			}
		}
	});
	
	$("input[name=shop_phone3]").on("propertychange change keyup paste input", function(e) {
		if (e.keyCode == 8 || e.which == 8) {
			if($(this).val().length == 0) {
				$("input[name=shop_phone2]").focus();
			}
		}
	});
});
</script>
<script type="text/javascript">
	window.onload = function() {
		var membership = document.membership;
		var now = new Date().getFullYear(); // 현재 날짜 및 시간

		for (var y = 1940; y <= now; y++) {
			membership.member_yy.add(new Option((y) + '년'));
		}
		for (var m = 1; m <= 12; m++) {
			membership.member_mm.add(new Option((m) + '월'));
		}
		for (var d = 1; d <= 31; d++) {
			membership.member_dd.add(new Option((d) + '일'));
		}
		for(var h=00; h<=23; h++){
			if(h<10){
			membership.open_time.add(new Option('0'+(h) + ':00'));
			membership.open_time.add(new Option('0'+(h) + ':30'));
			membership.close_time.add(new Option('0'+(h) + ':00'));
			membership.close_time.add(new Option('0'+(h) + ':30'));
			}else{
			membership.open_time.add(new Option((h) + ':00'));	
			membership.open_time.add(new Option((h) + ':30'));
			membership.close_time.add(new Option((h) + ':00'));
			membership.close_time.add(new Option((h) + ':30'));
			}
		}
			
	};
	
	// 회원 주소 찾기
	function memberFindAddr(){
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
	
	// 기업 주소 찾기
	function shopFindAddr(){
		new daum.Postcode({
	        oncomplete: function(data) {
	        		        	
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
	            var jibunAddr = data.jibunAddress; // 지번 주소 변수
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.membership.shop_post.value = data.zonecode;
	            
	            if(roadAddr !== ''){
	                document.membership.shop_addr.value = roadAddr;
	            } else if(jibunAddr !== ''){
	                document.membership.shop_addr.value = jibunAddr;
	            }
	            document.membership.shop_detailAddr.focus();
	        }
	    }).open();
	};
	
	function inputIdChk() {
		document.membership.idDuplication.value="idUncheck";
	}
	
	// 새창에서 아이디 중복 체크
	function openIdChk() {
		window.name = "parentForm";
		window.open("IdCheckForm.do",
				"chkForm", "width=500, height=300, resizable=no, scrollbars=no");
	}
	
	// submit
	function submitBtn() {		
		var frm = document.membership;
		
		if(frm.member_id.value == "") {
			alert("아이디를 입력하세요.");
			frm.member_id.focus();
		} else if(frm.idDuplication.value == "idUncheck") {
			alert("아이디 중복 확인을 해주세요.");
			frm.member_pwd.focus();
		} else if(frm.member_pwd.value == "") {
			alert("비밀번호를 입력하세요.");
			frm.member_pwd.focus();
		} else if(frm.member_pwd1.value == "") {
			alert("비밀번호 확인을 입력하세요.");
			frm.member_pwd1.focus();
		} else if(frm.pwdCheck.value == "pwdUnmatch") {
			alert("비밀번호를 확인하세요.");
			frm.member_pwd.focus();
		} else if(frm.member_name.value == "") {
			alert("이름을 입력하세요.");
			frm.member_name.focus();
		} else if(frm.member_yy.value == "#" || frm.member_mm.value == "#" || frm.member_dd.value == "#" ) {
			alert("생년월일을 선택하세요.");
			frm.member_yy.focus();
		} else if(frm.member_gender.value == "") {
			alert("성별을 선택하세요.");
			frm.member_gender.focus();
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
		} else if(frm.shop_name.value == "") {
			alert("매장명을 입력하세요.");
			frm.shop_name.focus();	
		} else if(frm.shop_ceo.value == "") {
			alert("대표자 명을 입력하세요.");
			frm.shop_ceo.focus();	
		} else if(frm.shop_post.value == "" || frm.shop_addr.value == "") {
			alert("매장 주소를 입력하세요.");
			frm.shop_post.focus();			
		} else if(frm.shop_detailAddr.value == "") {
			alert("매장 상세 주소를 입력하세요.");
			frm.shop_post.focus();	
		} else if(frm.open_time.value == "") {
			alert("오픈시간을 입력하세요.");
			frm.open_time.focus();	
		} else if(frm.close_time.value == "") {
			alert("마감시간을 입력하세요.");
			frm.close_time.focus();	
		} else if(frm.shop_phone1.value == "" || frm.shop_phone2.value == "" || frm.shop_phone3.value == "") {
			alert("매장전화를 입력하세요.");
			frm.shop_phone1.value = null;
			frm.shop_phone2.value = null;
			frm.shop_phone3.value = null;
			frm.shop_phone1.focus();
		} else if(frm.shop_code.value == "") {
			alert("사업자 등록번호를 입력하세요.");
			frm.shop_code.focus();	
		} else {
			frm.action = '${contextPath}/shop/addShop.do';
			frm.method = 'post';
			frm.submit();
		}
	}
	
	// 비밀번호 유효성 검사
	// 비밀번호 8자리 이상
	// 숫자 포함	
	// 영대 문자 포함	
	// 영소 문자 포함	
	// 특수문자 포함	
	// 공백 X	
	// 같은 문자 4번 반복 X	
	// 아이디 포함 X	
	// 한글 X
	function chkPW() {
		var id = document.membership.member_id.value;
		var pwd = document.membership.member_pwd.value;
		var pwd1 = document.membership.member_pwd1.value;
		
		var pwdtext = document.getElementById('pwdtext');
		var pwdCheck = document.membership.pwdCheck;
		
		var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		pwdCheck.value = 'pwdUnmatch';
		
		if(pwd == '') {
			pwdtext.innerHTML = '비밀번호를 입력하세요.';
			document.membership.member_pwd.focus();
		} else if (pwd1 == '') {
			pwdtext.innerHTML = '비밀번호 확인을 입력하세요.';
			document.membership.member_pwd1.focus();
		} else if(pwd != pwd1) {
			pwdtext.innerHTML = '비밀번호가 일치하지 않습니다.';
			// document.membership.member_pwd.focus();
		} else if (false === reg.test(pwd)) {
			pwdtext.innerHTML = '비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.';
			document.membership.member_pwd.focus();
		} else if (/(\w)\1\1\1/.test(pwd)) {
			pwdtext.innerHTML = '같은 문자를 4번 이상 사용하실 수 없습니다.';
			document.membership.member_pwd.focus();
		} else if (pwd.search(id) > -1) {
			pwdtext.innerHTML = '비밀번호에 아이디가 포함되었습니다.';
			document.membership.member_pwd.focus();
		} else if (pwd.search(/\s/) != -1) {
			pwdtext.innerHTML = '비밀번호는 공백 없이 입력해주세요.';
			document.membership.member_pwd.focus();
		} else if (hangulcheck.test(pwd)) {
			pwdtext.innerHTML = '비밀번호에 한글을 사용 할 수 없습니다.';
			document.membership.member_pwd.focus();
		} else {
			pwdtext.innerHTML = '';
			pwdCheck.value = 'pwdmatch';
		}
	}
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" ></script>

<!-- ㅡ ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->

<style type="text/css">
#img {
	width: 50px;
	position: absolute;
	left: 70%;
	top: 25px;
}

input {
	width: 300px;
	height: 35px;
	text-align: "center";
}

.id_check {
	width: 70px;
	height: 28px;
	font-size: 13px;
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

.gender {
	width: auto;
	height: auto;
}

#account {
	font-size: 13px;
	color: red;
}

.text2 {
	width: 70px;
	text-align: "center";
}

#check {
	margin: 0px;
	padding: 0px;
	width: 15px;
	height: 15px;
	position: relative;
	top: 2px;
}

/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

</style>
</head>
<body>
	<form method="post" name="membership">
		<header class="masthead">
			<div class="container">
				<div id="box1">
					<h1>
						기업회원 회원가입 <img id="img" src="${contextPath}/resources/assets/img/icon2.png">
					</h1>
					<div class="text1">
						아이디
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="button" value="중복 확인" class="id_check" onclick="openIdChk()">
					</div>
					<input type="text" name="member_id" onkeydown="inputIdChk()">
					<!-- onkeydown : 키보드 입력시 발생하는 이벤트 -->
					<!-- 사용자가 아이디 중복체크를 하고 난 뒤 아이디 입력란에 사용 가능한 아이디를 지우고 새로운 아이디를 입력햇을 경우에 대처 -->
					<input type="hidden" name="idDuplication" value="idUncheck">
					<!-- 아이디 중복체크를 했는지 판단하기위함 value가 idUncheck 이면 중복체크를 하지 않는 것 -->
					
					<div class="text1">비밀번호</div>
					<input type="password" name="member_pwd" onchange="chkPW()">
					
					<div class="text1">비밀번호 확인</div>
					<input type="password" name="member_pwd1" onchange="chkPW()">
					<input type="hidden" name="pwdCheck" value="pwdUnmatch">
					
					<p id="pwdtext"></p>
					
					<div class="text1">이름</div>
					<input type="text" name="member_name">
					
					<div class="text1">생년월일</div>
					<select name="member_yy">
						<option value="#">년도 선택</option>
					</select>년 
					<select name="member_mm">
						<option value="#">월 선택</option>
					</select>월 
					<select name="member_dd">
						<option value="#">일 선택</option>
					</select>일
					
					<div class="text1">성별</div>
					<input type="radio" class="gender" name="member_gender" value="m">남성
					<input type="radio" class="gender" name="member_gender" value="f" style="margin-left: 100px;">여성
					
					<div class="text1">
						연락처&nbsp;&nbsp;
					</div>
					<input type="text" class="text2" name="member_phone1">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="text" class="text2" name="member_phone2">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="text" class="text2" name="member_phone3">
					
					<div class="text1">이메일</div>
					<input type="email" name="member_email">
					
					<div class="text1">주소</div>
					<input name="member_post" type="text" placeholder="우편번호" readonly onclick="memberFindAddr()"> <br>
					<input name="member_addr" type="text" placeholder="주소" readonly> <br> 
					<input name="member_detailAddr" type="text" placeholder="상세주소"><br>
					<br>
					
					<div class="text1">[ 매장정보 입력 ]</div>
					
					<span style="position: relative; left: 78px;">
					<input type="checkbox" id="check" value="check"> &nbsp; 회원 정보와 동일
					</span>
					
					<br>
					<div class="text1">매장명</div>
					<input type="text" name="shop_name">
					
					<div class="text1">대표자 성명</div>
					<input type="text" name="shop_ceo">
					
					<div class="text1">매장 주소</div>
					<input name="shop_post" type="text" placeholder="우편번호" readonly onclick="shopFindAddr()"> <br>
					<input name="shop_addr" type="text" placeholder="주소" readonly> <br> 
					<input name="shop_detailAddr" type="text" placeholder="상세주소"><br>
					
					
					<div class="text1">운영시간</div>
					<select name="open_time">
						<option value="#">오픈시간 선택</option>
					</select>
					<select name="close_time">
						<option value="#">마감시간 선택</option>
					</select>		
					
					
					<div class="text1">매장 전화</div>
					<input type="text" class="text2" name="shop_phone1">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="text" class="text2" name="shop_phone2">&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="text" class="text2" name="shop_phone3">
					
					<div class="text1">사업자 등록번호</div>
					<input type="text" name="shop_code"> <br>
					<br>
					<input type="button" value="가입하기" onclick="submitBtn()">
				</div>
			</div>
		</header>
	</form>
</body>
</html>