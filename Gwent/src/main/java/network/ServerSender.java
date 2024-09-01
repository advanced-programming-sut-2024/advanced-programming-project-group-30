package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSender extends Thread {
    private final ServerSocket serverSocket;
    private final Gson gsonAgent;

    public ServerSender(ServerSocket serverSocket) {
        gsonAgent = new GsonBuilder().create();
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        ServerResponse response;
        Socket socket;
        while (true) {
            synchronized (Server.getResponses()) {
                while (Server.getResponses().keySet().isEmpty()) {
                    // TODO : wait
                }
                response = Server.getResponses().remove(Server.getResponses().keySet().iterator().next());
            }
            if (response != null) {
                socket = Server.getClient(response.getClientId());
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(gsonAgent.toJson(response));
                    dataOutputStream.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
