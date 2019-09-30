package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;

/**
 * Created by Stephen Gemin on 9/26/2019
 */
public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    public interface Listener {
        void onNavigateUpClicked();
    }

    void bindQuestions(QuestionDetails question);

    void showProgressIndication();

    void hideProgressIndication();
}
