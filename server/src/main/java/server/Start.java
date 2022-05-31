package server;

import controller.CommandManager;
import request.Request;
import response.Response;

import java.net.SocketException;

public class Start {
    Communicate communicate;
    CommandManager commandManager;

    public Start(Communicate communicate, CommandManager commandManager) {
        this.communicate = communicate;
        this.commandManager = commandManager;
    }

    public void start() {
        while (true) {
            try {
                Request request = communicate.getRequest();
                Response response = null;
                response = commandManager.callCommand(request);
                communicate.sendResponse(response);
            } catch (SocketException e) {
                Connect.connect();
                communicate = new Communicate(Connect.getSocket());
            }
        }
    }
}
