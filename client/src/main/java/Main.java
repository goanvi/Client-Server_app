import client.Client;
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
        Client client = new Client();
        client.start();
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
