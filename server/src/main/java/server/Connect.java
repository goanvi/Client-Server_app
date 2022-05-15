package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Connect {
    SocketChannel socket;
    ServerSocketChannel server;
    int port = 5000;
    public Connect (){}

    public void start (){
        try {
            server = ServerSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        try {
            server.bind(new InetSocketAddress(5000));
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketChannel getSocket() {
        return socket;
    }
}
