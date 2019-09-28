package com.techyourchance.mvc.screens.questionslist;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.techyourchance.mvc.screens.common.views.BaseObservableViewMvc;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;

import java.util.List;

/**
 * Created by Stephen Gemin on 9/23/2019
 */
public class QuestionsListViewMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener>
        implements QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc {

    private RecyclerView mRecyclerQuestions;
    private QuestionsRecyclerAdapter mAdapter;
    private final ProgressBar mProgressBar;
    private final ToolbarViewMvc mToolbarViewMvc;
    private final Toolbar mToolbar;

    public QuestionsListViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent,
                                    ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_questions_list, parent, false));

        mProgressBar = findViewById(R.id.question_list_progress_bar);
        mToolbar = findViewById(R.id.top_toolbar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);
        mToolbarViewMvc.setTitle(getString(R.string.questions_list_screen_title));
        mToolbar.addView(mToolbarViewMvc.getRootView());

        mRecyclerQuestions = findViewById(R.id.recycler_questions);
        mRecyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuestionsRecyclerAdapter(this, viewMvcFactory);
        mRecyclerQuestions.setAdapter(mAdapter);
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mAdapter.bindQuestions(questions);
    }

    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : getListeners()) {
            listener.onQuestionClicked(question);
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