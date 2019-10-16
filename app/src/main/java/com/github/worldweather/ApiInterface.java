package com.github.worldweather;

import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by Kashif on 9/27/2019.
 */
public interface ApiInterface {

    Call<String> fetchWeather(@Query("q") String city, @Query("APPID") String appId);

}
