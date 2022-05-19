package view.command.commands;

import controller.CollectionManager;
import controller.exceptions.EmptyCollectionException;
import dto.StudyGroupDTO;
import model.*;
import model.Exceptions.IncorrectNameEnumException;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

public class RemoveLower extends AbstractCommand {
    CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        StudyGroup group;
        try{
            StudyGroupDTO groupDTO = request.getGroupDto();
            if(groupDTO.getGroupAdmin().getWeight()==0){
                group = new StudyGroup(
                        groupDTO.getName(),
                        new Coordinates(groupDTO.getCoordinates().getX(),groupDTO.getCoordinates().getY()),
                        groupDTO.getStudentsCount(),
                        groupDTO.getAverageMark(),
                        FormOfEducation.convert(groupDTO.getFormOfEducation().getName()),
                        Semester.equals(groupDTO.getSemesterEnum().getName()),
                        null);
            }else {
                group = new StudyGroup(
                        groupDTO.getName(),
                        new Coordinates(groupDTO.getCoordinates().getX(),groupDTO.getCoordinates().getY()),
                        groupDTO.getStudentsCount(),
                        groupDTO.getAverageMark(),
                        FormOfEducation.convert(groupDTO.getFormOfEducation().getName()),
                        Semester.equals(groupDTO.getSemesterEnum().getName()),
                        new Person(groupDTO.getGroupAdmin().getName(),
                                groupDTO.getGroupAdmin().getBirthday(),
                                groupDTO.getGroupAdmin().getWeight()));}
//                ConsoleClient.println("Введите id элемента!");
////                if (Asker.getFileMode()){
////                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
////                    input = scriptScanner.nextLine().trim();
////                }else input = scanner.nextLine().trim();
//                int inputInt = Integer.parseInt(consoleClient.readLine());
//                if (!IdManager.containsStudyGroupID(inputInt)) throw new IncorrectInputException();
//                StudyGroupDTO group = collectionManager.getByID(inputInt);
            collectionManager.removeLower(group);
            return new Response(true, "Все элементы меньше заданного удалены!");
        } catch (EmptyCollectionException exception) {
            return new Response(true, "Коллекция пуста!");//Не уверен, что так должно быть.
                // Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        } catch (IncorrectNameEnumException e) {
                return new Response(false,"Данные введены некорректно!");
        }
//            catch (NoSuchElementException exception) {
//            ConsoleClient.printError("Значение поля не распознано!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        } catch (IllegalStateException exception) {
//            ConsoleClient.printError("Непредвиденная ошибка!");
//            System.exit(0);
//    }
//            return false;
    }

    public String getMessage() {
        return "remove_lower {element} - Удалит из коллекции все элементы, меньшие, чем заданный";
    }
}
