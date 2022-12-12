package commands.impl;

import commands.Command;
import services.Impl.RoomServiceImpl;
import services.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewRoomCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            int roomNumber = Integer.parseInt(req.getParameter("roomNumber"));
            RoomService roomService = new RoomServiceImpl();
            roomService.addNewRoom(roomNumber);
            res.sendRedirect("FrontController?COMMAND=GET_ROOMS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
