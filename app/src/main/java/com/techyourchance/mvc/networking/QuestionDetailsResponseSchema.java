package com.techyourchance.mvc.networking;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * Created by Stephen Gemin on 9/25/2019
 */
public class QuestionDetailsResponseSchema {

    @SerializedName("items")
    private final List<QuestionSchema> mQuestions;

    public QuestionDetailsResponseSchema(QuestionSchema question) {
        mQuestions = Collections.singletonList(question);
    }

    public QuestionSchema getQuestions() {
        return mQuestions.get(0);
    }
}
