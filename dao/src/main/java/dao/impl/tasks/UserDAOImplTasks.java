package dao.impl.tasks;

import dao.UserDAO;
import databseConnection.DatabaseConfig;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImplTasks implements UserDAO {

    private static final String CREATE_NEW_USER_QUERY = "INSERT INTO users (user_login, user_password, user_role) VALUES (?, ?, ?)";
    private static final String FIND_USER_QUERY = "SELECT * FROM users WHERE user_login = ? AND user_password = ?";

    @Override
    public boolean createNewUser(User user) throws SQLException {
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_USER_QUERY);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getRole());
        return preparedStatement.executeUpdate() != 0;
    }

    @Override
    public User findUser(User user) throws SQLException {
        Connection connection = DatabaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_QUERY);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setRole(resultSet.getInt(4));
        }
        if (user.getLogin() != null) {
            return user;
        }
        return null;
    }
}
