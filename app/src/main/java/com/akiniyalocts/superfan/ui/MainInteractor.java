package com.akiniyalocts.superfan.ui;

import com.akiniyalocts.superfan.base.Interactor;
import com.akiniyalocts.superfan.ui.imp.MainInteractorI;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
public interface MainInteractor extends Interactor{

    void fetchProducts(MainInteractorI.MainCallback callback);

    void fetchSys76Products(MainInteractorI.MainCallback callback);

    void fetchAppleProducts(MainInteractorI.MainCallback callback);
}
