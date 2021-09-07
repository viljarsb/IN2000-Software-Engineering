package com.example.team39_prosjekt.data.network

import android.util.Log
import com.example.team39_prosjekt.data.oceandata.OceanDataResponse
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.*
import com.google.gson.Gson
import kotlinx.coroutines.delay

    /*
    *   Requests data from the OceanData api https://in2000-apiproxy.ifi.uio.no/weatherapi/oceanforecast/0.9/documentation
    *   @Param  Coordinates for a certain location/beach as Strings.
    *   @Return The response from the API.
    */
    suspend fun fetchOceanData(lat: String, lon: String): OceanDataResponse?
    {
        val URL = "https://in2000-apiproxy.ifi.uio.no/weatherapi/oceanforecast/0.9/.json?lat=$lat&lon=$lon"
        val gsonConverter = Gson()
        var toReturn: OceanDataResponse? = null
        lateinit var response: String

        //Request data from API
        try
        {
            response = Fuel.get(URL).awaitString()
            toReturn = gsonConverter.fromJson(response, OceanDataResponse::class.java)
        }

        //On fail log the error, wait 100ms then attempt again.
        catch (exception: FuelError)
        {
            Log.e("OF-API", exception.toString())
            delay(100)
            try
            {
                response = Fuel.get(URL).awaitString()
                toReturn = gsonConverter.fromJson(response, OceanDataResponse::class.java)
            }
            catch (exception: FuelError)
            {
                Log.e("OF-API", exception.toString())
            }
        }
        return toReturn
    }

