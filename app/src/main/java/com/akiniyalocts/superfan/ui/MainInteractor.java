package com.akiniyalocts.superfan.ui;

import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.base.Interactor;
import com.akiniyalocts.superfan.model.AppleProduct;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.ui.imp.MainInteractorI;
import com.akiniyalocts.superfan.ui.imp.MainPresenterI;

import io.realm.RealmResults;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
public interface MainInteractor extends Interactor{

    void fetchProducts(MainInteractorI.MainCallback callback);

    void fetchSys76Products(MainInteractorI.MainCallback callback);

    void fetchAppleProducts(MainInteractorI.MainCallback callback);

    void fetchSpecs(long id, MainInteractorI.MainCallback callback);

    void addChangeListeners(MainInteractorI.AppleListener appleListener, MainInteractorI.ProductListener productListener, MainInteractorI.MainCallback mainCallback);

    void removeChangeListeners();

    RealmResults<Product> productsByType(@NonNull String type);

    RealmResults<AppleProduct> appleProductsByType(@NonNull String type);

    void productsByName(RealmResults<Product> products, MainPresenterI.Callback callback);

    void appleProductsByname(RealmResults<AppleProduct> appleProducts, MainPresenterI.Callback callback);

    Product productSelected(final String name);

    AppleProduct appleProductSelected(final String name);

    void resetForType(String type,  MainInteractorI.MainCallback callback);
}
