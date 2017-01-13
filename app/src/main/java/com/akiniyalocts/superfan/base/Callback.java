package com.akiniyalocts.superfan.base;

public interface Callback<T, R extends Object> {

    void onFetchCompleted(T response);

    void onFailure(Throwable throwable);
}