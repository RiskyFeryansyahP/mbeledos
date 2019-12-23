package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgButtonPlusBanBocor, imgButtonPlusBanDalam, imgButtonPlusBanLuar;
    ImageButton imgButtonMenBanBocor, imgButtonMenBanDalam, imgButtonMenBanLuar;
    TextView txtBanBocor, txtBanLuar, txtBanDalam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        imgButtonPlusBanBocor = findViewById(R.id.imgButtonPlusBanBocor);
        imgButtonPlusBanDalam = findViewById(R.id.imgButtonPlusBanDalam);
        imgButtonPlusBanLuar  = findViewById(R.id.imgButtonPlusBanLuar);
        imgButtonMenBanBocor  = findViewById(R.id.imgButtonMenBanBocor);
        imgButtonMenBanDalam  = findViewById(R.id.imgButtonMenBanDalam);
        imgButtonMenBanLuar   = findViewById(R.id.imgButtonMenBanLuar);

        txtBanBocor = findViewById(R.id.txtBanBocor);
        txtBanDalam = findViewById(R.id.txtBanDalam);
        txtBanLuar  = findViewById(R.id.txtBanLuar);

        imgButtonPlusBanBocor.setOnClickListener(this);
        imgButtonPlusBanDalam.setOnClickListener(this);
        imgButtonPlusBanLuar.setOnClickListener(this);

        imgButtonMenBanBocor.setOnClickListener(this);
        imgButtonMenBanDalam.setOnClickListener(this);
        imgButtonMenBanLuar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgButtonPlusBanBocor:
                txtBanBocor.setText("1");
                imgButtonPlusBanBocor.setEnabled(false);
                imgButtonMenBanBocor.setEnabled(true);
                break;
            case R.id.imgButtonPlusBanDalam:
                txtBanDalam.setText("1");
                imgButtonPlusBanDalam.setEnabled(false);
                imgButtonMenBanDalam.setEnabled(true);
                break;
            case R.id.imgButtonPlusBanLuar:
                txtBanLuar.setText("1");
                imgButtonPlusBanLuar.setEnabled(false);
                imgButtonMenBanLuar.setEnabled(true);
                break;
            case R.id.imgButtonMenBanBocor:
                txtBanBocor.setText("0");
                imgButtonMenBanBocor.setEnabled(false);
                imgButtonPlusBanBocor.setEnabled(true);
                break;
            case R.id.imgButtonMenBanDalam:
                txtBanDalam.setText("0");
                imgButtonMenBanDalam.setEnabled(false);
                imgButtonPlusBanDalam.setEnabled(true);
                break;
            case R.id.imgButtonMenBanLuar:
                txtBanLuar.setText("0");
                imgButtonMenBanLuar.setEnabled(false);
                imgButtonPlusBanLuar.setEnabled(true);
                break;
        }
    }
}
