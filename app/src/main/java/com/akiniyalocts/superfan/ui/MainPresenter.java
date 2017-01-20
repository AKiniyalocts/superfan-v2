package com.akiniyalocts.superfan.ui;

import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.base.ActivityPresenter;
import com.akiniyalocts.superfan.ui.imp.MainInteractorI;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public interface MainPresenter extends ActivityPresenter{

    void onProductSelected(final String name);

    void onAppleProductSelected(final String name);

    void onNewTypeSelected(@NonNull String type);
}
