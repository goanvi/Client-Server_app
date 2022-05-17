import controller.CollectionManager;
import controller.CommandManager;
import controller.FileWorker;
import request.Request;
import response.Response;
import server.Communicate;
import server.Connect;
import server.Start;
import view.command.AbstractCommand;
import view.command.commands.*;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /* при проверке работоспособности программы отловить все оставшиеся ошибки,
     * спросить про LocalDateTime и обобщить все заметки в классах здесь,
     * почистить классы от мусора,
     * Дописать исключения для Controller.FileWorker
     * проверить IgnoreCase в AskPerson*/

    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker(System.getenv("LABA5"));
        CollectionManager collectionManager = new CollectionManager(System.getenv("LABA5"));
        Map<String, AbstractCommand> commandMap = new HashMap<>();
        CommandManager commandManager = new CommandManager(commandMap);
        commandMap.put("add", new Add(collectionManager));
        commandMap.put("clear", new Clear(collectionManager));
        commandMap.put("filter_less_than_students_count", new FilterLessThanStudentsCount(collectionManager));
        commandMap.put("help", new Help(commandManager));
        commandMap.put("history", new History());
        commandMap.put("exit", new Save(collectionManager,fileWorker));
        commandMap.put("info",new Info(collectionManager));
        commandMap.put("exit1", new Exit());
        commandMap.put("remove_any_by_semester_enum", new RemoveAnyBySemesterEnum(collectionManager));
        commandMap.put("remove_by_id", new RemoveById(collectionManager));
        commandMap.put("remove_greater", new RemoveGreater(collectionManager));
        commandMap.put("remove_lower", new RemoveLower(collectionManager));
        commandMap.put("show", new Show(collectionManager));
        commandMap.put("sum_of_students_count", new SumOfStudentsCount(collectionManager));
        commandMap.put("update_id", new UpdateId(collectionManager));
//        Connect connect = new Connect();
        Connect.start();
        Connect.connect();
        Communicate communicate = new Communicate(Connect.getSocket());
        Scanner scanner = new Scanner(System.in);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("save"))  commandMap.get("exit").execute(null);
                if (input.equalsIgnoreCase("exit")) {
                    commandMap.get("exit").execute(null);
                    commandMap.get("exit1").execute(null);
                }
            }
        }).start();
//        Start start = new Start(communicate,commandManager);
//        start.start();
        while (true){
            try {
                Request request = communicate.getRequest();
                Response response = null;
                response = commandManager.callCommand(request);
                communicate.sendResponse(response);
            } catch (SocketException e) {
                Connect.connect();
                communicate = new Communicate(Connect.getSocket());
            }
        }

//        SocketChannel socket;
//        ServerSocketChannel server;
//        InputStream input;
//        OutputStream output;
//        int port = 5000;
//        System.out.println("Сервер работает");
//        Test inputTest;
//        Test outputTest;
//        try {
//            Selector selector = Selector.open();
//            server = ServerSocketChannel.open();
//            server.bind(new InetSocketAddress(port));
//            server.configureBlocking(false);
//            SelectionKey key = server.register(selector, SelectionKey.OP_ACCEPT);
//            while (true){
//                selector.select();
//                Set<SelectionKey> keys = selector.selectedKeys();
//                for (Iterator<SelectionKey> iter = keys.iterator(); iter.hasNext();){
//                    SelectionKey key1 = iter.next();
//                    iter.remove();
//                    if (key1.isValid()){
//                        if (key1.isAcceptable()){
//                            ServerSocketChannel serv = (ServerSocketChannel) key1.channel();
//                            SocketChannel sock = serv.accept();
//                            sock.configureBlocking(false);
//                            sock.register(key1.selector(), SelectionKey.OP_READ);
//                            System.out.println("подключено");
//                        }
//                        if (key1.isReadable()){
//                            System.out.println("готов к чтению");
//                            SocketChannel sock = (SocketChannel) key1.channel();
//                            System.out.println(sock.isBlocking());
//                            ObjectInputStream ois = new ObjectInputStream(sock.socket().getInputStream());
//                            inputTest = (Test) ois.readObject();
//                            System.out.println(inputTest.name);
//                            sock.configureBlocking(false);
//                            sock.register(key1.selector(), SelectionKey.OP_WRITE);
//                        }
//                        if (key1.isWritable()){
//                            SocketChannel sock = (SocketChannel) key1.channel();
//                            ObjectOutputStream oos = new ObjectOutputStream(sock.socket().getOutputStream());
//                            outputTest = new Test("server");
//                            oos.writeObject(outputTest);
//
//                        }
//                    }
//                }
//            }
//            server.bind(new InetSocketAddress(port));
//            socket = server.accept();
//
//            Test test = new Test("Good");
//            ObjectOutputStream oos = new ObjectOutputStream(socket.socket().getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(socket.socket().getInputStream());
//            oos.writeObject(test);
//            System.out.println(socket.isOpen());
//            Test test1 = (Test) ois.readObject();
//            System.out.println(test1.name);
//            System.out.println("good job");
//            input = socket.getInputStream();
//            System.out.println(input.available());
//            byte[] buffer = new byte[4];
//            for (byte b: buffer){
//                System.out.println((char)b);
//            }
//            output = socket.getOutputStream();
//            output.write(buffer);

//            ois.close();
//            oos.close();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        Console console = System.console();
//        FileWorker fileWorker = new FileWorker(System.getenv("LABA5"));
//        CollectionManager collectionManager = new CollectionManager(System.getenv("LABA5"));
//        Map<String, AbstractCommand> commandMap = new LinkedHashMap<>();
//        CommandManager commandManager = new CommandManager(commandMap);
//        ConsoleClient consoleClient = new ConsoleClient(commandManager, console);
//        Asker asker = new Asker(consoleClient);
//        Add add = new Add(asker,collectionManager);
//        commandMap.put(add.getName(),add);
//        Clear clear = new Clear(collectionManager);
//        commandMap.put(clear.getName(),clear);
//        Exit exit = new Exit();
//        commandMap.put(exit.getName(),exit);
//        FilterLessThanStudentsCount filterLessThanStudentsCount = new FilterLessThanStudentsCount(collectionManager,consoleClient);
//        commandMap.put(filterLessThanStudentsCount.getName(),filterLessThanStudentsCount);
//        Help help = new Help(commandManager);
//        commandMap.put(help.getName(),help);
//        History history = new History();
//        commandMap.put(history.getName(),history);
//        Info info = new Info(collectionManager);
//        commandMap.put(info.getName(),info);
//        RemoveAnyBySemesterEnum removeAnyBySemesterEnum = new RemoveAnyBySemesterEnum(collectionManager,consoleClient);
//        commandMap.put(removeAnyBySemesterEnum.getName(),removeAnyBySemesterEnum);
//        RemoveById removeById = new RemoveById(collectionManager,consoleClient);
//        commandMap.put(removeById.getName(),removeById);
//        RemoveGreater removeGreater = new RemoveGreater(collectionManager,consoleClient, asker);
//        commandMap.put(removeGreater.getName(), removeGreater);
//        RemoveLower removeLower = new RemoveLower(collectionManager,consoleClient, asker);
//        commandMap.put(removeLower.getName(),removeLower);
//        Save save = new Save(consoleClient, collectionManager,fileWorker);
//        commandMap.put(save.getName(),save);
//        Show show = new Show(collectionManager);
//        commandMap.put(show.getName(),show);
//        SumOfStudentsCount sumOfStudentsCount = new SumOfStudentsCount(collectionManager);
//        commandMap.put(sumOfStudentsCount.getName(), sumOfStudentsCount);
//        UpdateId updateId = new UpdateId(collectionManager,consoleClient, asker);
//        commandMap.put(updateId.getName(),updateId);
//        ExecuteScript executeScript = new ExecuteScript(consoleClient);
//        commandMap.put(executeScript.getName(),executeScript);
//
//        consoleClient.interactiveMode();

    }


}
