package com.app.nullbrainerassessment.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.nullbrainerassessment.R;
import com.app.nullbrainerassessment.databinding.ActivityMainBinding;
import com.app.nullbrainerassessment.databinding.SignupScreenActivityBinding;
import com.app.nullbrainerassessment.model.DataResponse;
import com.app.nullbrainerassessment.network.APIPresenter;
import com.app.nullbrainerassessment.network.ResponseCallBack;
import com.app.nullbrainerassessment.util.Library;
import com.google.gson.Gson;

import java.util.Objects;

public class SignupScreen extends AppCompatActivity implements ResponseCallBack {

    SignupScreenActivityBinding binding;
    APIPresenter apiPresenter;
    Library library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignupScreenActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        library = new Library(this);
        apiPresenter = new APIPresenter(this, this);

        binding.createAccountBtn.setOnClickListener(view1 -> {
            createAccount();
        });
    }

    private void createAccount() {
        if(validation()) {
            library.hideKeyBoard();
            apiPresenter.createUserAccount(binding.email.getText().toString(), binding.password.getText().toString(), binding.fullName.getText().toString(), binding.lastName.getText().toString(),
                    binding.cellNumber.getText().toString(), binding.email.getText().toString());
        }
    }

    private boolean validation() {
        if(Objects.requireNonNull(binding.fullName.getText()).toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.full_name_msg), Toast.LENGTH_SHORT).show();
            return false;
        } else if(Objects.requireNonNull(binding.lastName.getText()).toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.last_name_msg), Toast.LENGTH_SHORT).show();
            return false;
        } else if(Objects.requireNonNull(binding.email.getText()).toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.email_address_msg), Toast.LENGTH_SHORT).show();
            return false;
        }else if(Objects.requireNonNull(binding.cellNumber.getText()).toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.phone_number_msg), Toast.LENGTH_SHORT).show();
            return false;
        } else if(Objects.requireNonNull(binding.password.getText()).toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.your_password_msg), Toast.LENGTH_SHORT).show();
            return false;
        } else if(Objects.requireNonNull(binding.confirmPassword.getText()).toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.confirm_password_msg), Toast.LENGTH_SHORT).show();
            return false;
        } else if(!binding.confirmPassword.getText().toString().equals(binding.password.getText().toString())) {
            Toast.makeText(this, getString(R.string.password_match_msg), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess(String response, String type) {
        Gson gson = new Gson();
        DataResponse dataResponse = gson.fromJson(response, DataResponse.class);
        if(dataResponse.getRequestedAction()) {
            Toast.makeText(this, dataResponse.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, dataResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int value) {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
}