package com.github.latestexperiments.world_weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.worldweather.commons.Constants
import com.github.worldweather.commons.MyApplication
import com.github.worldweather.commons.RetrofitInterface
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Kashif on 10/16/2019.
 */
public class WeatherRepo {

    val TAG = javaClass.simpleName
    var weatherModel: WeatherModel = WeatherModel()
    var mutableData: MutableLiveData<WeatherModel> = MutableLiveData()

    fun fetchHolidays(city: String): MutableLiveData<WeatherModel> {
        val retrofitInterface = MyApplication.getRetrofitClient().create(RetrofitInterface::class.java)

        retrofitInterface.fetchWeatherOfCity(city, Constants.KEY_WEATHER_API).enqueue(object : Callback<String> {

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "onFailure call=" + call.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e(TAG, "onResponse response=" + response.toString())

                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse response=" + response.body())

                    if (response.body() != null) {
                        parseWeather(response.body())
                    }
                }

            }

        })

        return mutableData
    }

    fun parseWeather(response: String?) {

        try {
            val rootObject = JSONObject(response)
            if (rootObject.getInt("cod") != 200) {
                return
            }

            weatherModel = WeatherModel()

            weatherModel.name = rootObject.getString("name")
            weatherModel.id = rootObject.getInt("id")

            if (rootObject.has("weather")) {
                weatherModel.type = rootObject.getJSONArray("weather").getJSONObject(0).getString("main")
            }

            if (rootObject.has("weather")) {
                val jsonObj = rootObject.getJSONObject("main")
                weatherModel.temp = jsonObj.getString("temp")
                weatherModel.pressure = jsonObj.getString("pressure")
                weatherModel.humidity = jsonObj.getString("humidity")
                weatherModel.temp_min = jsonObj.getString("temp_min")
                weatherModel.temp_max = jsonObj.getString("temp_max")
            }

            if (rootObject.has("wind")) {
                val jsonObj = rootObject.getJSONObject("wind")
                weatherModel.speed = jsonObj.getString("speed")
            }

            mutableData.value = weatherModel
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}