package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-preferences")
public class UserPreferencesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theme = req.getParameter("theme");

        if (theme == null || !theme.equals("DARK"))
            theme = "LIGHT";

        Cookie cookie = new Cookie("theme", theme);
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);

        resp.sendRedirect("/news");
    }
}
