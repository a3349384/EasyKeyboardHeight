# EasyKeyboardHeight

Very easy way to get the android soft keyboard height

# Download

Download the latest library via Gradle:

`implementation 'cn.zmy.easykeyboardheight:1.0.0'`

# Get Started

1. init in Application#onCreate

```
@Override
public void onCreate()
{
    super.onCreate();
    EasyKeyBroadHeight.init(this);
}
```

2. Any activity wants to know the height of soft keyboard, just implements `IKeyboardHeightAware`.
When the soft keyboard is open or close, `onKeyboardHeightChanged` will be call by EasyKeyboardHeight.

```
public class MainActivity extends AppCompatActivity
        implements IKeyboardHeightAware
{
    @Override
    public void onKeyboardHeightChanged(int height, int orientation)
    {
        if (height == 0)
        {
            //keyboard is closed
        }
        else
        {
            //keyboard is open, it's height is `height`
        }
    }
}
```

# Thanks

- https://juejin.im/post/5bc6ce54f265da0afd4b69cb
