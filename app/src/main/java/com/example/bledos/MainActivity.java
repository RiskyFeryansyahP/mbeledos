package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
