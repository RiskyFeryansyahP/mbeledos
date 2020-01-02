package com.example.bledos.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bledos.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SharedPreferencesConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesConfig(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preferences), context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(context.getResources().getString(R.string.login_status_preferences), status);

        editor.apply();
    }

    public void writeLoginProfile(JsonObject profile) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(context.getResources().getString(R.string.user_profile), profile.toString());

        editor.apply();

    }

    public void writeLogoutStatus() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(context.getResources().getString(R.string.login_status_preferences), false);
        editor.putString(context.getResources().getString(R.string.user_profile), "");

        editor.apply();
    }

    public boolean readLoginStatus() {
        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preferences), false);
        return status;
    }

    public JsonObject readUserProfile() {
        String nameProfile = "";
        nameProfile = sharedPreferences.getString(context.getResources().getString(R.string.user_profile), "");


        return new Gson().fromJson(nameProfile, JsonObject.class);
    }

    public void writeLatitudeLocation(double latitude) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(context.getResources().getString(R.string.latitude), String.valueOf(latitude));
        editor.commit();
    }

    public void writeLongitudeLocation(double longitude) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(context.getResources().getString(R.string.longitude), String.valueOf(longitude));
        editor.commit();
    }

    public double readLatitudeLocation() {
        String latitude;

        latitude = sharedPreferences.getString(context.getResources().getString(R.string.latitude), "");
        return Double.valueOf(latitude);
    }

    public double readLongitudeLocation() {
        String longitude;

        longitude = sharedPreferences.getString(context.getResources().getString(R.string.longitude), "");
        return Double.valueOf(longitude);
    }
}
