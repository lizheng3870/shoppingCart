<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
body {
	background-image: url("/WebContent/images/acer.jpg");
	background-color: #cccccc;
	height: 100%;
	background-position: center;
	background-repeat: repeat;
	background-size: contain;
	background-position: inherit;
}
</style>
<title>Register</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</head>
<body>
	<p>
		<a href="index.jsp">HOME PAGE</a>
	</p>
	<style>
body {
	background-image:
		url("https://o.aolcdn.com/images/dims?quality=85&image_uri=https%3A%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fcrop%3D4751%252C3167%252C0%252C0%26quality%3D85%26format%3Djpg%26resize%3D1600%252C1067%26image_uri%3Dhttps%253A%252F%252Fs.yimg.com%252Fos%252Fcreatr-images%252F2019-10%252F02ff1f70-e5bb-11e9-bfdb-cd062f61cb1e%26client%3Da1acac3e1b3290917d92%26signature%3D88e651e23f2c5849c7fddb9069f0918f7d768815&client=amp-blogside-v2&signature=d1b279f8048d3619bab8f3aafc63926f634e0bdd");
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>
	<div class="container">
		<div class="page-header">
			<h1>Register Page</h1>
		</div>
		<form action="register.jsp" method="post">
			<table border="0">
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" size="30"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" size="30"></td>
				</tr>
			</table>
			<input type="submit" value="Register">
			</p>
		</form>


	</div>
</body>
</html>