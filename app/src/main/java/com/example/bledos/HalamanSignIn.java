package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bledos.Helper.SharedPreferencesConfig;
import com.example.bledos.interfaces.AuthenticationAPI;
import com.example.bledos.model.RequestNexmoSMS;
import com.example.bledos.model.SignInModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.security.auth.login.LoginException;

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

    // shared preferences load
    private SharedPreferencesConfig sharedPreferencesConfig;

    // declare variable of widget
    private Button btnSignin;
    private EditText editTextNoHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_sign_in);

        // inialize shared preferences
        sharedPreferencesConfig = new SharedPreferencesConfig(this);

        // initialize id for any widget
        editTextNoHp = findViewById(R.id.nohpRegister);
        btnSignin = findViewById(R.id.btnSignIn);

        // initialize action every widget
        btnSignin.setOnClickListener(this);

        // initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+getResources().getString(R.string.ip_server)+":8080/user/")
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

                if (editTextNoHp.length() == 0) {
                    editTextNoHp.setError("Please enter a nohp");
                    editTextNoHp.requestFocus();
                    return;
                }

                DoLogin(phonenumber);
                break;
        }
    }

    private void DoLogin(String phonenumber)
    {
        signIn = new SignInModel(phonenumber);
        final RequestNexmoSMS requestNexmoSMS = new RequestNexmoSMS(phonenumber);
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
                JsonObject jsonObject = response.body();

                sharedPreferencesConfig.writeLoginProfile(jsonObject);

                Call<JsonObject> sms = authenticationAPI.SendVerificationCode(requestNexmoSMS);
                sms.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "onResponse: code failed : " + response.code());
                        }

                        Log.d(TAG, "onResponse: Verification " + response.body());
                        JsonObject jsonObject1 = response.body();
                        String code = jsonObject1.get("OTP").getAsString();
                        VerificationActivity.OTP = Integer.parseInt(code);

                        startActivity(new Intent(HalamanSignIn.this, VerificationActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage() );
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
