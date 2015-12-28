package model;

import model.Model;
import model.User;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.net.Socket;

/**
 * Created by jonathan on 28-12-15.
 */
public abstract class Connection extends Thread {

    protected XMLStreamReader xmlStreamReader;
    protected XMLStreamWriter xmlStreamWriter;

    protected final Model model;
    protected User user;


    protected final Socket socket;
    public Connection(Socket socket, Model model) {
        this.model = model;
        this.socket = socket;
    }

    protected abstract void init();

    protected abstract void connect();

    protected abstract void serve();

    protected abstract void close();




}
