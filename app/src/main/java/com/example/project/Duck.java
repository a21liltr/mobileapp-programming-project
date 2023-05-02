package com.example.project;

import com.google.gson.annotations.SerializedName;

public class Duck {
    private String name;

    @SerializedName("location")
    private String origin;

    @SerializedName("company")
    private String characteristics;

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
    public String getOrigin() {
        return origin;
    }
    public String getCuriosity() {
        return curiosity;
    }
}
