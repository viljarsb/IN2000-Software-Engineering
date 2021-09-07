import com.google.gson.annotations.SerializedName



//Ignore this, just a data holder.
data class Properties (

	@SerializedName("meta") private val meta : Meta,
	@SerializedName("timeseries") private val timeseries : List<Timeseries>
)
{
	fun getMetaData(): Meta {return meta}
	fun getTimeseries(): List<Timeseries> {return timeseries}
}