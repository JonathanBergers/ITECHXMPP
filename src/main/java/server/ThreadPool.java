package server;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 28-12-15.
 */
public class ThreadPool extends Thread {



    private List<Connection> connections = new ArrayList<>();



    public void addConnection(Socket socket){
        XMPPConnection connection = new XMPPConnection(socket, null, new ServerSettings());
        connection.start();
        connections.add(connection);

    }


}
