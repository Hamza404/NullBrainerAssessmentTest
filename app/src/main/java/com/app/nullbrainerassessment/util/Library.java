package com.app.nullbrainerassessment.util;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class Library {

    Context contextL;
    private ProgressDialog progressDialog;
    Activity activity;
    public static String USER_TOKEN = "";
    public static String DoesUserExist = "doesUserExist";
    public static String UserLogin = "userLogin";
    public static String USERFOUND = "User Found.";

    public Library(Context context) {
        this.contextL = context;
        activity = (Activity) context;
    }
    public void showLoading(final String message) {
        activity.runOnUiThread(() -> {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(contextL, "", message
                        + "...", true, true);
                progressDialog.setCancelable(false);
            } else {
                progressDialog.setMessage(message + "...");
            }
            if(!((Activity) contextL).isFinishing()) {
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
            }
        });
    }

    public void hideLoading() {
        activity.runOnUiThread(() -> {
            try {
                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.cancel();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void hideKeyBoard() {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
