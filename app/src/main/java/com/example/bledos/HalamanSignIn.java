package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bledos.interfaces.AuthenticationAPI;
import com.example.bledos.model.SignInModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HalamanSignIn extends AppCompatActivity implements View.OnClickListener {

    // Log Tag
    private static final String TAG = "HalamanSignIn";

    private AuthenticationAPI authenticationAPI;

    private SignInModel signIn;

    // declare variable of widget
    private Button btnSignin;
    private EditText editTextNoHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_sign_in);

        // initialize id for any widget
        editTextNoHp = findViewById(R.id.nohpRegister);
        btnSignin = findViewById(R.id.btnSignIn);

        // initialize action every widget
        btnSignin.setOnClickListener(this);

        // initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.106:8080/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // initalize AuthenticationAPI Interface
        authenticationAPI = retrofit.create(AuthenticationAPI.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnSignIn:
                String phonenumber = editTextNoHp.getText().toString();
                DoLogin(phonenumber);
                break;
        }
    }

    private void DoLogin(String phonenumber)
    {
        signIn = new SignInModel(phonenumber);

        // call api authentication
        Call<JsonObject> call = authenticationAPI.LoginUser(signIn);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // if not success
                if (!response.isSuccessful())
                {
                    Log.d(TAG, "onResponse Status Code Failed : " + response.code());
                    return;
                }

                Log.d(TAG, "onResponse Status Code Succeed : " + response.code());
                Log.d(TAG, "onResponse Body : " + response.body());
                startActivity(new Intent(HalamanSignIn.this, HomeActivity.class));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
