<%@ page import="vn.edu.iuh.fit.models.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.repositories.RoleRepository" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.models.Role" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: lethi
  Date: 9/15/2023
  Time: 10:38 PM
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
    Account accGrand = (Account) request.getAttribute("accountGrant");
    ArrayList<Role> roles = (ArrayList<Role>) session.getAttribute("allRole");
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
    <h1>Grant Role For Account</h1>
    <form id="formAddAcc" action="login?action=grantRoleAccount" method="post">
        <label for="accountID">Account ID</label>
        <input type="text" id="accountID" name="accountID" value="<%=accGrand.getAccount_id()%>" readonly/>
        <label for="fullName">FullName</label>
        <input type="text" id="fullName" name="fullName" value="<%=accGrand.getFull_name()%>" readonly/> <br>
        <label for="email">Email</label>
        <input type="text" id="email" name="email" value="<%=accGrand.getEmail()%>" readonly/> <br>
        <label for="phone">Password</label>
        <input type="text" id="password" name="password" value="<%=accGrand.getPassword()%>" readonly/> <br>
        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone" value="<%=accGrand.getPhone()%>" readonly/> <br>
        <label for="roleSelect"> Select Role:
            <select id="roleSelect" name="roleSelect" style="margin-left: inherit">
                <%
                    for(Role r: roles){
                %>
                <option value="<%=r.getRole_id()%>"><%=r.getRole_name()%></option>
                <%}%>
            </select>
        </label>
        <label for="noteRole">Note</label>
        <input type="text" id="noteRole" name="noteRole" placeholder="Enter note for account role "/> <br>
        <button type="submit" id="addBtn" >GRANT ROLE</button>
    </form>
</div>

</body>
</html>
