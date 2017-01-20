package com.akiniyalocts.superfan.ui.imp;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.akiniyalocts.superfan.model.Product;

import java.util.List;

/**
 * Created by anthonykiniyalocts on 1/20/17.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
    }


}
