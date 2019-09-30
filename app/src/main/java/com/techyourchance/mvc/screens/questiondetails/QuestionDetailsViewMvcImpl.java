package com.techyourchance.mvc.screens.questiondetails;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;
import com.techyourchance.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.techyourchance.mvc.screens.common.views.BaseObservableViewMvc;
import com.techyourchance.mvc.screens.common.views.BaseViewMvc;

/**
 * Created by Stephen Gemin on 9/26/2019
 */
public class QuestionDetailsViewMvcImpl extends BaseObservableViewMvc<QuestionDetailsViewMvc.Listener>
        implements QuestionDetailsViewMvc {

    private final TextView mTxtTitle;
    private final TextView mTxtBody;
    private final ProgressBar mProgressBar;
    private final ToolbarViewMvc mToolbarViewMvc;
    private final Toolbar mToolbar;


    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup container,
                                      ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_question_details, container, false));
        mTxtTitle = findViewById(R.id.txt_title);
        mTxtBody = findViewById(R.id.txt_question_body);
        mProgressBar = findViewById(R.id.details_progress_bar);

        mToolbar = findViewById(R.id.top_toolbar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView());
        mToolbarViewMvc.setTitle(getString(R.string.questions_detail_screen_title));
        mToolbarViewMvc.enableUpButtonAndListen(new ToolbarViewMvc.NavigateUpClickListener() {
            @Override
            public void onNavigateUpClicked() {
                for (Listener listener : getListeners()) {
                    listener.onNavigateUpClicked();
                }
            }
        });
    }

    @Override
    public void bindQuestions(QuestionDetails question) {
        String questionTitle = question.getTitle();
        String questionBody = question.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtTitle.setText(Html.fromHtml(questionTitle, Html.FROM_HTML_MODE_LEGACY));
            mTxtBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtTitle.setText(Html.fromHtml(questionTitle));
            mTxtBody.setText(Html.fromHtml(questionBody));
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
