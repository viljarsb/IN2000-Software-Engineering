package com.example.team39_prosjekt.data.oceandata.oceanDataApiModules
import com.google.gson.annotations.SerializedName


//Ignore this, just a data holder.
data class forcasts(
    @SerializedName("metno:OceanForecast")
    private val oceanForecast: OceanForecast)
{
    fun getOceanForecast(): OceanForecast {return oceanForecast}
}