package cn.zmy.app;

import android.app.Application;

import cn.zmy.easykeyboradheight.EasyKeyBroadHeight;

/**
 * Created by zmy on 2018/10/18.
 */

public class TheApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        EasyKeyBroadHeight.init(this);
    }
}
