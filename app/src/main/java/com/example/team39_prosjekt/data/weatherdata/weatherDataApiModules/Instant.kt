import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class Instant (@SerializedName("details") private val details : Details)
{
	fun getCurrentWeather(): Details
	{
		return details
	}
}