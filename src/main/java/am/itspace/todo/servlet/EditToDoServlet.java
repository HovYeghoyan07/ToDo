package am.itspace.todo.servlet;

import am.itspace.todo.model.ToDo;
import am.itspace.todo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editToDo")
public class EditToDoServlet extends HttpServlet {
    private ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo toDo = toDoService.getToDoById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("toDo", toDo);
        req.getRequestDispatcher("/WEB-INF/editToDo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        toDoService.update(id,title);
        resp.sendRedirect("/main");


    }
}
