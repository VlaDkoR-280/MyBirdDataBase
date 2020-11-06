package com.vladkor.mybirddatabase.Item;

import android.media.Image;

public class Item {
    private String title;
    private String description;
    private String l_description;
    private Image img;


    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getL_description() {
        return l_description;
    }

    private void setL_description(String l_description) {
        this.l_description = l_description;
    }

    public Item(String title, String description, String l_description){
        setTitle(title);
        setDescription(description);
        setL_description(l_description);
    }

    public Item(){
        setL_description("");
        setTitle("");
        setDescription("");
        ;
    }

}
