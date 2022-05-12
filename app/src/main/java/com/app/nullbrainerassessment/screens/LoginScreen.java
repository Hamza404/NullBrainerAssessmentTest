package com.app.nullbrainerassessment.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.app.nullbrainerassessment.R;
import com.app.nullbrainerassessment.databinding.ActivityMainBinding;
import com.app.nullbrainerassessment.model.DataResponse;
import com.app.nullbrainerassessment.network.APIPresenter;
import com.app.nullbrainerassessment.network.ResponseCallBack;
import com.app.nullbrainerassessment.util.Library;
import com.google.gson.Gson;

public class LoginScreen extends AppCompatActivity implements ResponseCallBack {

    ActivityMainBinding binding;
    APIPresenter apiPresenter;
    boolean loginStatus = false;
    String emailAddress = "";
    Library library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        library = new Library(this);
        apiPresenter = new APIPresenter(this, this);
        binding.signInBtn.setOnClickListener(view1 -> {
            loginUser();
        });
    }

    private void loginUser() {
        if(!binding.editText1.getText().toString().isEmpty()) {
            if(!loginStatus) {
                library.hideKeyBoard();
                emailAddress = binding.editText1.getText().toString();
                apiPresenter.doesUserExist(binding.editText1.getText().toString());
            }
            else {
                library.hideKeyBoard();
                apiPresenter.userLogin(emailAddress, binding.editText1.getText().toString());
            }
        } else
            Toast.makeText(this, getString(R.string.please_enter_required_field), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String response, String type) {
        Gson gson = new Gson();
        if(type.equals(Library.DoesUserExist)) {
            DataResponse dataResponse = gson.fromJson(response, DataResponse.class);
            if(dataResponse.getRequestedAction() && dataResponse.getMessage().equals(Library.USERFOUND)) {
                Toast.makeText(this, dataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                updateUI();
            } else {
                Toast.makeText(this, dataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SignupScreen.class));
            }
        } else if(type.equals(Library.UserLogin)) {
            DataResponse dataResponse = gson.fromJson(response, DataResponse.class);
            if(dataResponse.getRequestedAction()) {
                Toast.makeText(this, getString(R.string.login_successfully), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, dataResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(int value) {

    }

    private void updateUI() {
        loginStatus = true;
        binding.signInBtn.setText(getString(R.string.sign_in));
        binding.editText1.setText("");
        binding.editText1.setHint("Password");
        binding.editText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.headerMsgText.setText(getString(R.string.sign_in));
    }
}