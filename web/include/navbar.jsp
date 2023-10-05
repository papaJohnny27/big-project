<%@ page import="domain.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.news_category.NewsCategory" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.09.2023
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String theme = "LIGHT";
  for (Cookie cookie : request.getCookies()) {
    if (cookie.getName().equals("theme") && !cookie.getValue().isEmpty()) {
      theme = cookie.getValue();
    }
  }
%>
<nav class="navbar navbar-expand-lg navbar-<%if (theme.equals("DARK")) {%>dark<%}%> bg-<%if (theme.equals("DARK")) {%>dark<%}%>">
  <div class="container-fluid">
    <a class="navbar-brand" href="/news">News</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="#">Test test</a>
        </li>
      </ul>
      <span class="navbar-text">
        <%
          if (session.getAttribute("current_user") != null) {
            User user = (User) session.getAttribute("current_user");
        %>
                <b><%=user.getFullName()%></b>
                <%
                  if ((Boolean) session.getAttribute("is_admin") == true) {
                %>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
  Add New News
</button>
                <a href="/admin/users" class="btn btn-primary text-light">Admin Panel</a>

        <!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Add New News</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="/news" method="post">
            <div class="form-group mt-3">
                <input type="text" name="title" placeholder="News Title" class="form-control" required>
            </div>
            <div class="form-group mt-3">
                <input type="text" name="content" placeholder="News Content" class="form-control" required>
            </div>
            <div class="form-group mt-3">
                <input type="date" name="post_date" placeholder="News Content" class="form-control" required>
            </div>
            <div class="form-group mt-3">
                <select name="category_id" id="categories" class="form-select form-select-lg mb-3">
                    <%
                      List<NewsCategory> categories = (List<NewsCategory>) session.getAttribute("categories");
                      for (NewsCategory category : categories) {
                    %>
                        <option value="<%=category.getId()%>"><%=category.getName()%></option>
                    <%
                      }
                    %>
                </select>
            </div>
            <button class="btn btn-primary w-100" type="submit">Add New News</button>
        </form>
      </div>
    </div>
  </div>
</div>
                <%
                  }
                %>
                <a href="/admin/users/details?id=<%=user.getId()%>" class="btn btn-primary text-light">Profile</a>
                <a href="/auth/logout" class="btn btn-primary text-light">Log Out</a>
        <%
        } else {
        %>
                <a href="/auth/login">Log In</a>
                <a href="/auth/register">Register</a>
                <%
                  }
                %>

                <form action="/user-preferences" method="post">
                    <input type="hidden" name="theme" value="<%if (theme.equals("DARK")) {%>LIGHT<%} else {%>DARK<%}%>">
<button class="btn" type="submit">SWITCH THEME</button>
                </form>
      </span>
    </div>
  </div>
</nav>