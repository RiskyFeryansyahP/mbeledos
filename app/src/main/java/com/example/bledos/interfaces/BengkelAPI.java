package com.example.bledos.interfaces;

import com.example.bledos.model.BengkelModel;
import com.example.bledos.model.LocationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BengkelAPI {

    @GET("all")
    Call<List<BengkelModel>> GetAllBengkel();

    @POST("nearest")
    Call<List<BengkelModel>> GetNearestBengkel(@Body LocationModel locationModel);

    @GET("kode/{kode_bengkel}")
    Call<BengkelModel> GetDetailBengkel(@Path("kode_bengkel") String kode_bengkel);

}
