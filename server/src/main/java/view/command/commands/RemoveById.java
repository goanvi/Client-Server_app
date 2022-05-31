package view.command.commands;

import controller.CollectionManager;
import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.exceptions.IncorrectInputException;

public class RemoveById extends AbstractCommand {
    CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удаляет элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            int id = Integer.parseInt(request.getArgument().trim());
            if (!collectionManager.getCollection().removeIf(studyGroup -> studyGroup.getID() == id))
                throw new IncorrectInputException();
            return new Response(true, "Элемент успешно удален!");
        } catch (IncorrectInputException exception) {
            return new Response(false, "Такого id не существует!");
        } catch (NumberFormatException exception) {
            return new Response(false, "Значением поля должно являться число!");
        }
    }

    public String getMessage() {
        return "remove_by_id  - Удаляет элемент из коллекции по его id";
    }
}
