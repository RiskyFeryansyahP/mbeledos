package com.example.bledos.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bledos.R;

public class SharedPreferencesConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesConfig(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preferences), context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status, String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(context.getResources().getString(R.string.login_status_preferences), status);
        editor.putString(context.getResources().getString(R.string.name_profile), name);

        editor.apply();
    }

    public void writeLogoutStatus() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(context.getResources().getString(R.string.login_status_preferences), false);
        editor.putString(context.getResources().getString(R.string.name_profile), "");

        editor.apply();
    }

    public boolean readLoginStatus() {
        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preferences), false);
        return status;
    }

    public String readNameProfile() {
        String nameProfile = "";
        nameProfile = sharedPreferences.getString(context.getResources().getString(R.string.name_profile), "");
        return nameProfile;
    }
}
