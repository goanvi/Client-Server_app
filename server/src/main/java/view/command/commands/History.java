package view.command.commands;

import request.Request;
import response.Response;
import view.command.AbstractCommand;

public class History extends AbstractCommand {
    static private final int HISTORY_BUFFER_SIZE = 11;
    static String[] historyBuffer = new String[HISTORY_BUFFER_SIZE];

    public History() {
        super("History", "Выводит последние 11 команд (без их аргументов)");
    }

    public static void addToHistory(String command) {
        System.arraycopy(historyBuffer, 0, historyBuffer, 1, HISTORY_BUFFER_SIZE - 1);
//            for (int i = HISTORY_BUFFER_SIZE - 1; i > 0; i--) {
//                historyBuffer[i] = historyBuffer[i - 1];
//            }
        historyBuffer[0] = command;
    }

    @Override
    public Response execute(Request request){
        StringBuilder stringBuilder = new StringBuilder();
            if (historyBuffer[0]==null) {
                return new Response(true, "Еще не было реализованно ни одной команды!");
            }
            for (String command: historyBuffer){
                if (command != null) stringBuilder.append(command).append("\n");
            }
            return new Response(true,stringBuilder + "\n" + "История команд успешно выведена!");
    }

    public String getMessage(){
        return "history - Выводит последние 11 команд (без их аргументов)";
    }
}
