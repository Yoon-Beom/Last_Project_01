<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>예약 하기</title>
<script type="text/javascript">

window.onload = function() {
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
	
}

function moveScroll(x, y) {
	var table = document.getElementById('table-box');
	table.scrollBy(x, y);
};

function pet_moveScroll(x, y) {
	var select_pet_table = document.getElementById('select_pet_table');
	console.log(select_pet_table);
	select_pet_table.scrollBy(x, y);
};

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

function select_day(e) {
	delete_color_day();
	var mon = document.getElementById('month').value;
	
	this.style.backgroundColor = '#f0b3c0';
	this.style.color = 'white';
	this.style.border = '3px solid #f17791';
	document.getElementById('reserve_Date').innerText = <%= yy %> + '-' + mon + '-' + this.innerText;
	document.getElementsByName('reserve_Date').value = <%= yy %> + '-' + mon + '-' + this.innerText;
}

function delete_color_day() {
	var str = document.getElementsByName('reserve_Date').value;
	if(str != null) {
		var date = str.split('-');
		var pastDay = document.getElementById(date[1] + '_' + date[2]);

		var className = pastDay.className;
		var cName = className.split(' ');
		
		pastDay.style.border = '';
		
		if(cName[0] == 'saturday') {
			pastDay.style.color = 'blue';
		} else if (cName[0] == 'saturday') {
			pastDay.style.color = 'red';
		} else {
			pastDay.style.color = 'gray';
		}
		pastDay.style.backgroundColor = '';
	}
}

function select_time(e) {
	delete_time();
	
	this.style.backgroundColor = '#f0b3c0';
	this.style.color = 'white';
	this.style.border = '3px solid #f17791';
	document.getElementById('reserve_TimeA').innerText = this.innerText;
	document.getElementsByName('reserve_TimeA').value = this.innerText;
}

function delete_time() {
	var str = document.getElementsByName('reserve_TimeA').value;
	if(str != null) {
		document.getElementById('time_' + str).style.backgroundColor = '';
		document.getElementById('time_' + str).style.color = 'gray';
		document.getElementById('time_' + str).style.border = '';
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
	
	.element {
		height: 100%;
	}
}

#table-box {
	overflow-x: scroll;
	white-space: nowrap;
	width: 560px;
	
	display: inline-block;
	float: left; 
	scroll-behavior : smooth;
	
	/* 드래그 막기 */
	-ms-user-select : none;
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
	height: calc( ( 100% - 40px ) / 11 );
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

#select_pet_table {
	width: 200px;
	height: 250px;
	display: inline-block;
	overflow: scroll;
	
	/* 드래그 막기 */
	-ms-user-select : none;
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

.past_day {
	background-color: rgb(180, 180, 180);
	color: rgb(104, 104, 104);
}

.next_day {
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
							out.println("<tr><td id='time_" + i + ":00'>" + i + ":00</td></tr>");
						}
						%>
					</table>
					<!-- timeline -->

					<div id="resContent" class='element'>
						<form name='reserve' method="post">
							<table style="width: 100%; margin: 10px 0 0 0; padding: 0px; height: 13%">
								<tr>
									<td colspan="4" style="height: 40px; text-align: center;">
										<h5 style="margin: 5px 0 0 0;">예약 내역</h5>
									</td>
								</tr>
								
								<tr>
									<td style="width:20%;">&nbsp;</td>
									<td style="width:30%; text-align: center;">
										<input type="radio" class="pet_tabe_selct" name="pet_select" value="0" checked="checked" onclick="pet_moveScroll(0, -250)">
									</td>
									<td style="width:30%; text-align: center;">
										<input type="radio" class="pet_tabe_selct" name="pet_select" value="1" onclick="pet_moveScroll(0, 250)">
									</td>
									<td style="width:20%;">&nbsp;</td>
								</tr>
							</table>
								
							
							<div id="select_pet_table"><!-- 반려동물 입력, 선택 -->
								<table style="width: 200px; height: 250px; display: inline-block; white-space:nowrap;"> <!-- 반려동물 정보 입력 -->
									<tr>
										<td colspan="2" style="text-align: center; height: 40px;">
											<span style="font-size: 18px;">[반려동물 정보 입력]</span>
										</td>
									</tr>
									
									<tr>
										<td style="width: 60px; height: 32px; text-align: center;">이름</td>
										<td>
											<input type="text" name="pet_name" style="width: 130px; height: 30px; text-align: center;">
										</td>
									</tr>
									
									<tr>
										<td rowspan="3" style="text-align: center; width: 60px; height: 120px;">크기</td>
										<td style="text-align: center; width: 120px; height: 40px;">
											<input type="radio" class="pet_tabe_selct" name="pet_scale" value="소형">
											&nbsp;
											<span style="vertical-align: 3.5px;">소형</span>
										</td>
									</tr>
									
									<tr>
										<td style="text-align: center; width: 120px; height: 40px;">
											<input type="radio" class="pet_tabe_selct" name="pet_scale" value="중형">
											&nbsp; 
											<span style="vertical-align: 3.5px;">중형</span>
										</td>
									</tr>
									
									<tr>
										<td style="text-align: center; width: 120px; height: 40px;">
											<input type="radio" class="pet_tabe_selct" name="pet_scale" value="대형">
											&nbsp;
											<span style="vertical-align: 3.5px;">대형</span>
										</td>
									</tr>
								</table>
								
								<table style="width: 200px; height: 250px; display: inline-block; text-align: center;"> <!-- 반려동물 선택 -->
									<tr>
										<td colspan="2" style="text-align: center; width: 200px;">
											<span>[반려동물 선택하기]</span>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											&nbsp;
										</td>
									</tr>
									
									<tr>
										<td>
											<button> ◀ </button>	
										</td>
										<td>
											<button> ▶ </button>
										</td>
									</tr>
								</table>
							</div>
				
							
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
									<td colspan="4" style="text-align: center; height: 40%; padding: 10% 0 0 0;">
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
										<button type="button" style="width: 80px; height: 30px;">예약하기</button>
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