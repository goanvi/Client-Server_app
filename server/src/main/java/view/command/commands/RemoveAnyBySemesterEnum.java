package view.command.commands;

import controller.CollectionManager;
import controller.exceptions.EmptyCollectionException;
import model.Exceptions.IncorrectNameEnumException;
import model.Semester;
import model.StudyGroup;
import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.exceptions.IncorrectInputException;

public class RemoveAnyBySemesterEnum extends AbstractCommand {
    CollectionManager collectionManager;

    public RemoveAnyBySemesterEnum(CollectionManager manager) {
        super("remove_any_by_semester_enum {Три, Пять, Семь}", "удаляет из коллекции один элемент," +
                " значение поля semesterEnum которого эквивалентно заданному");
        this.collectionManager = manager;
    }

    @Override
    public Response execute(Request request) {
        try {
            Semester semester = Semester.equals(request.getArgument().trim());
            StudyGroup studyGroup = collectionManager.getAnyBySemesterEnum(semester);
            if (studyGroup == null) throw new IncorrectInputException();
            collectionManager.remove(collectionManager.getAnyBySemesterEnum(semester));
            return new Response(true, "Элемент успешно удален!");
        } catch (EmptyCollectionException exception) {
            return new Response(true, "Коллекция пуста!");//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        } catch (IncorrectNameEnumException exception) {
            return new Response(false, "Семестр обучения введен неверно!");
        } catch (IncorrectInputException e) {
            return new Response(false, "Объекта с таким семестром обучения не найдено!");
        }
    }

    public String getMessage() {
        return "remove_any_by_semester_enum semesterEnum - Удаляет из коллекции один элемент," +
                " значение поля semesterEnum которого эквивалентно заданному";
    }
}
