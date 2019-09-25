package com.techyourchance.mvc.screens.questionslist;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.BaseViewMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Gemin on 9/23/2019
 */
public class QuestionsListViewMvcImpl extends BaseViewMvc implements QuestionsRecyclerAdapter.Listener,
        QuestionsListViewMvc {

    private RecyclerView mRecyclerQuestions;
    private QuestionsRecyclerAdapter mAdapter;
    private final List<Listener> mListeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_questions_list, parent, false));
        mRecyclerQuestions = findViewById(R.id.recycler_questions);
        mRecyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuestionsRecyclerAdapter(inflater, this);
        mRecyclerQuestions.setAdapter(mAdapter);
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mAdapter.bindQuestions(questions);
    }

    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : mListeners) {
            listener.onQuestionClicked(question);
        }
    }
}