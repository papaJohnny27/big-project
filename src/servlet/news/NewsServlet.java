package servlet.news;


import domain.news.News;
import domain.news.NewsDatabase;
import domain.news_category.NewsCategory;
import domain.news_category.NewsCategoryDatabase;
import domain.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if(session.getAttribute("current_user")==null){
            resp.sendRedirect("/auth/login?restricted=true");
            return;
        }

        List<News> news = NewsDatabase.getAllnews();
        List<NewsCategory> newsCategories = NewsCategoryDatabase.getAllNewsCategories();

        req.setAttribute("news",news);
        req.setAttribute("categories",newsCategories);

        req.getRequestDispatcher("/news.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          if(req.getSession().getAttribute("is_admin") != null &&
                  (Boolean) req.getSession().getAttribute("is_admin")){
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            Long categoryId = Long.valueOf(req.getParameter("category_id"));
            LocalDate postDate = LocalDate.parse(req.getParameter("post_date"));

            NewsDatabase.addNewNews(
                    title,
                    content,
                    categoryId,
                    postDate
            );
        }
        resp.sendRedirect("/news");
    }
}
