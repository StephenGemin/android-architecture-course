package com.techyourchance.mvc.screens.questionslist;

import android.os.Bundle;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.FetchQuestionListUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.BaseActivity;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

public class QuestionsListActivity extends BaseActivity
        implements QuestionsListViewMvcImpl.Listener, FetchQuestionListUseCase.Listener {

    private QuestionsListViewMvc mViewMvc;
    private FetchQuestionListUseCase mFetchQuestionListUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        mFetchQuestionListUseCase = getCompositionRoot().getFetchQuestionListUseCase();
        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchQuestionListUseCase.registerListener(this);

        mViewMvc.showProgressIndication();
        mFetchQuestionListUseCase.fetchQuestionListAndNotify();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchQuestionListUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionListFetchSuccess(List<Question> questions) {
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestions(questions);
    }

    @Override
    public void onQuestionListFetchFailure() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(this, question.getId());
    }
}
