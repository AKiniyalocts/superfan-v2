package com.akiniyalocts.superfan.ui.imp;

import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.model.AppleProduct;
import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.model.ProductTechSpecs;
import com.akiniyalocts.superfan.model.TypeUtil;
import com.akiniyalocts.superfan.network.AppleApi;
import com.akiniyalocts.superfan.network.System76Api;
import com.akiniyalocts.superfan.ui.MainInteractor;

import java.util.ArrayList;
import java.util.List;

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

        void onProductNames(List<String> productNames);

        void onAppleNames(List<String> appleNames);

        void onSpecsFetched(List<ProductTechSpecs> specs);
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
        return products.where().equalTo(field, value).findAll().where().equalTo("active", true).findAll();
    }

    @Override
    public RealmResults<Product> productsByType(@NonNull String type) {
        return filterByString("type", type);
    }

    @Override
    public RealmResults<AppleProduct> appleProductsByType(@NonNull String type) {
        return appleProducts.where().equalTo("type", type).findAll();
    }

    @Override
    public void resetForType(String type, MainCallback callback) {
        callback.onProductsChanged(productsByType(TypeUtil.typeForType(type)));
        callback.onAppleProductsChanged(appleProductsByType(TypeUtil.typeForType(type)));
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

    @Override
    public void fetchSpecs(long id, MainCallback callback) {
        system76Api.getTechSpecs(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if(response != null && response.specs != null){
                                callback.onSpecsFetched(response.specs);
                            }
                        }
                );
    }

    @Override
    public void productsByName(RealmResults<Product> products, MainPresenterI.Callback callback) {
        List<String> productNames = new ArrayList<>();
        for(Product product: products){
            if(!productNames.contains(product.getName())) {
                productNames.add(product.getName());
            }
        }

        callback.onProductNames(productNames);
    }

    @Override
    public void appleProductsByname(RealmResults<AppleProduct> appleProducts, MainPresenterI.Callback callback) {
        List<String> appleNames = new ArrayList<>();

        for(AppleProduct appleProduct: appleProducts){
            if(!appleNames.contains(appleProduct.getName())) {
                appleNames.add(appleProduct.getName());
            }
        }

        callback.onAppleNames(appleNames);
    }


    @Override
    public Product productSelected(final String name) {
        if(products.isValid() && !products.isEmpty()){
            return products.where().equalTo("name", name).findFirst();
        }
        return null;
    }

    @Override
    public AppleProduct appleProductSelected(final String name) {
        if(appleProducts.isValid() && !appleProducts.isEmpty()){
            return appleProducts.where().equalTo("name", name).findFirst();
        }
        return null;
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
