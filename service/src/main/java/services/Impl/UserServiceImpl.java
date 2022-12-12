package services.Impl;

import dao.impl.UserDAOImpl;
import entity.User;
import services.UserService;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserServiceImpl implements UserService {

    ExecutorService pool = Executors.newFixedThreadPool(3);

    @Override
    public boolean createNewUser(User user) throws SQLException {
        try {
            Callable<User> createNewUserCallable = new UserDAOImpl("createNewUser", user);
            boolean res = pool.submit(createNewUserCallable) != null;
            pool.shutdown();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findUser(User user) throws SQLException {
        try {
            Callable<User> findUserCallable = new UserDAOImpl("findUser", user);
            User res =pool.submit(findUserCallable).get();
            pool.shutdown();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
