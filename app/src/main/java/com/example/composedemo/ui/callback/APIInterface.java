package com.example.composedemo.ui.callback;

import com.example.composedemo.model.EntryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("entries")
    Call<EntryResponse> callApiEntry();

}