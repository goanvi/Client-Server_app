
package view.command.commands;

import controller.CommandManager;
import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.console.ConsoleClient;


public class Help extends AbstractCommand {
    private CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "выводит информацию по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request){
        StringBuilder stringBuilder = new StringBuilder();
            commandManager.getCommands().forEach((key,value) -> stringBuilder.append(key).append("  -  ").append(value.getDescription()).append("\n"));
            return new Response(true,stringBuilder + "\n" +"Справка по командам успешно выведена");
    }

    public String getMessage(){
        return "help - Выводит информацию по доступным командам";
    }
}

