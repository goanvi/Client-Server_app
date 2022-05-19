package view.command.commands;

import controller.CollectionManager;
import controller.exceptions.EmptyCollectionException;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

public class SumOfStudentsCount extends AbstractCommand {
    CollectionManager collectionManager;

    public SumOfStudentsCount(CollectionManager collectionManager) {
        super("sum_of_students_count", "выводит сумму значений поля studentsCount для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        String out;
        try{
            out = "Общее количество студентов: "+collectionManager.getSumOfStudentsCount() + "\n"+
                    "Количество студентов успешно выведено!";
            return new Response(true, out);
        }catch (EmptyCollectionException exception){
            return new Response(true, "Коллекция пуста!");//Не уверен, что так должно быть.
            // Пока что считаю, что пустая коллекция не повод выбрасывать ошибку выполнения
        }
    }

    public String getMessage(){
        return "sum_of_students_count - Выводит сумму значений поля studentsCount для всех элементов коллекции";
    }
}
