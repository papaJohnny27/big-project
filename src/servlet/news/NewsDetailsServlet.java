package servlet.news;

import domain.comment.Comment;
import domain.comment.CommentsDatabase;
import domain.news.News;
import domain.news.NewsDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/news/details")
public class NewsDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long newsId = Long.valueOf(req.getParameter("id"));
        News news = NewsDatabase.getNewsById(newsId);
        List<Comment> comments = CommentsDatabase.getAllCommentsByNewsId(newsId);

        req.setAttribute("news", news);
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/news_details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
