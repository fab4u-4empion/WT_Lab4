package commands;

import commands.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandsMap {
    Map<String, Command> commands = new HashMap<>();

    public CommandsMap(){
        init();
    }

    private void init(){
        commands.put("FIND_USER", new LoginCommand());
        commands.put("CREATE_NEW_USER", new RegisterCommand());
        commands.put("LOG_OUT_COMMAND", new LogOutCommand());
        commands.put("GET_ROOMS", new GetRoomsCommand());
        commands.put("RESERVE_ROOM", new ReserveRoomCommand());
        commands.put("GET_RESERVATIONS", new GetUserReservationsCommand());
        commands.put("DELETE_RESERVATION", new DeleteOrderCommand());
        commands.put("UNRESERVE_ROOM", new UnreserveRoomCommand());
        commands.put("ADD_NEW_ROOM", new AddNewRoomCommand());
    }

    public Command findCommand(String choice){
        return commands.get(choice);
    }


}
