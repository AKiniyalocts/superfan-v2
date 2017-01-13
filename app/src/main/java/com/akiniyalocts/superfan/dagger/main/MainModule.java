package com.akiniyalocts.superfan.dagger.main;

import com.akiniyalocts.superfan.base.ActivityScope;
import com.akiniyalocts.superfan.ui.imp.MainActivity;
import com.akiniyalocts.superfan.ui.MainInteractor;
import com.akiniyalocts.superfan.ui.MainPresenter;
import com.akiniyalocts.superfan.ui.imp.MainPresenterI;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
@Module
public class MainModule {
    private final MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @ActivityScope
    @Provides
    MainPresenter mainPresenter(MainInteractor mainInteractor){
        return new MainPresenterI(mainActivity, mainInteractor);
    }
}
