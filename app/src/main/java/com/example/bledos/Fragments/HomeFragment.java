package com.example.bledos.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bledos.Adapter.RecycleViewAdapterHome;
import com.example.bledos.Helper.SharedPreferencesConfig;
import com.example.bledos.R;
import com.example.bledos.interfaces.BengkelAPI;
import com.example.bledos.model.BengkelModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private SharedPreferencesConfig sharedPreferencesConfig;

    private TextView txtWelcomeHome;

    private BengkelAPI bengkelAPI;

    private RecyclerView recyclerView;

    // data for recyclerview
    private ArrayList<String> kodeBengkel = new ArrayList<>();
    private ArrayList<String> namaBengkel = new ArrayList<>();
    private ArrayList<String> alamatBengkel = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferencesConfig = new SharedPreferencesConfig(getActivity());

        return inflater.inflate(R.layout.activity_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtWelcomeHome = getActivity().findViewById(R.id.txtWelcomeHome);

        recyclerView = getActivity().findViewById(R.id.recyclerViewHome);

        JsonObject profile = sharedPreferencesConfig.readUserProfile();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+getActivity().getResources().getString(R.string.ip_server)+":8080/bengkel/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bengkelAPI = retrofit.create(BengkelAPI.class);

        Log.d(TAG, "onViewCreated: " + profile.get("nama").toString());

        txtWelcomeHome.setText("Hello, " + profile.get("nama").getAsString());

        GetAllBengkel();
    }

    public void GetAllBengkel() {
        Call<List<BengkelModel>> call = bengkelAPI.GetAllBengkel();
        call.enqueue(new Callback<List<BengkelModel>>() {
            @Override
            public void onResponse(Call<List<BengkelModel>> call, Response<List<BengkelModel>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Failed : " + response.code());
                    return;
                }

                if (response.body() != null) {
                    List<BengkelModel> bengkels = response.body();

                    for (BengkelModel bengkel : bengkels) {
                        kodeBengkel.add(bengkel.getKode_bengkel());
                        namaBengkel.add(bengkel.getNama_bengkel());
                        alamatBengkel.add(bengkel.getAlamat_bengkel());
                    }

                    setAdapterRecyclerViewHome(kodeBengkel, namaBengkel, alamatBengkel);
                }
                else
                {
                    Log.d(TAG, "onResponse: Data is Null");
                }
            }

            @Override
            public void onFailure(Call<List<BengkelModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    public void setAdapterRecyclerViewHome(ArrayList<String> kodeBengkel, ArrayList<String> namaBengkel, ArrayList<String> alamatBengkel) {
        Log.d(TAG, "setAdapterRecyclerViewHome: Called");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        RecycleViewAdapterHome recycleViewAdapterHome = new RecycleViewAdapterHome(getActivity(), kodeBengkel, namaBengkel, alamatBengkel);
        recyclerView.setAdapter(recycleViewAdapterHome);
    }
}
