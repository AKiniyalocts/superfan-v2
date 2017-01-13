package com.akiniyalocts.superfan.dagger;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

@Module
public class AndroidModule {
    private final Application application;

    public AndroidModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public SharedPreferences sharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    public Application application() {
        return application;
    }

    @Singleton
    @Provides
    public Resources resources() {
        return application.getResources();
    }

    @Singleton
    @Provides
    public DownloadManager provideDownloadManager() {
        return (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return application;
    }

}
