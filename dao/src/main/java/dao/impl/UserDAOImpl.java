package dao.impl;

import dao.UserDAO;
import dao.impl.tasks.UserDAOImplTasks;
import entity.User;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public class UserDAOImpl implements Callable<User> {

    private String command;
    private User user;

    public UserDAOImpl(String command, User user) {
        this.command = command;
        this.user = user;
    }

    @Override
    public User call() {
        switch (command) {
            case "createNewUser": {
                try {
                    UserDAO dao = new UserDAOImplTasks();
                    dao.createNewUser(user);
                    return null;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
            case "findUser": {
                try {
                    UserDAO dao = new UserDAOImplTasks();
                    return dao.findUser(user);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;
            }
        }
        return null;
    }
}
