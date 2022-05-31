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
    public Response execute(Request request) {
        StudyGroup group;
        try {
            StudyGroupDTO groupDTO = request.getGroupDto();
            if (groupDTO.getGroupAdmin().getWeight() == 0) {
                group = new StudyGroup(
                        groupDTO.getName(),
                        new Coordinates(groupDTO.getCoordinates().getX(), groupDTO.getCoordinates().getY()),
                        groupDTO.getStudentsCount(),
                        groupDTO.getAverageMark(),
                        FormOfEducation.convert(groupDTO.getFormOfEducation().getName()),
                        Semester.equals(groupDTO.getSemesterEnum().getName()),
                        null);
            } else {
                group = new StudyGroup(
                        groupDTO.getName(),
                        new Coordinates(groupDTO.getCoordinates().getX(), groupDTO.getCoordinates().getY()),
                        groupDTO.getStudentsCount(),
                        groupDTO.getAverageMark(),
                        FormOfEducation.convert(groupDTO.getFormOfEducation().getName()),
                        Semester.equals(groupDTO.getSemesterEnum().getName()),
                        new Person(groupDTO.getGroupAdmin().getName(),
                                groupDTO.getGroupAdmin().getBirthday(),
                                groupDTO.getGroupAdmin().getWeight()));
            }
            collectionManager.removeLower(group);
            return new Response(true, "Все элементы меньше заданного удалены!");
        } catch (EmptyCollectionException exception) {
            return new Response(true, "Коллекция пуста!");//Не уверен, что так должно быть.
            // Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        } catch (IncorrectNameEnumException e) {
            return new Response(false, "Данные введены некорректно!");
        }
    }

    public String getMessage() {
        return "remove_lower {element} - Удалит из коллекции все элементы, меньшие, чем заданный";
    }
}
