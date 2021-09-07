package com.example.team39_prosjekt.data.network

import WeatherDataResponse
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import kotlinx.coroutines.delay

    /*
    *   Requests data from the LocationForecast 2.0 api https://in2000-apiproxy.ifi.uio.no/weatherapi/oceanforecast/0.9/documentation
    *   @Param  Coordinates for a certain location/beach as Strings.
    *   @Return The response from the API.
    */
    suspend fun fetchWeatherData(lat: String, lon: String): WeatherDataResponse?
    {
        val URL = "https://in2000-apiproxy.ifi.uio.no/weatherapi/locationforecast/2.0/?lat=$lat&lon=$lon"
        val gsonConverter = Gson()
        var toReturn: WeatherDataResponse? = null
        lateinit var response: String

        //Request data from API
        try
        {
            response = Fuel.get(URL).awaitString()
            toReturn = gsonConverter.fromJson(response, WeatherDataResponse::class.java)
        }

        //On fail log the error, wait 100ms then attempt again.
        catch (exception: FuelError)
        {
            Log.e("LF-API", exception.toString())
            delay(100)
            try {
                response = Fuel.get(URL).awaitString()
                toReturn = gsonConverter.fromJson(response, WeatherDataResponse::class.java)
            }
            catch(exception: FuelError)
            {
                Log.e("LF-API", exception.toString())
            }
        }
        return toReturn
    }