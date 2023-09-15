<%@ page import="vn.edu.iuh.fit.models.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.models.Role" %><%--
  Created by IntelliJ IDEA.
  User: lethi
  Date: 9/14/2023
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%
    Account acc = (Account) session.getAttribute("accountLogin");
    List<Role> listRole = (List<Role>) session.getAttribute("listRole");
%>
<h3 style="font-family: SansSerif,sans-serif; margin: 0 auto">Welcome: <%= acc.getFull_name() %> </h3>
<!-- Menu -->
<div class="menu">
    <ul>
        <li><a href="login?action=info">Information Account</a></li>
        <li><a href="insert_account.jsp">Add account</a></li>
        <li><a href="login?action=listAllAccount">List Account</a></li>
        <li><a href="#">Grant Access</a></li>
        <li><a href="index.jsp">Log out</a></li>
    </ul>
</div>
<!-- Nội dung chính -->
<div class="container-dashboard">
    <h1>List Role Of Your Account</h1>
    <table>
        <thead>
        <tr>
            <th>Role ID</th>
            <th> Name </th>
            <th>Description</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(Role r : listRole){
        %>
        <tr>
            <td><%=r.getRole_id()%></td>
            <td><%=r.getRole_name()%></td>
            <td><%=r.getDescription()%></td>
            <td><%=r.getStatus()%></td>
        </tr>
        <%}%>

        </tbody>
    </table>
</div>
</body>
</html>
