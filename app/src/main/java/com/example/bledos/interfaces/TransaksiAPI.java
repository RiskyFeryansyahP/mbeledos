package com.example.bledos.interfaces;

import com.example.bledos.model.TransaksiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TransaksiAPI {

    @GET("{orderphone}")
    Call<List<TransaksiModel>> GetTransaksi(@Path("orderphone") String ordephone);

}
