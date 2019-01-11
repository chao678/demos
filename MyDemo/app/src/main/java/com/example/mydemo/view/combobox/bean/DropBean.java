package com.example.mydemo.view.combobox.bean;

public class DropBean {
    private String id;
    private String name;
    private boolean choiced;

    public DropBean(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoiced() {
        return choiced;
    }

    public void setChoiced(boolean choiced) {
        this.choiced = choiced;
    }
}
