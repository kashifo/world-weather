package com.github.worldweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.github.latestexperiments.world_weather.WeatherModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherViewModel = WeatherViewModel()
        weatherViewModel.getWeather("chennai").observe(this, object: Observer<WeatherModel>{
            override fun onChanged(t: WeatherModel?) {
                showPrettyJson(t!!)
            }
        })
    }

    internal fun showPrettyJson(t: WeatherModel?) {
        var text = t.toString()

        try {
            val gson = GsonBuilder().setPrettyPrinting().create()
            text = gson.toJson(t)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        tvWeather.text = text
    }
}
