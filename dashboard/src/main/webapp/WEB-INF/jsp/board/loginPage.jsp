<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
<link rel="stylesheet" type="text/css" href="/css/dashboard.css" />
<link rel="stylesheet" type="text/css" href="/css/login.css" />
</head>
<!-- <body onload="document.f.username.focus();"> -->
<div id="loginPage">
	<header>
	<h2>로그인</h2>
	</header>
	<form action="/login" method="POST" id="loginForm" class="loginForm">

		<div class="input-box">
			<input id="user_id" type="text" name="user_id" placeholder="아이디">
			<label for="user_id">아이디</label>
		</div>
		<div class="input-box">
			<input id="password" type="password" name="password" placeholder="비밀번호">
			<label for="password">비밀번호</label>
		</div>
		<input type="submit" value="로그인">
		<input name="${_csrf.parameterName}" type="hidden" value="${_crsf.token}">
		
	</form>
	<a href="/signUpPage.do"><button>회원가입</button></a>
	
<!-- 		<table> -->
<!-- 			<tbody> -->
<!-- 				<tr> -->
<!-- 					<td>아이디:</td> -->
<!-- 					<td> -->
<!-- 						<input type="text" name="user_id"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>비밀번호:</td> -->
<!-- 					<td> -->
<!-- 						<input type="password" name="password"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- <!-- 				<tr> -->
<!-- 					<td colspan="2"><input name="submit" type="submit" value="로그인"></td> -->
<!-- <!-- 				</tr> --> 
<%-- 				<input name="${_csrf.parameterName}" type="hidden" value="${_crsf.token}"> --%>
<!-- 			</tbody> -->
<!-- 		</table> -->
	

	<!-- 에러메시지 -->
<%-- 	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }"> --%>
<!-- 		<p style="color: red; font-weight: bold;">login Failed : -->
<%-- 			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }</p> --%>
<%-- 		<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" /> --%>
<%-- 	</c:if> --%>

<!-- 		<button id="loginBtn" onClick="login();">로그인</button> -->

</div>
</body>

</html>