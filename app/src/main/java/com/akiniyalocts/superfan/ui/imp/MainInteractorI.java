package com.akiniyalocts.superfan.ui.imp;

import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.model.AppleProduct;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.network.AppleApi;
import com.akiniyalocts.superfan.network.System76Api;
import com.akiniyalocts.superfan.ui.MainInteractor;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
public class MainInteractorI implements MainInteractor {

    public interface MainCallback{
        void onProductsFetched();
        void onFailure();

        void onProductsChanged(RealmResults<Product> products);

        void onAppleProductsChanged(RealmResults<AppleProduct> appleProducts);
    }

    private final System76Api system76Api;

    private final AppleApi appleApi;

    private final RealmResults<Product> products;

    private final RealmResults<AppleProduct> appleProducts;

    public MainInteractorI(System76Api system76Api, AppleApi appleApi) {
        this.system76Api = system76Api;
        this.appleApi = appleApi;

        products = Realm.getDefaultInstance().where(Product.class).findAllAsync();
        appleProducts = Realm.getDefaultInstance().where(AppleProduct.class).findAllAsync();

    }

    @Override
    public void addChangeListeners(AppleListener appleListener, ProductListener productListener, MainCallback mainCallback) {
        productListener.setMainCallback(mainCallback);
        appleListener.setMainCallback(mainCallback);

        products.addChangeListener(productListener);
        appleProducts.addChangeListener(appleListener);
    }

    @Override
    public void removeChangeListeners() {
        products.removeChangeListeners();
        appleProducts.removeChangeListeners();
    }

    @Override
    public void fetchProducts(MainCallback callback) {
        fetchSys76Products(callback);
        fetchAppleProducts(callback);
    }

    private RealmResults<Product> filterByString(@NonNull final String field, @NonNull final String value){
        return products.where().equalTo(field, value).findAll();
    }

    @Override
    public RealmResults<Product> productsByType(@NonNull String type, final MainCallback callback) {
        return filterByString("type", type);
    }

    @Override
    public void fetchSys76Products(MainCallback callback) {
        system76Api.getProducts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            callback.onProductsFetched();
                            if(response != null) {

                                if (products.isLoaded() && products.isEmpty()) {
                                    Realm.getDefaultInstance().executeTransaction(realm -> realm.copyToRealmOrUpdate(response));
                                } else {
                                    Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(response));
                                }

                            }
                        },

                        throwable -> {
                            callback.onFailure();
                        }
                );
    }

    @Override
    public void fetchAppleProducts(MainCallback callback) {
        appleApi.getProducts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if(response != null && response.products != null){
                                callback.onProductsFetched();

                                if(appleProducts.isLoaded() && appleProducts.isEmpty()){
                                    Realm.getDefaultInstance().executeTransaction(realm -> realm.copyToRealmOrUpdate(response.products));
                                } else {
                                    Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(response.products));
                                }
                            }
                        },

                        throwable -> {
                            callback.onFailure();
                        }
                );
    }

    public final static class AppleListener implements RealmChangeListener<RealmResults<AppleProduct>>{

        private MainCallback mainCallback;

        public void setMainCallback(MainCallback mainCallback) {
            this.mainCallback = mainCallback;
        }

        @Override
        public void onChange(RealmResults<AppleProduct> appleProducts) {
            if(mainCallback != null) {
                mainCallback.onAppleProductsChanged(appleProducts);
            }
        }
    }

    public final static class ProductListener implements RealmChangeListener<RealmResults<Product>>{

        private MainCallback mainCallback;

        public void setMainCallback(MainCallback mainCallback) {
            this.mainCallback = mainCallback;
        }

        @Override
        public void onChange(RealmResults<Product> products) {
            if(mainCallback != null) {
                mainCallback.onProductsChanged(products);
            }
        }
    }

}
