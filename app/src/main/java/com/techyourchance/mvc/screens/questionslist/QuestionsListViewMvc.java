package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.common.ObservableViewMvc;
import com.techyourchance.mvc.common.ViewMvc;
import com.techyourchance.mvc.questions.Question;

import java.util.List;

/**
 * Created by Stephen Gemin on 9/23/2019
 */
interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    public interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);
}
