package com.example.testapp;

public class WaterfallItemObj {
    private String colorText;
    private String bgColor;
    private int height;

    public WaterfallItemObj(String colorText, String bgColor, int height) {
        this.colorText = colorText;
        this.bgColor = bgColor;
        this.height = height;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
