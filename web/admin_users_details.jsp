<%@ page import="domain.user.User" %>
<%@ page import="domain.role.Role" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.10.2023
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    User currentUser = (User) session.getAttribute("current_user");
    User userDetails = (User) request.getAttribute("user_details");
    List<Role> roles = (List<Role>) request.getAttribute("roles");
%>
<head>
    <title>Admin | <%=userDetails.getFullName()%>
    </title>
    <%@ include file="/include/frontend-libs.jsp" %>
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container">
    <div class="row mt-5">
        <h1 class="display-3"><%=userDetails.getFullName()%>
        </h1>
        <hr>
        <form action="/admin/users/details" method="post">
            <input type="hidden" name="user_id" value="<%=userDetails.getId()%>">
            <div class="form-group">
                <label for="user_full_name_input">Full Name:</label>
                <input type="text" name="user_name" class="form-control" value="<%=userDetails.getFullName()%>">
            </div>
            <div class="form-group">
                <label for="user_email_input">Email:</label>
                <input type="text" name="user_email" class="form-control" value="<%=userDetails.getEmail()%>">
            </div>
            <div class="form-group">
                <label for="user_password_input">Password:</label>
                <input type="password" name="user_password" class="form-control" value="<%=userDetails.getPassword()%>">
            </div>
            <div class="form-group mt-3">
                <select name="role_id" id="roles" class="form-select form-select-lg mb-3">
                    <%
                        for (Role role : roles) {
                    %>
                    <option value="<%=role.getId()%>"
                            <% if (role.getId().equals(userDetails.getRole().getId())) {%>
                            selected
                            <%
                            } else if (!currentUser.getRole().getId().equals(1L)) {
                            %>
                            disabled
                            <%
                                }
                            %>
                    >
                        <%=role.getName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
            <div class="form-group">
                <button class="btn btn-primary w-100" type="submit">Update</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
