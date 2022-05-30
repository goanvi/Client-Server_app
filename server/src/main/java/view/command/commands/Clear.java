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
    public Response execute(Request request){
//        try {
            collectionManager.clearCollection();
            return new Response(true,"Очистка коллекции успешно проведена!");

//        }catch (WrongCommandInputException exception){
//            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
//                    "команда не должна содержать символы после своего названия!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        }catch (NoSuchElementException exception){
//            ConsoleClient.printError("Значение поля не распознано!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        } catch (IllegalStateException exception) {
//            ConsoleClient.printError("Непредвиденная ошибка!");
//            System.exit(0);
//        }
//        return false;
    }

    public String getMessage() {
        return "clear - Очищает коллекцию";
    }
}
