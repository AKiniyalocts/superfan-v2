package com.akiniyalocts.superfan.ui;

import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.base.Interactor;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.ui.imp.MainInteractorI;

import io.realm.RealmResults;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
public interface MainInteractor extends Interactor{

    void fetchProducts(MainInteractorI.MainCallback callback);

    void fetchSys76Products(MainInteractorI.MainCallback callback);

    void fetchAppleProducts(MainInteractorI.MainCallback callback);

    RealmResults<Product> productsByType(@NonNull String type,MainInteractorI.MainCallback callback);
}
