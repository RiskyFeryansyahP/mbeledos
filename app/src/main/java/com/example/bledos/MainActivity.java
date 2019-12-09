package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bledos.Helper.SharedPreferencesConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn, btnSignup;

    private SharedPreferencesConfig sharedPreferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesConfig = new SharedPreferencesConfig(this);

        if (sharedPreferencesConfig.readLoginStatus()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        // declare variable of button
        btnSignIn = findViewById(R.id.btnSignInRoute);
        btnSignup = findViewById(R.id.btnSignUpRoute);

        // give action variable of button
        btnSignIn.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // switch for any button id
        switch (view.getId())
        {
            case R.id.btnSignInRoute:
                startActivity(new Intent(MainActivity.this, HalamanSignIn.class));
                finish();
                break;
            case R.id.btnSignUpRoute:
                startActivity(new Intent(MainActivity.this, SignUp.class));
                break;

        }
    }
}
