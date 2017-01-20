package com.akiniyalocts.superfan.dagger;

import com.akiniyalocts.superfan.network.AppleApi;
import com.akiniyalocts.superfan.network.System76Api;
import com.akiniyalocts.superfan.ui.MainInteractor;
import com.akiniyalocts.superfan.ui.imp.MainInteractorI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
@Module
public class InteractorModule {

    @Singleton
    @Provides
    public MainInteractor mainInteractor(System76Api system76Api, AppleApi appleApi){
        return new MainInteractorI(system76Api, appleApi);
    }
}
