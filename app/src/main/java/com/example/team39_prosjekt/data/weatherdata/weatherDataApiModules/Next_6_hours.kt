import com.example.team39_prosjekt.data.weatherdata.weatherDataApiModules.DetailsSixHours
import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class Next_6_hours (

	@SerializedName("summary") private val summary : Summary,
	@SerializedName("details") private val details : DetailsSixHours
)
{
	fun getForecastSixHours(): DetailsSixHours {return details}
	fun getForecastSixHoursSummary(): Summary {return summary}
}