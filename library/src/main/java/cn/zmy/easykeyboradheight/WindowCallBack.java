package cn.zmy.easykeyboradheight;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by zmy on 2018/10/19.
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class WindowCallBack implements Window.Callback
{
    private Window.Callback preCallBack;
    private KeyboardHeightProvider provider;

    public WindowCallBack(Window.Callback preCallBack, KeyboardHeightProvider provider)
    {
        this.preCallBack = preCallBack;
        this.provider = provider;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        return preCallBack.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event)
    {
        return preCallBack.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        return preCallBack.dispatchTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event)
    {
        return preCallBack.dispatchTrackballEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event)
    {
        return preCallBack.dispatchGenericMotionEvent(event);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event)
    {
        return preCallBack.dispatchPopulateAccessibilityEvent(event);
    }

    @Override
    public View onCreatePanelView(int featureId)
    {
        return preCallBack.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu)
    {
        return preCallBack.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu)
    {
        return preCallBack.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {
        return preCallBack.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        return preCallBack.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs)
    {
        preCallBack.onWindowAttributesChanged(attrs);
    }

    @Override
    public void onContentChanged()
    {
        preCallBack.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        preCallBack.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            provider.attach();
        }
        else
        {
            provider.detach();
        }
    }

    @Override
    public void onAttachedToWindow()
    {
        preCallBack.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow()
    {
        preCallBack.onDetachedFromWindow();
        provider.destroy();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu)
    {
        preCallBack.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onSearchRequested()
    {
        return preCallBack.onSearchRequested();
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent)
    {
        return false;
    }

    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback)
    {
        return preCallBack.onWindowStartingActionMode(callback);
    }

    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type)
    {
        return null;
    }

    @Override
    public void onActionModeStarted(ActionMode mode)
    {
        preCallBack.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(ActionMode mode)
    {
        preCallBack.onActionModeFinished(mode);
    }
}
