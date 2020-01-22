package com.example.bledos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bledos.Helper.SharedPreferencesConfig;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    private FusedLocationProviderClient fusedLocationProviderClient;

    private Button btnSignIn, btnSignup;

    private TextView txt_about;

    private SharedPreferencesConfig sharedPreferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesConfig = new SharedPreferencesConfig(this);

        // get last location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // createRequestPermission();
        askRequestPermission();

        if (sharedPreferencesConfig.readLoginStatus()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        // declare variable of button
        btnSignIn = findViewById(R.id.btnSignInRoute);
        btnSignup = findViewById(R.id.btnSignUpRoute);

        txt_about = findViewById(R.id.txt_about);

        // give action variable of button
        btnSignIn.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        txt_about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // switch for any button id
        switch (view.getId())
        {
            case R.id.btnSignInRoute:
                startActivity(new Intent(MainActivity.this, HalamanSignIn.class));
                break;
            case R.id.btnSignUpRoute:
                startActivity(new Intent(MainActivity.this, SignUp.class));
                break;
            case R.id.txt_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: Called");

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: Request has been granted");
                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location == null) {
                                Log.d(TAG, "onSuccess: Location Null / Location Undetected!");
                                return;
                            }

                            sharedPreferencesConfig.writeLatitudeLocation(location.getLatitude());
                            sharedPreferencesConfig.writeLongitudeLocation(location.getLongitude());
                        }
                    });
                }
                else {
                    Log.d(TAG, "onRequestPermissionsResult: Create Request Permission");
                    createRequestPermission();
                }
                return;
        }
    }


    public void askRequestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    public void createRequestPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // permission tidak diberikan
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Required Location")
                        .setMessage("You have to give this permission to access the feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }
        else {
            Log.d(TAG, "createRequestPermission: Permission has been Granted!");
        }


    }
}
