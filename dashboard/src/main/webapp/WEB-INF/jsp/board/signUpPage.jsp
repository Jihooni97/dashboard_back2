<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/signUp.js"></script>
</head>
<body>

	<form name="signUp-form" id="signUp-form" method="POST">
<!-- 		<input type="hidden" name="checkId" id="checkId"> -->
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="userid" id="userid"> 
					<input type="button" value="중복확인" id="idCheck" onclick="fn_idCheck()">
				</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td>
					<input type="text" name="name" id="name">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" id="password">
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" name="password2" id="password2">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input class="email" name="email" placeholder="이메일 양식) example@naver.com">
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="text" name="tel" id="tel" placeholder="전화번호 양식) 01012345678">
				</td>
			</tr>
		</table>
	</form>

		<button id="signUpBtn">확인</button>
		<button id="cancelBackBtn">취소</button>

</body>
</html>