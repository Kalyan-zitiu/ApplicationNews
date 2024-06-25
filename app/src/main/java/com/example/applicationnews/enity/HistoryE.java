package com.example.applicationnews.enity;

public class HistoryE {
    private int history_id;
    private String uniquekey;
    private String username;
    private String news_json;

    public HistoryE(int history_id, String uniquekey, String username, String new_json) {
        this.history_id = history_id;
        this.uniquekey = uniquekey;
        this.username = username;
        this.news_json = new_json;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNew_json() {
        return news_json;
    }

    public void setNew_json(String new_json) {
        this.news_json = new_json;
    }
}
