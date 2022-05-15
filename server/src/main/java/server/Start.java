package server;

import controller.CommandManager;
import request.Request;
import response.Response;

public class Start {
    Communicate communicate;
    CommandManager commandManager;
    public Start (Communicate communicate, CommandManager commandManager){
        this.communicate = communicate;
        this.commandManager = commandManager;
    }

    public void start(){
        while (true){
            Request request = communicate.getRequest();
            Response response = commandManager.callCommand(request);
            communicate.sendResponse(response);
        }
    }
}
