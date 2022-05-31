package controller;

import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.command.commands.History;

import java.net.SocketException;
import java.util.Map;

public class CommandManager {
    Map<String, AbstractCommand> commands;

    public CommandManager(Map<String, AbstractCommand> commands) {
        this.commands = commands;
    }

    public Response callCommand(Request request) throws SocketException {
        if (request == null) throw new SocketException("Client is disconnected");
        Response response = commands.get(request.getName()).execute(request);
        if (response.getAnswer()) History.addToHistory(request.getName());
        return response;
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }
}
