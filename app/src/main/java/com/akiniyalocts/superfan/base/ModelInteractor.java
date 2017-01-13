package com.akiniyalocts.superfan.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

public interface ModelInteractor<T extends Parcelable> extends Interactor{

    void put(T model, Bundle savedInstanceState);

    T get(Bundle savedInstanceState, Intent launchIntent);

    T get(Bundle savedInstanceState);

    void store(T model);

}