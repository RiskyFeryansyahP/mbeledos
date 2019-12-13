package com.example.bledos.interfaces;

import com.example.bledos.model.BengkelModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BengkelAPI {

    @GET("all")
    Call<List<BengkelModel>> GetAllBengkel();

    @GET("kode/{kode_bengkel}")
    Call<BengkelModel> GetDetailBengkel(@Path("kode_bengkel") String kode_bengkel);

}
