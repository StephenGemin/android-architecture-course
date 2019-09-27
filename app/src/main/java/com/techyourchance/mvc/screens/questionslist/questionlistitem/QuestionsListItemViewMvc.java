package com.techyourchance.mvc.screens.questionslist.questionlistitem;

import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;
import com.techyourchance.mvc.questions.Question;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    public interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestion(Question question);
}