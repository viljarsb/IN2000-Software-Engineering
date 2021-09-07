import android.util.Log
import com.google.gson.annotations.SerializedName
import java.util.*

//WeatherDataResponse, holds all data recieved from a GET request to the Location Forecast API
//All interactivity with weather forecasts is done trough an instance of this class.
data class WeatherDataResponse (@SerializedName("properties") private val properties : Properties)
{
	/*
	*   Returns the return the time for a specific forecast for the specified lon/lat.
	* 	@Param hour  0 for current weather and n for n hours into the future.
	*	@Return  The time for the specified forecast as Number.
	*/
	fun getTime(hour: Int): Date { return properties.getTimeseries().get(hour).getTime() }

	/*
	*   Returns the current air pressure at sea level for the specified lon/lat.
	* 	@Param hour  0 for current weather and n for n hours into the future.
	*	@Return  The current air pressure in hPa at sea level as String.
	*/
	fun getAirPressure(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getAirPressure() }

	/*
	*   Returns the current air temperature for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current air temperature in celcius as String.
	*/
	fun getTemperature(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getAirTemperature() }

	/*
	*   Returns the current cloud area fraction for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current cloud area fraction in % as String.
	*/
	fun getCloudAreaFraction(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getCloudAreaFraction() }

	/*
	*   Returns the current high cloud area fraction for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current high cloud area fraction in % as String.
	*/
	fun getHighCloudAreaFraction(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getCloudAreaFractionHigh() }

	/*
	*   Returns the current low cloud area fraction for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current low cloud area fraction in % as String.
	*/
	fun getLowCloudAreaFraction(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getCloudAreaFractionLow() }

	/*
	*   Returns the current medium cloud area fraction for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current medium cloud area fraction in % as String.
	*/
	fun getMediumCloudAreaFraction(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getCloudAreaFractionMedium() }

	/*
	*   Returns the dew point temperature for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The dew point temperature in celcius as String.
	*/
	fun getDewPointTemperature(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getDewPointTemperature() }

	/*
	*   Returns the current fog area fraction for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current fog area fraction in % as String.
	*/
	fun getFogAreaFraction(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getFogAreaFraction() }

	/*
	*   Returns the current relative humidity for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current relative humidity in % as String.
	*/
	fun getRelativeHumidity(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getRelativeHumidity() }

	/*
	*   Returns the current UV index for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current uv index as String.
	*/
	fun getUvIndex(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getUvIndex() }

	/*
	*   Returns the current wind direction for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The wind direction in degrees as String.
	*/
	fun getWindDirection(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getWindFromDirection() }

	/*
	*   Returns the wind speed for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current wind speed in m/s as String.
	*/
	fun getWindSpeed(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getWindSpeed() }

	/*
	*   Returns the wind speed of gust for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current gust speed in m/s as String.
	*/
	fun getGustSpeed(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getInstant().getCurrentWeather().getWindSpeedOfGust() }


	/* These methods returns a prognosis for the next hour from the point time of time specified*/


	/*
	*   Returns the estimated amount of rain for specified lon/lat in the next hour.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The estimated amount of rain for the next hour in mm as String.
	*/
	fun precipitationForecast(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getPrecipitationAmount() }

	/*
	*   Returns the expected maxiumum amount of rain for specified lon/lat in the next hour.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The maxiumum amount of rain for the next hour in mm as String.
	*/
	fun maxPrecipitationForecast(hour: Int): Double { return properties.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getPrecipitationAmountMax() }

	/*
	*   Returns the expected minimum amount of rain for specified lon/lat in the next hour.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The minimum amount of rain for the next hour in mm as String.
	*/
	fun minPrecipitationForecast(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getPrecipitationAmountMin()}

	/*
	*   Returns the expected likelihood of rain to for specified lon/lat in the next hour.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The likelihood of rain for the next hour in % as String.
	*/
	fun precipitationProbForecast(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getProbabilityOfPrecipitation()}

	/*
	*   Returns the expected likelihood of thunder to occur to for specified lon/lat in the next hour.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The likelihood of thunder to occur for the next hour in % as String.
	*/
	fun thunderProbForecast(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHour().getProbabilityOfThunder()}


	/* These methods returns a summery of the prognosis for the next six hours from the point of time specified*/


	/*
	*   Returns the estimated maximum temperature specified lon/lat in the next six hours.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The estimated maximum temperature to occur in the next six hours as String.
	*/
	fun maxTemperaturePrognosis(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getSixHourForecast().getForecastSixHours().getAirTemperatureMax()}

	/*
	*   Returns the estimated minimum temperature for specified lon/lat in the next six hours.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The estimated minimum temperature to occur in the next six hours as String.
	*/
	fun minTemperaturePrognosis(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getSixHourForecast().getForecastSixHours().getAirTemperatureMin()}

	/*
	*   Returns the estimated maximum amount of rain to fall specified lon/lat in the six hours.
	*   @Param hour  0 for current weather and n for n hours into the future.
	*	@Return  The estimated maximum amount of rain to fall in the next six hours in mm as String.
	*/
	fun maxPrecipationPrognosis(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getSixHourForecast().getForecastSixHours().getPrecipitationAmountMax()}

	/*
	*   Returns the estimated minimum amount of rain to fall specified lon/lat in the six hours.
	*   @Param hour  0 for current weather and n for n hours into the future.
	*	@Return  The estimated minimum amount of rain to fall in the next six hours in mm as String.
	*/
	fun minPrecipationPrognosis(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getSixHourForecast().getForecastSixHours().getPrecipitationAmountMin()}

	/*
	*   Returns the estimated likelihood of rain rain to fall on the specified lon/lat in the six hours.
	*   @Param hour  0 for current weather and n for n hours into the future.
	*	@Return  The estimated likelihood of rain to fall in the next six hours in % as String.
	*/
	fun precipitationProbPrognosis(hour: Int): Double {return properties.getTimeseries().get(hour).getForecastData().getSixHourForecast().getForecastSixHours().getProbabilityOfPrecipitation()}

	/*
	*   Returns the symbol that represents the weather, can be fetched from the symbol api.
	* 	@Param hour  0 for weathersymbol now and n for n hours into the future.
	*	@Return  The symbolId.
	*/
	fun getSymbol(hour: Int): String { return properties.getTimeseries().get(hour).getForecastData().getOneHourForecast().getForecastOneHourSummary().getSymbol()}


	/*
	*   Returns the current relative temp for the specified lon/lat.
	*   @Param hour  0 for current weather and n for n hours into the future.
    *	@Return  The current relative temp in celcius as String.
	*/
	fun getRelativeTemp(hour: Int): String
	{
		val temp = getTemperature(hour)
		val wind = getWindSpeed(hour)
		val HeatIndex = 13.12 + (0.6215 * temp) - (11.37 * Math.pow(wind, -0.16)) + (0.3965 * temp) * Math.pow(wind, 0.16)
		return "%.1f".format(HeatIndex).replace(",", ".")
	}
}


