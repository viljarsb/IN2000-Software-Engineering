import com.google.gson.annotations.SerializedName
import java.util.zip.DeflaterOutputStream



//Ignore this, just a data holder.
data class Details (
	@SerializedName("air_pressure_at_sea_level") private val air_pressure_at_sea_level: Double,
	@SerializedName("air_temperature") private val air_temperature: Double,
	@SerializedName("cloud_area_fraction") private val cloud_area_fraction: Double,
	@SerializedName("cloud_area_fraction_high") private val cloud_area_fraction_high: Double,
	@SerializedName("cloud_area_fraction_low") private val cloud_area_fraction_low: Double,
	@SerializedName("cloud_area_fraction_medium") private val cloud_area_fraction_medium: Double,
	@SerializedName("dew_point_temperature") private val dew_point_temperature: Double,
	@SerializedName("fog_area_fraction") private val fog_area_fraction: Double,
	@SerializedName("relative_humidity") private val relative_humidity: Double,
	@SerializedName("ultraviolet_index_clear_sky") private val ultraviolet_index_clear_sky: Double,
	@SerializedName("wind_from_direction") private val wind_from_direction: Double,
	@SerializedName("wind_speed") private val wind_speed: Double,
	@SerializedName("wind_speed_of_gust") private val wind_speed_gust: Double
)
{
	/* These methods return weather information about an hour forecast */
    //Replaces "," with ".", if not the devices crashes on deviced with non English style seperators (norwegian etc..)
	fun getAirPressure(): Double {return air_pressure_at_sea_level}
	fun getAirTemperature(): Double {return air_temperature}
	fun getCloudAreaFraction(): Double {return cloud_area_fraction}
	fun getCloudAreaFractionHigh(): Double {return cloud_area_fraction_high}
	fun getCloudAreaFractionLow(): Double {return cloud_area_fraction_low}
	fun getCloudAreaFractionMedium(): Double {return cloud_area_fraction_medium}
	fun getDewPointTemperature(): Double {return dew_point_temperature}
	fun getFogAreaFraction(): Double {return fog_area_fraction}
	fun getRelativeHumidity(): Double {return relative_humidity}
	fun getUvIndex(): Double {return ultraviolet_index_clear_sky}
	fun getWindFromDirection(): Double {return wind_from_direction}
	fun getWindSpeed(): Double {return wind_speed}
	fun getWindSpeedOfGust(): Double{return wind_speed_gust}
}

