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
        super("show", "выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder stringBuilder = new StringBuilder();
        TreeSet<StudyGroup> studyGroups = collectionManager.getCollection();
        if (studyGroups.isEmpty()) {
            return new Response(true, "Коллекция пуста!");
        } else {
            studyGroups.forEach((group) -> stringBuilder.append(Formatter.format(group)).append("\n"));
        }
        return new Response(true, stringBuilder + "Коллекция успешно выведена!");
    }

    public String getMessage() {
        return "show - Выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
