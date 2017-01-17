package com.akiniyalocts.superfan.network.converters;

import com.akiniyalocts.superfan.model.Product;

import java.util.List;

/**
 * Created by anthonykiniyalocts on 1/16/17.
 */

public class ProductType {

    public final List<Product> products;

    public ProductType(List<Product> products) {
        this.products = products;
    }
}
