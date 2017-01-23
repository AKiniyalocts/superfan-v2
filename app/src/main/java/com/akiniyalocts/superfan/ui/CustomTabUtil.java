package com.akiniyalocts.superfan.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.akiniyalocts.superfan.R;

/**
 * Created by anthonykiniyalocts on 1/22/17.
 */

public class CustomTabUtil {


    private static final String EXTRA_CUSTOM_TABS_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";

    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra.SESSION";


    public static void startTabsIntent(Context context, @NonNull final String productUrl){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(productUrl));

        Bundle extras = new Bundle();
        intent.putExtra(EXTRA_CUSTOM_TABS_TOOLBAR_COLOR, context.getResources().getColor(R.color.colorPrimary));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            extras.putBinder(EXTRA_CUSTOM_TABS_SESSION, null);
        }

        intent.putExtras(extras);

        context.startActivity(intent);
    }
}
