<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.09.2023
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
  <%@ include file="/include/frontend-libs.jsp" %>
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container">
  <div>
    <div class="row align-items-center" style="height: 100vh;">
      <div class="mx-auto col-10 col-md-8 col-lg-6">
        <!-- Form -->
        <form class="/auth/register" action="" method="post">
          <h1>Register</h1>
          <p class="description">
            Register into system
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
                    required
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
                    required
            />
          </div>
          <div class="form-group">
            <label for="full_name">Full name:</label>
            <input
                    type="text"
                    class="form-control password"
                    id="full_name"
                    placeholder="Password..."
                    name="fullName"
                    required
            />
          </div>

          <button type="submit" class="btn btn-primary btn-customized mt-4">
            Register
          </button>
        </form>
        <!-- Form end -->
      </div>
    </div>
  </div>
</div>
</body>
</html>
