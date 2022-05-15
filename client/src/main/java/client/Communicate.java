package client;

import request.Request;
import response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communicate {
    Socket socket;

    public Communicate (Socket socket) {
        this.socket = socket;
    }

    public void send(Request request) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response get(){
        Response response = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            response = (Response) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
