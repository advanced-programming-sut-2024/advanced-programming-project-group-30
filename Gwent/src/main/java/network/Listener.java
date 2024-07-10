package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {
    private final ServerSocket serverSocket;

    public Listener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("can't accept client Request!");
                continue;
            }
            synchronized (Server.getConnections()) {
                Server.getConnections().add(socket);
                Server.getConnections().notify();
            }
        }
    }
}