import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class Data (
	@SerializedName("instant") private val instant: Instant,
	@SerializedName("next_1_hours") private val next_1_hours : Next_1_hours,
	@SerializedName("next_6_hours") private val next_6_hours : Next_6_hours
)
{
	fun getInstant(): Instant {return instant}
	fun getOneHourForecast(): Next_1_hours {return next_1_hours}
	fun getSixHourForecast(): Next_6_hours {return next_6_hours}
}