package com.akiniyalocts.superfan.network;

import com.akiniyalocts.superfan.model.ProductTechSpecs;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anthonykiniyalocts on 1/22/17.
 */

public class SpecsResponse {

    @SerializedName("product_tech_specs")
    public final List<ProductTechSpecs> specs;

    public SpecsResponse(List<ProductTechSpecs> specs) {
        this.specs = specs;
    }
}
