package commands.impl;

import commands.Command;
import services.Impl.OrderServiceImpl;
import services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserReservationsCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Here");
        try {
            int userId = (int) req.getSession().getAttribute("userId");
            OrderService service = new OrderServiceImpl();
            req.getSession().setAttribute("orders", service.getUserOrders(userId));
            if((int)req.getSession().getAttribute("userRole") == 1){
                req.getRequestDispatcher("adminPage.jsp").forward(req, res);
            }
            else{
                req.getRequestDispatcher("userPage.jsp").forward(req, res);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
