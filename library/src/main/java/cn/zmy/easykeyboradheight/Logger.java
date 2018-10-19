package cn.zmy.easykeyboradheight;

import android.util.Log;

/**
 * Created by zmy on 2018/4/11.
 */
public class Logger
{
    private static final String TAG = "Logger";

    public static int v(String msg)
    {
        return Log.v(TAG, msg);
    }

    public static int v(String tag, String msg) {
        return Log.v(tag, msg);
    }
}
