package view.command.commands;

import request.Request;
import response.Response;
import view.command.AbstractCommand;

import java.util.LinkedList;
import java.util.List;

public class History extends AbstractCommand {
    static private final int HISTORY_BUFFER_SIZE = 11;
    static String[] historyBuffer = new String[HISTORY_BUFFER_SIZE];
    static LinkedList<String> buf = new LinkedList<>();

    public History() {
        super("history", "выводит последние 11 команд (без их аргументов)");
    }

    public static void addToHistory(String command) {
        if (buf.size()>HISTORY_BUFFER_SIZE){
            buf.addFirst(command);
            buf.removeLast();
        }
        else buf.addFirst(command);
//        System.arraycopy(historyBuffer, 0, historyBuffer, 1, HISTORY_BUFFER_SIZE - 1);
//        historyBuffer[0] = command;
    }

    @Override
    public Response execute(Request request){
        StringBuilder stringBuilder = new StringBuilder();
        if (buf.isEmpty()) return new Response(true, "Еще не было реализованно ни одной команды!");
        buf.forEach((command)-> stringBuilder.append(command).append("\n"));
        return new Response(true,stringBuilder + "\n" + "История команд успешно выведена!");
//            if (historyBuffer[0]==null) {
//                return new Response(true, "Еще не было реализованно ни одной команды!");
//            }
//            for (String command: historyBuffer){
//                if (command != null) stringBuilder.append(command).append("\n");
//            }
//            return new Response(true,stringBuilder + "\n" + "История команд успешно выведена!");
    }

    public String getMessage(){
        return "history - Выводит последние 11 команд (без их аргументов)";
    }
}
