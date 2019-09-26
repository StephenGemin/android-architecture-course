package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ViewMvc;

/**
 * Created by Stephen Gemin on 9/26/2019
 */
public interface QuestionDetailsViewMvc extends ViewMvc {

    void showProgressIndication();
    void hideProgressIndication();
    void bindQuestion(QuestionDetails question);
}
