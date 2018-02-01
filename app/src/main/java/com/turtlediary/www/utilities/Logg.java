package com.turtlediary.www.utilities;

import android.util.Log;

/**
 * Created by pratibha on 16/1/18.
 */

public  class    Logg {
    public  static  void p(String tag, String msg)
    {
        try
        {
            Log.e(tag, msg.toString());
        }catch ( Exception ex)
        {
            Log.e("Exception", ex.toString());
        }
    }
}
