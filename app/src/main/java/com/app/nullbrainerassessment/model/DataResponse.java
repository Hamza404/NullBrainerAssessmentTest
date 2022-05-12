package com.app.nullbrainerassessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Requested_Action")
    @Expose
    private Boolean requestedAction;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRequestedAction() {
        return requestedAction;
    }

    public void setRequestedAction(Boolean requestedAction) {
        this.requestedAction = requestedAction;
    }
}