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
import com.google.gson.JsonObject;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private SharedPreferencesConfig sharedPreferencesConfig;

    private TextView txtWelcomeHome;

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

        JsonObject profile = sharedPreferencesConfig.readUserProfile();

        Log.d(TAG, "onViewCreated: " + profile.get("nama").toString());

        txtWelcomeHome.setText("Hello, " + profile.get("nama").getAsString());

        setAdapterRecyclerViewHome();
    }

    public void setAdapterRecyclerViewHome() {
        Log.d(TAG, "setAdapterRecyclerViewHome: Called");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(layoutManager);

        RecycleViewAdapterHome recycleViewAdapterHome = new RecycleViewAdapterHome();
        recyclerView.setAdapter(recycleViewAdapterHome);
    }
}
