package com.akiniyalocts.superfan.model;

import android.support.annotation.NonNull;

/**
 * Created by anthonykiniyalocts on 1/20/17.
 */

public class TypeUtil {

    public static String typeForType(@NonNull final String type){
        if(type.equalsIgnoreCase("Laptops")){
            return "laptop";

        } else if(type.equalsIgnoreCase("Desktops")){
            return "desktop";
        } else {
            return "server";
        }
    }

}
