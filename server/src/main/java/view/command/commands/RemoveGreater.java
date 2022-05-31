package view.command.commands;

import controller.CollectionManager;
import controller.exceptions.EmptyCollectionException;
import dto.StudyGroupDTO;
import model.*;
import model.Exceptions.IncorrectNameEnumException;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

public class RemoveGreater extends AbstractCommand {
    CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
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
                        new Person(groupDTO.getGroupAdmin().getName(), groupDTO.getGroupAdmin().getBirthday(), groupDTO.getGroupAdmin().getWeight()));
            }
            collectionManager.removeGreater(group);
            return new Response(true, "Все элементы больше заданного удалены!");
        } catch (EmptyCollectionException exception) {
            return new Response(true, "Коллекция пуста!");//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        } catch (IncorrectNameEnumException e) {
            return new Response(false, "Данные введены некорректно!");
        }
    }

    public String getMessage() {
        return "remove_any_by_semester_enum semesterEnum - Удалит из коллекции один элемент, значение поля semesterEnum которого эквивалентно заданному";
    }
}
