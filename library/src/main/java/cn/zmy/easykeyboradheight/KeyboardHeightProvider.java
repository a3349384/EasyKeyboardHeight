package cn.zmy.easykeyboradheight;

/**
 * Created by zmy on 2018/10/18.
 */

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * The keyboard height provider, this class uses a PopupWindow
 * to calculate the window height when the floating keyboard is opened and closed.
 */
public class KeyboardHeightProvider extends PopupWindow
    implements ViewTreeObserver.OnGlobalLayoutListener
{
    private IKeyboardHeightAware mKeyboardHeightAware;

    /** The view that is used to calculate the keyboard height */
    private View popupView;

    /** The activity content view, used to get the window token */
    private View parentView;

    private Activity activity;

    private Point screenSize = new Point();
    private Rect rect = new Rect();

    /**
     * Construct a new KeyboardHeightProvider
     */
    public KeyboardHeightProvider(Activity activity, IKeyboardHeightAware keyboardHeightAware) {
        super(activity);
        setWidth(0);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

        this.activity = activity;
        this.mKeyboardHeightAware = keyboardHeightAware;
        this.popupView = new View(activity);
        setContentView(popupView);
        parentView = activity.findViewById(android.R.id.content);

        Logger.v("init completed");
    }

    /**
     * attach to activity, called on the {@link Activity#onWindowFocusChanged(boolean)} is RECOMMENDED
     */
    public void attach() {
        //init if first attch
        if (!isShowing()) {
            if (parentView.getWindowToken() != null) {
                showAtLocation(parentView, Gravity.NO_GRAVITY, 0, 0);
            } else {
                Logger.v("attach failed, because of the window token is null.");
                return;
            }
        }
        popupView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        Logger.v("attach success");
    }

    /**
     * detach from activity
     */
    public void detach() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            popupView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            popupView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        Logger.v("detach success");
    }

    /**
     * release，then the provider cannot be reused.
     * */
    public void destroy()
    {
        mKeyboardHeightAware = null;
        activity = null;
        parentView = null;
        dismiss();
        Logger.v("destroyed success");
    }

    @Override
    public void onGlobalLayout()
    {
        if (popupView != null) {
            handleOnGlobalLayout();
        }
    }

    /**
     * Popup window itself is as big as the window of the Activity.
     * The keyboard can then be calculated by extracting the popup view bottom
     * from the activity window height.
     */
    private void handleOnGlobalLayout() {
        activity.getWindowManager().getDefaultDisplay().getSize(screenSize);
        popupView.getWindowVisibleDisplayFrame(rect);

        int keyboardHeight = screenSize.y - rect.bottom;
        notifyKeyboardHeightChanged(keyboardHeight, getScreenOrientation());
    }

    /**
     * Get the screen orientation
     *
     * @return the screen orientation
     */
    private int getScreenOrientation() {
        return activity.getResources().getConfiguration().orientation;
    }

    private void notifyKeyboardHeightChanged(int height, int orientation) {
        if (mKeyboardHeightAware != null) {
            mKeyboardHeightAware.onKeyboardHeightChanged(height, orientation);
        }
    }
}

