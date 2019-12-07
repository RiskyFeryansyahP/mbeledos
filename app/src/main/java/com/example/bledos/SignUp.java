package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bledos.interfaces.AuthenticationAPI;
import com.example.bledos.model.SignUpModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    // log tag
    private static final String TAG = "SignUp";

    // declare of AuthenticationAPI
    private AuthenticationAPI authenticationAPI;

    // declare of widget component
    private Button btnSignup;
    private EditText editTextName, editTextAddress, editTextDate, editTextPhoneNumber;

    // declare Signup model
    private SignUpModel signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // initialize widget component
        editTextName = findViewById(R.id.nama);
        editTextAddress = findViewById(R.id.alamat);
        editTextDate = findViewById(R.id.tgl);
        editTextPhoneNumber = findViewById(R.id.nohpRegister);

        btnSignup = findViewById(R.id.btnSignUp);

        // set action of widget or component
        btnSignup.setOnClickListener(this);

        // initialize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.117:8080/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // initialize AuthenticationAPI interface
        authenticationAPI = retrofit.create(AuthenticationAPI.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnSignUp:

                break;
        }
    }

    private void DoRegister(String name, String address, String date, String phonenumber)
    {
        signUp = new SignUpModel(name, address, date, phonenumber);
        Call<JsonObject> call = authenticationAPI.RegisterUser(signUp);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                // if not succed
                if (!response.isSuccessful())
                {
                    Log.d(TAG, "onResponse Status Code Failed : " + response.code());
                    return;
                }
                Log.d(TAG, "onResponse Status Code Success : " + response.code());
                Log.d(TAG, "onResponse Body : " + response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
