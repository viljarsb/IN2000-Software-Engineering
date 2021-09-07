import com.example.team39_prosjekt.data.weatherdata.weatherDataApiModules.DetailsOneHour
import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class Next_1_hours (

	@SerializedName("summary") private val summary : Summary,
	@SerializedName("details") private val details : DetailsOneHour
)
{
	fun getForecastOneHour(): DetailsOneHour {return details}
	fun getForecastOneHourSummary(): Summary {return summary}
}