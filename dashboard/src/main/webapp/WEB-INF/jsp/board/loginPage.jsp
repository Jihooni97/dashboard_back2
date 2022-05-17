<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
</head>
<!-- <body onload="document.f.username.focus();"> -->
<h3>아이디와 비밀번호를 입력하세요.</h3>
<div>
	<form action="/login" method="POST" id="loginForm" class="loginForm">
		<table>
			<tbody>
				<tr>
					<td>아이디:</td>
					<td>
						<input type="text" name="username" value="">
					</td>
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td>
						<input type="password" name="password" onkeyup="enterKey()">
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td colspan="2"><input name="submit" type="submit" value="Login"></td> -->
<!-- 				</tr> -->
				<input name="${_csrf.parameterName}" type="hidden" value="${_crsf.token}">
			</tbody>
		</table>
	</form>
	<div class="loginWrap">
		<button id="loginBtn" onClick="login();">로그인</button>
		<a href="/signUpPage.do"><button>회원가입</button></a>
	</div>
</div>
</body>

</html>