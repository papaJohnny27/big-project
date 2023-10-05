package servlet.comment;

import domain.comment.CommentsDatabase;
import domain.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/comments/add")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("current_user");
        String content = req.getParameter("comment_content");
        Long newsId = Long.valueOf(req.getParameter("news_id"));

        CommentsDatabase.addNewComment(
                LocalDateTime.now(),
                currentUser.getId(),
                newsId,
                content
        );

        resp.sendRedirect("/news/details?id=" + newsId);
    }
}
