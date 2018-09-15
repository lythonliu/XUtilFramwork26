package com.example.administrator.xutilframwork26;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class BaseActivity  extends com.lythonliu.LinkActivity{

    @Override
    public String getAppName(){
        return BuildConfig.APP_NAME;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);
    }
}
