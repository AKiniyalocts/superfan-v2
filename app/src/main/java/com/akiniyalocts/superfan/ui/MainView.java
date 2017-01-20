package com.akiniyalocts.superfan.ui;

import com.akiniyalocts.superfan.base.View;
import com.akiniyalocts.superfan.model.AppleProduct;
import com.akiniyalocts.superfan.model.Product;

import java.util.List;

/**
 * Created by anthonykiniyalocts on 1/13/17.
 */
public interface MainView extends View{

    void toggleLoading(boolean loading);

    void showProductNames(List<String> productNames);

    void showAppleNames(List<String> appleNames);

    void showCurrentProduct(Product product);

    void showCurrentAppleProduct(AppleProduct appleProduct);
}
