package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWorker extends Thread {
    private final ServerSocket serverSocket;
    private DataOutputStream sendBuffer;
    private DataInputStream recieveBuffer;

    public ServerWorker(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            synchronized (Server.getConnections()) {
                while (Server.getConnections().isEmpty()) {
                    try {
                        Server.getConnections().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socket = Server.getConnections().remove(0);
            }
            if (socket != null) {
                handleConnection(socket);
            }
        }
    }

    private void handleConnection(Socket socket) {
        String clientRequest;
        try {
            recieveBuffer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            sendBuffer = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
//            clientRequest = recieveBuffer.readUTF();
//            ClientMessage msg = extractClientMessage(clientRequest);
//
//            if (msg instanceof SignupLoginMessage) {
//                if (((SignupLoginMessage) msg).newUser)
//                    registerNewClient((SignupLoginMessage) msg);
//                else
//                    loginClient((SignupLoginMessage) msg);
//            } else if (msg instanceof GetBioMessage) {
//                sendBioToClient((GetBioMessage) msg);
//            } else if (msg instanceof SetBioMessage) {
//                updateBioOfClient((SetBioMessage) msg);
//            } else if (msg instanceof LogoutMessage) {
//                logoutClient((LogoutMessage) msg);
//            } else {
//                sendFailure(INTERNAL_ERROR);
//            }
            sendBuffer.close();
            recieveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
