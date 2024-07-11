package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.App;
import model.User;
import model.card.UserData;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataSaverController {
    private static final String USER_JSON_PATH = "src/main/resources/json/users.json";
    public void loadUsers() {
        List<UserData> users;
        try (Reader reader = new FileReader(USER_JSON_PATH)) {
            Type userListType = new TypeToken<ArrayList<UserData>>() {
            }.getType();
            Gson gson = new Gson();
            users = gson.fromJson(reader, userListType);
            if (users != null)
                App.setAllUsers((ArrayList<UserData>) users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveUsers(List<UserData> userDatas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userDatas);
        try (Writer writer = new FileWriter(USER_JSON_PATH)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
