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
import java.util.NoSuchElementException;

public class Clear extends AbstractCommand {
    Communicate communicate;

    public Clear(Communicate communicate) {
        super("Clear", "Очищает коллекцию");
        this.communicate = communicate;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        Request request = null;
        try {
            if (argument.isEmpty()){
                request = new Request(null,"clear", null);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println("\n"+response.getText()+"\n");
                //Придумать как передавать request на сервер и получать обратно response
                return response.getAnswer();
            }
            else throw new WrongCommandInputException();

        }catch (WrongCommandInputException exception){
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }catch (SocketException exception){
            Client.waitingConnection();
            try {
                communicate.send(request);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    public String getMessage() {
        return "clear - Очищает коллекцию";
    }
}
