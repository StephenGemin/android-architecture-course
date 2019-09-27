package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;

/**
 * Created by Stephen Gemin on 9/26/2019
 */
interface QuestionDetailsViewMvc {
    void bindQuestions(QuestionDetails question);

    void showProgressIndication();

    void hideProgressIndication();
}
