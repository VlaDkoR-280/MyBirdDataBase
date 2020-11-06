package com.vladkor.mybirddatabase.Item;

import android.graphics.Bitmap;
import android.media.Image;

public class Item {
    private String title;
    private String description;
    private String l_description;
    private String img;

    public String getImg() {
        return img;
    }

    private void setImg(String img) {
        this.img = img;
    }

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

    public Item(String title, String description, String l_description, String image){
        setTitle(title);
        setDescription(description);
        setL_description(l_description);
        img = image;
    }

    public Item(){
        setL_description("");
        setTitle("");
        setDescription("");
        img = null;
    }

}
