package commands.impl;

import commands.Command;
import services.Impl.OrderServiceImpl;
import services.Impl.RoomServiceImpl;
import services.OrderService;
import services.RoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReserveRoomCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {

        try {
            int userId = (int) req.getSession().getAttribute("userId");
            int roomId = Integer.parseInt(req.getParameter("roomId"));

            OrderService orderService = new OrderServiceImpl();
            orderService.orderRoom(userId, roomId);

            RoomService roomService = new RoomServiceImpl();
            roomService.updateRoomStatus(roomId, true);

            req.getRequestDispatcher("FrontController?COMMAND=GET_ROOMS").forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
