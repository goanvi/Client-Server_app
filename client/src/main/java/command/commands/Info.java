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

public class Info extends AbstractCommand {
    Communicate communicate;

    public Info(Communicate communicate) {
        super("info", "Выводит в стандартный поток вывода информацию о коллекции" +
                " (тип, дата инициализации, количество элементов и т.д.)");
        this.communicate = communicate;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        Request request = null;
        try {
            if (argument.isEmpty()) {
                request = new Request(null, "info", null);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println("\n"+response.getText()+"\n");
                return response.getAnswer();
            } else throw new WrongCommandInputException();
        }catch (SocketException exception){
            Client.waitingConnection();
            try {
                communicate.send(request);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        } catch (WrongCommandInputException exception) {
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage() {
        return "info - Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
