package network;

import java.util.ArrayList;

public class ClientMessage {
    private String controllerName;
    private String methodName;
    private ArrayList<Object> fields;

    public ClientMessage(String classAddress, String methodAddress, ArrayList<Object> fields) {
        this.controllerName = classAddress;
        this.methodName = methodAddress;
        this.fields = fields;
    }

    public String getControllerName() {
        return controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public ArrayList<Object> getFields() {
        return fields;
    }
}
