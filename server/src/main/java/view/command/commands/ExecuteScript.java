package view.command.commands;

import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.console.ConsoleClient;

public class ExecuteScript extends AbstractCommand {
    private ConsoleClient consoleClient;

    public ExecuteScript(ConsoleClient consoleClient) {
        super("Execute_script", "Считывает и исполняет скрипт из указанного файла");
        this.consoleClient = consoleClient;
    }

    @Override
    public Response execute(Request request){
//        String input;
//        File file;
//        try {
//            if (!argument.isEmpty()) {
////                ConsoleClient.println("Введите путь к файлу!");
//////                if (Asker.getFileMode()) {
//////                    Scanner scriptScanner = ConsoleClient.getScriptScanner();
//////                    file = scriptScanner.nextLine().trim();
//////                }else file = scanner.nextLine().trim();
////                input = consoleClient.readLine();
//                file = new File(argument.trim());
//                if (consoleClient.getFiles().contains(file.getAbsolutePath())) throw new ScriptLoopingException();
//                consoleClient.getFiles().add(file.getAbsolutePath());
//                consoleClient.fileMode(new Scanner(file));
//                ConsoleClient.println("Скрипт из файла " + consoleClient.getFiles().getLast() + " успешно выполнен!");
//                consoleClient.getFiles().removeLast();
//                return true;
//            }else throw new WrongCommandInputException();
//        }catch (FileNotFoundException exception){
//            ConsoleClient.printError("Файл не найден!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        }catch (ScriptLoopingException exception){
//            ConsoleClient.printError("Зацикливание скрипта!");
//            if (Asker.getFileMode()) throw new IncorrectScriptException();
//        }catch (WrongCommandInputException exception) {
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
        return null;
    }

    public String getMessage(){
        return "execute_script file_name - Считает и исполнит скрипт из указанного файла";
    }
}
