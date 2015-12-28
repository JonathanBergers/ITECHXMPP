package generic;

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import interfaces.Writable;
import model.User;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.net.Socket;

/**
 * Created by jonathan on 30-10-15.
 */
public class TestClient extends Thread{

    private final String hostName;
    private final int port;
    private final User u;
    private Socket socket;


    XMLStreamReader xmlStreamReader;
    XMLStreamWriter xmlStreamWriter;


    public static void main(String[] args) {
        new TestClient(new User("jonathan")).start();
        new TestClient(new User("falco")).start();
    }


    public TestClient(String hostName, int port, User u) {
        this.hostName = hostName;
        this.port = port;
        this.u = u;
    }

    public TestClient(){

        this.hostName = "localhost";
        this.port = 8080;
        this.u = new User("default@user.com");
    }
    public TestClient(User u){

        this.hostName = "localhost";
        this.port = 8080;
        this.u = u;
    }


    public void run(){

            try {

                // connect to model
              socket = new Socket(hostName, port);
                System.out.println("Client: socket connection established with server");

                // start the threads

               






            } catch (IOException e) {
                System.out.println("Cannot connect to server");
                e.printStackTrace();
            }







        }




    public synchronized boolean write(Writable w) {

        try {

            System.out.println("CLIENT SENT:" + w.toString());
            w.write(xmlStreamWriter);

            return true;
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return false;

    }

    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
