package com.example.bledos.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bledos.Helper.SharedPreferencesConfig;
import com.example.bledos.R;

public class HomeFragment extends Fragment {

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

        String name = sharedPreferencesConfig.readNameProfile();


        txtWelcomeHome = getActivity().findViewById(R.id.txtWelcomeHome);
        txtWelcomeHome.setText("Hello, " + name);
    }
}
