package com.akiniyalocts.superfan.ui.imp;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by anthonykiniyalocts on 1/20/17.
 */

public class MainBinding {

    @BindingAdapter("setPrice")
    public static void setPrice(TextView textView, Double price){
        if(price != null) {
            textView.setText("$" + price);
        }
    }
}
