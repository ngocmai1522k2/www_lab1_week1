<%@ page import="vn.edu.iuh.fit.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: lethi
  Date: 9/14/2023
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddAccount</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<!-- Menu -->
<div class="menu">
    <ul>
        <li><a href="dashboard.jsp">Information Account</a></li>
        <li><a href="#">List role</a></li>
        <li><a href="#">Grant Access</a></li>
        <li><a href="index.jsp">Log out</a></li>
    </ul>
</div>
<!-- Nội dung chính -->
<div >
    <h1>Information Of New Account</h1>
    <form id="formAddAcc" action="login?action=addAccount" method="post">
        <label for="accountID">Account ID</label>
        <input type="text" id="accountID" name="accountID" placeholder="Enter account ID"/>
        <label for="fullName">FullName</label>
        <input type="text" id="fullName" name="fullName" placeholder="Enter full name"/> <br>
        <label for="email">Email</label>
        <input type="text" id="email" name="email" placeholder="Enter email"/> <br>
        <label for="phone">Password</label>
        <input type="text" id="password" name="password" placeholder="Enter password"/> <br>
        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone" placeholder="Enter your phone"/> <br>
        <button type="submit" id="addBtn" >ADD ACCOUNT</button>
    </form>
</div>

</body>
</html>
