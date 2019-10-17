package com.github.latestexperiments.world_weather

/**
 * Created by Kashif on 10/16/2019.
 */
public class WeatherModel {

    var cod:Int = 0
    var name:String? = null
    var id:Int = 0
    var type:String? = null
    var temp:String? = null
    var temp_min:String? = null
    var temp_max:String? = null
    var pressure:String? = null
    var humidity:String? = null
    var speed:String? = null

    override fun toString(): String {
        return "name=$name, id=$id, type=$type, temp=$temp, temp_min=$temp_min, temp_max=$temp_max, pressure=$pressure, humidity=$humidity, speed=$speed"
    }


}