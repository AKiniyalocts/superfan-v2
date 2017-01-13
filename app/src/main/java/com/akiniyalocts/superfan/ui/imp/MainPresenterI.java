package com.akiniyalocts.superfan.ui.imp;

import android.content.Intent;
import android.os.Bundle;

import com.akiniyalocts.superfan.base.PresenterI;
import com.akiniyalocts.superfan.ui.MainInteractor;
import com.akiniyalocts.superfan.ui.MainPresenter;
import com.akiniyalocts.superfan.ui.MainView;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public class MainPresenterI extends PresenterI<MainView, MainInteractor> implements MainPresenter {

    public MainPresenterI(MainView view, MainInteractor interactor) {
        super(view, interactor);
    }

    @Override
    public void onCreateFreshState(Intent launchIntent) {

    }

    @Override
    public void onCreateSavedState(Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
