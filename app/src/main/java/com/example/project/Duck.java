package com.example.project;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Duck implements Serializable {
    private String name;
    @SerializedName("company")
    private String characteristics;
    private int size;
    private int cost;
    @SerializedName("location")
    private String origin;
    private String category;
    @SerializedName("auxdata")
    private String curiosity;

    public Duck(String name, String origin, String characteristics, String curiosity) {
        this.name = name;
        this.origin = origin;
        this.characteristics = characteristics;
        this.curiosity = curiosity;
    }

    public String getName() {
        return name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCategory() {
        return category;
    }

    public String getCuriosity() {
        return curiosity;
    }
}
