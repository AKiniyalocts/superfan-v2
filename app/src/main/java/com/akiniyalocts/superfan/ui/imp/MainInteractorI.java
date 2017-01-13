package com.akiniyalocts.superfan.ui.imp;

import com.akiniyalocts.superfan.network.System76Api;
import com.akiniyalocts.superfan.ui.MainInteractor;

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

    public MainInteractorI(System76Api system76Api) {
        this.system76Api = system76Api;
    }


    @Override
    public void fetchProducts(MainCallback callback) {
        fetchSys76Products(callback);
    }

    @Override
    public void fetchSys76Products(MainCallback callback) {
        system76Api.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            callback.onProductsFetched();
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
