package com.akiniyalocts.superfan.network;

import com.akiniyalocts.superfan.annotations.Envelope;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.model.ProductTechSpecs;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public interface System76Api {

    String base = "https://api-v2.system76.com";

    @Envelope
    @GET("/products")
    Observable<List<Product>> getProducts();

    @GET("/products/{id}/tech_specs")
    Observable<SpecsResponse> getTechSpecs(@Path("id")long id);
}
