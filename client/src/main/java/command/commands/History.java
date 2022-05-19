package command.commands;

import client.Client;
import client.Communicate;
import command.AbstractCommand;
import command.exceptions.WrongCommandInputException;
import console.ConsoleClient;
import exceptions.IncorrectScriptException;
import request.Request;
import response.Response;
import utility.Asker;

import java.net.SocketException;

public class History extends AbstractCommand {
    Communicate communicate;

    public History(Communicate communicate) {
        super("History", "Выводит последние 11 команд (без их аргументов)");
        this.communicate = communicate;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        Request request = null;
        try{
            if (argument.isEmpty()) {
                request = new Request(null, "history", null);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println("\n"+response.getText());
                return response.getAnswer();
            }
            else throw new WrongCommandInputException();
        }
//        catch (SocketException exception){
//            Client.waitingConnection();
//            try {
//                communicate.send(request);
//            } catch (SocketException e) {
//                e.printStackTrace();
//            }
//        }
        catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage(){
        return "history - Выводит последние 11 команд (без их аргументов)";
    }
}
