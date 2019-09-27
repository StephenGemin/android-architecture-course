package com.techyourchance.mvc.common;

import android.app.Application;

import com.techyourchance.mvc.common.di.CompositionRoot;

/**
 * Created by Stephen Gemin on 9/25/2019
 */
public class BaseApplication extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }

}
