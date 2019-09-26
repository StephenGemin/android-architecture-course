package com.techyourchance.mvc.screens.common;

import android.content.Context;
import android.view.View;

/**
 * Created by Stephen Gemin on 9/24/2019
 */
public abstract class BaseViewMvc implements ViewMvc {
    private View mRootView;

    @Override
    public View getRootView() {
        return mRootView;
    }

    protected void setRootView(View rootView) {
        mRootView = rootView;
    }

    protected <T extends View> T findViewById(int id) {
        return mRootView.findViewById(id);
    }

    protected Context getContext() {
        return mRootView.getContext();
    }
}