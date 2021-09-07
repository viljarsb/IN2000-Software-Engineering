import com.google.gson.annotations.SerializedName
import java.util.*



//Ignore this, just a data holder.
data class Timeseries (
	@SerializedName("time") private val time: Date,
	@SerializedName("data") private val data: Data
)
{
	fun getTime(): Date {return time}
	fun getForecastData(): Data {return data}
}