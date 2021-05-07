package com.example.appxemphim;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import TrangChu.Advertist.MovieAdverArrays;
import TrangChu.PhimDacSac.DacSacArrayImage;
import TrangChu.PhimPhoBien.PhoBienArrays;
import TrangChu.hotSeries.HighlightFilmsArrays;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface GetJsonAPI {
    String baseUrl="http://demo9487644.mockable.io/";
    String adver="Advertisement";
    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    GetJsonAPI getGetJsonAPI=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(GetJsonAPI.class);

    @GET(adver)
    Call<MovieAdverArrays> movieAdver();
    @GET(adver)
    Call<HighlightFilmsArrays> hotSeries();
    @GET(adver)
    Call<DacSacArrayImage> dacSac();
    @GET(adver)
    Call<PhoBienArrays> phoBien();
}

