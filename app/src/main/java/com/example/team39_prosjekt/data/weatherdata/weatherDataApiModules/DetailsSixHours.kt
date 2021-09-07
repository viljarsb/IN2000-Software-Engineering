package com.example.team39_prosjekt.data.weatherdata.weatherDataApiModules

import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class DetailsSixHours(
    @SerializedName("air_temperature_max") private val air_temperature_max: Double,
    @SerializedName("air_temperature_min") private val air_temperature_min: Double,
    @SerializedName("precipitation_amount_max") private val precipitation_amount_max: Double,
    @SerializedName("precipitation_amount_min") private val precipitation_amount_min: Double,
    @SerializedName("probability_of_precipitation") private val probability_of_precipitation: Double
)
{
    //Replaces "," with ".", if not the devices crashes on deviced with non English style seperators (norwegian etc..)
    fun getAirTemperatureMax(): Double {return air_temperature_max}
    fun getAirTemperatureMin(): Double {return air_temperature_min}
    fun getPrecipitationAmountMax(): Double {return precipitation_amount_max}
    fun getPrecipitationAmountMin(): Double {return precipitation_amount_min}
    fun getProbabilityOfPrecipitation(): Double {return probability_of_precipitation}
}
