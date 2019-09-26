package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.common.Constants;
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailsActivity extends BaseActivity {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private StackoverflowApi mStackoverflowApi;
    private QuestionDetailsViewMvc mViewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.TAG, "onCreate || Question ID: " + getQuestionId());
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);
        mStackoverflowApi = getCompositionRoot().getStackoverflowApi();

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.showProgressIndication();
        fetchQuestionDetails();
    }

    private void fetchQuestionDetails() {
        mStackoverflowApi.fetchQuestionDetails(getQuestionId())
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call,
                                           Response<QuestionDetailsResponseSchema> response) {
                        if (response.isSuccessful()) {
                            QuestionSchema webResponse = response.body().getQuestions();
                            Log.i(Constants.TAG, "onResponse || ID: " + webResponse.getId());
                            Log.i(Constants.TAG, "onResponse || Title: " + webResponse.getTitle());
                            Log.i(Constants.TAG, "onResponse || Body: " + webResponse.getBody());
                            onFetchSuccess(response.body().getQuestions());
                        } else {
                            onFetchFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        Log.e(Constants.TAG, "onFailure || ", t);
                        onFetchFailure();
                    }
                });
        ;
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }


    private void onFetchSuccess(QuestionSchema questionSchema) {
        mViewMvc.hideProgressIndication();
        QuestionDetails questionDetails = new QuestionDetails(
                questionSchema.getId(),
                questionSchema.getTitle(),
                questionSchema.getBody());
        mViewMvc.bindQuestion(questionDetails);
    }

    private void onFetchFailure() {
        mViewMvc.hideProgressIndication();
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_LONG).show();
    }
}
