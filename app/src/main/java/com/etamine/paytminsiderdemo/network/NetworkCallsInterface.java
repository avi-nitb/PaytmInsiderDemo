package com.etamine.paytminsiderdemo.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkCallsInterface {

    @GET("/home?norm=1&filterBy=go-out&city=mumbai")
    Call<JsonObject> getData();
}
