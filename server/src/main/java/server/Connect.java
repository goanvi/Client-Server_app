package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Connect {
    static SocketChannel socket;
    static ServerSocketChannel server;
    static int port = 5000;
    static InetSocketAddress inetSocketAddress;
    public Connect (){}

    public static void start (){
        try {
            server = ServerSocketChannel.open();
            inetSocketAddress = new InetSocketAddress(port);
            server.bind(inetSocketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connect(){
        try {
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SocketChannel getSocket() {
        return socket;
    }

    public static int getPort() {
        return port;
    }

    public static ServerSocketChannel getServer() {
        return server;
    }

    public static InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }
}
