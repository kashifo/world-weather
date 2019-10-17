package com.github.worldweather.commons

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kashif on 10/17/2019.
 */
interface RetrofitInterface {

    @GET("data/2.5/weather")
    fun fetchWeatherOfCity(@Query("q") city: String, @Query("APPID") appId: String): Call<String>

}