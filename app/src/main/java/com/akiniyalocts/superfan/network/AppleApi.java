package com.akiniyalocts.superfan.network;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public interface AppleApi {

    String base = "";

    @GET()
    Observable<List<Object>> getProducts(@Url String url);

}
