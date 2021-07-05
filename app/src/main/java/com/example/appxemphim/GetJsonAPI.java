package com.example.appxemphim;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import TrangChu.Advertist.MovieAdver;
import TrangChu.PhimDacSac.DacSacFilm;
import TrangChu.PhimSapChieu.SapChieuFilm;
import TrangChu.hotSeries.HotSeriesFilm;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface GetJsonAPI {
    String baseUrl="http://demo9487644.mockable.io/";
    String adver="Advertisement";
    String hotSeries="HotSeries";
    String dacSac="DacSac";
    String phoBien="PhoBien";
    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    GetJsonAPI getJsonAPI=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(GetJsonAPI.class);

    @GET(adver)
    Call<ArrayList<MovieAdver>> movieAdver();
    @GET(hotSeries)
    Call<ArrayList<HotSeriesFilm>> hotSeries();
    @GET(dacSac)
    Call<ArrayList<DacSacFilm>> dacSac();
    @GET(phoBien)
    Call<ArrayList<SapChieuFilm>> phoBien();
}

