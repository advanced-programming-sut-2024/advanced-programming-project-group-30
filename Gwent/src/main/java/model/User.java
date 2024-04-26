package model;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;

    public User(String username, String password, String email, String nickName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }
}
