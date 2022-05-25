<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/dashboard.css" />
<link rel="stylesheet" type="text/css" href="/css/signUp.css" />
<script type="text/javascript" src="/js/signUp.js"></script>
</head>
<body>
	<div id="signPage">
		<form name="signUp-form" id="signUp-form" method="POST">
			<!-- 		<input type="hidden" name="checkId" id="checkId"> -->
			<header>
			<h2>회원가입</h2>
			</header>
			<div class="input-box">
				<input type="text" name="user_id" id="user_id" placeholder="　　　　　　　　　　　　아이디를 입력해주세요.">
				<label for="user_id">아이디</label>
			</div>
			<input type="button" value="중복확인" id="idCheck" onClick="fn_idCheck()">
			
			<div class="input-box">
				<input type="text" name="name" id="name" placeholder="　　　　　　　　　　　　회원명을 입력해주세요.">
				<label for="name">회원명</label>
			</div>
			<div class="input-box">
				<input type="passowrd" name="password" id="password" placeholder="　　　　　　　　　　　　비밀번호를 입력해주세요.">
				<label for="password">비밀번호</label>
			</div>
			<div class="input-box">
				<input type="password" name="password2" id="password2" placeholder="　　　　　　　　　　　　비밀번호를 다시 한번 입력해주세요.">
				<label for="password2">비밀번호 확인</label>
			</div>
			<div class="input-box">
				<input type="text" name="email" id="email" placeholder="　　　　　　　　　　　　이메일을 입력해주세요.">
				<label for="email">이메일</label>
			</div>
			<div class="input-box">
				<input type="text" name="tel" id="tel" placeholder="　　　　　　　　　　　　전화번호 양식) 01012345678">
				<label for="tel">전화번호</label>
			</div>
			<!-- 성별 -->
			<select id="gender">
				<option value="남자" selected="selected">남자</option>
				<option value="여자">여자</option>
			</select>
			
			<input type="hidden" id="authorities_box" name="authorities" value="ROLE_USER">
				<input type="hidden" id="enabled" value="1">
				<input type="hidden" id="login_count" value="0">
			</table>
		</form>

		<button id="signUpBtn">확인</button>
		<button id="cancelBackBtn">취소</button>
	</div>
</body>
</html>