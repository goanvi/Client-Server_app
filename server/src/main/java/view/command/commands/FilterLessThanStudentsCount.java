package view.command.commands;

import controller.CollectionManager;
import controller.exceptions.EmptyCollectionException;
import model.StudyGroup;
import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.utility.Formatter;

import java.util.List;

public class FilterLessThanStudentsCount extends AbstractCommand {
    private final CollectionManager collectionManager;

    public FilterLessThanStudentsCount(CollectionManager manager) {
        super("filter_less_than_students_count", "выводит элементы, значение поля studentsCount" +
                " которых меньше заданного");
        this.collectionManager = manager;
    }

    @Override
    public Response execute(Request request){
        long studCount;
        StringBuilder out = new StringBuilder();
        try {
            studCount = Long.parseLong(request.getArgument().trim());
            List<StudyGroup> output = collectionManager.getLessThanStudentsCount(studCount);
            if (output.isEmpty()) return new Response(true, "Во всех группах количество человек больше");
            else {
                output.forEach((group)-> out.append(Formatter.format(group)).append("\n"));
                return new Response(true, out + "Элементы коллекции успешно выведены!");
            }
        }catch (EmptyCollectionException exception){
            return new Response(true, "Коллекция пуста!");//Не уверен, что так должно быть. Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        }catch (NumberFormatException exception) {
            return new Response(false, "Значением поля должно являться число!");
        }
    }

    public String getMessage() {
        return "filter_less_than_students_count studentsCount - Выводит элементы, значение поля studentsCount которых меньше заданного";
    }
}
