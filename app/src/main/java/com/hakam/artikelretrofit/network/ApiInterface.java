package com.hakam.artikelretrofit.network;

import com.hakam.artikelretrofit.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/posts")
    Call<List<PostModel>> getPosts();
}
