<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.demo.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Shopping Page</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
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
	<table class="table table-striped table-bordered table-hover">
		<tr class="table-primary">
			<th>ID</th>
			<th>Image</th>
			<th>Name</th>
			<th>Price</th>
			<th>Add</th>
			<th>Like</th>
		</tr>

		<jsp:useBean id="productService"
			class="com.demo.service.ProductService"></jsp:useBean>
		<%
			try {
				List<Product> productList = productService.view();
				for (Product product : productList) {
		%>
		<tr>
			<td><%=product.getId()%></td>
			<td><img src=<%=product.getImage()%> width="300" height="300"></td>
			<td><%=product.getName()%></td>
			<td><%=product.getPrice()%></td>
			<td>
				<form method="post" action="ShopServlet">
					<input type="hidden" name="product_id" value=<%=product.getId()%>>
					<input type="hidden" name="product_image"
						value=<%=product.getImage()%>> <label>QUANTITY <input
						name="quantity">
					</label> <br> <input type="submit" value="add to cart">
				</form>
			</td>
			<td>
				<form method="get" action="WishListServlet">
					<input type="hidden" name="product_id" value=<%=product.getId()%>>
					<input type="hidden" name="product_image"
						value=<%=product.getImage()%>> </label> <br>

					<button type="submit">
						<img src="images/love.png" width="30" height="30">
					</button>
				</form>
		</tr>
		<%
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>

	</table>
	<%
		Integer userId = (Integer) request.getSession().getAttribute("user_id");
		System.out.println("Session user_id:" + userId);
	%>
</body>
</html>