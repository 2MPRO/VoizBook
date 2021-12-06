package com.example.voizbook.model;

public class BookSelection {
    private int icon;
    private String category;

    public BookSelection(int icon, String category) {
        this.icon = icon;
        this.category = category;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
