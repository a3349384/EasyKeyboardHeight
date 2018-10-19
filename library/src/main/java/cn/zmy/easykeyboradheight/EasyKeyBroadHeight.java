package cn.zmy.easykeyboradheight;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by zmy on 2018/10/19.
 */

public class EasyKeyBroadHeight
{
    public static void init(Application application)
    {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
        {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState)
            {
                if (!(activity instanceof IKeyboardHeightAware))
                {
                    return;
                }
                final Window.Callback preCallBack = activity.getWindow().getCallback();
                final KeyboardHeightProvider provider = new KeyboardHeightProvider(activity, (IKeyboardHeightAware) activity);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.getWindow().setCallback(new WindowCallBackApi23(preCallBack, provider));
                } else {
                    activity.getWindow().setCallback(new WindowCallBack(preCallBack, provider));
                }
            }

            @Override
            public void onActivityStarted(Activity activity)
            {

            }

            @Override
            public void onActivityResumed(Activity activity)
            {

            }

            @Override
            public void onActivityPaused(Activity activity)
            {

            }

            @Override
            public void onActivityStopped(Activity activity)
            {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState)
            {

            }

            @Override
            public void onActivityDestroyed(Activity activity)
            {

            }
        });
    }
}
