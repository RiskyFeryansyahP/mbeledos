package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TransaksiActivity extends AppCompatActivity {

    private static final String TAG = "TransaksiActivity";

    public static int JumlahBanBocor;
    public static int JumlahBanDalam;
    public static int JumlahBanLuar;


    private int TotalHarga, TotalBanBocor = 0, TotalBanDalam = 0, TotalBanLuar = 0;

    private TextView HargaBanBocor, HargaBanDalam, HargaBanLuar, Total;

    private Button tombolPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        HargaBanBocor = findViewById(R.id.HargaBanBocor);
        HargaBanDalam = findViewById(R.id.HargaBanDalam);
        HargaBanLuar = findViewById(R.id.HargaBanLuar);
        Total = findViewById(R.id.Total);

        tombolPesanan = findViewById(R.id.tombolpesan);

        TotalBanBocor = JumlahBanBocor * 15000;
        TotalBanDalam = JumlahBanDalam * 125000;
        TotalBanLuar = JumlahBanLuar * 30000;

        TotalHarga = TotalBanBocor + TotalBanDalam + TotalBanLuar;

        HargaBanBocor.setText("Rp. " + String.valueOf(TotalBanBocor));
        HargaBanDalam.setText("Rp" +String.valueOf(TotalBanDalam) );
        HargaBanLuar.setText("Rp" +String.valueOf(TotalBanLuar));
        Total.setText("Rp"  +  String.valueOf(TotalHarga));

        tombolPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransaksiActivity.this, MapsOrder.class));
            }
        });

    }
}
