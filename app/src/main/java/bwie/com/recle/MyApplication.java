package bwie.com.recle;

import android.app.Application;

import org.xutils.x;

/**
 * Created by $USER_NAME on 2017/5/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
