package client;

import request.Request;
import response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

public class Communicate {
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Request request;

    public Communicate(ObjectInputStream ois, ObjectOutputStream oos) {
        this.oos = oos;
        this.ois = ois;
    }

    public void send(Request req) {
        request = req;
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (SocketException e) {
            Client.waitingConnection();
            send(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response get() {
        Response response = null;
        try {
            response = (Response) ois.readObject();
        } catch (SocketException exception) {
            Client.waitingConnection();
            send(request);
            return get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }
}
