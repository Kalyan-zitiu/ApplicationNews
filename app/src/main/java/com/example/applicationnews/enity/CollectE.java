package com.example.applicationnews.enity;

public class CollectE {
    private int collect_id;
    private String uniquekey;
    private String username;
    private String news_json;

    public CollectE(int collect_id, String uniquekey, String username, String new_json) {
        this.collect_id = collect_id;
        this.uniquekey = uniquekey;
        this.username = username;
        this.news_json = new_json;
}
    public int getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(int collect_id) {
        this.collect_id = collect_id;
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