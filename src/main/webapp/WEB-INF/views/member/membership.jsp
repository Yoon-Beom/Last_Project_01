<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
h1 {
	padding-top: 30px;
	font-size: 50px;
	padding-bottom: 50px;
}

#button {
	font-size: 30px;
	margin: auto;
	margin-top: 80px;
}

input {
	width: 200px;
	height: 100px;
	text-align: "center";
	border-radius: 5px;
}

img {
	width: 100px;
	height: 100px;
}

#box1 {
	width: 80%;
	height: 500px;
	margin: auto;
	margin-top: 100px;
	background-color: white;
}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css" />
</head>
<body>
	<form>
		<header class="masthead">

			<div id="box1">
				<h1>회원가입</h1>
				<div id="button">
					<img src="${pageContext.request.contextPath}/resources/assets/img/icon.png">
					<a href="${pageContext.request.contextPath}/member/membershipForm.do">
						<input type="button" value="개인 회원"></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<img src="${pageContext.request.contextPath}/resources/assets/img/icon2.png">
					<a href="${pageContext.request.contextPath}/shop/shopmembershipForm.do">
						<input type="button" value="기업 회원">
					</a>
				</div>
			</div>
		</header>
	</form>
</body>
</html>