package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TransaksiActivity extends AppCompatActivity {

    private static final String TAG = "TransaksiActivity";

    public static int BanBocor;
    public static int BanDalam;
    public static int BanLuar;
    public static int TotalHarga;

    TextView HargaBanBocor, HargaBanDalam, HargaBanLuar, Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        BanBocor = BanBocor * 15000;
        BanDalam = BanDalam * 125000;
        BanLuar = BanLuar * 30000;

        TotalHarga = BanBocor + BanDalam + BanLuar;

        HargaBanBocor = findViewById(R.id.HargaBanBocor);
        HargaBanDalam = findViewById(R.id.HargaBanDalam);
        HargaBanLuar = findViewById(R.id.HargaBanLuar);
        Total = findViewById(R.id.Total);

        HargaBanBocor.setText("Rp. " + String.valueOf(BanBocor));
        HargaBanDalam.setText("Rp" +String.valueOf(BanDalam) );
        HargaBanLuar.setText("Rp" +String.valueOf(BanLuar));
        Total.setText("Rp"  +  String.valueOf(TotalHarga));



    }
}
