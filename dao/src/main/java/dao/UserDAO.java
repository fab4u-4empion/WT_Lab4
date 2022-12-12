package dao;

import entity.User;

import java.sql.SQLException;

public interface UserDAO{
    boolean createNewUser(User user) throws SQLException;
    User findUser(User user) throws SQLException;
}
