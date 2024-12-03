package am.itspace.todo.servlet;

import am.itspace.todo.model.Status;
import am.itspace.todo.model.ToDo;
import am.itspace.todo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/finishToDo")
public class FinishToDoServlet extends HttpServlet {
    private ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo toDo = toDoService.getToDoById(Integer.parseInt(req.getParameter("id")));
        if (toDo.getStatus() == Status.NEW) {
            toDo.setStatus(Status.DONE);
        }else {
            toDo.setStatus(Status.NEW);
        }
        toDoService.updateStatusToDo(toDo);
        resp.sendRedirect("/main");
    }
}
