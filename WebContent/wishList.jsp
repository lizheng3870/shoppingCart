<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.demo.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WISHLIST PAGE</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
</style>
</head>
<body>

	<p>
		<a href="index.jsp">HOME PAGE</a>
	</p>

	<p>
		<a href="loginform.jsp"><img src="images/login.jpg" width="30"
			height="30"></a>
	</p>
	<p>
		<a href="LogoutServlet"><img src="images/logout.jpg" width="30"
			height="30"></a>
	</p>

	<p>
		<a href="cart.jsp"><img src="images/cart.png" width="30"
			height="30"></a>
	</p>
	<p>
		<a href="wishList.jsp"><img src="images/wishlist.jpg" width="30"
			height="30"> </a>
	</p>
	<jsp:useBean id="wishListService"
		class="com.demo.service.WishListService"></jsp:useBean>
	<table class="table table-striped table-bordered table-hover">
		<tr class="table-primary">
			<th>Image</th>
			<th>Product_id</th>
			<th>Delete</th>
			<th>add to Cart</th>
		</tr>
		<%
			try {
				String user_id = String.valueOf(session.getAttribute("user_id"));

				if (user_id.equals("null"))
					out.println("PLEASE LOGIN TO SEE WISHLIST!!!");
				else {
					List<Item> itemList = wishListService.viewAll(user_id);
					for (Item item : itemList) {
		%>

		<tr>
			<td><img src=<%=item.getImage()%> width="300" height="300"></td>
			<td><%=item.getProduct_id()%></td>
			<td>
				<form action="WishListServlet?action=delete" method="post">
					<input type="hidden" name="product_id"
						value=<%=item.getProduct_id()%>> <input type="submit"
						value="delete">
				</form>

			</td>
			<td>
				<form action="WishListServlet?action=move" method="post">
					<input type="hidden" name="product_id"
						value=<%=item.getProduct_id()%>> <input type="hidden"
						name="product_image" value=<%=item.getImage()%>> <input
						type="submit" value="add to cart">
				</form>
			</td>

			<%
				}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
		
</body>
</html>