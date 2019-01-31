package com.example.depeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements TextWatcher {

    private EditText insMail;
    private EditText insPassword;
    private EditText insTelefono;

    private Button register;

    final static int LEN_PASSWORD = 6;
    final static int LEN_number = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        insMail = findViewById(R.id.insMail);
        insMail.addTextChangedListener(this);
        insPassword = findViewById(R.id.insPassword);
        insPassword.addTextChangedListener(this);
        insTelefono = findViewById(R.id.insTelefono);
        insTelefono.addTextChangedListener(this);

        register = findViewById(R.id.registerB);
    }

    private boolean verifyEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private void convalidation() {
        if (verifyEmail(insMail.getText().toString()) && insPassword.getText().toString().length() > LEN_PASSWORD && insTelefono.getText().length() == LEN_number) {
            register.setEnabled(true);
        }else
            register.setEnabled(false);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        convalidation();
    }
}