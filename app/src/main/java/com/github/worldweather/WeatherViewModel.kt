package com.github.worldweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.latestexperiments.world_weather.WeatherModel
import com.github.latestexperiments.world_weather.WeatherRepo

/**
 * Created by Kashif on 10/17/2019.
 */
class WeatherViewModel: ViewModel(){

    var weatherRepo: WeatherRepo? = null

    init {
        weatherRepo = WeatherRepo()
    }

    fun getWeather(city: String): LiveData<WeatherModel> {
        return weatherRepo!!.fetchHolidays(city)
    }

}