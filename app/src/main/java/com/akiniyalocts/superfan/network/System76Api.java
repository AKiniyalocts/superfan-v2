package com.akiniyalocts.superfan.network;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public interface System76Api {

    String base = "https://api-v2.system76.com";

    @GET("/products")
    Observable<List<Object>> getProducts();
}
