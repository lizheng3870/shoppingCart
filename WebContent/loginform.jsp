<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>

    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</head>
<body>
<style>  body{
    background-image: url("https://www.france-hotel-guide.com/en/blog/wp-content/uploads/2017/02/paris-shopping.jpg");
    background-color: #cccccc;
    height: 100%;
    background-position: center;
    background-repeat: repeat-x;
    background-size: cover;
}</style><p><p><p><p>
    <div class="container ">

        <h1>Login Page</h1>
        <form action="login.jsp" method="post">
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
            <p><input type="submit" value="Login"></p>
        </form>
        <p>(<a href="registerform.jsp">new user? register here.</a>)</p>
    </div>


</body>
</html>