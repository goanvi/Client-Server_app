package view.command.commands;

import controller.CollectionManager;
import controller.FileWorker;
import controller.ParserCSV;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

import java.io.IOException;

public class Save extends AbstractCommand {
    CollectionManager collectionManager;
    FileWorker fileWorker;

    public Save(CollectionManager manager, FileWorker fileWorker) {
        super("save", "сохраняет коллекцию в файл");
        this.collectionManager = manager;
        this.fileWorker = fileWorker;
    }

    @Override
    public Response execute(Request request){
        try {
//                ConsoleClient.println("Введите ссылку на файл!");
//                if (Asker.getFileMode()){
//                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//                    input = scriptScanner.nextLine().trim();
//                }else input = scanner.nextLine().trim();
            fileWorker.writer(ParserCSV.toCSV(collectionManager.getCollection()));
            collectionManager.setLastSaveTime();
//                collectionManager.saveCollection(consoleClient.readLine());
            return new Response(true,"Коллекция успешно сохранена!");
//            {
//                FileWorker fileWorker1 = new FileWorker(argument);
//                fileWorker1.writer(ParserCSV.toCSV(collectionManager.getCollection()));
//                collectionManager.setLastSaveTime();
//                ConsoleClient.println("Коллекция успешно сохранена!");
//            }


        }catch (SecurityException exception){
            return new Response(false,"Ошибка прав доступа к файлу!");
        }catch (IOException exception){
            return new Response(false,"Не удалось сохранить данные в файл!");
        }
//        catch (NoSuchElementException exception){
//            ConsoleClient.printError("Значение поля не распознано!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        } catch (IllegalStateException exception) {
//            ConsoleClient.printError("Непредвиденная ошибка!");
//            System.exit(0);
//        }
//        return false;
    }

    public String getMessage(){
        return "save - Сохраняет коллекцию в файл";
    }
}
