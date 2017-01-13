package com.akiniyalocts.superfan.dagger;

public interface HasComponent<T> {
    T getComponent();

    void injectComponent();
}