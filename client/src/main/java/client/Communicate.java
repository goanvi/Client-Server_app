package client;

import request.Request;
import response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Communicate {
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public Communicate (Socket socket) {
        this.socket = socket;
    }

    public void send(Request request)throws SocketException {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
        } catch (IOException e) {
            Client.waitingConnection();
        }
    }

    public Response get(){
        Response response = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            response = (Response) ois.readObject();
        } catch (SocketException exception){
            Client.waitingConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
