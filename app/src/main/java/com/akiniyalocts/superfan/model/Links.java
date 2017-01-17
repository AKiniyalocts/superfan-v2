package com.akiniyalocts.superfan.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by anthonykiniyalocts on 1/16/17.
 */
public class Links extends RealmObject {

    @SerializedName("product_tech_specs")
    private String productTechSpechs;

    public String getProductTechSpechs() {
        return productTechSpechs;
    }

    public void setProductTechSpechs(String productTechSpechs) {
        this.productTechSpechs = productTechSpechs;
    }
}
