package com.akiniyalocts.superfan.dagger;

import android.app.Application;

import dagger.Module;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }
}
