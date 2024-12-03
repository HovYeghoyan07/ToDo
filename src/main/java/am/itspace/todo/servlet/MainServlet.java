package am.itspace.todo.servlet;

import am.itspace.todo.model.ToDo;
import am.itspace.todo.model.User;
import am.itspace.todo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            List<ToDo> toDos = toDoService.getToDosByUserId(((User)req.getSession().getAttribute("user")).getId());
            System.out.println(toDos);
            req.setAttribute("toDos", toDos);
        }

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
