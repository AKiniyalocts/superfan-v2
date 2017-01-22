package com.akiniyalocts.superfan.ui.imp;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.akiniyalocts.superfan.model.ProductTechSpecs;
import com.akiniyalocts.superfan.model.Tier;

import java.util.List;

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

    @BindingAdapter("setTier")
    public static void setTier(TextView textView, Tier tier){
        StringBuilder builder = new StringBuilder();

        if(tier != null){
            if(tier.getCpu() != null){
                builder.append(String.format("%s\n\n",tier.getCpu()));
            }
            if(tier.getRam() != null){
                builder.append(String.format("%s\n\n", tier.getRam()));
            }
            if(tier.getStorage() != null){
                builder.append(String.format("%s\n\n", tier.getStorage()));
            }
            if(tier.getScreen() != null){
                builder.append(String.format("%s\n\n", tier.getScreen()));
            }
            if(tier.getGpu() != null){
                builder.append(String.format("%s\n", tier.getGpu()));
            }
        }
        textView.setText(builder.toString());
    }

    @BindingAdapter("setSpecs")
    public static void setSpecs (TextView textView, List<ProductTechSpecs> specs){
        if(specs != null && !specs.isEmpty()){
            StringBuilder builder = new StringBuilder();

            for(ProductTechSpecs spec: specs){
                if(spec.getTitle() != null){

                    if(spec.getDetails() != null){

                        builder
                                .append(spec.getTitle())
                                .append(": ");
                        String newDetails = spec.getDetails().replace("&Prime;","''");
                        builder.append(newDetails)
                                .append("\n\n");

                    }
                }
            }

            textView.setText(builder.toString());
        }
    }
}
