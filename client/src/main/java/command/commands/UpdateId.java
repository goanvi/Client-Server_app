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
import java.util.Arrays;
import java.util.NoSuchElementException;

public class UpdateId extends AbstractCommand {
    Asker asker;
    ConsoleClient consoleClient;
    Communicate communicate;
    String[] parameters;

    public UpdateId(Asker asker, Communicate communicate, ConsoleClient consoleClient) {
        super("update_id", "Обновляет значение элемента коллекции, id которого равен заданному");
        this.asker = asker;
        this.consoleClient = consoleClient;
        this.communicate = communicate;
    }

    private boolean setNewParameters(StudyGroupDTO studyGroup)throws IncorrectScriptException {
        try {
            ConsoleClient.println("Какие параметры группы вы хотите изменить?\n" +
                    "Имя\n" +
                    "Координаты\n" +
                    "Количество студентов\n" +
                    "Средняя оценка\n" +
                    "Форма обучения\n" +
                    "Семестр\n" +
                    "Админ группы");
            ConsoleClient.println("Запишите все изменяемые параметры в строчку через запятую");
            parameters = consoleClient.readLine().split(",");
            return asker.changeParameters(parameters, studyGroup);
        } catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    @Override
    public boolean execute(String argument)throws IncorrectScriptException{
        Request request = null;
        try{
            if (!argument.isEmpty()){
                StudyGroupDTO groupDTO = new StudyGroupDTO();
                while (true){
                    if (setNewParameters(groupDTO)){
                        break;
                    }
                }
                String out = Arrays.toString(parameters).replaceAll("[\\[\\]]", "");
                request = new Request(groupDTO,"update_id", argument + "," + out);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println(response.getText());
                return response.getAnswer();
//                Integer inputInt = Integer.parseInt(argument.trim());
//                if (!IdManager.containsStudyGroupID(inputInt))
//                    throw new IncorrectInputException();
//                StudyGroup group = collectionManager.getByID(inputInt);
//                while (true){
//                    if (setNewParameters(group)) {
//                        ConsoleClient.println("Параметры элемента успешно изменены!");
//                        return true;
//                    }
//                }
            }else throw new WrongCommandInputException();
//        }catch (EmptyCollectionException exception){
//            ConsoleClient.printError("Коллекция пуста!");
//            return true;//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
//        }catch (IncorrectInputException exception){
//            ConsoleClient.printError("Такого id не существует!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
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
        } catch (NoSuchElementException exception){
            ConsoleClient.printError("Значение поля не распознано!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        } catch (IllegalStateException exception) {
            ConsoleClient.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
        return false;
    }

    public String getMessage(){
        return "update id {element} - Обновляет значение элемента коллекции, id которого равен заданному";
    }
}
