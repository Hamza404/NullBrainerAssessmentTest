package com.app.nullbrainerassessment.network;

public interface ResponseCallBack {
    void onSuccess(String response, String type);
    void onFailure(int value);
}
