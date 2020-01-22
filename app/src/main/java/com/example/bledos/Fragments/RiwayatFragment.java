package com.example.bledos.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bledos.Adapter.RecycleViewAdapterHome;
import com.example.bledos.Adapter.RecyclerViewAdapterRiwayat;
import com.example.bledos.Helper.SharedPreferencesConfig;
import com.example.bledos.R;
import com.example.bledos.interfaces.TransaksiAPI;
import com.example.bledos.model.TransaksiModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RiwayatFragment extends Fragment {

    private static final String TAG = "RiwayatFragment";

    SharedPreferencesConfig sharedPreferencesConfig;

    RecyclerView recyclerView;

    public ArrayList<String> notelp = new ArrayList<>();
    public ArrayList<String> bengkelorder = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferencesConfig = new SharedPreferencesConfig(getActivity());

        return inflater.inflate(R.layout.activity_riwayat_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.recyclerViewRiwayat);

        JsonObject profile = sharedPreferencesConfig.readUserProfile();
        String nohp = profile.get("nohp").getAsString();

        Log.d(TAG, "onViewCreated: Nohp  " + nohp);

        getTransaksi(nohp);

    }

    public void getTransaksi(String nohp) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+getActivity().getResources().getString(R.string.ip_server)+":8080/transaction/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TransaksiAPI transaksiAPI = retrofit.create(TransaksiAPI.class);

        Call<List<TransaksiModel>> call = transaksiAPI.GetTransaksi(nohp);

        call.enqueue(new Callback<List<TransaksiModel>>() {
            @Override
            public void onResponse(Call<List<TransaksiModel>> call, Response<List<TransaksiModel>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Failed : " + response.code());
                }

                if (response != null) {
                    Log.d(TAG, "onResponse: " + response.body());

                    List<TransaksiModel> transaksiModels = response.body();

                    for (TransaksiModel transaksiModel : transaksiModels) {

                        Log.d(TAG, "No Telp :  " + transaksiModel.getOrderphone());
                        Log.d(TAG, "Bengkel Name : " + transaksiModel.getNamabengkel());
                        notelp.add(transaksiModel.getOrderphone());
                        bengkelorder.add(transaksiModel.getNamabengkel());
                    }
                    setAdapterRecyclerViewRiwayat(notelp, bengkelorder, getActivity());
                }
            }

            @Override
            public void onFailure(Call<List<TransaksiModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    public void setAdapterRecyclerViewRiwayat(ArrayList<String> notelp, ArrayList<String> bengkelorder, Context context) {
        Log.d(TAG, "setAdapterRecyclerViewRiwayat: Called");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterRiwayat recyclerViewAdapterRiwayat = new RecyclerViewAdapterRiwayat(notelp, bengkelorder, context);
        recyclerView.setAdapter(recyclerViewAdapterRiwayat);

    }
}
