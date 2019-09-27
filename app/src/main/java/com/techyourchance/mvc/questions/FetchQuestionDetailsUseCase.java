package com.techyourchance.mvc.questions;

import com.techyourchance.mvc.networking.questions.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.screens.common.views.BaseObservable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Stephen Gemin on 9/27/2019
 */
public class FetchQuestionDetailsUseCase extends BaseObservable<FetchQuestionDetailsUseCase.Listener> {

    private final StackoverflowApi mStackoverflowApi;

    public FetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        mStackoverflowApi = stackoverflowApi;
    }

    public interface Listener {

        void onQuestionDetailsFetched(QuestionDetails questionDetails);
        void onQuestionDetailsFetchedFailed();
    }

    public void fetchQuestionDetailsAndNotify(String questionId) {
        mStackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if (response.isSuccessful()) {
                            notifySuccess(response.body().getQuestions());
                        } else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        notifyFailure();
                    }
                });
    }

    public void notifySuccess(QuestionSchema question) {
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailsFetched(
                    new QuestionDetails(
                            question.getId(),
                            question.getTitle(),
                            question.getBody()));
        }
    }

    public void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailsFetchedFailed();
        }
    }
}
