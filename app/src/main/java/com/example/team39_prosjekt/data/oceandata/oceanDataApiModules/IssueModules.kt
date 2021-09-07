package com.example.team39_prosjekt.data.oceandata.oceanDataApiModules

import com.google.gson.annotations.SerializedName

//Ignore this, just a data holder.
data class IssueTime(@SerializedName("gml:TimeInstant") private val timeInstant: TimeInstant)
{
    fun getTime(): Number {return timeInstant.getTime()}
}

data class NextIssueTime(@SerializedName("gml:TimeInstant")private val timeInstant: TimeInstant)
{
    fun getTime(): Number {return timeInstant.getTime()}
}

data class TimeInstant(@SerializedName("gml:id") val id: String, @SerializedName("gml:timePosition") private val timePosition: Number)
{
    fun getTime(): Number {return timePosition}
}


