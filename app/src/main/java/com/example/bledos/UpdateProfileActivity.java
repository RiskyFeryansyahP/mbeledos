package com.example.bledos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bledos.interfaces.ProfileAPI;
import com.example.bledos.model.ProfileModel;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateProfileActivity extends AppCompatActivity {

    private static final String TAG = "UpdateProfileActivity";

    public static String nohp;

    EditText editNama, editAlamat;

    Button btnUpdate;

    ProfileAPI profileAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Log.d(TAG, "onCreate: nohp " + nohp);

        editAlamat = findViewById(R.id.editAlamat);
        editNama = findViewById(R.id.editNama);

        btnUpdate = findViewById(R.id.btnEdit);

        OkHttpClient client = new OkHttpClient.Builder()
                .followSslRedirects(true)
                .followRedirects(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.172:8080/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        profileAPI = retrofit.create(ProfileAPI.class);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    public void updateProfile() {
        String nama = editNama.getText().toString();
        String alamat = editAlamat.getText().toString();
        ProfileModel profileModel = new ProfileModel(nama, alamat, nohp);
        Call<JsonObject> call = profileAPI.UpdateData(profileModel);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: failed " + response.code());
                    return;
                }

                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
