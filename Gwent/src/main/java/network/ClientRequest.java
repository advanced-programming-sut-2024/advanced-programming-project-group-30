package network;

import java.util.ArrayList;

public class ClientRequest {
    private final String clientId;
    private final String requestType;
    private final String request;
    private final ArrayList<Object> contents;

    public ClientRequest(String clientId, String classAddress, String methodAddress, ArrayList<Object> contents) {
        this.clientId = clientId;
        this.requestType = classAddress;
        this.request = methodAddress;
        this.contents = contents;
    }

    public ClientRequest(String classAddress, String methodAddress, ArrayList<Object> contents) {
        this.clientId = null;
        this.requestType = classAddress;
        this.request = methodAddress;
        this.contents = contents;
    }

    public String getClientId() {
        return clientId;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getRequest() {
        return request;
    }

    public ArrayList<Object> getContents() {
        return contents;
    }
}