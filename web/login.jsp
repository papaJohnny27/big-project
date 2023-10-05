<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.09.2023
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
  <%@ include file="/include/frontend-libs.jsp" %>
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container">
  <div>
    <%
      if (request.getParameter("errorMessage") != null) {
    %>
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>Error:</strong> Inavlid credentials.
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
      }
    %>
    <%
      if (request.getParameter("restricted") != null) {
    %>
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>Error:</strong> You have to log in into system.
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
      }
    %>
    <%
      if (request.getParameter("afterRegister") != null) {
    %>
    <div class="alert alert-success d-flex align-items-center" role="alert">
      <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
      <div>
        You have successfully created an account. Please, log in
      </div>
    </div>
    <%
      }
    %>
    <div class="row align-items-center" style="height: 100vh;">
      <div class="mx-auto col-10 col-md-8 col-lg-6">
        <!-- Form -->
        <form class="/auth/login" action="" method="post">
          <h1>Login</h1>
          <p class="description">
            Login into system
          </p>
          <!-- Input fields -->
          <div class="form-group">
            <label for="username">Email:</label>
            <input
                    type="text"
                    class="form-control username"
                    id="username"
                    placeholder="Email..."
                    name="email"
            />
          </div>
          <div class="form-group">
            <label for="password">Password:</label>
            <input
                    type="password"
                    class="form-control password"
                    id="password"
                    placeholder="Password..."
                    name="password"
            />
          </div>

          <button type="submit" class="btn btn-primary btn-customized mt-4">
            Login
          </button>
        </form>
        <!-- Form end -->
      </div>
    </div>
  </div>
</div>
</body>
</html>
