package com.example.fabricio.myapplication.Util;

import android.content.Context;

/**
 * Created by Fabricio on 23/07/2015.
 */
public final class AppUtil {

    public static Context CONTEXT;


    public static boolean stringIsNullOrEmpty(String texto){
        return  texto == null || texto.trim().isEmpty();
    }


}
