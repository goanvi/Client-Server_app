package controller;

import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.command.commands.History;

import java.util.Map;

public class CommandManager {
    private final int HISTORY_BUFFER_SIZE = 11;
    String[] historyBuffer = new String[HISTORY_BUFFER_SIZE];
    Map<String, AbstractCommand> commands;

    public CommandManager(Map<String, AbstractCommand> commands) {
        this.commands = commands;
    }

    public Response callCommand(Request request){
        Response response = commands.get(request.getName()).execute(request);
        if (response.getAnswer()) History.addToHistory(request.getName());
        return response;
//        try {
//            int count = 0;
//            for (Map.Entry<String, AbstractCommand> commandMap : commands.entrySet()) {
//                if (commandMap.getKey().equalsIgnoreCase(request.getName())) {
//                    count += 1;
//                    response = commandMap.getValue().execute(request);
//                    if (response.getAnswer()) History.addToHistory(request.getName());
////                    else throw new ExecuteCommandException();
//                }
//            }
//            if (count == 0) throw new WrongCommandInputException();
//            if (commands.containsKey(command)){
//                answer = commands.get(command).execute(argument);
//                if (answer) History.addToHistory(command);
//            } else throw new WrongCommandInputException();

//        } catch (WrongCommandInputException exception) {
//            ConsoleClient.printError("Некорректный ввод команды!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        } catch (ExecuteCommandException exception) {
//            ConsoleClient.printError(command + ": ошибка в выполнении команды!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        }
    }

//    public boolean callCommandAdd(String argument) {
//        return commandAdd.execute(argument);
//    }
//
//    public boolean callCommandClear(String argument) {
//        return commandClear.execute(argument);
//    }
//
//    public boolean callCommandExit(String argument) {
//        return commandExit.execute(argument);
//    }
//
//    public boolean callCommandFilterLessThanStudentsCount(String argument) {
//        return commandFLTSC.execute(argument);
//    }
//
//    public boolean callCommandHelp(String argument) {
//        if (commandHelp.execute(argument)) {
//            for (AbstractCommand command : commands) {
//                ConsoleClient.println(command.getName() + "  -  " + command.getDescription());
//            }
//            ConsoleClient.println("Команда успешно выполнена!");
//            return true;
//        }
//        return false;
//    }
//
//    public boolean callCommandHistory(String argument) { //Что делать если весь массив пуст?
//        if (commandHistory.execute(argument)) {
//            if (historyBuffer[0] != null) {
//                for (String command : historyBuffer) {
//                    ConsoleClient.println(command);
//                }
//                ConsoleClient.println("Команда успешно выполнена!");
//                return true;
//            } else ConsoleClient.println("Ни одной команды еще не было инициализировано!");
//        }
//        return false;
//    }
//
//    public boolean callCommandInfo(String argument){
//        return commandInfo.execute(argument);
//    }
//
//    public boolean callCommandRemoveAnyBySemesterEnum(String argument){
//        return commandRABSE.execute(argument);
//    }
//
//    public boolean callCommandRemoveById(String argument){
//        return commandRBI.execute(argument);
//    }
//    public boolean callCommandRemoveGreater(String argument){
//        return commandRG.execute(argument);
//    }
//
//    public boolean callCommandRemoveLower(String argument){
//        return commandRL.execute(argument);
//    }
//
//    public boolean callCommandSave(String argument){
//        return commandSave.execute(argument);
//    }
//
//    public boolean callCommandShow(String argument){
//        return commandShow.execute(argument);
//    }
//
//    public boolean callCommandSumOfStudentsCount(String argument){
//        return commandSOSC.execute(argument);
//    }
//
//    public boolean callCommandUpdateID(String argument){return commandUI.execute(argument);}
//
//    public void addCommand(AbstractCommand command) {
//        commandsName.put(command.getName(), command.getDescription());
//    }

    public void addToHistory(String command) {
        if (commands.containsKey(command)) {
            System.arraycopy(historyBuffer, 0, historyBuffer, 1, HISTORY_BUFFER_SIZE - 1);
//            for (int i = HISTORY_BUFFER_SIZE - 1; i > 0; i--) {
//                historyBuffer[i] = historyBuffer[i - 1];
//            }
            historyBuffer[0] = command;
        }
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public String[] getHistoryBuffer() {
        return historyBuffer;
    }
}
