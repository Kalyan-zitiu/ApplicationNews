package com.example.applicationnews.enity;

public class UserE {
    private int user_id;
    private String username;
    private String password;
    private String nickname;

    public static UserE sUserE;

    public static UserE getsUserE() {
        return sUserE;
    }

    public static void setsUserE(UserE sUserE) {
        UserE.sUserE = sUserE;
    }

    public UserE(int user_id, String username, String password, String nickname) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return nickname;
    }

    public void setName(String nicknamename) {
        this.nickname= nicknamename;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
