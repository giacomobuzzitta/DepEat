package com.example.depeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.depeat.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton, registerButton;

    private EditText emailEt, passwordEt;

    private LinearLayout layout;


    final static int LEN_PASSWORD = 6;
    final static String EMAIL_key = "riporto";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Allega queta activity a activity_layout.xml file
        setContentView(R.layout.activity_main);



        loginButton = findViewById(R.id.login_btn);
        registerButton = findViewById(R.id.register_batton);
        emailEt = findViewById(R.id.email_Et);
        passwordEt = findViewById(R.id.password_Et);

        layout = findViewById(R.id.layout);



        registerButton.setVisibility(View.VISIBLE);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        Log.i("LoginActivity", "Activity created");

    }
    private void doLogin(String email){
        if(verifyEmail(email) && passwordEt.getText().toString().length()>LEN_PASSWORD){
            showToast("Accesso eseguito!");

        }else{
            showToast("Accesso negato!");
        }
    }

    private void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    private boolean verifyEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    @Override
    public void onClick(View v) {
        //TODO DO SOMETHING
        if(v.getId() == R.id.login_btn){
            doLogin(emailEt.getText().toString());  // chiamo il metodo "doLogin" che verifica mail e Fa partire una nuova activity
            //TODO DO LOGIN

        }else if(v.getId() == R.id.register_batton){
            Intent i = new Intent(this,RegisterActivity.class);
            startActivity(i);
            //TODO GO REGISTER ACTIVITY
        }
    }
}