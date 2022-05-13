<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="document.f.username.focus();">
    <h3>Login with Username and Password</h3>
    <form name="f" action="/login" method="POST">
        <table>
            <tbody>
                <tr>
                    <td>아이디:</td>
                    <td><input type="text" name="username" value=""></td>
                </tr>
                <tr>
                    <td>비밀번호:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2"><input name="submit" type="submit" value="Login"></td>
                </tr>
                <input name="${_csrf.parameterName}" type="hidden" value="${_crsf.token}">
            </tbody>
        </table>
    </form>
</body>

</html>