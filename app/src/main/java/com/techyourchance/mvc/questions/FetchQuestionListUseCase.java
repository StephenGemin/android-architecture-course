package com.techyourchance.mvc.questions;

import com.techyourchance.mvc.common.Constants;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.QuestionsListResponseSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.screens.common.BaseObservable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Stephen Gemin on 9/27/2019
 */
public class FetchQuestionListUseCase extends BaseObservable<FetchQuestionListUseCase.Listener> {

    private final StackoverflowApi mStackoverflowApi;

    public FetchQuestionListUseCase(StackoverflowApi stackoverflowApi) {
        mStackoverflowApi = stackoverflowApi;
    }

    public interface Listener {

        void onQuestionListFetchSuccess(List<Question> questions);

        void onQuestionListFetchFailure();
    }

    public void fetchQuestionListAndNotify() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                        if (response.isSuccessful()) {
                            notifyFetchSuccess(response.body().getQuestions());
                        } else {
                            notifyFetchFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                        notifyFetchFailure();
                    }
                } );
    }

    public void notifyFetchSuccess(List<QuestionSchema> questionSchemas) {
        List<Question> questions = new ArrayList<>(questionSchemas.size());
        for (QuestionSchema questionSchema : questionSchemas) {
            questions.add(new Question(questionSchema.getId(), questionSchema.getTitle()));
        }

        for (Listener listener : getListeners()) {
            listener.onQuestionListFetchSuccess(questions);
        }
    }

    public void notifyFetchFailure() {
        for (Listener listener : getListeners()) {
            listener.onQuestionListFetchFailure();
        }
    }
}
