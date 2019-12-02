package com.example.bledos.interfaces;

import com.example.bledos.SignUp;
import com.example.bledos.model.SignInModel;
import com.example.bledos.model.SignUpModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationAPI {

    @POST("login")
    Call<JsonObject> LoginUser(@Body SignInModel signIn);

    @POST("register")
    Call<JsonObject> RegisterUser(@Body SignUpModel signUp);
}
