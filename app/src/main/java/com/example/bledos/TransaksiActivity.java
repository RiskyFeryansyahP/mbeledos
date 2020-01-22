package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bledos.Helper.SharedPreferencesConfig;
import com.example.bledos.interfaces.PubnubAPI;
import com.example.bledos.model.DataPubnubModel;
import com.example.bledos.model.PubnubModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransaksiActivity extends AppCompatActivity {

    private static final String TAG = "TransaksiActivity";

    public static int JumlahBanBocor;
    public static int JumlahBanDalam;
    public static int JumlahBanLuar;

    public static String phonenumber;


    private int TotalHarga, TotalBanBocor = 0, TotalBanDalam = 0, TotalBanLuar = 0;

    private TextView HargaBanBocor, HargaBanDalam, HargaBanLuar, Total, bengkel_nama, bengkel_alamat;

    private Button tombolPesanan;

    private SharedPreferencesConfig sharedPreferencesConfig;

    private PubnubAPI pubnubAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        Log.d(TAG, "onCreate: " + phonenumber);

        sharedPreferencesConfig = new SharedPreferencesConfig(this);

        bengkel_nama = findViewById(R.id.bengkel_nama);
        bengkel_alamat = findViewById(R.id.bengkel_alamat);

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

        bengkel_alamat.setText(OrderActivity.alamatBengkel);
        bengkel_nama.setText(OrderActivity.namaBengkel);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ps.pndsn.com/publish/pub-c-e3c84d6a-103c-4b05-b545-97bfeab0459f/sub-c-8ce99d92-36dc-11ea-8078-f6a3bb2caa12/0/"+phonenumber+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pubnubAPI = retrofit.create(PubnubAPI.class);

        tombolPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessage();
                startActivity(new Intent(TransaksiActivity.this, MapsOrder.class));
            }
        });

    }

    private void SendMessage() {
        Double latitude = sharedPreferencesConfig.readLatitudeLocation();
        Double longitude = sharedPreferencesConfig.readLongitudeLocation();
        DataPubnubModel dataPubnubModel = new DataPubnubModel(phonenumber, latitude, longitude);
        PubnubModel model = new PubnubModel();
        PubnubModel.PNGCM pn_gcm = model.setPNGCM(dataPubnubModel);
        model.setPubnubModel(pn_gcm);

        Gson gson = new Gson();
        String result = gson.toJson(model);

        Log.d(TAG, "SendMessage: Result " + result);


        Call<JsonArray> call = pubnubAPI.SendNotificationToBengkel(model);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse Failed : " + response.code());
                    return;
                }

                Log.d(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
