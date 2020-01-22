package com.example.bledos.interfaces;

import com.example.bledos.model.PubnubModel;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PubnubAPI {

    @POST("0")
    Call<JsonArray> SendNotificationToBengkel(@Body PubnubModel pubnubModel);

}
