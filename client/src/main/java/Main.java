import client.Communicate;
import client.Connect;
import command.AbstractCommand;
import command.commands.*;
import console.ConsoleClient;
import utility.Asker;
import utility.CommandManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connect connect = new Connect(InetAddress.getLocalHost(), 5000);
            connect.connect();
            Communicate communicate = new Communicate(connect.getSocket());
            System.out.println(connect.getSocket().isConnected());
            Map<String , AbstractCommand> commandMap = new HashMap<>();
            CommandManager commandManager = new CommandManager(commandMap);
            ConsoleClient consoleClient = new ConsoleClient(commandManager, System.console(), new Scanner(System.in));
            Asker asker = new Asker(consoleClient);
            commandMap.put("add", new Add(asker, communicate));
            commandMap.put("exit", new Exit(communicate));
            commandMap.put("execute_script" , new ExecuteScript(consoleClient));
            commandMap.put("clear", new Clear(communicate));
            commandMap.put("filter_less_than_students_count", new FilterLessThanStudentsCount(communicate));
            commandMap.put("help", new Help(communicate));
            commandMap.put("history", new History(communicate));
            commandMap.put("info",new Info(communicate));
            commandMap.put("remove_any_by_semester_enum", new RemoveAnyBySemesterEnum(communicate));
            commandMap.put("remove_by_id", new RemoveById(communicate));
            commandMap.put("remove_greater", new RemoveGreater(asker, communicate));
            commandMap.put("remove_lower", new RemoveLower(asker,communicate));
            commandMap.put("show", new Show(communicate));
            commandMap.put("sum_of_students_count", new SumOfStudentsCount(communicate));
            commandMap.put("update_id", new UpdateId(asker,communicate, consoleClient));
            consoleClient.interactiveMode();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//        Socket socket;
//        int port = 5000;
//        try {
//            socket = new Socket(InetAddress.getLocalHost(), port);
//            Test outputTest = new Test("Client1");
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            oos.writeObject(outputTest);
//            System.out.println("object sent");
//            Test inputTest = (Test) ois.readObject();
//            System.out.println("object received");
//            System.out.println(inputTest.name);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();

    }
}
