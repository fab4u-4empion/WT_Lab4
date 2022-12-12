package commands.impl;

import commands.Command;
import entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import services.Impl.UserServiceImpl;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            String userLogin = req.getParameter("RegUserLogin");
            String userPassword = req.getParameter("RegPassword");
            String userCheckPassword = req.getParameter("RegCheckPassword");

            if (!userPassword.equals(userCheckPassword)) {
                return;
            }
            userPassword = DigestUtils.md5Hex(userPassword);
            UserService userService = new UserServiceImpl();
            User user = new User(userLogin, userPassword, 2);
            if (userService.createNewUser(user)) {
                req.getSession(true).setAttribute("userId", user.getId());
                req.getSession().setAttribute("userLogin", user.getLogin());
                req.getSession().setAttribute("userRole", user.getRole());
                res.sendRedirect("FrontController?COMMAND=GET_ROOMS");
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        } catch (SQLException | ServletException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
