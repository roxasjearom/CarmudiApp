package com.jearomr.carmudiapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DecimalFormat;

public class Utils {

    /**
     * Formats double value to a string without decimal place
     * @param value value to be converted
     * @return formatted string value
     */
    public static String formatPrice(double value) {
        try {
            DecimalFormat accountingFormat = new DecimalFormat("#,##0");
            return accountingFormat.format(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + value;
    }

    public static boolean isOffline(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork == null || !activeNetwork.isConnectedOrConnecting();
    }

}
