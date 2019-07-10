package com.hcitest.testapi.interfaces;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("home")
    Call<String> getData();
}
