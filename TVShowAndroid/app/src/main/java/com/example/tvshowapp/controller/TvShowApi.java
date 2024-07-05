package com.example.tvshowapp.controller;

import com.example.tvshowapp.model.TvShow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TvShowApi {

    @GET("tvshows")
    Call<List<TvShow>> getTvShows();
}
