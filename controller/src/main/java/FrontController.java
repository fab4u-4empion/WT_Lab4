import commands.Command;
import commands.CommandsMap;
import commands.impl.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse res){
        String choice = req.getParameter("COMMAND");
        CommandsMap commandsMap = new CommandsMap();
        Command command = commandsMap.findCommand(choice);
        command.execute(req, res);
    }
}
