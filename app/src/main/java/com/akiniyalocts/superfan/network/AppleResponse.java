package com.akiniyalocts.superfan.network;

import com.akiniyalocts.superfan.model.AppleProduct;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

/**
 * Created by anthonykiniyalocts on 1/20/17.
 */

public final class AppleResponse {

    @SerializedName("products")
    public final RealmList<AppleProduct> products;

    public AppleResponse(RealmList<AppleProduct> products) {
        this.products = products;
    }
}
