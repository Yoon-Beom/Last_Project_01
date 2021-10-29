<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*, java.text.*, java.io.*"%>
<%@ page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내주변</title>
<style>
#box1 {
	width: 80%;
	height: 500px;
	margin: auto;
	margin-top: 100px;
	background-color: white;
}

#shopTable .shopInfo {
	background-color: white;
}

table tr td {
	background-color: #FFEAEA;
	border: 1px solid #FFFFFF;
}

tabe td {
	
}

#table-box {
	overflow-x: scroll;
	white-space: nowrap;
	width: 608px;
	margin: auto;
	scroll-behavior: smooth;
	/* 드래그 막기 */
	-ms-user-select: none;
	-moz-user-select: -moz-none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	user-select: none;
}

#table-box table {
	display: inline;
	/*    width: 500px;
   text-overflow: ellipsis; */
}

/* 스크롤바 숨기기 */
::-webkit-scrollbar {
	display: none;
}

/* #test-table table:nth-child(odd) {
  float:left;
} */

/* #table-box:after {
  content: '';
  display: block;
  clear: left;
} */
li {
	list-style: none;
}
</style>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/styles.css" />
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
   
   window.onload = function(){
	   
   }

   // 가로스크롤 이동 함수 (이동할 거리만큼의 매개변수는 버튼에서 함수 호출 시 지정)
   function moveScroll(x, y) {
      var table = document.getElementById('table-box');
       table.scrollBy(x, y);
   };
   
/* function calDis(){
   var test = map.getCenter().toString().slice(0, -1).slice(1),
   split1 = test.split(', '),
   lat1 = split1[0],
   lon1 = split1[1],      
   lat2 = ${shopList[0].shop_latitude},
   lon2 = ${shopList[0].shop_longitude};
   
   var testtest = calculateDistance(lat1, lon1, lat2, lon2);

   //document.getElementById('test2').value = '나와의 거리 : '+Math.round(testtest*1000)+'M';
   document.getElementById('test').value = '레몬이네그루밍샵과의 거리는 '+Math.round(testtest*1000)+'M입니다';
   /* alert('lat : ' + lat1 + '\nlon : ' + lon1);
   const arr = [];
   <c:forEach var="shop" items="${shopList}">
      arr.push(${shop.shop_NO});
   </c:forEach>
   alert(arr); */
   
         
   /* alert(${shopList[0].shop_latitude}+'\n'+${shopList[0].shop_longitude}+'\n${shopList[0].shop_name}');

} */

   // 두 좌표간의 거리 계산 함수
   function calculateDistance(lat1, lon1, lat2, lon2) {
      //Radius of the earth in:  1.609344 miles,  6371 km  | var R = (6371 / 1.609344);
      var R = 6371; 
      var dLat = toRad(lat2-lat1);
      var dLon = toRad(lon2-lon1); 
      var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
      Math.sin(dLon/2) * Math.sin(dLon/2); 
      var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
      var d = R * c;
      return d;
   }

   function toRad(Value) {
      return Value * Math.PI / 180;
   }

   // 현 위치에서 재검색 버튼 함수
   function reSearchShop(){ 
      // 내 위치의 위도, 경도 
      var myLC = map.getCenter().toString().slice(0,-1).slice(1).split(', '),
      myLCLat = myLC[0],
      myLCLon   = myLC[1];
         
      // 밑에 forEach 문의 조건을 만족할 시 push 할 데이터들의 배열
      const arr = [];
      <c:forEach var="shop" items="${shopList}">
         // 현재 내 위치와 매장간의 거리를 담을 객체
         var calDis = calculateDistance(myLCLat,myLCLon,${shop.shop_latitude},${shop.shop_longitude});
         //
         if(calDis < 1.0) {
            var mCal = Math.round(calDis * 1000);
            document.getElementById(${shop.shop_NO}+'List').innerText='거리 : ' + mCal +'m';
         } else {
            document.getElementById(${shop.shop_NO}+'List').innerText='거리 : ' + calDis.toFixed(1) +'km';
         }
         
         // 만약 거리가 5키로 이내일 경우,
         if(calDis <= 5.0) {
            // 배열에 담습니다   ':' <- 구분자
            arr.push('${shop.shop_NO} : ${shop.shop_name} : ${shop.shop_latitude} : ${shop.shop_longitude} : ${shop.shop_address} : ${shop.shopDetailVO.shop_imageMain} : '+calDis);
            // 추천 매장 테이블 정보 변경
            
         }
      </c:forEach>
       
      // 만약 배열이 비어있다면,
      if(arr.length === 0){
         
         alert('5km 이내에 매장이 없습니다!');
         
      // 만약 5키로 이내의 매장이 하나밖에 없다면,
      } else if(arr.length === 1){
          
          
         // split은 문자열을 배열로 전환시켜주기 때문에 join함수를 이용해 문자열로 바꾼 후 다시 구분자를 통해 나눕니다
         var arrS = arr.join().split(' : ');
             
         var no = arrS[0], 
         name = arrS[1], 
         lat = arrS[2], 
         lon = arrS[3],
         address = arrS[4];
         image = arrS[5];
             
         alert('5km 이내에 있는 매장은 "' + name +'" 밖에 없습니다.\n매장 위치로 이동합니다.');
         // search함수를 재사용합니다
         search(no+'a!d#$D'+lat+'a!d#$D'+lon+'a!d#$D'+name+'a!d#$D'+address+'a!d#$D'+image);
      // 만약 매장이 여러 곳 이라면?     
      } else {
         // 재검색 버튼 숨기기
         document.getElementById('reSearch').style.display = 'none';
         
         // (메뉴 컨테이너, 매장이 뜰 목록창, 페이지 넘버요소 객체, 자식객체를 받아둘 임시 컨테이너 객체 선언)                                  
         var menuEl = document.getElementById('menu_wrap'),       
         listEl = document.getElementById('placesList'),        
         paginationEl = document.getElementById('pagination'),        
         fragment = document.createDocumentFragment(),
         bounds = new kakao.maps.LatLngBounds(), listStr = '';
         
         // search 함수 호출 시 이전 정보들을 제거하는 작업들
         // 검색란 비우기, 페이지번호 삭제, 검색목록에 추가된 항목들 제거, 인포윈도우 제거, 마커 제거
         var keyword = document.getElementById('keyword');
         keyword.value = '';
         
         while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.lastChild);
         }
         
         removeAllChildNods(listEl);
         removeWindows();
         removeMarker();
         
         
         
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 중요 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ      
         for (var i = 0; i < arr.length; i++) {
                // 마커를 생성하고 지도에 표시합니다
                var location = arr[i].split(' : ');//경로1
                var placePosition = new kakao.maps.LatLng(location[2], location[3]), 
                   marker = addMarker(placePosition, i),                   
                   itemEl = getListItem2(i,location[1],location[4],location[5]);
                // 검색 결과 항목 Element를 생성합니다
                         
                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                bounds.extend(placePosition);
                         
                // 마커와 검색결과 항목에 mouseover 했을때
                // 해당 장소에 인포윈도우에 장소명을 표시합니다
                // mouseout 했을 때는 인포윈도우를 닫습니다
                (function(marker, title) {
                   kakao.maps.event.addListener(marker,'mouseover', function() {
                      displayInfowindow(marker, title);
                   });
                   
                   kakao.maps.event.addListener(marker,'mouseout', function() {
                      infowindow.close();
                   });
                   
                   itemEl.onmouseover = function() {
                      displayInfowindow(marker, title);
                   };
                   
                   itemEl.onmouseout = function() {
                      infowindow.close();
                   };
                
                })
                
                (marker, location[1]);
                
                fragment.appendChild(itemEl);
             } 

             // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
             listEl.appendChild(fragment);
             menuEl.scrollTop = 0;
          
             // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
             map.setBounds(bounds);
             
      }
     
   }
   
   // 검색결과 항목을 Element로 반환하는 함수입니다
    function getListItem2(index, name, address, no, image) {
    
       var el = document.createElement('li'), 
       itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>'
         + '<br><div class="info1">'
         + '   <h3>' + name + '</h3><br>';
       if (shopImg) {
         itemStr += '<div class="shopImg"><img src="${contextPath}/downloadShopDetail.do?shopDetail_NO=' + shop_NO + '&shop_imageMain=' + shopImg + ' style="max-width: 100px; height: 100px;"/></div><br>'
       } else {
           itemStr += '<div class="shopImg"><img src="${contextPath}/resources/assets/img/dog2.png" style="max-width: 100px; height: 100px;"/></div><br>'
       }
       
       itemStr += '   <span>' + address + '</span><br>'
         + '<a href="${contextPath}/shop/shopDetail.do?shop_NO='+no+'"><button class="btn btn-primary">상세페이지</button></a>'
         + '<a href="${contextPath}/reserve/reserve.do?shop_NO='+shop_NO+'"><button class="btn btn-primary">예약</button></a>'
         + '</div><br>';  
       
       el.innerHTML = itemStr;
       el.className = 'item';
       
       return el;
    }
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
   
   /* function changeTest(){
      document.getElementById(${shopList[0].shop_NO}+'List').innerText='제발 돼라';
   } */ 

   
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b47d4f2e1f1f2f33a525416b1d6042d0&libraries=services"></script>
<body>
	<header class="masthead">
		<div class="container">
			<br> <br>
			<h2>추천 매장</h2>
			<div id='table-box'>
				<c:forEach var="shop" items="${shopList}">
					<table id="${shop.shop_NO}" class="scroll" style="padding: 0px;">
						<tr>
							<td>${shop.shop_name}</td>
						</tr>
						<c:choose>
							<c:when test="${not empty shop.shopDetailVO.shop_imageMain && shop.shopDetailVO.shop_imageMain!='null' }">
								<tr id="tr_file_upload">
									<td>
										<img
											src="${contextPath}/downloadShopDetail.do?shopDetail_NO=${shop.shopDetailVO.shopDetail_NO}&shop_imageMain=${shop.shopDetailVO.shop_imageMain }"
											style="max-width: 290px; max-height: 100px;"
										/>
										<%-- <img src="${contextPath}/download.do?board_NO=${shop.shop_NO}&board_image=${shop.shopDetailVO.shop_imageMain }" id="preview" width="300" height="200px" />    --%>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr id="tr_file_upload">
									<td>
										<img src="${contextPath}/resources/assets/img/dog2.png" width="120px" />
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
						<tr>
							<td style="width: 300px;">${fn:split(shop.shop_address,',')[0]}<br>${fn:split(shop.shop_address,',')[1]}<br>${fn:split(shop.shop_address,',')[2]}</td>
						</tr>
						<tr>
							<td>
								<li style="" id="${shop.shop_NO}List">거리를 나타내야해요</li>
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${memberCode != '2'}">
									<a href="${contextPath}/reserve/reserve.do?shop_NO=${shop.shop_NO}"><button class="btn btn-primary">예약</button></a>
								</c:if>
								
								<input class="btn btn-primary"
									id="${shop.shop_NO}a!d#$D ${shop.shop_latitude} a!d#$D ${shop.shop_longitude} a!d#$D ${shop.shop_name} a!d#$D ${shop.shop_address} a!d#$D${shop.shopDetailVO.shop_imageMain}"
									type="button" onclick="search(this.id)" value="찾기"
								>
							</td>
						</tr>
					</table>
				</c:forEach>
			</div>
			<br>
			<button class="btn btn-primary" onclick="moveScroll(-304,0)">◀</button>
			<select name="select">
				<option value="1">가까운 매장순</option>
				<option value="2">리뷰 많은순</option>
				<option value="3">별점 높은 순</option>
			</select>
			<button class="btn btn-primary" onclick="moveScroll(304,0)">▶</button>
			<br> <br>
			<input type="hidden" value="" id="test" style="width: 330px;">
			<br>
			<!-- <button type="button" class="btn btn-primary" onclick="changeTest();">변경이 될까요?</button> -->
			<div id="box1">
				<div class="map_wrap">
					<div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
					<a id="reSearch" onclick="reSearchShop();" class="btn btn-primary btn-lg"
						style="position: relative; width: 30%; height: 30px; bottom: 80px; z-index: 2; font-size: medium; text-align: center; display: none;"
					>현 위치에서 재검색 <i class="glyphicon glyphicon-zoom-in"></i></a>
					<button type="button" class="btn btn-primary" id="btn2" onclick="expand();" style="position: relative; bottom: 475px; display: none; z-index: 2;">
						<img src="${contextPath}/resources/assets/img/expand.png" width="18px">
					</button>
					<div id="menu_wrap" class="bg_white">
						<div class="option">
							<div>
								<form onsubmit="searchPlaces(); return false;">
									<input type="text" value="" id="keyword" size="15">
									<button type="submit" class="btn btn-primary">검색하기</button>
									<button type="button" onclick=" myLocation();" class="btn btn-primary">내위치</button>
									<div>
										<button class="btn btn-primary" type="button" onclick="reduce();">
											<img src="${contextPath}/resources/assets/img/reduce.png" width="18px">
										</button>
									</div>
								</form>
							</div>
						</div>
						<hr>
						<ul id="placesList"></ul>
						<div id="pagination"></div>
					</div>
				</div>
				<!--ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ-->
				<script>
               var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                   mapOption = { 
                       center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                       level: 6// 지도의 확대 레벨 
                   }; 
               
               var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
               
               // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
               if (navigator.geolocation) {
                   
                   // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                   navigator.geolocation.getCurrentPosition(function(position) {
                       
                       var lat = position.coords.latitude, // 위도
                           lon = position.coords.longitude; // 경도
                       
                           
                       var locPosition = new kakao.maps.LatLng(lat, lon) 
                           
                       
                       // 지도 중심좌표를 접속위치로 변경합니다
                     map.setCenter(locPosition);
                                                                            
                  });
                   
               } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
                   
                   var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), message = 'geolocation을 사용할수 없어요..'                                          
               }
                       
               // 마커를 담을 배열입니다
               var markers = [];
            
               // 인포윈도우를 담을 배열입니다
               var infowindows = [];
               
               var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
               mapOption = {
                  center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                  level : 3
               // 지도의 확대 레벨
               };
               
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
               // 지도를 생성합니다    
               //var map = new kakao.maps.Map(mapContainer, mapOption);
                                 
               // 지도의 중심 좌표 이벤트
               
               function centerMove(searchLat, searchLon){
                  kakao.maps.event.addListener(map, 'center_changed',function() {
                        console.log('지도의 중심 좌표는 '
                        + map.getCenter().toString()
                        + ' 입니다.');
                          document.getElementById('reSearch').style.display = 'none';
                        var test = map.getCenter().toString().slice(0, -1).slice(1),
                             split1 = test.split(', '),
                             lat1 = split1[0],
                             lon1 = split1[1],      
                             lat2 = searchLat,
                             lon2 = searchLon;
                          
                          var testtest = calculateDistance(lat1, lon1, lat2, lon2);
                          document.getElementById('test').value = '검색좌표에서 '+Math.round(testtest*1000)+'M 벗어났습니다';
                          
                          if(Math.round(testtest*1000) >= 150) {
                             document.getElementById('test').value = '화면 이동시 재검색버튼 출력';
                             var reBtn = document.getElementById('reSearch')
                             reBtn.style.display = 'inline-block';
                             /* reBtn.style.left = '400px'; */ 
                             return;
                          };
                          return;
                  });
               };  

               /* //지도 클릭 시 이벤트 발생
               kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

                  // 클릭한 위도, 경도 정보를 가져옵니다 
                  var latlng = mouseEvent.latLng;
                     
                  var myLocation = latlng.getLat() + ',' + latlng.getLng();
                     
                  console.log(myLocation);
                                       
               }); */

               // 장소 검색 객체를 생성합니다
               var ps = new kakao.maps.services.Places();
               
               // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
               var infowindow = new kakao.maps.InfoWindow({
                  zIndex : 1
               }); 
            
               // 생성된 마커를 제거합니다.
               function removeMarker() {
                   for (var i = 0; i < markers.length; i++) {
                      markers[i].setMap(null);
                   }
                  markers = [];
               }
               
               // 기존에 있던 인포윈도우를 지도에서 제거합니다
               function removeWindows() {
                  for (var i = 0; i < infowindows.length; i++) {
                     infowindows[i].close();
                  }
                  infowindows = [];
               }
            
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

               // 키워드로 장소를 검색합니다
               //searchPlaces();

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

               // 키워드 검색을 요청하는 함수입니다
               function searchPlaces() {
               
                  var keyword = document.getElementById('keyword').value;
                  
                  if (!keyword.replace(/^\s+|\s+$/g, '')) {
                     alert('키워드를 입력해주세요!');
                     return false;
                  }

                  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
                  ps.keywordSearch(keyword, placesSearchCB);
               }

               // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
               function placesSearchCB(data, status, pagination) {
                  if (status === kakao.maps.services.Status.OK) {
                     // 정상적으로 검색이 완료됐으면 검색 목록과 마커를 표출합니다
                     displayPlaces(data);           
               
                     // 페이지 번호를 표출합니다
                     displayPagination(pagination);
               
                  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
               
                     alert('검색 결과가 존재하지 않습니다.');
                     return;
               
                  } else if (status === kakao.maps.services.Status.ERROR) {
               
                     alert('검색 결과 중 오류가 발생했습니다.');
                     return;
                  
                  }
               }
             
               // 검색 결과 목록과 마커를 표출하는 함수입니다
               function displayPlaces(places) {

                  var listEl = document.getElementById('placesList'),
                     menuEl = document.getElementById('menu_wrap'),
                     fragment = document.createDocumentFragment(),
                     bounds = new kakao.maps.LatLngBounds(), listStr = '';

                  // 검색 결과 목록에 추가된 항목들을 제거합니다
                  removeAllChildNods(listEl);
                  
                  // 지도에 표시되고 있는 마커를 제거합니다
                  removeMarker();
                     
                  // 기존에 있던 인포윈도우를 지도에서 제거합니다
                  removeWindows();

                  for (var i = 0; i < places.length; i++) {//경로2
                     // 마커를 생성하고 지도에 표시합니다
                     var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x), 
                        marker = addMarker(placePosition, i),
                        itemEl = getListItem(i,places[i]); // 검색 결과 항목 Element를 생성합니다
                              
                     // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                     // LatLngBounds 객체에 좌표를 추가합니다
                     bounds.extend(placePosition);
                              
                     // 마커와 검색결과 항목에 mouseover 했을때
                     // 해당 장소에 인포윈도우에 장소명을 표시합니다
                     // mouseout 했을 때는 인포윈도우를 닫습니다
                     (function(marker, title) {
                        kakao.maps.event.addListener(marker,'mouseover', function() {
                           displayInfowindow(marker, title);
                        });
                        
                        kakao.maps.event.addListener(marker,'mouseout', function() {
                           infowindow.close();
                        });
                        
                        itemEl.onmouseover = function() {
                           displayInfowindow(marker, title);
                        };
                        
                        itemEl.onmouseout = function() {
                           infowindow.close();
                        };
                     
                     })
                     
                     (marker, places[i].place_name);
                     
                     fragment.appendChild(itemEl);
                  } 

                  // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
                  listEl.appendChild(fragment);
                  menuEl.scrollTop = 0;
               
                  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                  map.setBounds(bounds);
               }

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

               // 검색결과 항목을 Element로 반환하는 함수입니다
               function getListItem(index, places) {
               
                  var el = document.createElement('li'), 
                  itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>'
                  + '<div class="info">'
                  + '   <h5>' + places.place_name + '</h5>';
                  
                  if (places.road_address_name) {
                     itemStr += '    <span>' + places.road_address_name + '</span>'
                     + '   <span class="jibun gray">' + places.address_name + '</span>';
                     } else {
                     itemStr += '    <span>' + places.address_name + '</span>';
                  }
                  
                  itemStr += '  <span class="tel">' + places.phone + '</span>' + '</div>';
                  
                  el.innerHTML = itemStr;
                  el.className = 'item';
                  
                  return el;
               }

               // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
               function addMarker(position, idx, title) {
                  var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
                  imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
                  imgOptions = {
                     spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                     spriteOrigin : new kakao.maps.Point(0,(idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                     offset : new kakao.maps.Point(13, 37)
                     // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                  }, 
                  markerImage = new kakao.maps.MarkerImage(imageSrc,imageSize, imgOptions), 
                  marker = new kakao.maps.Marker({
                     position : position, // 마커의 위치
                     image : markerImage
                  });
                  
                  marker.setMap(map); // 지도 위에 마커를 표출합니다
                  markers.push(marker); // 배열에 생성된 마커를 추가합니다
                  
                  return marker;
               }

                   // 지도 위에 표시되고 있는 마커를 모두 제거합니다
                   removeMarker();

               // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
               function displayPagination(pagination) {
                  var paginationEl = document.getElementById('pagination'), 
                  fragment = document.createDocumentFragment(), i;
                  
                  // 기존에 추가된 페이지번호를 삭제합니다
                  while (paginationEl.hasChildNodes()) {
                     paginationEl.removeChild(paginationEl.lastChild);
                  }
                  
                  for (i = 1; i <= pagination.last; i++) {
                     var el = document.createElement('a');
                        el.href = "#";
                        el.innerHTML = i;
                        
                     if (i === pagination.current) {
                        el.className = 'on';
                     } else {
                        el.onclick = (function(i) {
                           return function() {
                              pagination.gotoPage(i);
                           }
                        })(i);
                     }                     
                     fragment.appendChild(el);
                  }
                  paginationEl.appendChild(fragment);
               }

               // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
               // 인포윈도우에 장소명을 표시합니다
               function displayInfowindow(marker, title) {
                  var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';
                  
                  infowindow.setContent(content);
                  infowindow.open(map, marker);
               }

               // 검색결과 목록의 자식 Element를 제거하는 함수입니다
               function removeAllChildNods(el) {
                  while (el.hasChildNodes()) {
                     el.removeChild(el.lastChild);
                  }
               }
               
//-------------------------------------------------------------------------------------------------------------------------------               
               
               // 내 위치를 찾는 함수입니다
               function myLocation() {
                  if (navigator.geolocation) {
                  
                     // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                     navigator.geolocation.getCurrentPosition(function(position) {
                     
                        var lat = position.coords.latitude; // 위도
                        lon = position.coords.longitude; // 경도
                        centerMove(lat, lon);         
                        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                        message = '<div style="padding:5px;height: 30px;">' + '위:'
                        + lat + '경:' + lon + '</div>'; // 인포윈도우에 표시될 내용입니다
                        // 마커와 인포윈도우를 표시합니다
                        displayMarker(locPosition, message);
                           
                     });
                  
                  } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
                     alert('geolocation을 사용할수 없어요..');
                     var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), message = 'geolocation을 사용할수 없어요..'
                     
                     displayMarker(locPosition, message);
                  }
                  
                  // 지도에 마커와 인포윈도우를 표시하는 함수입니다
                  function displayMarker(locPosition, message) {
                  
                     // 마커를 생성합니다
                     /* var marker = new kakao.maps.Marker({
                        map : map,
                        position : locPosition
                     }); */
                     
                     var iwContent = message, // 인포윈도우에 표시할 내용
                     iwRemoveable = true;
                     
                     // 인포윈도우를 생성합니다
                     var infowindow = new kakao.maps.InfoWindow({
                        content : iwContent,
                        removable : iwRemoveable
                     });
                     
                     // 인포윈도우를 마커위에 표시합니다 
                     /* infowindow.open(map, marker); */
                           
                     // 지도 확대 레벨을 변경 합니다 
                     map.setLevel(4);
                              
                     // 지도 중심좌표를 접속위치로 변경합니다                             
                     map.setCenter(locPosition);
                     
                     
                        
                  }
               }

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ               

               // 메뉴창을 축소합니다
               function reduce() {
                  const div = document.getElementById("menu_wrap");
                  const div2 = document.getElementById("btn2");
                  
                  if (div.style.display === 'none') {
                     div.style.display = 'block';
                     div2.style.display = 'none';
                  } else {
                     div.style.display = 'none';
                     div2.style.display = 'block';
                  }
               }
                  
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ                  

               // 메뉴창을 확장합니다
               function expand() {
                  const div = document.getElementById("menu_wrap");
                  const div2 = document.getElementById("btn2");
                  
                  if (div2.style.display === 'block') {
                     div2.style.display = 'none';
                     div.style.display = 'block';
                  }
               }
               
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ                  

               // 매장테이블의 위치정보를 조회하고 지도에 반영합니다
               function search(clicked_id) {
   
                  document.getElementById('reSearch').style.display = 'none';
                  
                  
                  // 메뉴 컨테이너
                  var menuEl = document.getElementById('menu_wrap');         
      
                  // 매장이 뜰 목록창
                  var listEl = document.getElementById('placesList');
                  
                  // 페이지 넘버요소 객체
                  var paginationEl = document.getElementById('pagination')
                  
                  // 자식객체를 받아둘 임시 컨테이너 -> 라고 구글이 합니다
                  var fragment = document.createDocumentFragment();
                  
                  // search 함수 호출 시 이전 정보들을 제거하는 작업들
                  // 검색키워드를 삭제합니다
                  var keyword = document.getElementById('keyword');
                  keyword.value = '';
                  
                  // search 함수 호출 시 이전 정보들을 제거하는 작업들
                  // 2. 기존에 추가된 페이지번호를 삭제합니다
                  while (paginationEl.hasChildNodes()) {
                     paginationEl.removeChild(paginationEl.lastChild);
                  }
                  
                  // search 함수 호출 시 이전 정보들을 제거하는 작업들   
                  // 검색 결과 목록에 추가된 항목들을 제거합니다
                  removeAllChildNods(listEl);
                  
                  // search 함수 호출 시 이전 정보들을 제거하는 작업들
                  // 기존에 있던 인포윈도우를 지도에서 제거합니다
                  removeWindows();
                  
                  // search 함수 호출 시 이전 정보들을 제거하는 작업들
                  // 지도에 표시되고 있는 마커를 제거합니다
                  removeMarker();                                          
                     
                  var id = clicked_id; //alert('매장일련번호 = ' + id);
                                  
                  // lat 위도, lon 경도 , 마커이름을 지정할 매장명을 분리합니다
                  var split = id.split('a!d#$D'),
                      shop_NO = split[0]; // 매장 일련번호
                      lat = split[1], // 위도
                     lon = split[2], // 경도            
                     shopName = split[3], // 이름
                     shopAddress = split[4]; // 주소
                     shopImg = split[5]; // 사진경로
                     
                                  
                  centerMove(lat, lon);
                                                                
                  var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                  message = '<div style="padding:5px;height: 30px;">'+ shopName +'</div>'; // 인포윈도우에 표시될 내용입니다                      
                                    
                  // 마커와 인포윈도우를 표시합니다
                  displayMarker(locPosition, message);
                                                                            
                  var itemEl = getListItem1(shopName, shopAddress, shop_NO, shopImg);
                     
                  fragment.appendChild(itemEl);
    
                  
                  //검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
                  listEl.appendChild(fragment);
                  menuEl.scrollTop = 0;
                                                                        
                  // 지도에 마커와 인포윈도우를 표시하는 함수입니다
                  function displayMarker(locPosition, message) {
                  
                     // 마커를 생성합니다
                     var marker = new kakao.maps.Marker({
                        map : map,
                        position : locPosition                           
                     });
                           
                     // 이벤트 이후 마커를 제어하기 위해 배열에 넣습니다
                     markers.push(marker);
                           
                     var iwContent = message, // 인포윈도우에 표시할 내용
                        iwRemoveable = true;
                     
                     // 인포윈도우를 생성합니다
                     var infowindow = new kakao.maps.InfoWindow({
                        content : iwContent,
                        removable : iwRemoveable
                     });
                     
                     // 이벤트 이후 인포윈도우를 제어하기 위해 배열에 넣습니다
                     infowindows.push(infowindow);
                           
                     // 인포윈도우를 마커위에 표시합니다 
                     infowindow.open(map, marker);
                     
                     // 지도 확대 레벨을 변경 합니다 
                     map.setLevel(4);
                           
                     // 지도 중심좌표를 접속위치로 변경합니다                        
                     map.setCenter(locPosition);
                                  
                  }
                  
               }
                    
                      
               //검색결과 항목을 Element로 반환하는 함수입니다
                function getListItem1(shopName, shopAddress, shop_NO, shopImg) {
                   var el = document.createElement('li');
                   var itemStr = 
                        '<div class="info1">'
                      + '   <h3>' + shopName + '</h3><br>';
                      
                   if (shopImg) {
                      itemStr += '<div class="shopImg"><img src="${contextPath}/downloadShopDetail.do?shopDetail_NO=' + shop_NO + '&shop_imageMain=' + shopImg + '" style="max-width: 100px; height: 100px;"/></div><br>'
                   } else {
                     itemStr += '<div class="shopImg"><img src="${contextPath}/resources/assets/img/dog2.png" style="max-width: 100px; height: 100px;"/></div><br>'
                   } 
                      itemStr += '   <span>' + shopAddress + '</span><br>'
                      + '<a href="${contextPath}/shop/shopDetail.do?shop_NO='+shop_NO+'"><button class="btn btn-primary">상세페이지</button></a>';
                      
                      if(${memberCode != "2"}) {
                    	  itemStr += '<a href="${contextPath}/reserve/reserve.do?shop_NO='+shop_NO+'"><button class="btn btn-primary">예약</button></a>';
                      }
                      
                      itemStr += '</div>';  
                      el.innerHTML = itemStr;
                      el.className = 'item1';

                   return el;
                  }
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
            
            </script>
			</div>
		</div>
	</header>
</body>
</html>