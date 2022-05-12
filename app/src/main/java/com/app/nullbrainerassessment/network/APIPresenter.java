package com.app.nullbrainerassessment.network;

import android.content.Context;

import com.app.nullbrainerassessment.R;
import com.app.nullbrainerassessment.util.Library;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class APIPresenter {
    ApiInterface apiInterface;
    Context context;
    ResponseCallBack response;
    Library library;

    public APIPresenter(Context context, ResponseCallBack callbackListener) {
        this.response = callbackListener;
        this.context = context;
        library = new Library(context);
        this.apiInterface = APIClient.getClient().create(ApiInterface.class);
    }

    public void createUserAccount(String email, String password, String firstName, String lastName, String cellNo, String mailingAddress) {
        library.showLoading(context.getResources().getString(R.string.loading));
        apiInterface.createUserAccount(email, password, firstName, lastName, cellNo, mailingAddress)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonElement jsonElement) {
                        Gson gson = new Gson();
                        String data = gson.toJson(jsonElement); // Gson Converting reason to handle Generic response as String.
                        response.onSuccess(data, "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        response.onFailure(0);
                        library.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        library.hideLoading();
                    }
                });
    }

    public void doesUserExist(String email) {
        library.showLoading(context.getResources().getString(R.string.loading));
        apiInterface.doesUserExist(email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonElement jsonElement) {
                        Gson gson = new Gson();
                        String data = gson.toJson(jsonElement); // Gson Converting reason to handle Generic response as String.
                        response.onSuccess(data, Library.DoesUserExist);
                    }

                    @Override
                    public void onError(Throwable e) {
                        response.onFailure(0);
                        library.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        library.hideLoading();
                    }
                });
    }

    public void userLogin(String email, String password) {
        library.showLoading(context.getResources().getString(R.string.loading));
        apiInterface.userLogin(email, password, "e43afb11-7fdd-4796-825d-018500b98b4f")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonElement jsonElement) {
                        Gson gson = new Gson();
                        String data = gson.toJson(jsonElement); // Gson Converting reason to handle Generic response as String.
                        response.onSuccess(data, Library.UserLogin);
                    }

                    @Override
                    public void onError(Throwable e) {
                        response.onFailure(0);
                        library.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        library.hideLoading();
                    }
                });
    }
}
