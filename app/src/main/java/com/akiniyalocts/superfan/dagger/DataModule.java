package com.akiniyalocts.superfan.dagger;

import android.content.Context;

import com.akiniyalocts.superfan.BuildConfig;
import com.akiniyalocts.superfan.SuperfanApp;
import com.akiniyalocts.superfan.network.AppleApi;
import com.akiniyalocts.superfan.network.System76Api;
import com.akiniyalocts.superfan.network.converters.ConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
@Module
public class DataModule {

    private final SuperfanApp app;

    public DataModule(SuperfanApp app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkhttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.interceptors().add(interceptor);
        }

        return builder.build();
    }

    @Singleton
    @Provides
    public Picasso picasso(Context context){
        return new Picasso.Builder(context)
                .loggingEnabled(BuildConfig.DEBUG)
                .build();
    }

    @Singleton
    @Provides
    public Gson gson(){
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    public RxJavaCallAdapterFactory rxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }


    @Singleton
    @Provides
    public System76Api system76Api(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory rxJavaCallAdapterFactory){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(System76Api.base)
                .addConverterFactory(new ConverterFactory())
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();

        return retrofit.create(System76Api.class);
    }


    @Singleton
    @Provides
    public AppleApi appleApi(OkHttpClient client, GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory rxJavaCallAdapterFactory){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppleApi.base)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();

        return retrofit.create(AppleApi.class);
    }

}
