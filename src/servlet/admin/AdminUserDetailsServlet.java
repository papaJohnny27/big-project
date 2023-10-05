package servlet.admin;

import domain.role.Role;
import domain.role.RoleDatabase;
import domain.user.User;
import domain.user.UserDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users/details")
public class AdminUserDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("id"));
        User user = UserDatabase.getUser(userId);
        List<Role> roles = RoleDatabase.getAllRoles();

        req.setAttribute("user_details", user);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/admin_users_details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        String userFullName = req.getParameter("user_name");
        String userEmail = req.getParameter("user_email");
        String userPassword = req.getParameter("user_password");
        Long roleId = Long.valueOf(req.getParameter("role_id"));

        UserDatabase.updateUser(
                userId,
                userEmail,
                userPassword,
                userFullName,
                roleId
        );

        User currentUser = (User) req.getSession().getAttribute("current_user");
        String redirectRoute = "/news";

        if (currentUser.getRole().getId().equals(1L))
            redirectRoute = "/admin/users";

        resp.sendRedirect(redirectRoute);
    }
}
