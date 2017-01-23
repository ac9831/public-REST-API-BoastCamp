package com.gunjun.android.alarm_app;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by gunjunLee on 2017-01-23.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
