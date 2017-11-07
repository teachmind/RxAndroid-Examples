package me.shuza.rxdemoapicall;

import android.util.Log;

/**
 * :=  created by:  Shuza
 * :=  create date:  11/5/2017
 * :=  (C) CopyRight Shuza
 * :=  www.shuza.me
 * :=  shuza.sa@gmail.com
 * :=  Fun  :  Coffee  :  Code
 **/

public class LogUtil {

    public static void printLogMessage(String type, String message) {
        Log.d("shuza_log", type + "        ==/      " + message);
    }
}
