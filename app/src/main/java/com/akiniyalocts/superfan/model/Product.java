package com.akiniyalocts.superfan.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by anthonykiniyalocts on 1/16/17.
 */

public class Product extends RealmObject {

    private boolean visible;

    private String type;

    private String subtitle;

    @SerializedName("sort_order")
    private int sortOrder;

    private String sku;

    @SerializedName("shipping_width")
    private Double shippingWidth;

    @SerializedName("shipping_weight")
    private Double shippingWeight;

    @SerializedName("shipping_insurance")
    private String shippingInsurance;

    @SerializedName("shipping_height")
    private Double shippingHeight;

    @SerializedName("shipping_handling_fee")
    private int shippingHandlingFee;

    @SerializedName("shipping_depth")
    private Double shippingDepth;

    @SerializedName("shipping_confirmation")
    private String shippingConfirmation;

    private String series;

    private Double price;

    @SerializedName("presale_price")
    private Double presalePrice;

    private String name;

    private String model;

    private Links links;

    @PrimaryKey
    private long id;

    @SerializedName("has_guide")
    private boolean hasGuide;

    @SerializedName("has_accessories")
    private boolean hasAccessories;

    private String description;

    private String callout;

    private boolean active;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getShippingWidth() {
        return shippingWidth;
    }

    public void setShippingWidth(Double shippingWidth) {
        this.shippingWidth = shippingWidth;
    }

    public Double getShippingWeight() {
        return shippingWeight;
    }

    public void setShippingWeight(Double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public String getShippingInsurance() {
        return shippingInsurance;
    }

    public void setShippingInsurance(String shippingInsurance) {
        this.shippingInsurance = shippingInsurance;
    }

    public Double getShippingHeight() {
        return shippingHeight;
    }

    public void setShippingHeight(Double shippingHeight) {
        this.shippingHeight = shippingHeight;
    }

    public int getShippingHandlingFee() {
        return shippingHandlingFee;
    }

    public void setShippingHandlingFee(int shippingHandlingFee) {
        this.shippingHandlingFee = shippingHandlingFee;
    }

    public Double getShippingDepth() {
        return shippingDepth;
    }

    public void setShippingDepth(Double shippingDepth) {
        this.shippingDepth = shippingDepth;
    }

    public String getShippingConfirmation() {
        return shippingConfirmation;
    }

    public void setShippingConfirmation(String shippingConfirmation) {
        this.shippingConfirmation = shippingConfirmation;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPresalePrice() {
        return presalePrice;
    }

    public void setPresalePrice(Double presalePrice) {
        this.presalePrice = presalePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isHasGuide() {
        return hasGuide;
    }

    public void setHasGuide(boolean hasGuide) {
        this.hasGuide = hasGuide;
    }

    public boolean isHasAccessories() {
        return hasAccessories;
    }

    public void setHasAccessories(boolean hasAccessories) {
        this.hasAccessories = hasAccessories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallout() {
        return callout;
    }

    public void setCallout(String callout) {
        this.callout = callout;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getThumbUrl(){

        StringBuilder builder = new StringBuilder();

        builder.append("https://system76.com/images/");
        builder.append(this.getType());
        builder.append("s/");
        builder.append(this.getSeries());
        builder.append("/");
        builder.append(this.getModel());
        builder.append("/thumb.png");

        return builder.toString();


    }
}
