package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;

public class QuestionDetailsActivity extends BaseActivity
        implements FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private QuestionDetailsViewMvc mViewMvc;
    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);
        mFetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase();

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFetchQuestionDetailsUseCase.registerListener(this);
        mViewMvc.registerListener(this);
        mViewMvc.showProgressIndication();
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(getQuestionId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFetchQuestionDetailsUseCase.unregisterListener(this);
        mViewMvc.unregisterListener(this);
    }

    public void bindQuestionDetails(QuestionDetails questionDetails) {
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestions(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        bindQuestionDetails(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetchedFailed() {
        mViewMvc.hideProgressIndication();
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }

    @Override
    public void onNavigateUpClicked() {
        onBackPressed();
    }
}