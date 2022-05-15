package view.command.commands;

import controller.CollectionManager;
import controller.IdManager;
import controller.exceptions.EmptyCollectionException;
import model.*;
import model.Exceptions.IncorrectNameEnumException;
import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.console.ConsoleClient;
import view.exceptions.IncorrectInputException;

import java.util.ArrayList;
import java.util.Arrays;

public class UpdateId extends AbstractCommand {
    CollectionManager collectionManager;

    public UpdateId(CollectionManager collectionManager) {
        super("Update_id", "Обновляет значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

//    private boolean setNewParameters(StudyGroup studyGroup)throws IncorrectScriptException{
//        try {
//            ConsoleClient.println("Какие параметры группы вы хотите изменить?\n" +
//                    "Имя\n" +
//                    "Координаты\n" +
//                    "Количество студентов\n" +
//                    "Средняя оценка\n" +
//                    "Форма обучения\n" +
//                    "Семестр\n" +
//                    "Админ группы");
//            ConsoleClient.println("Запишите все изменяемые параметры в строчку через запятую");
//            String[] parameters = consoleClient.readLine().split(",");
//            return asker.changeParameters(parameters, studyGroup);
//        } catch (NoSuchElementException exception){
//            ConsoleClient.printError("Значение поля не распознано!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        } catch (IllegalStateException exception) {
//            ConsoleClient.printError("Непредвиденная ошибка!");
//            System.exit(0);
//        }
//        return false;
//    }

    @Override
    public Response execute(Request request) {
        try {
//                ConsoleClient.println("Введите id элемента, которого вы хотите изменить:");
////                if (Asker.getFileMode()){
////                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
////                    input = scriptScanner.nextLine().trim();
////                }else input = scanner.nextLine().trim();
            String[] params = request.getArgument().split(",");
            Integer inputInt = Integer.parseInt(params[0].trim());
            if (!IdManager.containsStudyGroupID(inputInt))
                throw new IncorrectInputException();
            StudyGroup studyGroup = collectionManager.getByID(inputInt);
            ArrayList<String> parameters = new ArrayList<>(Arrays.asList(params));
            parameters.remove(0);
            System.out.println(parameters);
            for (String parameter : parameters) {
                switch (parameter.toLowerCase().trim()) {
                    case "имя":
                        studyGroup.setName(request.getGroupDto().getName());
                        break;
                    case "координаты":
                        studyGroup.setCoordinates(new Coordinates(request.getGroupDto().getCoordinates().getX(),
                                request.getGroupDto().getCoordinates().getY()));
                        break;
                    case "количество студентов":
                        studyGroup.setStudentsCount(request.getGroupDto().getStudentsCount());
                        break;
                    case "средняя оценка":
                        studyGroup.setAverageMark(request.getGroupDto().getAverageMark());
                        break;
                    case "форма обучения":
                        studyGroup.setFormOfEducation(FormOfEducation.convert(request.getGroupDto().getFormOfEducation().getName()));
                        break;
                    case "семестр":
                        studyGroup.setSemesterEnum(Semester.equals(request.getGroupDto().getSemesterEnum().getName()));
                        break;
                    case "админ группы":
                        if (request.getGroupDto().getGroupAdmin().getWeight() == 0) studyGroup.setGroupAdmin(null);
                        else studyGroup.setGroupAdmin(
                                new Person(
                                        request.getGroupDto().getGroupAdmin().getName(),
                                        request.getGroupDto().getGroupAdmin().getBirthday(),
                                        request.getGroupDto().getGroupAdmin().getWeight()));
                        break;
                    default:
                        throw new IncorrectInputException();
                }
            }
            return new Response(true, "Параметры элемента успешно изменены!");
//            while (true){
//                if (setNewParameters(group)) {
//                    ConsoleClient.println("Параметры элемента успешно изменены!");
//                    return true;
//                }
//            }
        } catch (EmptyCollectionException exception) {
            ConsoleClient.printError("Коллекция пуста!");
            return new Response(true, "Коллекция пуста!");//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        } catch (IncorrectInputException exception) {
            ConsoleClient.printError("Такого id не существует!");
            return new Response(false, "Такого id не существует!");
        } catch (IncorrectNameEnumException e) {
            return new Response(false,"Данные введены некорректно!");
        }
//        catch (NoSuchElementException exception){
//            ConsoleClient.printError("Значение поля не распознано!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        } catch (IllegalStateException exception) {
//            ConsoleClient.printError("Непредвиденная ошибка!");
//            System.exit(0);
//        }
//        return false;

    }

    public String getMessage(){
        return "update id {element} - Обновляет значение элемента коллекции, id которого равен заданному";
    }
}
