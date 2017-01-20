package com.akiniyalocts.superfan.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AppleProduct extends RealmObject {

    @PrimaryKey
    private String name;

    private String type;

    @SerializedName("low_tier")
    private Tier low;

    @SerializedName("med_tier")
    private Tier medium;

    @SerializedName("high_tier")
    private Tier high;

    @SerializedName("image_url")
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tier getLow() {
        return low;
    }

    public void setLow(Tier low) {
        this.low = low;
    }

    public Tier getMedium() {
        return medium;
    }

    public void setMedium(Tier medium) {
        this.medium = medium;
    }

    public Tier getHigh() {
        return high;
    }

    public void setHigh(Tier high) {
        this.high = high;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
