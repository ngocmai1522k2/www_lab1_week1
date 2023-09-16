<%@ page import="vn.edu.iuh.fit.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: lethi
  Date: 9/16/2023
  Time: 6:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateAccount</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%
    Account acc = (Account) session.getAttribute("accountLogin");
    String grant_accessLogin = session.getAttribute("grant_accessLogin").toString();
    Account accUpdate = (Account) request.getAttribute("accountUpdate");
%>
<h3 style="font-family: SansSerif,sans-serif; margin: 0 auto">Welcome: <%= acc.getFull_name() %> </h3>
<!-- Menu -->
<div class="menu">
    <ul>
        <li><a href="login?action=info" methods="get">Information Account</a></li>
        <li><a href="login?action=listRole">List role</a></li>
        <% if(grant_accessLogin.equals("admin")){%>
        <li><a href="login?action=listAllAccount">List Account</a></li>
        <%}%>
        <li><a href="index.jsp">Log out</a></li>
    </ul>
</div>
<!-- Nội dung chính -->
<div >
    <h1>Update Information</h1>
    <form id="formAddAcc" action="login?action=updateAccount" method="post">
        <label for="accountID">Account ID</label>
        <input type="text" id="accountID" name="accountID" value="<%=accUpdate.getAccount_id()%>" readonly/>
        <label for="fullName">FullName</label>
        <input type="text" id="fullName" name="fullName" value="<%=accUpdate.getFull_name()%>"/> <br>
        <label for="email">Email</label>
        <input type="text" id="email" name="email" value="<%=accUpdate.getEmail()%>"/> <br>
        <label for="phone">Password</label>
        <input type="text" id="password" name="password" value="<%=accUpdate.getPassword()%>"/> <br>
        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone" value="<%=accUpdate.getPhone()%>"/> <br>
        <button type="submit" id="addBtn" >UPDATE ACCOUNT</button>
    </form>
</div>

</body>
</html>
