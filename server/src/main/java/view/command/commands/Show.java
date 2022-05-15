package view.command.commands;

import controller.CollectionManager;
import model.StudyGroup;
import request.Request;
import response.Response;
import view.command.AbstractCommand;
import view.utility.Formatter;

import java.util.TreeSet;

public class Show extends AbstractCommand {
    CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("Show", "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

//    private String formatOutput(TreeSet<StudyGroupDTO> groups){
//        ArrayList<String> strings = new ArrayList<>();
//        groups.forEach(group -> {
//            strings.add(group.toString().replace(",","\n")+"\n\n");
//        });
//        return strings.toString().replaceAll("[\\[\\],]","");
//    }
    @Override
    public Response execute(Request request){
        StringBuilder stringBuilder = new StringBuilder();
        TreeSet<StudyGroup> studyGroups = collectionManager.getCollection();
        if (studyGroups.isEmpty()) {
            return new Response(true, "Коллекция пуста!");
        }else {
            for (StudyGroup group:studyGroups){
                stringBuilder.append(Formatter.format(group));
            }
        }
        return new Response(true, stringBuilder + "Коллекция успешно выведена!");
    }

    public String getMessage(){
        return "show - Выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
