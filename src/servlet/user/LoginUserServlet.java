package servlet.user;

import domain.news_category.NewsCategoryDatabase;
import domain.user.User;
import domain.user.UserDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = UserDatabase.getUserByEmailAndPassword(email, password);
        if (user == null) {
            resp.sendRedirect("/auth/login?errorMessage=true");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("current_user", user);
        session.setAttribute("is_admin", user.getRole().getId() == 1L);
        session.setAttribute("categories", NewsCategoryDatabase.getAllNewsCategories());

        resp.sendRedirect("/news");
    }
}
