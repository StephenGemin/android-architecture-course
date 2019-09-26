package com.techyourchance.mvc.common.di;

import android.app.Activity;
import android.view.LayoutInflater;

import com.techyourchance.mvc.networking.StackoverflowApi;

/**
 * Created by Stephen Gemin on 9/25/2019
 */
public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final Activity mActivity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        mCompositionRoot = compositionRoot;
        mActivity = activity;
    }

    public StackoverflowApi getStackoverflowApi() {
        return mCompositionRoot.getStackoverflowApi();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }
}
