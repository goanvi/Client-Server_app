package command.commands;

import client.Client;
import client.Communicate;
import command.AbstractCommand;
import command.exceptions.WrongCommandInputException;
import console.ConsoleClient;
import dto.StudyGroupDTO;
import exceptions.IncorrectScriptException;
import request.Request;
import response.Response;
import utility.Asker;

import java.net.SocketException;
import java.util.NoSuchElementException;

public class RemoveLower extends AbstractCommand {
    Communicate communicate;
    Asker asker;

    public RemoveLower(Asker asker, Communicate communicate) {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный");
        this.communicate = communicate;
        this.asker = asker;
    }

    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        Request request = null;
        try {
            if (argument.isEmpty()) {
                StudyGroupDTO group = new StudyGroupDTO(
                        asker.askName(),
                        asker.askCoordinates(),
                        asker.askStudentsCount(),
                        asker.askAverageMark(),
                        asker.askFromOfEducation(),
                        asker.askSemester(),
                        asker.askPerson());
                request = new Request(group, "remove_lower", null);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println(response.getText());
                return response.getAnswer();
            } else throw new WrongCommandInputException();
        }
//        catch (SocketException exception){
//            Client.waitingConnection();
//            try {
//                communicate.send(request);
//            } catch (SocketException e) {
//                e.printStackTrace();
//            }
//        }
        catch (WrongCommandInputException exception) {
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (NumberFormatException exception) {
            ConsoleClient.printError("Значением поля должно являться число!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (NoSuchElementException exception) {
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    public String getMessage() {
        return "remove_lower {element} - Удалит из коллекции все элементы, меньшие, чем заданный";
    }
}
