package com.akiniyalocts.superfan.network;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public interface AppleApi {

    String base = "https://api.myjson.com";

    @GET("/bins/1akxj3")
    Observable<AppleResponse> getProducts();

}
