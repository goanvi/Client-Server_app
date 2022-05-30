package client;

import command.AbstractCommand;
import command.commands.*;
import console.ConsoleClient;
import utility.Asker;
import utility.CommandManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    static String addr;
    static int port;
    static Socket socket;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static Communicate communicate;
    ConsoleClient consoleClient;

    public Client (){

    }

    public static void setup(){
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("open oos");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            InputStream input = socket.getInputStream();
//            System.out.println("get input");
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("open ois");
        } catch (IOException e) {
            e.printStackTrace();
            ConsoleClient.printError("Ошибка создания потоков");
        }
    }

    public static void close(){
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean connect(){
        while (true){
        try {
            ConsoleClient.println("Введите данные для подключения: хост порт");
            String input = consoleClient.readLine() + " ";
            String[] arg = input.split(" ",2);
            addr = arg[0].trim();
            port = Integer.parseInt(arg[1].trim());
            socket=new Socket();
            socket.connect(new InetSocketAddress(addr, port));
//            System.out.println("connect");
            return true;
        } catch (ConnectException exception){
            waitingConnection();
            return false;
        } catch (IOException e) {
            ConsoleClient.printError("Ошибка подключения");
        }catch (NumberFormatException e){
            ConsoleClient.printError("Порт введен неверно");
        }
        }
    }

    public void start(){
        Map<String , AbstractCommand> commandMap = new HashMap<>();
        CommandManager commandManager = new CommandManager(commandMap);
        consoleClient = new ConsoleClient(commandManager, System.console());
        Asker asker = new Asker(consoleClient);
        if (connect()) setup();
        communicate = new Communicate(ois,oos);
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
    }

    public static void waitingConnection(){
        int sec = 0;
        boolean flag = false;
        socket = new Socket();
        while (socket.isClosed() || !socket.isConnected()) {
            socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(addr, port));
                setup();
                if (communicate!=null){
                communicate.setOos(oos);
                communicate.setOis(ois);
                }
                ConsoleClient.println("Повторное подключение произведено успешно. Продолжение выполнения");
                return;
            } catch (IOException e) {
                if (!flag)
                ConsoleClient.printError("Ошибка подключения");
                flag=true;
            }
            ConsoleClient.print("\rОжидание повторного подключения: " + sec + "/60 секунд");
            sec++;
            if (sec > 60) {
                ConsoleClient.println("Клиент не дождался подключения. Завершение работы программы");
                System.exit(0);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ConsoleClient.println("Ошибка приостановки выполнения потока");
            }
        }
    }

    public static Socket getSocket() {
        return socket;
    }
}
