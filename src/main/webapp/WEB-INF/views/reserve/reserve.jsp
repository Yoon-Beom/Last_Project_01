<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>예약 하기</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

/* function ajaxList(str, shop_NO) {
	var sendData = {"shop_NO" : shop_NO, "reserve_Date" : str};
	$.ajax({
		url:"${contextPath}/reserve/timeList.do",
		type:"POST",
		dataType: 'json',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify(sendData),
		success: function(timeList) {
			
		}
	});
} */

</script>
<script type="text/javascript">

window.onload = function() {
	// 달력 초기 세팅
	<% 
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int yy = cal.get(java.util.Calendar.YEAR);
		int mm = cal.get(java.util.Calendar.MONTH); 
		int dd = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		int now = cal.get(java.util.Calendar.DATE);
		
		
		if(mm > 10) {
			cal.set(cal.get(java.util.Calendar.YEAR) + 1, 0, 1);
		} else {
			cal.set(cal.get(java.util.Calendar.YEAR), mm+1, 1);
		}
		
		int nextday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
	%>
	
	for(var i = 1; i <= <%= dd %>; i++) {
		if(<%= now %> <= i) {
			document.getElementById((<%= mm %> + 1) + '_' + i).addEventListener('click', select_day);
		}
	}
	
	for(var i = 1; i <= <%= nextday %>; i++) {
		document.getElementById((<%= mm %> + 2) + '_' + i).addEventListener('click', select_day);
	}
	
	for(var i = 10; i <= 20; i++ ) {
		document.getElementById('time_' + i + ':00').addEventListener('click', select_time);
	}
	
	<c:forEach items="${dayList}" var="day">
		var str1 = "${day}".split(" ");
		var str = str1[0].split("-");
		addBlock(document.getElementById(str[1] + '_' + str[2]), 'addBlockDayStyle');
	</c:forEach>
}

// 달력 div 이동 메서드
function moveScroll(x, y) {
	var table = document.getElementById('table-box');
	table.scrollBy(x, y);
};

// 반려동물 정보 / 입력 div 이동
function pet_moveScroll(x, y) {
	var select_pet_table = document.getElementById('select_pet_table');
	select_pet_table.scrollBy(x, y);

	var pet_select = document.querySelector('input[name="pet_select"]:checked');
	
	document.getElementById('pet_name').value = '';
	document.getElementById('pet_age').value = '';
	if(document.querySelector('input[name="pet_scale"]:checked') != null) {
		document.querySelector('input[name="pet_scale"]:checked').checked = false;
	}
	
	var pet = document.getElementsByName('pet_NO');
	var pet_info = document.getElementById('pet_info');
	
	pet.value = '';
	pet_info.innerText = '\u00a0';
};

// 반려동물 정보 선택 div 이동
function pet_listScroll(x, y) {
	var select_pet_table = document.getElementById('pet_select_div');
	select_pet_table.scrollBy(x, y);
}

// 달력 이동
function month_move(m) {
	var mon = document.getElementById('month');
	
	if(m == mon.value) {

	} else if (m < mon.value) {
		moveScroll(-700, 0);
		mon.value = m;
		document.getElementById('mon_' + m).style.color = 'black';
		document.getElementById('mon_' + (m+1)).style.color = 'gray';
	} else if (m > mon.value) {
		moveScroll(700, 0);
		mon.value = m;
		document.getElementById('mon_' + m).style.color = 'black';
		document.getElementById('mon_' + (m-1)).style.color = 'gray';
	}
}

// 펫 선택
function select_pet(pet_NO) {
	var pet = document.reserve.pet_NO;
	var pet_info = document.getElementById('pet_info');

	var pet_name = document.getElementById('pet_name_' + pet_NO).innerText;
	var pet_scale = document.getElementById('pet_scale_' + pet_NO).innerText;
	var pet_age = document.getElementById('pet_age_' + pet_NO).innerText;
	
	
	pet.value = pet_NO;	
	pet_info.innerHTML = pet_name + ', ' + pet_age + ', ' + pet_scale;
}

// 펫 입력
function input_pet() {
	var pet_name = document.getElementById('pet_name').value;
	var pet_age = document.getElementById('pet_age').value;
		
	if(document.querySelector('input[name="pet_scale"]:checked') != null) {
		var pet_scale = document.querySelector('input[name="pet_scale"]:checked').value;
	} else {
		var pet_scale = "";
	}
	
	
	
	if((pet_name != "") && (pet_age != "") && (pet_scale != "")) {
		pet_info.innerText = pet_name + ', ' + pet_age + '살, ' + pet_scale;
	} else if((pet_name != "") && (pet_age != "")) {
		pet_info.innerText = pet_name + ', ' + pet_age + '살';
	} else if((pet_name != "") && (pet_scale != "")) {
		pet_info.innerText = pet_name + ', ' + pet_scale;
	} else if((pet_age != "") && (pet_scale != "")) {
		pet_info.innerText = pet_age + '살, ' + pet_scale;
	} else if(pet_name != "") {
		pet_info.innerText = pet_name;
	} else if(pet_age != "") {
		pet_info.innerText = pet_age + '살';
	} else if(pet_scale != "") {
		pet_info.innerText = pet_scale;
	} else{
		pet_info.innerText = '\u00a0';
	}
}

// ajax 부분
//httpRequest 객체 생성
var httpRequest = null;

function getXMLHttpRequest(){
	httpRequest = null;

	if(window.ActiveXObject){
		try{
			httpRequest = new ActiveXObject("Msxml2.XMLHTTP");	
		} catch(e) {
			try{
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) { httpRequest = null; }
		}
	}
	else if(window.XMLHttpRequest){
		httpRequest = new window.XMLHttpRequest();
	}
	return httpRequest;	
}

function ajaxList (str, shop_NO) {
	var sendData = "shop_NO=" + shop_NO + "&reserve_Date=" + str;
	httpRequest = getXMLHttpRequest();
	httpRequest.onreadystatechange = callback;
	httpRequest.open("POST", "${contextPath}/reserve/timeList.do", true);	
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
	httpRequest.send(sendData);
}

function callback() {
	if(httpRequest.readyState == 4){
		var resultText = httpRequest.responseText;

		var str = resultText.split(',');
		for(var i = 0; i < str.length; i++) {
			if(str[i] != '') {
				var timeA = document.getElementById('time_' + str[i]);
				addBlock(timeA, 'addBlockTimeStyle');
			}
		}
	}
}

function deleteBlock() {
	for(var i = 10; i <= 20; i++) {
		var timeA = document.getElementById('time_' + i + ':00');
		addBlock(timeA, 'deleteBlockTimeStyle');
	}
}

// 날짜 선택
function select_day(e) {
	delete_color_day();
	var mon = document.getElementById('month').value;
	var shop_NO = document.reserve.shop_NO.value;
		
	this.style.backgroundColor = '#f0b3c0';
	this.style.color = 'white';
	this.style.border = '3px solid #f17791';
	
	var date = <%= yy %> + '-' + mon + '-' + this.innerText;
	
	document.getElementById('reserve_Date').innerText = date;
	document.reserve.reserve_Date.value = date;

	deleteBlock();
	todayCheck(date);
	ajaxList(date, shop_NO);
}

function todayCheck(str) {
	// 오늘 날짜 선택 햇을 시 시간대 현재시간 이후 2시간 예약은 막음
	var today = new Date();
	var month = today.getMonth() + 1;	// 월
	var date = today.getDate();			// 날짜
	var hours = today.getHours();		// 시
	var minutes = today.getMinutes();	// 분
	
	var arrdate = str.split('-');
	var startHours = ((minutes > 30) ? hours + 3 : hours + 2);

	if(startHours >= 20) { startHours = 20; }
	
	if(arrdate[1] == month && arrdate[2] == date) {
		delete_time();
		document.getElementById('reserve_TimeA').innerText = '\u00a0';
		
		for(var i = 10; i <= startHours; i++) {
			addBlock(document.getElementById('time_' + i + ':00'), 'addBlockTimeStyle');
		}
	} else {
		for(var i = 10; i <= 20; i++ ) {
			addBlock(document.getElementById('time_' + i + ':00'), 'deleteBlockTimeStyle');
		}
	}
}

// 클릭 이벤트와 스타일 추가, 삭제
function addBlock(element, flag) {
	if(flag == 'addBlockTimeStyle') {
		element.removeEventListener('click', select_time);
		element.style.backgroundColor = 'rgb(180, 180, 180)';
		element.style.color = 'rgb(104, 104, 104)';
	} else if(flag == 'deleteBlockTimeStyle') {
		element.addEventListener('click', select_time);
		element.style.backgroundColor = '';
		element.style.color = 'gray';
		element.style.border = '1px solid black';
	} else if(flag == 'addBlockDayStyle') {
		element.removeEventListener('click', select_day);
		element.style.backgroundColor = 'rgb(180, 180, 180)';
		element.style.color = 'rgb(104, 104, 104)';
	}
}

// 날짜 스타일 지우기
function delete_color_day() {
	var str = document.getElementById('reserve_Date').innerText;
	
	if(str != null && str != '') {
		var date = str.split('-');
		var pastDay = document.getElementById(date[1] + '_' + date[2]);
		
		var nowdate = new Date();
		var year = nowdate.getFullYear();
		
		nowdate.setMonth(date[1]);
		nowdate.setDate(date[2]);		
		
		var className = pastDay.className;
		var cName = className.split(' ');
		
		pastDay.style.border = '';
		
		if(nowdate.getDay() == '2') {
			pastDay.style.color = 'blue';
		} else if (nowdate.getDay() == '1') {
			pastDay.style.color = 'red';
		} else {
			pastDay.style.color = 'gray';
		}
		pastDay.style.backgroundColor = '';
	}
}

// 시간대 선택
function select_time(e) {
	delete_time();
	
	this.style.backgroundColor = '#f0b3c0';
	this.style.color = 'white';
	this.style.border = '3px solid #f17791';
	document.getElementById('reserve_TimeA').innerText = this.innerText;
	document.getElementsByName('reserve_TimeA').value = this.innerText;
	document.reserve.reserve_TimeA.value = this.innerText;
}

// 시간대 스타일 지우기
function delete_time() {
	var str = document.getElementsByName('reserve_TimeA').value;
	if(str != null) {
		document.getElementById('time_' + str).style.backgroundColor = '';
		document.getElementById('time_' + str).style.color = 'gray';
		document.getElementById('time_' + str).style.border = '';
	}
}

//submit 예약하기
function sumit_action() {
	var reservefrm = document.reserve;
	var pet_select = document.querySelector('input[name="pet_select"]:checked');
	var pet_scale = document.querySelector('input[name="pet_scale"]:checked');
	var res_spot = document.querySelector('input[name="res_spot"]:checked').value;
	var res_option = document.querySelector('input[name="res_option"]:checked').value;
	
	if(reservefrm.reserve_Date.value == '') {
		alert('날짜를 선택해주세요.');
	} else if(reservefrm.reserve_TimeA.value == '') {
		alert('시간을 선택해주세요.');	
	} else if(pet_select.value == '0' && ((reservefrm.pet_name.value == '') || (reservefrm.pet_age.value == '') || (pet_scale == null))) {
		if(reservefrm.pet_name.value == '') {
			alert('반려동물의 이름을 입력하세요');
			reservefrm.pet_name.focus();
		} else if(reservefrm.pet_age.value == '') {
			alert('반려동물의 나이를 입력하세요');
			reservefrm.pet_age.focus();
		} else if(pet_scale == null) {
			alert('반려동물의 크기를 체크하세요');
			reservefrm.pet_age.focus();
		}
	} else if(pet_select.value == '1' && reservefrm.pet_NO.value == '') {
		alert('반려동물을 선택하세요');
		document.getElementById('pet_select_div').focus();
	} else {
		var str = '';

		if(pet_select.value == '0') {
			
			str += '예약 내역\n' + '이름 : ${member.member_name}\n'
					+ '반려동물 이름 : ' + reservefrm.pet_name.value + '\n'
					+ '반려동물 나이 : ' + reservefrm.pet_age.value + '\n'
					+ '반려동물 크기 : ' + pet_scale.value + '\n'
					+ '${shopVO.shop_name}' + '\n'
					+ '예약 날짜 : ' + reservefrm.reserve_Date.value + '\n'
					+ '예약 시간 : ' + reservefrm.reserve_TimeA.value + '\n'
					+ '미용 : ' + ((res_spot == '0') ? '전체' : '부분') + '\n'
					+ '목욕 : ' + ((res_option == '0') ? '선택' : '미선택') + '\n'
					+ '예약 하시겠습니까?';
		} else if(pet_select.value == '1') {
			var pet_NO = reservefrm.pet_NO.value;
			var select_pet_name = document.getElementById('pet_name_' + pet_NO).innerText;
			var select_pet_scale = document.getElementById('pet_scale_' + pet_NO).innerText;
			var select_pet_age = document.getElementById('pet_age_' + pet_NO).innerText;
			
			str += '예약 내역\n' + '이름 : ${member.member_name}\n'
					+ '반려동물 이름 : ' + select_pet_name + '\n'
					+ '반려동물 나이 : ' + select_pet_scale + '\n'
					+ '반려동물 크기 : ' + select_pet_age + '\n'
					+ '${shopVO.shop_name}' + '\n'
					+ '예약 날짜 : ' + reservefrm.reserve_Date.value + '\n'
					+ '예약 시간 : ' + reservefrm.reserve_TimeA.value + '\n'
					+ '미용 : ' + ((res_spot == '0') ? '전체' : '부분') + '\n'
					+ '목욕 : ' + ((res_option == '0') ? '선택' : '미선택') + '\n'
					+ '예약 하시겠습니까?';
		}
		
		if(confirm(str)) {
			reservefrm.action = "${contextPath}/reserve/reserveAction.do";
			reservefrm.submit();
		}

	}
}
	
</script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css" />
<style type="text/css">
body {
	height: 100%;
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
	height: 600px;
	padding-bottom: 80px;
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

.saturday {
	color: blue;
}

.monday {
	color: red;
}

/* 스크롤바 숨기기 */
::-webkit-scrollbar {
	display: none;
}

.calMonth {
	font-size: 20px;
	display: inline;
}

#bigDiv {
	padding: 0px 16px;
	width: 900px;
	display: flex;
	.
	element
	{
	height
	:
	100%;
}

}
#table-box {
	overflow-x: scroll;
	white-space: nowrap;
	width: 560px;
	display: inline-block;
	float: left;
	scroll-behavior: smooth;
	/* 드래그 막기 */
	-ms-user-select: none;
	-moz-user-select: -moz-none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	border: 1px solid black;
	-ms-user-select: none;
	scroll-behavior: smooth;
}

#table-box table {
	display: inline;
	width: 560px;
}

#table-box table tr td {
	padding: 0 0 0 15px;
}

#timeline {
	border: 1px solid black;
	width: 100px;
	float: left;
	margin: 0 1px;
	padding: 0px;
	text-align: center;
}

#timeline td {
	margin: 0px;
	padding: 0px;
	width: 100px;
	height: calc(( 100% - 40px)/11);
	text-align: center;
	border-collapse: collapse;
	border: 1px solid black;
}

#resContent {
	border: 1px solid black;
	width: 200px;
	float: left;
	display: inline;
	overflow: scroll;
}

.pet_tabe_selct {
	width: 15px;
	height: 15px;
}

.pet_scale_selct {
	width: 15px;
	height: 15px;
}

#select_pet_table {
	width: 200px;
	height: 180px;
	display: inline-block;
	overflow: scroll;
	scroll-behavior: smooth;
	
	/* 드래그 막기 */
	-ms-user-select: none;
	-moz-user-select: -moz-none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	-ms-user-select: none;
	scroll-behavior: smooth;
}

#select_pet_table table {
	white-space: nowrap;
}

#petlist_div {
	width: 200px;
	height: 180px;
	display: inline-block; 
	overflow : scroll;
	scroll-behavior: smooth;
	
	/* 드래그 막기 */
	-ms-user-select: none;
	-moz-user-select: -moz-none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	-ms-user-select: none;
	scroll-behavior: smooth;
	overflow: scroll;
}

.past_day {
	background-color: rgb(180, 180, 180);
	color: rgb(104, 104, 104);
}

.next_day {
	
}

#pet_select_div {
	width: 200px;
	height: 78px;
	display: inline-block;
	overflow: hidden;
	text-align: center;
}
</style>
</head>
<body>
	<header class="masthead">
	<div class="container">
		<div id="box1">

			<%
				java.util.Calendar cal1 = java.util.Calendar.getInstance(); //Calendar객체 cal생성
				java.util.Calendar cal2 = java.util.Calendar.getInstance(); //Calendar객체 cal생성
				int currentYear = cal1.get(java.util.Calendar.YEAR); //현재 날짜 기억
				int currentMonth = cal1.get(java.util.Calendar.MONTH);
				int currentDate = cal1.get(java.util.Calendar.DATE);
	
				String Year = request.getParameter("year"); //나타내고자 하는 날짜
				String Month = request.getParameter("month");
	
				int year, month;
				if (Year == null && Month == null) { //처음 호출했을 때
					year = currentYear;
					month = currentMonth;
				} else { //나타내고자 하는 날짜를 숫자로 변환
					year = Integer.parseInt(Year);
					month = Integer.parseInt(Month);
					if (month < 0) {
						month = 11;
						year = year - 1;
					} //1월부터 12월까지 범위 지정.
					if (month > 11) {
						month = 0;
						year = year + 1;
					}
				}
			%>

			<center>
				<table border=0 style="height: 50px;">
					<!-- 달력 상단 부분, 더 좋은 방법이 없을까? -->
					<tr>
						<td align=left width=160>&nbsp;</td>
						<td align=center width=240>
							<!-- 월 -->

							<p id='mon_<% out.print(month + 1); %>' class='calMonth' onclick="month_move(<% out.print(month + 1); %>)" style="color: black;">
								<% out.print(month + 1); %>월
							</p> &nbsp;&nbsp;

							<p id='mon_<% out.print(month + 2); %>' class='calMonth' onclick="month_move(<% out.print(month + 2); %>)">
								<% out.print(month + 2); %>월
							</p>
							
							<input type="hidden" id='month' value="<% out.print(month + 1); %>">
						</td>
						<td align=right width=160>
							<% out.print(currentYear + "-" + (currentMonth + 1) + "-" + currentDate); %>
						</td>
					</tr>

				</table>
				
				<div id="bigDiv">
					<div id='table-box' class='element'>
						<table>
							<!-- 달력 부분 -->
							<tr>
								<td width=80 class="monday">일</td>
								<!-- 일=1 -->
								<td width=80>월</td>
								<!-- 월=2 -->
								<td width=80>화</td>
								<!-- 화=3 -->
								<td width=80>수</td>
								<!-- 수=4 -->
								<td width=80>목</td>
								<!-- 목=5 -->
								<td width=80>금</td>
								<!-- 금=6 -->
								<td width=80 class="saturday">토</td>
								<!-- 토=7 -->
							</tr>

							<tr height=80>
								<%
								cal1.set(year, month, 1); //현재 날짜를 현재 월의 1일로 설정
								int startDay = cal1.get(java.util.Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
								int end = cal1.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
								int br = 0; //7일마다 줄 바꾸기

								for (int i = 0; i < (startDay - 1); i++) { //빈칸출력
									out.println("<td>&nbsp;</td>");

									br++;
									if ((br % 7) == 0) {
										out.println("<br>");
									}

								}

								for (int i = 1; i <= end; i++) { //날짜출력
									if(i < currentDate) {
										if (br % 7 == 0) {
											out.println("<td class='monday past_day'>" + i + "</td>");
										} else if (br % 7 == 6) {
											out.println("<td class='saturday past_day'>" + i + "</td>");
										} else {
											out.println("<td class='past_day'>" + i + "</td>");
										}
									} else {
										if (br % 7 == 0) {
											out.println("<td class='monday next_day' id='" + (month + 1) +"_" + i + "'>" + i + "</td>");
										} else if (br % 7 == 6) {
											out.println("<td class='saturday next_day' id='" + (month + 1) +"_" + i + "'>" + i + "</td>");
										} else {
											out.println("<td class='next_day' id='" + (month + 1) +"_" + i + "'>" + i + "</td>");
										}
									}

									br++;
									if ((br % 7) == 0 && i != end) {
										out.println("</tr><tr height=80>");
									}

								}

								while ((br++) % 7 != 0) //말일 이후 빈칸출력
									out.println("<td>&nbsp;</td>");
								%>
							</tr>
						</table>

						<table>
							<!-- 달력 부분 -->
							<tr>
								<td width=80 class="monday">일</td>
								<!-- 일=1 -->
								<td width=80>월</td>
								<!-- 월=2 -->
								<td width=80>화</td>
								<!-- 화=3 -->
								<td width=80>수</td>
								<!-- 수=4 -->
								<td width=80>목</td>
								<!-- 목=5 -->
								<td width=80>금</td>
								<!-- 금=6 -->
								<td width=80 class="saturday">토</td>
								<!-- 토=7 -->
							</tr>

							<tr height=80>
								<%
								if(month > 10) { // 12월(month == 11)이면 내년 1월로 
									cal2.set(year + 1, 0, 1); //현재 날짜를 현재 월의 1일로 설정
								} else {
									cal2.set(year, month + 1, 1); //현재 날짜를 현재 월의 1일로 설정
								}
								
								int startDay2 = cal2.get(java.util.Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
								int end2 = cal2.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
								int br2 = 0; //7일마다 줄 바꾸기

								for (int i = 0; i < (startDay2 - 1); i++) { //빈칸출력
									out.println("<td>&nbsp;</td>");

									br2++;
									if ((br2 % 7) == 0) {
										out.println("<br>");
									}

								}

								for (int i = 1; i <= end2; i++) { //날짜출력
									if (br2 % 7 == 0) {
										out.println("<td class='monday next_day' id='" + (month + 2) + "_" + i +"'>" + i + "</td>");
									} else if (br2 % 7 == 6) {
										out.println("<td class='saturday next_day' id='" + (month + 2) + "_" + i +"'>" + i + "</td>");
									} else {
										out.println("<td class='next_day' id='" + (month + 2) + "_" + i + "'>" + i + "</td>");
									}

									br2++;
									if ((br2 % 7) == 0 && i != end) {
										out.println("</tr><tr height=80>");
									}

								}

								while ((br2++) % 7 != 0) //말일 이후 빈칸출력
									out.println("<td>&nbsp;</td>");
								%>
							</tr>
						</table>

					</div>
					<!-- table-box -->

					<table id="timeline" class='element'>
						<tr>
							<td style="height: 40px; padding: 5px 0 0 0;">
								<h5>예약 시간</h5>
							</td>
						</tr>

						<%
							for (int i = 10; i < 21; i++) {
								out.println("<tr><td id='time_" + i + ":00' class=''>" + i + ":00</td></tr>");
							}
						%>
					</table>
					<!-- timeline -->

					<div id="resContent" class='element'>
						<form id="" name='reserve' method="post">
							<table style="width: 100%; margin: 20px 0 0 0; padding: 0px; height: 13%">
								<tr>
									<td colspan="4" style="height: 40px; text-align: center;">
										<h5 style="margin: 5px 0 0 0;">예약 내역</h5>
									</td>
								</tr>
								
								<tr>

									<c:choose>
										<c:when test="${empty petList}">
											<td colspan="4" style="text-align: center;">
												<input type="radio" class="pet_tabe_selct" name="pet_select" value="0" checked="checked">
											</td>
										</c:when>
										
										<c:otherwise>
											<td style="width: 20%;">&nbsp;</td>
											<td style="width: 30%; text-align: center;">
												<input type="radio" class="pet_tabe_selct" name="pet_select" value="0" checked="checked" onclick="pet_moveScroll(0, -195)">
											</td>
											<td style="width: 30%; text-align: center;">
												<input type="radio" class="pet_tabe_selct" name="pet_select" value="1" onclick="pet_moveScroll(0, 195)">
											</td>
											<td style="width: 20%;">&nbsp;</td>
										</c:otherwise>
									</c:choose>
									
								</tr>
							</table>
							
							<div id="select_pet_table">
								<!-- 반려동물 입력, 선택 -->
								<table style="width: 200px; height: 180px; display: inline-block; white-space: nowrap;">
									<!-- 반려동물 정보 입력 -->
									<tr>
										<td colspan="2" style="text-align: center; height: 40px;">
											<span style="font-size: 18px;">[반려동물 정보 입력]</span>
										</td>
									</tr>
									<tr>
										<td style="width: 60px; height: 20px; text-align: center;">이름</td>
										<td>
											<input type="text" id="pet_name" name="pet_name" style="width: 130px; height: 20px; text-align: center; border: none 0px;" onchange="input_pet()">
										</td>
									</tr>
									<tr>
										<td style="text-align: center;">나이</td>
										<td style="text-align: center; width: 120px; height: 20px;">
											<input type="text" id="pet_age" name="pet_age" style="width: 120px; height: 20px; text-align: center; border: none 0px;" onchange="input_pet()">살
										</td>
									</tr>
									<tr>
										<td rowspan="3" style="text-align: center; width: 60px; height: 60px;">크기</td>
										<td style="text-align: center; width: 120px; height: 20px;">
											<input type="radio" class="pet_scale_selct" name="pet_scale" value="소형" onclick="input_pet()">
											&nbsp; <span style="vertical-align: 3.5px;">소형</span>
										</td>
									</tr>
									<tr>
										<td style="text-align: center; width: 120px; height: 20px;">
											<input type="radio" class="pet_scale_selct" name="pet_scale" value="중형" onclick="input_pet()">
											&nbsp; <span style="vertical-align: 3.5px;">중형</span>
										</td>
									</tr>
									<tr>
										<td style="text-align: center; width: 120px; height: 20px;">
											<input type="radio" class="pet_scale_selct" name="pet_scale" value="대형" onclick="input_pet()">
											&nbsp; <span style="vertical-align: 3.5px;">대형</span>
										</td>
									</tr>
								</table>
								
								<div id="petlist_div">
									<table style="width: 200px; height: 20px; display: block; text-align: center;  margin: 0 0 30px 0;">
										<!-- 반려동물 선택 -->
										<tr>
											<td colspan="2" style="text-align: center; width: 200px;">
												<span>[반려동물 선택하기]</span>
											</td>
										</tr>
									</table>
									
									<div id="pet_select_div">
										<c:choose>
											<c:when test="${not empty petList}">
												<c:forEach var="pet" items="${petList}">
													<table style="width: 200px; display: inline;">
														<tr>
															<td rowspan="3" style="width: 100px; height: 78px; padding: 0; overflow: hidden;">
																<c:choose>
																	<c:when test="${not empty pet.pet_image}">
																		<img src="${contextPath}/downloadPet.do?pet_NO=${pet.pet_NO}&pet_image=${pet.pet_image}" id="pet_previewNon${pet.pet_NO}" style="max-height: 78px; max-width: 100px;"/>
																	</c:when>
																	<c:otherwise>
																		<img src="${contextPath}/resources/assets/img/pet_image.png" id="pet_preview${pet.pet_NO}" style="max-height: 78px; max-width: 100px;"/>
																	</c:otherwise>
																</c:choose>
															</td>
															
															<td style="width: 100px;">
																<span id="pet_name_${pet.pet_NO}" style="text-align: center;">${pet.pet_name}</span>
															</td>
															
															<td rowspan="3" style="height: 72px;">
																<div style="width: 45px; height: 30px; text-align: center; padding: 0 2px 0 0; margin: auto; font-weight: bold;" onclick="select_pet(${pet.pet_NO})">선택</div>
															</td>
														</tr>
														
														<tr>
															<td>
																<span id="pet_age_${pet.pet_NO}">${pet.pet_age}살</span>
															</td>
														</tr>
														
														<tr>
															<td>
																<span id="pet_scale_${pet.pet_NO}">${pet.pet_scale}</span>
															</td>
														</tr>
													</table>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
									<!-- pet_select_div -->
									
									<table style="width: 200px; height: 30px; text-align: center;">
										<tr>
											<td>
												<div style="width: 95px" onclick="pet_listScroll(0, -78)">◀</div>
											</td>
											<td>
												<div style="width: 95px" onclick="pet_listScroll(0, 78)">▶</div>
											</td>
										</tr>
									</table>
								</div>
								<!-- petlist_div -->
							</div>
							<!-- select_pet_table -->
							<table style="height: 35%;">
								<tr>
									<td style="text-align: center; width: 60px;">미용</td>
									<td colspan="3">
										<input type="radio" class="pet_tabe_selct" name="res_spot" value="0" checked="checked">&nbsp;
										<span style="vertical-align: 3.5px;">전체</span>
										&nbsp;&nbsp; 
										<input type="radio" class="pet_tabe_selct" name="res_spot" value="1">&nbsp;
										<span style="vertical-align: 3.5px;">부분</span>
									</td>
								</tr>
								
								<tr>
									<td style="text-align: center; width: 60px;">목욕</td>
									<td colspan="3">
										<input type="radio" class="pet_tabe_selct" name="res_option" value="0" checked="checked">&nbsp;
										<span style="vertical-align: 3.5px; font-size: 12px;">선택</span>
										&nbsp;&nbsp; 
										<input type="radio" class="pet_tabe_selct" name="res_option" value="1">&nbsp;
										<span style="vertical-align: 3.5px; font-size: 12px;">미선택</span>
									</td>
								</tr>
								
								<tr>
									<td colspan="4" style="text-align: center; height: 20%;">
										<h4 style="font-size: 20px; margin: 0;">${shopVO.shop_name}</h4>
										<input type="hidden" name="shop_NO" value="${shopVO.shop_NO}">
									</td>
								</tr>
								
								<tr>
									<td colspan="4" style="text-align: center; height: 20%;">
										<span id="pet_info" style="font-size: 18px; margin: 0;">&nbsp;</span>
										<input type="hidden" name="pet_NO" value="">
									</td>
								</tr>
								
								<tr>
									<td colspan="4" style="text-align: center; height: 40%;">
										<h4 style="font-size: 16px; display: inline;">예약 날짜 : &nbsp;</h4>
										<h4 id='reserve_Date' style="font-size: 16px; display: inline;"></h4>
										<input type="hidden" name="reserve_Date" value="">
									</td>
								</tr>
								
								<tr>
									<td colspan="4" style="text-align: center; height: 30%; margin: 0 0 3px 0; ">
										<h4 style="font-size: 16px; display: inline;">예약 시간 : &nbsp;</h4>
										<h4 id='reserve_TimeA' style="font-size: 16px; display: inline;"></h4>
										<input type="hidden" name="reserve_TimeA" value="">
									</td>
								</tr>
								
								<tr>
									<td colspan="4" style="text-align: center;">
										<button type="button" style="width: 80px; height: 30px;">취소하기</button>
										<button type="button" style="width: 80px; height: 30px;" onclick="sumit_action()">예약하기</button>
									</td>
								</tr>
							</table>
						</form>
					</div><!-- resContent -->
				</div><!-- bigDiv -->
			</center>
		</div>	<!-- box1 -->
	</div>	<!-- container -->
	</header> <!-- masthead -->
</body>
</html>