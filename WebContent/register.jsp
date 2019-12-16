<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="user" class="com.demo.model.User" />
	<jsp:setProperty property="*" name="user" />
	<jsp:useBean id="userService" class="com.demo.service.UserService"></jsp:useBean>

	<%
		boolean is_duplicate = userService.checkUsername(user);
		if (is_duplicate)
			out.println("<p style=\"color: red;\"> USERNAME ERROR!!! PLEASE CHANGE A NEW USERNAME  </p>");
		else {
			int key = userService.insert(user);
			response.sendRedirect("loginform.jsp");
		}
	%>

</body>
</html>