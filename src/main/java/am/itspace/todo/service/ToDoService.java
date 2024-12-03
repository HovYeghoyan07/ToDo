package am.itspace.todo.service;

import am.itspace.todo.db.DbConnectionProvider;
import am.itspace.todo.model.Status;
import am.itspace.todo.model.ToDo;
import am.itspace.todo.model.User;
import am.itspace.todo.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ToDoService {
    private Connection connection = DbConnectionProvider.getInstance().getConnection();
    UserService userService = new UserService();

    public void add(ToDo toDo) {
        java.sql.Date finishD = null;
        if (toDo.getFinishDate() != null) {
            finishD = new java.sql.Date(toDo.getFinishDate().getTime());
        }
        try {
            String sql = "INSERT INTO todo(title,created_date,finish_date,user_id,status) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setDate(2, new java.sql.Date(toDo.getCreatedDate().getTime()));
            preparedStatement.setDate(3, finishD);
            preparedStatement.setInt(4, toDo.getUser().getId());
            preparedStatement.setString(5, toDo.getStatus().NEW.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<ToDo> getToDosByUserId(int user_id) {
        List<ToDo> results = new ArrayList<>();
        String sql = "SELECT * FROM todo WHERE user_id = " + user_id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                results.add(ToDo.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .createdDate(resultSet.getTimestamp(3))
                        .finishDate(resultSet.getTimestamp(4))
                        .user(userService.getUserById(resultSet.getInt(5)))
                        .status(Status.valueOf(resultSet.getString(6)))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ToDo getToDoById(int id) {
        ToDo result = null;
        String sql = "SELECT * FROM todo WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .createdDate(resultSet.getTimestamp(3))
                        .finishDate(resultSet.getTimestamp(4))
                        .user(userService.getUserById(resultSet.getInt(5)))
                        .status(Status.valueOf(resultSet.getString(6)))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    public void deleteToDo(int toDoId) {
        String sql = "DELETE FROM todo WHERE id = " + toDoId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, String title) {
        String sql = "UPDATE todo SET title = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusToDo(ToDo toDo) {
        try {
            String sql = "UPDATE todo SET status = ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, toDo.getStatus().name());
            preparedStatement.setInt(2, toDo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
