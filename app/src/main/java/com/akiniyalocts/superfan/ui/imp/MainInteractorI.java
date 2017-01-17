package com.akiniyalocts.superfan.ui.imp;

import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.model.Product;
import com.akiniyalocts.superfan.network.System76Api;
import com.akiniyalocts.superfan.ui.MainInteractor;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
public class MainInteractorI implements MainInteractor {

    public interface MainCallback{
        void onProductsFetched();
        void onFailure();
    }

    private final System76Api system76Api;

    private final RealmResults<Product> products;

    public MainInteractorI(System76Api system76Api) {
        this.system76Api = system76Api;

        products = Realm.getDefaultInstance().where(Product.class).findAllAsync();
    }


    @Override
    public void fetchProducts(MainCallback callback) {
        fetchSys76Products(callback);
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
                .subscribeOn(Schedulers.io())
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

    }
}
