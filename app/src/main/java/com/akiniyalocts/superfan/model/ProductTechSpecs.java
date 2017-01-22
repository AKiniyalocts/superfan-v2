package com.akiniyalocts.superfan.model;

import com.google.gson.annotations.SerializedName;

import io.realm.annotations.PrimaryKey;

/**
 * Created by anthonykiniyalocts on 1/22/17.
 */

public class ProductTechSpecs {

    private String title;

    @SerializedName("sort_order")
    private int sortOrder;

    @PrimaryKey
    private long id;

    private String details;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
