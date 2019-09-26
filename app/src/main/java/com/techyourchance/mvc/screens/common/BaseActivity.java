package com.techyourchance.mvc.screens.common;

import android.support.v7.app.AppCompatActivity;

import com.techyourchance.mvc.BaseApplication;
import com.techyourchance.mvc.common.di.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((BaseApplication) getApplication()).getCompositionRoot(), this);
        }
        return mControllerCompositionRoot;
    }
}
