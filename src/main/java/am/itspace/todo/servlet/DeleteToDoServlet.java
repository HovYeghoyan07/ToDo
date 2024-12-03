package am.itspace.todo.servlet;

import am.itspace.todo.model.User;
import am.itspace.todo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteToDo")
public class DeleteToDoServlet extends HttpServlet {
    private ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        toDoService.deleteToDo(id);
        resp.sendRedirect("/main");
    }
}
