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
    public Response execute(Request request) {
        try {
            fileWorker.writer(ParserCSV.toCSV(collectionManager.getCollection()));
            collectionManager.setLastSaveTime();
            return new Response(true, "Коллекция успешно сохранена!");
        } catch (SecurityException exception) {
            return new Response(false, "Ошибка прав доступа к файлу!");
        } catch (IOException exception) {
            return new Response(false, "Не удалось сохранить данные в файл!");
        }
    }

    public String getMessage() {
        return "save - Сохраняет коллекцию в файл";
    }
}
