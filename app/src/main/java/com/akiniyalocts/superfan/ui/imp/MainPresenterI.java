package com.akiniyalocts.superfan.ui.imp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.base.PresenterI;
import com.akiniyalocts.superfan.model.AppleProduct;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.model.ProductTechSpecs;
import com.akiniyalocts.superfan.model.TypeUtil;
import com.akiniyalocts.superfan.ui.MainInteractor;
import com.akiniyalocts.superfan.ui.MainPresenter;
import com.akiniyalocts.superfan.ui.MainView;

import org.antlr.runtime.tree.TreeAdaptor;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */

public class MainPresenterI extends PresenterI<MainView, MainInteractor> implements MainPresenter {

    private final Callback callback;

    private final MainInteractorI.AppleListener appleListener;

    private final MainInteractorI.ProductListener productListener;

    private static String currentType = "laptop";

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
    public void onNewTypeSelected(@NonNull String type) {
        String realmType = TypeUtil.typeForType(type);
        currentType = realmType;
        interactor.resetForType(realmType, callback);
    }

    @Override
    public void onProductSelected(final String name) {
        Product product = interactor.productSelected(name);
        if(product != null) {
            view.showCurrentProduct(product);
            interactor.fetchSpecs(product.getId(), callback);
        }
    }

    @Override
    public void onAppleProductSelected(final String name) {
        AppleProduct appleProduct = interactor.appleProductSelected(name);
        if(appleProduct != null) {
            view.showCurrentAppleProduct(appleProduct);
        }
    }

    @Override
    public void findSpecs(long id) {

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
            interactor.productsByName(interactor.productsByType(currentType), this);
        }

        @Override
        public void onAppleProductsChanged(RealmResults<AppleProduct> appleProducts) {
            interactor.appleProductsByname( interactor.appleProductsByType(currentType), this);
        }

        @Override
        public void onProductNames(List<String> productNames) {
            view.showProductNames(productNames);
        }

        @Override
        public void onAppleNames(List<String> appleNames) {
            view.showAppleNames(appleNames);
        }

        @Override
        public void onSpecsFetched(List<ProductTechSpecs> specs) {
            view.showSpecs(specs);
        }
    }


}
