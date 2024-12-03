package am.itspace.todo.servlet;

import am.itspace.todo.model.Status;
import am.itspace.todo.model.ToDo;
import am.itspace.todo.model.User;
import am.itspace.todo.service.ToDoService;
import am.itspace.todo.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/addTodo")
public class AddToDoServlet extends HttpServlet {
    ToDoService toDoService = new ToDoService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String todoText = req.getParameter("todoText");
        String dueDate = req.getParameter("dueDate");
        Date finishD = null;
        if (dueDate.isEmpty()) {
            dueDate = null;
        }else {
            finishD = new DateUtil().jsFromJava(dueDate);
        }
        toDoService.add(ToDo.builder()
                .title(todoText)
                .createdDate(new Date())
                .finishDate(finishD)
                .user(((User) req.getSession().getAttribute("user")))
                .status(Status.NEW)
                .build());

        resp.sendRedirect("/main");
    }

}
