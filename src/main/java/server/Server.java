package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jonathan on 12-10-15.
 */
public class Server {

    private final ServerSettings serverSettings;

    private ThreadPool threadPool;

    public Server(ServerSettings serverSettings) {
        this.serverSettings = serverSettings;

    }

    public Server(){
        serverSettings = new ServerSettings();
    }



    // run the model
    public static void main(String[] args) {
        new Server().run();
    }


    public void run(){


        threadPool = new ThreadPool();
        threadPool.start();


        try {
            ServerSocket serverSocket = new ServerSocket(serverSettings.getPort());
            System.out.println("model socket initialized");

            // wait for connection then add thread
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client socket accepted");
                threadPool.addConnection(socket);




                System.out.println("client socket connection established");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
