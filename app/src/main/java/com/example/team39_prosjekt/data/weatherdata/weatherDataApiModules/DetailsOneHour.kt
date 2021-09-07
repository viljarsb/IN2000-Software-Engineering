package com.example.team39_prosjekt.data.weatherdata.weatherDataApiModules

import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class DetailsOneHour(
    @SerializedName("precipitation_amount") private val precipitation_amount : Double,
    @SerializedName("precipitation_amount_max") private val precipitation_amount_max : Double,
    @SerializedName("precipitation_amount_min") private val precipitation_amount_min : Double,
    @SerializedName("probability_of_precipitation") private val probability_of_precipitation : Double,
    @SerializedName("probability_of_thunder") private val probability_of_thunder : Double
)
{
    /* These methods returns summary of weather information/prognosis for 1 hour from the specified time*/
    //Replaces "," with ".", if not the devices crashes on deviced with non English style seperators (norwegian etc..)
    fun getPrecipitationAmount(): Double {return precipitation_amount}
    fun getPrecipitationAmountMax(): Double {return precipitation_amount_max}
    fun getPrecipitationAmountMin(): Double {return precipitation_amount_min}
    fun getProbabilityOfPrecipitation(): Double {return probability_of_precipitation}
    fun getProbabilityOfThunder(): Double {return probability_of_thunder}
}
