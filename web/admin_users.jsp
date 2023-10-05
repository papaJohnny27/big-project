<%@ page import="java.util.List" %>
<%@ page import="domain.user.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.10.2023
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<head>
    <title>Admin | Users</title>
    <%@ include file="/include/frontend-libs.jsp" %>
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container">
    <div class="row mt-5">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>EMAIL</th>
                <th>FULL NAME</th>
                <th>ROLE</th>
                <th>DETAILS</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (User user : users) {
            %>
            <tr>
                <td><%=user.getId()%>
                </td>
                <td><%=user.getEmail()%>
                </td>
                <td><%=user.getFullName()%>
                </td>
                <td><%=user.getRole().getName()%></td>
                <td><a href="/admin/users/details?id=<%=user.getId()%>" class="btn btn-primary">Details</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
