package com.example.bledos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        code1.addTextChangedListener(VerificationTextWatcher);
        code2.addTextChangedListener(VerificationTextWatcher);
        code3.addTextChangedListener(VerificationTextWatcher);
        code4.addTextChangedListener(VerificationTextWatcher);

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
    private TextWatcher VerificationTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable == code1.getEditableText())
            {
                if(code1.length() == 1)
                {
                    code2.requestFocus();
                }
                else if(code1.length() == 0)
                {
                    code1.clearFocus();
                }
            }
            else if(editable == code2.getEditableText())
            {
                if(code2.length() == 1)
                {
                    code3.requestFocus();
                }
                else if(code2.length() == 0)
                {
                    code1.requestFocus();
                }
            }
            else if(editable == code3.getEditableText())
            {
                if(code3.length() == 1)
                {
                    code4.requestFocus();
                }
                else if(code3.length() == 0)
                {
                    code2.requestFocus();
                }
            }
            else if(editable == code4.getEditableText())
            {
                if(code4.length() == 1)
                {
                    code4.clearFocus();
                }
                else if(code4.length() == 0)
                {
                    code3.requestFocus();
                }
            }
        }
    };
}
