package network;

import java.util.ArrayList;

public class ServerResponse {
    private final String clientId;
    private final String responseType;
    private final String response;
    private final ArrayList<Object> contents;

    public ServerResponse(String clientId, String responseType, String response, ArrayList<Object> contents) {
        this.clientId = clientId;
        this.responseType = responseType;
        this.response = response;
        this.contents = contents;
    }

    public String getClientId() {
        return clientId;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getResponse() {
        return response;
    }

    public ArrayList<Object> getContents() {
        return contents;
    }
}
