package com.example.bledos.interfaces;

import com.example.bledos.model.ProfileModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfileAPI {

    @POST("update")
    Call<JsonObject> UpdateData(@Body ProfileModel model);

}
