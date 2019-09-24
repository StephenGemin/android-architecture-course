package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Gemin on 9/23/2019
 */
public class QuestionsListViewMvcImpl implements QuestionsListAdapter.OnQuestionClickListener,
        QuestionListViewMvc  {

    private ListView mLstQuestions;
    private QuestionsListAdapter mQuestionsListAdapter;
    private final View mRootView;
    private final List<Listener> mListeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        mRootView = inflater.inflate(R.layout.layout_questions_list, parent, false);
        mLstQuestions = findViewById(R.id.lst_questions);
        mQuestionsListAdapter = new QuestionsListAdapter(getContext(),this);
        mLstQuestions.setAdapter(mQuestionsListAdapter);
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
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionsListAdapter.clear();
        mQuestionsListAdapter.addAll(questions);
        mQuestionsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener: mListeners) {
            listener.onQuestionClicked(question);
        }
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    private Context getContext() {
        return getRootView().getContext();
    }
}
