package com.techyourchance.mvc.common.di;

import android.app.Activity;
import android.view.LayoutInflater;

import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.FetchQuestionListUseCase;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;

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

    private StackoverflowApi getStackoverflowApi() {
        return mCompositionRoot.getStackoverflowApi();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(getStackoverflowApi());
    }

    public FetchQuestionListUseCase getFetchQuestionListUseCase() {
        return new FetchQuestionListUseCase(getStackoverflowApi());
    }
}
