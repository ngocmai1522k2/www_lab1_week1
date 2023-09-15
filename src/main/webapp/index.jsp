<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>FormLogin</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div id="container">
      <form id="formLogin" action="login?action=checkLogin" method="post">
        <h2 style="color: #6d31dc; font-family: SansSerif,serif">LOGIN FORM</h2>
        <input type="text" name="email" placeholder="Enter email"/> <br>
        <input type="password" name="password" placeholder="Enter password"/> <br>
        <button type="submit" id="loginbtn" >Login</button>
      </form>
    </div>
</body>
</html>