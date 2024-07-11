package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public record Connection(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
}
