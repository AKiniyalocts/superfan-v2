package com.akiniyalocts.superfan.dagger.main;

import com.akiniyalocts.superfan.base.ActivityScope;
import com.akiniyalocts.superfan.dagger.ApplicationComponent;
import com.akiniyalocts.superfan.ui.imp.MainActivity;
import com.akiniyalocts.superfan.ui.MainPresenter;

import dagger.Component;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    MainPresenter mainPresenter();
}
