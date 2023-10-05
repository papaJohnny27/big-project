<%@ page import="domain.news.News" %>
<%@ page import="domain.comment.Comment" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03.10.2023
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    News news = (News) request.getAttribute("news");
%>
<head>
    <title>
        <%=news.getTitle()%>
    </title>
    <%@ include file="/include/frontend-libs.jsp" %>
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <h1 class="display-3"><%=news.getTitle()%>
        </h1>
        <p><%=news.getContent()%>
        </p>
        <hr>
        <h5>Comments:</h5>
        <div class="row mb-4">
            <form action="/comments/add" method="post">
                <input type="hidden" value="<%=news.getId()%>" name="news_id">
                <div class="form-control">
                    <label for="comment_content_input">Your comment:</label>
                    <textarea id="comment_content_input" name="comment_content" class="form-control">
At w3schools.com you will learn how to make a website. They offer free tutorials in all web development technologies.
</textarea>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary w-100" type="submit">Submit</button>
                </div>
            </form>
        </div>
        <%
            List<Comment> comments = (List<Comment>) request.getAttribute("comments");
            for (Comment comment : comments) {
        %>
        <div class="row mt-3">
            <div class="card">
                <div class="card-header">
                    <b><%=comment.getUser().getFullName()%>
                    </b> at <%=comment.getPostDate()%>
                </div>
                <div class="card-body">
                    <p class="card-text"><%=comment.getContent()%>
                    </p>
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
