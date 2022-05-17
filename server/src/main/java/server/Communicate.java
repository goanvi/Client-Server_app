package server;

import request.Request;
import response.Response;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

public class Communicate {
    SocketChannel socket;
    ObjectInput ois;
    ObjectOutputStream oos;

    public Communicate (SocketChannel socket) {
        this.socket = socket;
        try {
            oos = new ObjectOutputStream(socket.socket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(socket.socket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Request getRequest (){
//        ByteBuffer buf = ByteBuffer.allocate(10000);
        Request request = null;
        try {
//            buf.clear();
//            socket.read(buf);
//            System.out.println(buf);
//            byte[] buffer = new byte[buf.position()];
//            buf.flip();
//            System.out.println(buf);
//            for (int i = 0; i<buffer.length; i++){
//                buffer[i] = buf.get();
//            }
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf.array());
//            ObjectInput ois = new ObjectInputStream(socket.socket().getInputStream());
            request = (Request) ois.readObject();
        }catch (SocketException e){

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return request;
    }

    public void sendResponse(Response response){
//        ByteBuffer buf = ByteBuffer.allocate(1000);
        try {
//            ByteArrayOutputStream stream = new ByteArrayOutputStream(1000);
//            ObjectOutputStream oos = new ObjectOutputStream(socket.socket().getOutputStream());
            oos.writeObject(response);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketChannel getSocket() {
        return socket;
    }
}
