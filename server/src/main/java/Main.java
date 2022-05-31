import controller.CollectionManager;
import controller.CommandManager;
import controller.FileWorker;
import server.Communicate;
import server.Connect;
import server.Start;
import view.command.AbstractCommand;
import view.command.commands.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker(System.getenv("LABA6"));
        CollectionManager collectionManager = new CollectionManager(System.getenv("LABA6"));
        Map<String, AbstractCommand> commandMap = new HashMap<>();
        CommandManager commandManager = new CommandManager(commandMap);
        commandMap.put("add", new Add(collectionManager));
        commandMap.put("clear", new Clear(collectionManager));
        commandMap.put("filter_less_than_students_count", new FilterLessThanStudentsCount(collectionManager));
        commandMap.put("help", new Help(commandManager));
        commandMap.put("history", new History());
        commandMap.put("exit", new ClientExit());
        commandMap.put("info", new Info(collectionManager));
        commandMap.put("remove_any_by_semester_enum", new RemoveAnyBySemesterEnum(collectionManager));
        commandMap.put("remove_by_id", new RemoveById(collectionManager));
        commandMap.put("remove_greater", new RemoveGreater(collectionManager));
        commandMap.put("remove_lower", new RemoveLower(collectionManager));
        commandMap.put("show", new Show(collectionManager));
        commandMap.put("sum_of_students_count", new SumOfStudentsCount(collectionManager));
        commandMap.put("update_id", new UpdateId(collectionManager));
        Connect.setPort(Integer.parseInt(args[0]));
        Connect.open();
        Connect.start();
        System.out.println(" host: " + Connect.getPort());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String input = System.console().readLine().trim();
                    if (input.equalsIgnoreCase("save")) new Save(collectionManager, fileWorker).execute(null);
                    if (input.equalsIgnoreCase("exit")) {
                        new ServerExit(collectionManager, fileWorker).execute(null);

                    }
                }
            }
        }).start();
        Connect.connect();
        Communicate communicate = new Communicate(Connect.getSocket());
        Start start = new Start(communicate, commandManager);
        start.start();

    }


}
