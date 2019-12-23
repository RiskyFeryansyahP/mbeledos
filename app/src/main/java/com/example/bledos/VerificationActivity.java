package com.example.bledos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bledos.Helper.SharedPreferencesConfig;

public class VerificationActivity extends AppCompatActivity {

    private static final String TAG = "VerificationActivity";

    public static int OTP;

    int code;

    EditText code1,code2,code3,code4;
    Button button;

    private SharedPreferencesConfig sharedPreferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        sharedPreferencesConfig = new SharedPreferencesConfig(this);

        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        button = findViewById(R.id.btnsubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String kode1 = code1.getText().toString();
                String kode2 = code2.getText().toString();
                String kode3 = code3.getText().toString();
                String kode4 = code4.getText().toString();

                String kode = kode1 + "" + kode2 + "" + kode3 + "" + kode4;

                code = Integer.parseInt(kode);

                if (code == OTP){
                    sharedPreferencesConfig.writeLoginStatus(true);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }
        });
    }
}
