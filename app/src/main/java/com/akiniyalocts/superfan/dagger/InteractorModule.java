package com.akiniyalocts.superfan.dagger;

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
    public MainInteractor mainInteractor(){
        return new MainInteractorI();
    }
}
