package com.example.team39_prosjekt.data.oceandata.oceanDataApiModules

import com.google.gson.annotations.SerializedName


//Ignore this, just a data holder.
data class OceanForecast(
    @SerializedName("mox:seaCurrentDirection")
    val SeaCurrentDirection: SeaCurrentDirection?,
    @SerializedName("mox:seaCurrentSpeed")
    val SeaCurrentSpeed: SeaCurrentSpeed?,
    @SerializedName("mox:seaTemperature")
    val SeaTemperature: SeaTemperature?,
    @SerializedName("mox:meanTotalWaveDirection")
    val MeanTotalWaveDirection: MeanTotalWaveDirection?,
    @SerializedName("mox:significantTotalWaveHeight")
    val SignificantTotalWaveHeight: SignificantTotalWaveHeight?,
    @SerializedName("mox:validTime") val ValidTime: ValidTime)