package com.techyourchance.mvc.screens.questiondetails;

import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.common.Constants;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseViewMvc;

/**
 * Created by Stephen Gemin on 9/26/2019
 */
public class QuestionDetailsViewMvcImpl extends BaseViewMvc implements QuestionDetailsViewMvc {

    private final TextView mTxtQuestionTitle;
    private final TextView mTxtQuestionBody;
    private final ProgressBar mProgressBar;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup container) {
        setRootView(inflater.inflate(R.layout.activity_question_details, container, false));

        mTxtQuestionTitle = findViewById(R.id.txt_question_title);
        mTxtQuestionBody = findViewById(R.id.txt_question_body);
        mProgressBar = findViewById(R.id.progress);
    }

    @Override
    public void bindQuestion(QuestionDetails question) {
        String questionTitle = question.getTitle();
        String questionBody = question.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Log.i(Constants.TAG, "bindQuestion || Using legacy HTML");
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle, Html.FROM_HTML_MODE_LEGACY));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            Log.i(Constants.TAG, "bindQuestion || Using regular HTML");
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }
}
