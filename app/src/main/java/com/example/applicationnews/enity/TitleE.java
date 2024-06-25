package com.example.applicationnews.enity;

public class TitleE {
    private String title;
    private String py_title;

    public TitleE(String title,String py_title) {
        this.title = title;
        this.py_title=py_title;
    }

    public String getPy_title() {
        return py_title;
    }

    public void setPy_title(String py_title) {
        this.py_title = py_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
