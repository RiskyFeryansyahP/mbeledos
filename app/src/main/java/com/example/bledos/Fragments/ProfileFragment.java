package com.example.bledos.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bledos.HalamanSignIn;
import com.example.bledos.Helper.SharedPreferencesConfig;
import com.example.bledos.R;
import com.example.bledos.UpdateProfileActivity;
import com.google.gson.JsonObject;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";

    private SharedPreferencesConfig sharedPreferencesConfig;

    private TextView txtNameProfile, txtLevelProfile, txtNumberLevelProfile;

    private Button btnLogout, btnUpdate;

    String nohp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferencesConfig = new SharedPreferencesConfig(getActivity());
        return inflater.inflate(R.layout.activity_profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        JsonObject profile = sharedPreferencesConfig.readUserProfile();

        nohp = profile.get("nohp").getAsString();

        Log.d(TAG, "onViewCreated: Profile " + nohp );

        txtNameProfile = getActivity().findViewById(R.id.txtNameProfile);
        txtLevelProfile = getActivity().findViewById(R.id.txtLevelProfile);
        txtNumberLevelProfile = getActivity().findViewById(R.id.txtNumberLevelProfile);

        btnLogout = getActivity().findViewById(R.id.btnLogout);
        btnUpdate = getActivity().findViewById(R.id.btnUpdate);

        txtNameProfile.setText(profile.get("nama").getAsString());
        txtLevelProfile.setText(profile.get("kategori_level").getAsString());
        txtNumberLevelProfile.setText(profile.get("level").toString());

        btnLogout.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogout:
                sharedPreferencesConfig.writeLogoutStatus();
                startActivity(new Intent(getActivity(), HalamanSignIn.class));
                getActivity().finish();
                break;
            case R.id.btnUpdate:
                startActivity(new Intent(getActivity(), UpdateProfileActivity.class));
                UpdateProfileActivity.nohp = nohp;
                break;
        }
    }
}
