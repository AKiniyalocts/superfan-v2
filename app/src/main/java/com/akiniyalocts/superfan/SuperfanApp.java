package com.akiniyalocts.superfan;

import android.app.Application;

import com.akiniyalocts.superfan.dagger.AndroidModule;
import com.akiniyalocts.superfan.dagger.ApplicationComponent;
import com.akiniyalocts.superfan.dagger.DaggerApplicationComponent;
import com.akiniyalocts.superfan.dagger.DataModule;
import com.akiniyalocts.superfan.base.HasComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public class SuperfanApp extends Application implements HasComponent<ApplicationComponent>{

    @Override
    public void onCreate() {
        super.onCreate();
        this.injectComponent();
        initRealm();
    }


    @Override
    public void injectComponent() {
        getComponent().inject(this);
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }

    @Override
    public ApplicationComponent getComponent() {
        return DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .dataModule(new DataModule(this))
                .build();
    }
}
