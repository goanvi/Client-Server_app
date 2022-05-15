package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connect {
    private InetAddress address;
    private int port;
    Socket socket;

    public Connect (InetAddress address, int port){
        this.address = address;
        this.port = port;
    }

    public void connect(){
        try {
            socket = new Socket(address,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket (){
        return socket;
    }
}
