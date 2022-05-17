package view.command.commands;

import request.Request;
import response.Response;
import view.command.AbstractCommand;

public class Exit extends AbstractCommand {
    public Exit() {
        super("exit", "Завершает программу (без сохранения в файл)");
    }

    @Override
    public Response execute(Request request){


            System.exit(0);
//            ConsoleClient.println("Выход из программы успешно выполнен!");
//            return true;
//
//            else throw new WrongCommandInputException();


        return null;
    }

    public String getMessage(){
        return "exit - Завершает программу (без сохранения в файл)";
    }
}
