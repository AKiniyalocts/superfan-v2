package com.akiniyalocts.superfan.base;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by anthonykiniyalocts on 8/7/16.
 */
public abstract class PresenterI<V extends View, I extends Interactor> implements ActivityPresenter{

    public final V view;

    public final I interactor;

    public PresenterI(V view, I interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent launchIntent) {
        view.init();

        if(savedInstanceState != null){
            onCreateSavedState(savedInstanceState);
        } else {
            onCreateFreshState(launchIntent);
        }
    }


    public abstract void onCreateFreshState(Intent launchIntent);

    public abstract void onCreateSavedState(Bundle savedInstanceState);
}
