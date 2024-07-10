package network;

import java.util.ArrayList;

public class ClientMessage {
    private final String classAddress;
    private final String methodName;
    private final ArrayList<Object> fields;

    public ClientMessage(String classAddress, String methodName, ArrayList<Object> fields) {
        this.classAddress = classAddress;
        this.methodName = methodName;
        this.fields = fields;
    }

    public String getClassAddress() {
        return classAddress;
    }

    public String getMethodName() {
        return methodName;
    }

    public ArrayList<Object> getFields() {
        return fields;
    }
}
