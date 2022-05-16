
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


public class Help extends AbstractCommand {
    Communicate communicate;

    public Help(Communicate communicate) {
        super("Help", "Выводит информацию по доступным командам");
        this.communicate = communicate;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        Request request = null;
        Response response;
        try {
            if (argument.isEmpty()){
                request = new Request(null , "help", null);
                communicate.send(request);
                response = communicate.get();
                ConsoleClient.println("\n"+response.getText()+"\n");
                return response.getAnswer();
            }
            else throw new WrongCommandInputException();
        }catch (NullPointerException nullPointerException){
            Client.waitingConnection();
            response= communicate.get();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (SocketException exception){
            Client.waitingConnection();
        }
        return false;
    }

    public String getMessage(){
        return "help - Выводит информацию по доступным командам";
    }
}

