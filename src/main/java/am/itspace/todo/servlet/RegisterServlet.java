package am.itspace.todo.servlet;

import am.itspace.todo.model.User;
import am.itspace.todo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        StringBuilder msgBuilder = new StringBuilder();

        if (name == null || name.trim().isEmpty()) {
            msgBuilder.append("Please enter your Name");
        }

        if (surname == null || surname.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Surname");
        }
        if (email == null || email.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Email");
        }


        if (password == null || password.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Password");
        } else if (password.length() < 6) {
            msgBuilder.append("<br>");
            msgBuilder.append("Password must be longer than 6 characters");
        }


        if (userService.getUserByEmail(email) != null) {
            msgBuilder.append("<br>");
            msgBuilder.append("Your account has been registered with this email");
        } else if (!msgBuilder.isEmpty()) {
            req.setAttribute("msg", msgBuilder.toString());
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        } else {

            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            userService.add(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        }


    }
}
