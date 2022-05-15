package command.commands;

import client.Communicate;
import command.AbstractCommand;
import command.exceptions.WrongCommandInputException;
import console.ConsoleClient;
import exceptions.IncorrectScriptException;
import request.Request;
import response.Response;
import utility.Asker;

public class Show extends AbstractCommand {
    Communicate communicate;

    public Show(Communicate communicate) {
        super("show", "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.communicate = communicate;
    }

    //    private String formatOutput(TreeSet<StudyGroupDTO> groups){
//        ArrayList<String> strings = new ArrayList<>();
//        groups.forEach(group -> {
//            strings.add(group.toString().replace(",","\n")+"\n\n");
//        });
//        return strings.toString().replaceAll("[\\[\\],]","");
//    }
    @Override
    public boolean execute(String argument) throws IncorrectScriptException {
        try {
            if (argument.isEmpty()) {
//                TreeSet<StudyGroup> studyGroups = collectionManager.getCollection();
//                if (studyGroups.isEmpty()) {
//                    ConsoleClient.println("Коллекция пуста");
//                }else {
//                for (StudyGroup group:studyGroups){
//                    System.out.println(Formatter.format(group));
//                }
                Request request = new Request(null, "show", null);
                communicate.send(request);
                Response response = communicate.get();
                ConsoleClient.println(response.getText());
//                ConsoleClient.println("Коллекция успешно выведена!");
//                }
                return response.getAnswer();
            } else throw new WrongCommandInputException();
        } catch (WrongCommandInputException exception) {
            ConsoleClient.printError("Команда " + getName() + " введена с ошибкой: " +
                    "команда не должна содержать символы после своего названия!");
            if (Asker.getFileMode()) throw new IncorrectScriptException();
        }
        return false;
    }

    public String getMessage() {
        return "show - Выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
