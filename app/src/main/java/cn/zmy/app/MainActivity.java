package cn.zmy.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import cn.zmy.easykeyboradheight.IKeyboardHeightAware;

/**
 * Created by zmy on 2018/10/18.
 */

public class MainActivity extends AppCompatActivity
        implements IKeyboardHeightAware
{
    private View viewInputContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        setContentView(R.layout.main_activity);
        viewInputContainer = findViewById(R.id.viewInputContainer);
    }

    @Override
    public void onKeyboradHeightChanged(int height, int orientation)
    {
        String or = orientation == Configuration.ORIENTATION_PORTRAIT ? "portrait" : "landscape";
        Log.d("MainActivity", "onKeyboardHeightChanged: " + height);

        viewInputContainer.setTranslationY(0 - height);
    }

    public void gotoNewActivity(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
    }
}
