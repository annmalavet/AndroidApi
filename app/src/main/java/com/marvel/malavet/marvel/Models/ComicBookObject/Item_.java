package com.marvel.malavet.marvel.Models.ComicBookObject;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item_ {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
