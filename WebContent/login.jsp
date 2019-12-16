<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.demo.model.*"%>
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
	<jsp:useBean id="cartService" class="com.demo.service.CartService"></jsp:useBean>



	<%
		if (session.getAttribute("user_id") != null) {
	%>

	<script type="text/javascript" language="javascript">
		alert("you have already logged in");
		window.document.location.href = "index.jsp";
	</script>
	<%
		} else {
	%>
	<%
		int user_id = userService.get_user_id(user);

			if (user_id != 0 && userService.authorize(user)) {
				session.setAttribute("user_id", user_id);

				List<Item> visitorItemList = (List<Item>) session.getAttribute("visitor_cart");
				System.out.println("LOGIN: " + visitorItemList);
				cartService.combine(visitorItemList, String.valueOf(user_id));
				session.removeAttribute("visitor_cart");

				response.sendRedirect("index.jsp");

			} else
				out.println("<p style=\"color: red;\"> LOGIN ERROR  </p>");
		}
	%>

</body>
</html>
