package com.akiniyalocts.superfan.dagger;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.akiniyalocts.superfan.ui.MainInteractor;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, AndroidModule.class, InteractorModule.class})
public interface ApplicationComponent {

    OkHttpClient okhttpclient();

    Application application();

    Resources resources();

    Context context();

    Gson gson();

    Picasso picasso();

    //Interactors
    MainInteractor mainInteractor();

    void inject(Application app);
}

