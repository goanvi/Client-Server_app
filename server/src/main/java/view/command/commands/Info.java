package view.command.commands;

import controller.CollectionManager;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

import java.time.format.DateTimeFormatter;

public class Info extends AbstractCommand {
    CollectionManager collectionManager;

    public Info(CollectionManager manager) {
        super("info", "выводит в стандартный поток вывода информацию о коллекции" +
                " (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = manager;
    }

    @Override
    public Response execute(Request request){
        String stringBuilder =
                "Коллекция типа: " + collectionManager.getCollectionType() + "\n" +
                "Размер коллекции: " + collectionManager.getCollectionSize() + "\n" +
                "Время последней инициализации: " + collectionManager.getLastLoadTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n" +
                "Информация о коллекции успешно выведена!";
        return new Response(true, stringBuilder);
    }

    public String getMessage() {
        return "info - Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
