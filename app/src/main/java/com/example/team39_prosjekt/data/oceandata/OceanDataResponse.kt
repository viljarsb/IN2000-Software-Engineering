package com.example.team39_prosjekt.data.oceandata
import com.example.team39_prosjekt.data.oceandata.oceanDataApiModules.NextIssueTime
import com.example.team39_prosjekt.data.oceandata.oceanDataApiModules.forcasts
import com.google.gson.annotations.SerializedName
import java.util.*

//OceanDataResponse, holds all data recieved from a GET request to the Ocean Forecast API
//All interactivity with ocean forecasts is done trough an instance of this class.
data class OceanDataResponse(
    @SerializedName("mox:nextIssueTime") private val nextIssueTime: NextIssueTime,
    @SerializedName("mox:forecast") private val forecasts: List<forcasts>)
{

    fun getTime(hour: Int): Date { return forecasts.get(hour).getOceanForecast().ValidTime.timePeriod.begin }

    /*
	*	Finds the wave height for a specific lon/lat
	*   @Param hour  0 for current ocean conditions and n for n hours into the future.
	* 	@Return  returns the wave height in m as String
	*/
    fun getWaveHeight(hour: Int): Double? { return forecasts.get(hour).getOceanForecast().SignificantTotalWaveHeight?.getContent() }


    /*
	*	Finds the wave direction for a specific lon/lat
	*   @Param hour  0 for current ocean conditions and n for n hours into the future.
	* 	@Return  returns the wave direction in deg as String
	*/
    fun getWaveDirection(hour: Int): Double? { return forecasts.get(hour).getOceanForecast().MeanTotalWaveDirection?.getContent() }


    /*
	*	Finds the sea current direction for a specific lon/lat
	*   @Param hour  0 for current ocean conditions and n for n hours into the future.
	* 	@Return  returns the direction of sea current in deg as String String
	*/
    fun getSeaCurrentDirection(hour: Int): Double? { return forecasts.get(hour).getOceanForecast().SeaCurrentDirection?.getContent() }


    /*
	*	Finds the speed of the sea current for a specific lon/lat
	*   @Param hour  0 for current ocean conditions and n for n hours into the future.
	* 	@Return  returns the sea current in m/s as String
	*/
    fun getSeaCurrentSpeed(hour: Int): Double? { return forecasts.get(hour).getOceanForecast().SeaCurrentSpeed?.getContent() }


    /*
     *	 Finds the sea temperature for a specific lon/lat
     *   @Param hour  0 for current ocean conditions and n for n hours into the future.
     * 	 @Return  returns the sea temperature in celcius as String
     */
    fun getSeaTemperature(hour: Int): Double? { return forecasts.get(hour).getOceanForecast().SeaTemperature?.getContent() }
}



