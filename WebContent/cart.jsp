<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.demo.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CART PAGE</title>
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
	<jsp:useBean id="cartService" class="com.demo.service.CartService"></jsp:useBean>

	<table class="table table-striped table-bordered table-hover">
		<tr class="table-primary">
			<th>Image</th>
			<th>Product_id</th>
			<th>Quantity</th>
			<th>Modify</th>
			<th>Delete</th>
			<th>add to WishList</th>
		</tr>

		<%
			try {
				String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
				List<Item> itemList = new ArrayList<>();
				List<Item> visitorItemList = (List<Item>) session.getAttribute("visitor_cart");
				if (user_id.equals("null")) {
					itemList = visitorItemList;
				} else {
					/* List<Item> userItemList = cartService.view(user_id);
					cartService.combine(visitorItemList, user_id); */
					itemList = cartService.view(user_id);
					session.removeAttribute("visitor_cart");
				}
				for (Item item : itemList) {
		%>

		<tr>
			<td><img src=<%=item.getImage()%> width="300" height="300"></td>
			<td><%=item.getProduct_id()%></td>
			<td><%=item.getQuantity()%></td>
			<td>
				<form action="UpdateCartServlet" method="post">
					<div class="form-group">
						<label for="sel1">Select list (select quantity):</label> <select
							class="form-control" id="sel1" name="quantity">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select> <input type="hidden" name="product_id"
							value=<%=item.getProduct_id()%>> <input type="submit"
							value="confirm"> <br>
					</div>
				</form>
			</td>
			<td><form action="DeleteServlet" method="post">
					<input type="hidden" name="product_id"
						value=<%=item.getProduct_id()%>> <input type="submit"
						value="delete">
				</form></td>
			<td>
				<form action="CartServlet" method="post">
					<input type="hidden" name="product_id"
						value=<%=item.getProduct_id()%>> <input type="hidden"
						name="product_image" value=<%=item.getImage()%>> <input
						type="submit" value="add to wishList">
				</form>
			</td>
		</tr>

		<%
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>

	</table>
</body>
</html>
