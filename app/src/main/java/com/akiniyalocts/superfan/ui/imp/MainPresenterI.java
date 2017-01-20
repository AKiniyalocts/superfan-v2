package com.akiniyalocts.superfan.ui.imp;

import android.content.Intent;
import android.os.Bundle;

import com.akiniyalocts.superfan.base.PresenterI;
import com.akiniyalocts.superfan.model.AppleProduct;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.ui.MainInteractor;
import com.akiniyalocts.superfan.ui.MainPresenter;
import com.akiniyalocts.superfan.ui.MainView;

import io.realm.RealmResults;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public class MainPresenterI extends PresenterI<MainView, MainInteractor> implements MainPresenter {

    private final Callback callback;

    private final MainInteractorI.AppleListener appleListener;

    private final MainInteractorI.ProductListener productListener;

    public MainPresenterI(MainView view, MainInteractor interactor) {
        super(view, interactor);
        this.callback = new Callback();
        this.appleListener = new MainInteractorI.AppleListener();
        this.productListener = new MainInteractorI.ProductListener();
    }

    @Override
    public void onCreateFreshState(Intent launchIntent) {
        interactor.fetchProducts(callback);
        view.toggleLoading(true);
    }

    @Override
    public void onCreateSavedState(Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {

    }

    @Override
    public void onResume() {
        interactor.addChangeListeners(appleListener, productListener, callback);
    }

    @Override
    public void onDestroy() {
        interactor.removeChangeListeners();
    }

    @Override
    public void onPause() {
        interactor.removeChangeListeners();
    }

    public final class Callback implements MainInteractorI.MainCallback{

        @Override
        public void onProductsFetched() {
            view.toggleLoading(false);
        }

        @Override
        public void onFailure() {
            view.toggleLoading(false);
        }

        @Override
        public void onProductsChanged(RealmResults<Product> products) {

        }

        @Override
        public void onAppleProductsChanged(RealmResults<AppleProduct> appleProducts) {

        }
    }


}
