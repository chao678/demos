package com.example.testapp.bean;

import java.io.File;

public class PicBean {
    private File file;
    private String url;

    public PicBean() {}

    public PicBean(File file, String url) {
        this.file = file;
        this.url = url;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
