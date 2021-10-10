<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#box1 {
	width: 80%;
	height: 2000px;
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

.title {
	border-bottom: 3px solid gray;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
window.onload = function() {	
	

	<c:forEach var="pet" items="${petList}">
		var p_btn_mod = '#modPet' + ${pet.pet_NO};
		var p_btn_del = '#delPet' + ${pet.pet_NO};
		var p_btn_Ok = '#modPetCheck' + ${pet.pet_NO};
		var p_btn_can = '#cancle' + ${pet.pet_NO};
		var p_btn_file = '#file' + ${pet.pet_NO};
		var p_origin_file = '#originFile' + ${pet.pet_NO};
		var p_previewNon='#pet_previewNon' +${pet.pet_NO};
		$(p_btn_mod).show();
		$(p_btn_del).show();
    	$(p_btn_Ok).hide();
    	$(p_btn_can).hide();
    	$(p_btn_file).hide();
    	$(p_previewNon).hide();
    	
		var pscale1 = '#pet_scale' + ${pet.pet_NO } + '1';
		var pscale2 = '#pet_scale' + ${pet.pet_NO } + '2';
		var pscale3 = '#pet_scale' + ${pet.pet_NO } + '3';

		$(pscale1).attr('disabled', 'true');
		$(pscale2).attr('disabled', 'true');
		$(pscale3).attr('disabled', 'true');

		
		if('${pet.pet_scale}' == '소형'){
			$(pscale1).prop('checked', true);
		} else if('${pet.pet_scale}' =='중형'){
			$(pscale2).prop('checked', true);
		} else if('${pet.pet_scale}' =='대형'){
			$(pscale3).prop('checked', true);
		}

		var pname = "#pet_name" + ${pet.pet_NO };
		var page = "#pet_age" + ${pet.pet_NO };

		
		$(pname).attr('readOnly', true);

		$(page).attr('readOnly', true);
	</c:forEach>
	};
	
/* 	if($("#petMod").click{
		$("#petModCheck").attr("type","submit");
		$("#petMod").attr("type","hidden");
	} */
	function petMod(pet_NO) {

		var pname = "#pet_name" + pet_NO;
		var page = "#pet_age" + pet_NO;
		
		$(pname).attr('readonly', false);

		$(page).attr('readonly', false);
		
		var pscale1 = '#pet_scale' + pet_NO + '1';
		var pscale2 = '#pet_scale' + pet_NO + '2';
		var pscale3 = '#pet_scale' + pet_NO + '3';
	
		$(pscale1).attr('disabled', false);
		$(pscale2).attr('disabled', false);
		$(pscale3).attr('disabled', false);
		
		var p_btn_mod = '#modPet' + pet_NO;
		var p_btn_del = '#delPet' + pet_NO;
		var p_btn_Ok = '#modPetCheck' + pet_NO;
		var p_btn_can = '#cancle' + pet_NO;
		var p_btn_file = '#file' + pet_NO;
		var p_origin_file = 'originFile' + pet_NO;
				
		$(p_btn_mod).hide();
		$(p_btn_del).hide();
	    $(p_btn_Ok).show();
	    $(p_btn_can).show();
	    $(p_btn_file).show();
	};
	
	function readURL(input, pet_NO) {
	    if (input.files && input.files[0]) {
		      var reader = new FileReader();
		      
		      //동물 이미지를 가져오면 커버 사진 숨김
		      var p_cover ="#pet_cover" + pet_NO;
		      $(p_cover).hide();
		      
		      //가져온 동물 이미지 보여줌
		      var p_previewNon='#pet_previewNon' +pet_NO;
		      $(p_previewNon).show();
		      
		      var p_preview = '#pet_preview' + pet_NO;
		      
		      reader.onload = function (e) {
		        $(p_preview).attr('src', e.target.result);
		        $(p_previewNon).attr('src', e.target.result);
		       
	        }
	       reader.readAsDataURL(input.files[0]);
	    }
	};
	
	function delPet(pet_NO) {
 	
		if(!confirm("반려동물 정보를 삭제하시겠습니까?")){
			alert("취소되었습니다.");
		} else {
			
			location.href="${pageContext.request.contextPath}/mypage/removePet.do?pet_NO="+pet_NO;
			alert("삭제되었습니다.");
		}
	}; 
</script>
<c:choose>

	<c:when test="${result == 'pwdChange'}">
	  <script>
	  window.onload=function(){
	      alert("비밀번호가 수정되었습니다.");
	    }
	  </script>
	</c:when>
	
</c:choose>
<title>마이페이지</title>
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
				
				<form method="post" name="modPet" action="${contextPath}/mypage/updatePet.do" enctype="multipart/form-data">
					<table>
						<tr>
							<td class="title" colspan="4">
								<p>반려동물</p> <a
								href="${pageContext.request.contextPath}/mypage/petAdd.do">
									<input type="button" value="등록하기" class="button">
							</a>
							</td>
						</tr>

						<tr>
							<td><br></td>
						</tr>


						<c:choose>
							<c:when test="${empty petList}">
								<tr>
									<td>등록된 반려동물이 없습니다!</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="pet" items="${petList}">
									<tr>
										<c:if test="${pet.pet_image == null }">
											<td rowspan="3" id="petimage">
												<img src="${pageContext.request.contextPath}/resources/assets/img/pet_image.png" id="pet_cover${pet.pet_NO }">
												<img src="${contextPath}/downloadPet.do?pet_NO=${pet.pet_NO}&pet_image=${pet.pet_image}" id="pet_previewNon${pet.pet_NO }" width="120px" />
												<input type="file" id="file${pet.pet_NO }" name="pet_image" onchange="readURL(this,${pet.pet_NO });">
											</td>
										</c:if>
										
										<c:if test="${pet.pet_image != null }">
											<td rowspan="3" id="petimage">
												<img src="${contextPath}/downloadPet.do?pet_NO=${pet.pet_NO}&pet_image=${pet.pet_image}" id="pet_preview${pet.pet_NO }" width="120px" />
												<input type="file" id="file${pet.pet_NO }" name="pet_image" onchange="readURL(this,${pet.pet_NO });">
												<input type="hidden" name="originalFileName" value="${pet.pet_image }" id="originFile${pet.pet_NO }" />
											</td>
										</c:if>
										
										
										
										<td>이름 :</td>
										<td>
											<input type="text" name="pet_name" id="pet_name${pet.pet_NO }" value="${pet.pet_name }" style="border: none;"/>
											<input type="hidden" name="pet_NO" value="${pet.pet_NO }" readonly>
											<input type="hidden" name="member_NO" value="${member.member_NO }" readonly>
										</td>
									</tr>

									<tr>
										<td>나이 :</td>
										<td>
											<input type="number" name="pet_age" id="pet_age${pet.pet_NO }" value="${pet.pet_age }" style="width: 50px; border: none;">살
										</td>
									</tr>

									<tr>
										<td>크기 :</td>
										<td>
											<input type="radio" name="pet_scale${pet.pet_NO }" id="pet_scale${pet.pet_NO }1" value="소형">
											소형&nbsp;&nbsp;&nbsp;

											<input type="radio" name="pet_scale${pet.pet_NO }" id="pet_scale${pet.pet_NO }2" value="중형">
											중형&nbsp;&nbsp;&nbsp;

											<input type="radio" name="pet_scale${pet.pet_NO }" id="pet_scale${pet.pet_NO }3" value="대형">
											대형
										</td>

										<td>
											<input type="button" value="수정" id="modPet${pet.pet_NO }" onclick="petMod(${pet.pet_NO})">
									<!-- 	<a href="${contextPath }/mypage/removePet.do?pet_NO=${pet.pet_NO}"> --!>
									<!-- 	</a> -->
											<input type="button" value="삭제" id="delPet${pet.pet_NO }" onclick="delPet(${pet.pet_NO})">
											<input type="submit" value="확인" id="modPetCheck${pet.pet_NO }">
											<a href="${pageContext.request.contextPath}/mypage/myPage.do">
												<input type="button" value="취소" id="cancle${pet.pet_NO }">
											</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</form>

				<br> <br>
				<table>
					<tr>
						<td class="title" colspan="6"><h2>최근 예약 내역</h2></td>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="td" width="7%">번호</td>
						<td class="td" width="21%">매장명</td>
						<td class="td" width="18%">예약시간</td>
						<td class="td" width="21%">예약 날짜</td>
						<td class="td" width="21%">반려견 이름</td>
						<td class="td" width="18%">취소하기</td>
					</tr>
					<c:forEach var="i" begin="1" end="3">
						<tr>
							<td class="td" width="7%">1</td>
							<td class="td" width="21%">A 매장</td>
							<td class="td" width="18%">17시 30분</td>
							<td class="td" width="21%">09.23.21</td>
							<td class="td" width="21%">홍뽀삐</td>
							<td class="td" width="18%"><input type="button" value="취소하기"></td>
						</tr>
					</c:forEach>
				</table>
				<br> <br>
				<table>
					<tr>
						<td class="title" colspan="7"><h2>관심매장</h2></td>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="td" width="30%">사진</td>
						<td class="td" width="21%">매장명</td>
						<td class="td" width="18%">매장 위치</td>
						<td class="td" width="18%">매장 운영 시간</td>
						<td class="td" width="7%">별점</td>
						<td class="td" width="10%">관심</td>
						<td class="td" width="21%">예약하기</td>
					</tr>
					<c:forEach var="i" begin="1" end="3">
						<tr>
							<td class="td" width="30%"><img
								src="${pageContext.request.contextPath}/resources/assets/img/dog4.png"></td>
							<td class="td" width="21%">A 매장</td>
							<td class="td" width="18%">경기도 남양주시</td>
							<td class="td" width="18%">11:00~18:00</td>
							<td class="td" width="7%">5</td>
							<td class="td" width="10%" style="font-size: 30px;">♥</td>
							<td class="td" width="21%"><input type="button" value="예약하기"></td>
						</tr>
					</c:forEach>
				</table>
				<br> <br>
				<table>
					<tr>
						<td class="title" colspan="5"><p>내가 쓴 리뷰</p> <a
							href="${pageContext.request.contextPath}/mypage/review.do"> <input
								type="button" value="더보기" class="button1"></a></td>
					<tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="td" width="7%">번호</td>
						<td class="td" width="21%">매장명</td>
						<td class="td" width="30%">리뷰 내용</td>
						<td class="td" width="18%">반려견 이름</td>
						<td class="td" width="21%">작성날짜</td>

					</tr>
					<c:forEach var="i" begin="1" end="3">
						<tr>
							<td class="td">1</td>
							<td class="td">A매장</td>
							<td class="td">넘 좋았어요</td>
							<td class="td">홍뽀삐</td>
							<td class="td">09.23.21</td>
						</tr>
					</c:forEach>
				</table>
				<br> <br>
				<table>
					<tr>
						<td class="title" colspan="5"><p>방문 내역</p> <a
							href="${pageContext.request.contextPath}/mypage/visit.do"> <input
								type="button" value="더보기" class="button"></a></td>
					<tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="td" width="7%">번호</td>
						<td class="td" width="21%">매장명</td>
						<td class="td" width="21%">반려견 이름</td>
						<td class="td" width="21%">방문 날짜</td>
						<td class="td" width="25%">리뷰쓰기</td>

					</tr>
					<c:forEach var="i" begin="1" end="3">
						<tr>
							<td class="td">1</td>
							<td class="td">A매장</td>
							<td class="td">홍뽀삐</td>
							<td class="td">09.23.21</td>
							<td class="td"><a
								href="${pageContext.request.contextPath}/mypage/reviewWrite.do">
									<input type="button" value="리뷰쓰기">
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</header>
</body>
</html>