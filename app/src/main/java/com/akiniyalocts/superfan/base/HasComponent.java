package com.akiniyalocts.superfan.base;

public interface HasComponent<T> {
    T getComponent();

    void injectComponent();
}