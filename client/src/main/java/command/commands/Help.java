
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

import java.io.IOException;
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
                if (response==null) return false;
                ConsoleClient.println("\n"+response.getText());
                return response.getAnswer();
            }
            else throw new WrongCommandInputException();
//        }catch (NullPointerException nullPointerException){
//            System.out.println("xnj 'nj ");
//            Client.waitingConnection();
//            response= communicate.get();
        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
//        catch (SocketException exception){
//            System.out.println("в команде");
//            System.out.println(exception.getMessage());
//        }
        return false;
    }

    public String getMessage(){
        return "help - Выводит информацию по доступным командам";
    }
}

