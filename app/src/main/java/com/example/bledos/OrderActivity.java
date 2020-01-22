package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bledos.Helper.SharedPreferencesConfig;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "OrderActivity";
    
    Button imgtombolpesan;
    ImageButton imgButtonPlusBanBocor, imgButtonPlusBanDalam, imgButtonPlusBanLuar;
    ImageButton imgButtonMenBanBocor, imgButtonMenBanDalam, imgButtonMenBanLuar;
    TextView txtBanBocor, txtBanLuar, txtBanDalam;
    TextView jarak;
    TextView nama_bengkel, alamat_bengkel;

    private static SharedPreferencesConfig sharedPreferencesConfig;

    public static double latitudePlace, longitudePlace;

    public static String namaBengkel, alamatBengkel;

    private static Point ORIGIN_POINT;
    private static Point DESTIONATION_POINT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        sharedPreferencesConfig = new SharedPreferencesConfig(this);

        // origin point / location user
        ORIGIN_POINT = Point.fromLngLat(sharedPreferencesConfig.readLongitudeLocation(), sharedPreferencesConfig.readLatitudeLocation());
        // destination point / bengkel order
        DESTIONATION_POINT = Point.fromLngLat(longitudePlace, latitudePlace);

        imgtombolpesan = findViewById(R.id.tombolpesan);
        imgtombolpesan.setOnClickListener(this);

        imgButtonPlusBanBocor = findViewById(R.id.imgButtonPlusBanBocor);
        imgButtonPlusBanDalam = findViewById(R.id.imgButtonPlusBanDalam);
        imgButtonPlusBanLuar  = findViewById(R.id.imgButtonPlusBanLuar);
        imgButtonMenBanBocor  = findViewById(R.id.imgButtonMenBanBocor);
        imgButtonMenBanDalam  = findViewById(R.id.imgButtonMenBanDalam);
        imgButtonMenBanLuar   = findViewById(R.id.imgButtonMenBanLuar);

        nama_bengkel = findViewById(R.id.nama_bengkel);
        alamat_bengkel = findViewById(R.id.bengkel_alamat);

        jarak = findViewById(R.id.jarak);

        txtBanBocor = findViewById(R.id.txtBanBocor);
        txtBanDalam = findViewById(R.id.txtBanDalam);
        txtBanLuar  = findViewById(R.id.txtBanLuar);

        imgButtonPlusBanBocor.setOnClickListener(this);
        imgButtonPlusBanDalam.setOnClickListener(this);
        imgButtonPlusBanLuar.setOnClickListener(this);

        imgButtonMenBanDalam.setEnabled(false);
        imgButtonMenBanBocor.setEnabled(false);
        imgButtonMenBanLuar.setEnabled(false);

        imgButtonMenBanBocor.setOnClickListener(this);
        imgButtonMenBanDalam.setOnClickListener(this);
        imgButtonMenBanLuar.setOnClickListener(this);

        nama_bengkel.setText(namaBengkel);
        alamat_bengkel.setText(alamatBengkel);
        
        requestDirectionPoint(ORIGIN_POINT, DESTIONATION_POINT);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgButtonPlusBanBocor:
                txtBanBocor.setText("1");
                TransaksiActivity.JumlahBanBocor = 1;
                imgButtonPlusBanBocor.setEnabled(false);
                imgButtonMenBanBocor.setEnabled(true);
                break;
            case R.id.imgButtonPlusBanDalam:
                txtBanDalam.setText("1");
                TransaksiActivity.JumlahBanDalam = 1;
                imgButtonPlusBanDalam.setEnabled(false);
                imgButtonMenBanDalam.setEnabled(true);
                break;
            case R.id.imgButtonPlusBanLuar:
                txtBanLuar.setText("1");
                TransaksiActivity.JumlahBanLuar = 1;
                imgButtonPlusBanLuar.setEnabled(false);
                imgButtonMenBanLuar.setEnabled(true);
                break;
            case R.id.imgButtonMenBanBocor:
                txtBanBocor.setText("0");
                TransaksiActivity.JumlahBanBocor = 0;
                imgButtonMenBanBocor.setEnabled(false);
                imgButtonPlusBanBocor.setEnabled(true);
                break;
            case R.id.imgButtonMenBanDalam:
                txtBanDalam.setText("0");
                TransaksiActivity.JumlahBanDalam = 0;
                imgButtonMenBanDalam.setEnabled(false);
                imgButtonPlusBanDalam.setEnabled(true);
                break;
            case R.id.imgButtonMenBanLuar:
                txtBanLuar.setText("0");
                TransaksiActivity.JumlahBanLuar = 0;
                imgButtonMenBanLuar.setEnabled(false);
                imgButtonPlusBanLuar.setEnabled(true);
                break;

            case R.id.tombolpesan:
                startActivity(new Intent(OrderActivity.this,TransaksiActivity.class));
                break;
        }
    }

    public void requestDirectionPoint(Point originPoint, Point destinationPoint) {
        MapboxDirections mapboxDirections = MapboxDirections.builder()
            .origin(originPoint)
            .destination(destinationPoint)
            .overview(DirectionsCriteria.OVERVIEW_FULL)
            .profile(DirectionsCriteria.PROFILE_DRIVING)
            .accessToken(getResources().getString(R.string.API_KEY_MAPBOX))
            .build();
        
        mapboxDirections.enqueueCall(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body() );

                DirectionsResponse body = response.body();
                List<DirectionsRoute> routes = body.routes();
                double distance = routes.get(0).distance();

                jarak.setText(String.valueOf(distance) + " Meter");
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
