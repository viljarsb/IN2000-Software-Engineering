package com.example.team39_prosjekt.data.oceandata.oceanDataApiModules

import com.google.gson.annotations.SerializedName
import java.util.*



//Ignore this, just a data holder.
data class ValidTime(
    @SerializedName("gml:TimePeriod") val timePeriod: TimePeriod
) {
    data class TimePeriod(
        @SerializedName("gml:begin") val begin: Date,
        @SerializedName("gml:end") val end: Date,
        @SerializedName("gml:id") val id: String
    )
}