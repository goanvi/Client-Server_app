package view.command.commands;

import controller.CollectionManager;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

public class Clear extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager manager) {
        super("clear", "очищает коллекцию");
        this.collectionManager = manager;
    }

    @Override
    public Response execute(Request request) {
        collectionManager.clearCollection();
        return new Response(true, "Очистка коллекции успешно проведена!");
    }

    public String getMessage() {
        return "clear - Очищает коллекцию";
    }
}
