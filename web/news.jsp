<%@ page import="domain.news.News" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.09.2023
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>News</title>
  <%@ include file="/include/frontend-libs.jsp" %>
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container">
  <div class="row mt-5">
    <%
      List<News> news = (List<News>) request.getAttribute("news");
      for (News news1 : news) {
    %>
    <div class="col-sm-6">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title"><%=news1.getTitle()%>
          </h5>
          <p class="card-text"><%=news1.getContent()%>
          </p>
          <a href="/news/details?id=<%=news1.getId()%>" class="btn btn-primary">Details</a>
        </div>
      </div>
    </div>
    <%
      }
    %>
  </div>
</div>
</body>
</html>
