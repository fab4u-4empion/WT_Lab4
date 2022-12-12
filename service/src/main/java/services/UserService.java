package services;

import entity.User;

import java.sql.SQLException;

public interface UserService {
    boolean createNewUser(User user) throws SQLException;
    User findUser(User user) throws SQLException;

}
