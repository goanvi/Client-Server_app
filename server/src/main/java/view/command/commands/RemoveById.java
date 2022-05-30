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
    public Response execute(Request request){
        try{
//                ConsoleClient.println("Ведите id элемента!");
////                if (Asker.getFileMode()){
////                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
////                    input = scriptScanner.nextLine().trim();
////                }else input = scanner.nextLine().trim();
//                input = consoleClient.readLine();
            int id = Integer.parseInt(request.getArgument().trim());
            if (!collectionManager.getCollection().removeIf(studyGroup -> studyGroup.getID()==id))
                throw new IncorrectInputException();
//            ConsoleClient.println("Элемент успешно удален!");
            return new Response(true,"Элемент успешно удален!");
        }catch (IncorrectInputException exception){
            return new Response(false, "Такого id не существует!");
        }catch (NumberFormatException exception){
            return new Response(false, "Значением поля должно являться число!");
        }
        //Дальше хз
//        catch (NoSuchElementException exception){
//            ConsoleClient.printError("Значение поля не распознано!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        } catch (IllegalStateException exception) {
//            ConsoleClient.printError("Непредвиденная ошибка!");
//            System.exit(0);
//        }
//        return false;
    }

    public String getMessage() {
        return "remove_by_id  - Удаляет элемент из коллекции по его id";
    }
}
