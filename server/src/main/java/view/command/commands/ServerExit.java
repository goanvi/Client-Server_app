package view.command.commands;

import controller.CollectionManager;
import controller.FileWorker;
import controller.ParserCSV;
import request.Request;
import response.Response;
import view.command.AbstractCommand;

import java.io.IOException;

public class ServerExit extends AbstractCommand {
    CollectionManager collectionManager;
    FileWorker fileWorker;

    public ServerExit(CollectionManager manager, FileWorker fileWorker) {
        super("server_exit", "закрывает сервер с сохранением прогресса");
        this.collectionManager = manager;
        this.fileWorker = fileWorker;
    }

    @Override
    public Response execute(Request request) {
        try {
            fileWorker.writer(ParserCSV.toCSV(collectionManager.getCollection()));
            System.exit(0);
            return null;

        }catch (SecurityException exception){
            System.out.println("Ошибка прав доступа к файлу!");
            return new Response(false,"Ошибка прав доступа к файлу!");
        }catch (IOException exception){
            System.out.println("Не удалось сохранить данные в файл!");
            return new Response(false,"Не удалось сохранить данные в файл!");
        }
    }
}
