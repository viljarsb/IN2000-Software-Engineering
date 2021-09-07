package com.example.team39_prosjekt.data

import WeatherDataResponse

import com.example.team39_prosjekt.data.oceandata.OceanDataResponse
import java.util.*
import kotlin.math.roundToInt

//A beach class, holds weather data and ocean data about a specific beach.
//Also holds a general description of the beach and the name of the beach.
data class BeachLocationForecast(private val name: String, private val oceanData: OceanDataResponse?, private val weatherData: WeatherDataResponse?)
{

    /*
    *   @Return  A boolean value. Will return false if both ocean and weather data is null, if not true.
    */
    fun isValid(): Boolean {
        return !(oceanData == null && weatherData == null)
    }

    /*
    *   Gets the name of the current beach instance.
    *   @Return  The name of the beach as a String.
    */
    fun getName(): String { return name }

    /*
    *   Gets the time of a certain forecast.
    *   @Return  the data as a Date.
    */
    fun getForecastTime(hour: Int): Date? { return weatherData?.getTime(hour)}


    /*
        The following methods are meant to display
        basic information about a beach/location to the CardViews
        or to bee used as support functions in other methods of this class.
        that is held by a RecyclerView in the ListView fragment.
    */


    /*
    *   Gets the air temperature for a certain forecast (hour).
    *   @Param   int, 0 for realtime and n for n hours from now.
    *   @Return  The temperature in Celcius or an non value found as String.
    */
    fun getTemp(hour: Int): String {
        val temp = weatherData?.getTemperature(hour)
        if(temp == null) { return "{N/A}"}
        return "$temp"
    }

    /*
    *   Gets the sea temperature for a certain forecast (hour).
    *   @Param   int, 0 for realtime and n for n hours from now.
    *   @Return  The sea temperature in Celcius or an non value found as String.
    */
    fun getOceanTemp(hour: Int): String {
        val temp = oceanData?.getSeaTemperature(hour)
        if(temp == null) { return "{N/A}"}
        return "$temp"
    }

    /*
    *   gets the cloud cover for a certain forecast (hour)
    *   @Param   int, 0 for realtime and n for n hours from now.
    *   @Return  The cloud cover in % or an noon value found as String.
    */
    fun getCloudCover(hour: Int): String {
        val cover = weatherData?.getCloudAreaFraction(hour)
        if(cover == null) {return "{N/A}"}
        return "$cover"
    }


    /*
     *   Gets the UV index for a certain forecast (hour).
     *   @Param   int, 0 for realtime and n for n hours from now.
     *   @Return  The UV index or an non value found as String.
     */
    fun getUv(hour: Int): String {
        val uv = weatherData?.getUvIndex(hour)
        if(uv == null){return "{N/A}"}
        return "$uv"
    }


    /*
    *   Gets the current wind conditions for a certain forecast (hour).
    *   @Param   int, 0 for realtime and n for n hours from now.
    *   @Return  The current wind in m/s index or an non value found as String.
    */
    fun getWind(hour: Int): String {
        val wind = weatherData?.getWindSpeed(hour)
        if(wind == null) {return "{N/A}"}
        return "$wind"
    }


    /*
    *   Gets the current expected downfall for a certain forecast (hour).
    *   @Param   int, 0 for realtime and n for n hours from now.
    *   @Return  The expected downfall in the following hour as String.
    */
    fun getRain(hour: Int): String {
        val rain = weatherData?.precipitationForecast(hour)
        if(rain == null) {return "{N/A}"}
        return "$rain"
    }


    /*
    *   Gets a summary icon of the weather, example "rainy", "cloudy".
    *   @Param   int, 0 for realtime and n for n hours from now.
    *   @Return  The id of a certain weather symbol as String.
    */
    fun getSymbol(hour: Int): String {
        val symbol = weatherData?.getSymbol(hour)
        if(symbol == null) {return "{N/A}"}
        return symbol
    }

    /*
    *   Gets the relative temperature for a certain forecast (hour),
    *   formula from https://hjelp.yr.no/hc/no/articles/360001695513-Effektiv-temperatur-og-f%C3%B8les-som-
    *   @Param   int, 0 for reatime and n for n hours from now.
    *   @Return  The relative temperature or an nonuv value found as String.
     */
    fun getRelativeTemp(hour: Int): String {
        val temp = weatherData?.getRelativeTemp(hour)
        if(temp == null) {return "{N/A}"}
        return temp
    }

    /*
        The following methods are meant to display
        detailed information about current weather and ocean conditions
        of a specific beach/location.
    */


    /*
    *   @Return  A string that represents detailed data about current and future temperatures.
    */
    @Suppress("DEPRECATION")
    fun getAirTemperatureDescription(): String {
        val temperatureRealtime = weatherData?.getTemperature(0)
        val temperaturePrognosisMax = weatherData?.maxTemperaturePrognosis(0)
        val temperaturePrognosisMin = weatherData?.minTemperaturePrognosis(0)

        if(temperatureRealtime == null) {
            return "Ingen data tilgjengelig"
        }

        var averageTemp = 0.0
        for(i in 1..6) { if(weatherData?.getTemperature(i) != null) averageTemp += weatherData.getTemperature(i) }
        val averageTempSir = "%.1f".format(averageTemp.div(6))

        return "Gjennomsnitts temperatur ca. $averageTempSir°." +
                "\nMaksimum temperatur på $temperaturePrognosisMax°" +
                " \nMinimums temperatur på $temperaturePrognosisMin°."
    }


    /*
    *   @Param   A string resource array with different UV guidelines.
    *   @Return  A string with todays UV index, and recommendations according to this value.
    */
    fun getUvDescription(UvIndexes: List<String>): String {
        val UV = weatherData?.getUvIndex(0)
        if(UV == null) {return "Ingen data tilgjengelig"}

        val UvIndex = "UV-indeks: $UV"
        val warning = "\nOvereksponering for sol kan gi alvorlige skader på hud og øyne. \n\nHusk at lett til medium skydekke gir liten til ingen beskyttelse."
        when(UV.toDouble().toInt()) {
            in 0..2 -> return UvIndex + UvIndexes[0]
            in 3..5 -> return UvIndex + UvIndexes[1] + "\n$warning"
            in 5..7 -> return UvIndex + UvIndexes[2] + "\n$warning"
            in 8..10 -> return UvIndex + UvIndexes[3] + "\n$warning"
            in 11..15 -> return UvIndex + UvIndexes[4] + "\n$warning"
        }
        return "will never get here."
    }


    /*
    *   @Param   A string resource array with baufort values (wind scale)
    *   @Return  A string detailed string of wind conditions,
    *   and the corresponding value in the baufort scale according to the current windspeed.
    */
    fun getWindDescription(BaufortScale: List<String>): String {
        val wind = weatherData?.getWindSpeed(0)
        val windGust = weatherData?.getGustSpeed(0)
        val windDirection = weatherData?.getWindDirection(0)
        val windKh = if (wind != null) (wind * 3.6) else return "Ingen data tilgjengelig"
        val baufort: String?
        when(windKh.toInt()) {
            in 0..1 -> baufort = BaufortScale[0]
            in 1..5 -> baufort = BaufortScale[1]
            in 6..11 -> baufort = BaufortScale[2]
            in 12..19 -> baufort = BaufortScale[3]
            in 20..28 -> baufort = BaufortScale[4]
            in 29..38 -> baufort = BaufortScale[5]
            in 39..49 -> baufort = BaufortScale[6]
            in 50..51 -> baufort = BaufortScale[7]
            in 62..74 -> baufort = BaufortScale[8]
            in 75..88 -> baufort = BaufortScale[9]
            in 89..102 -> baufort = BaufortScale[10]
            in 103..117 -> baufort = BaufortScale[11]
            in 118..Int.MAX_VALUE -> baufort = BaufortScale[12]
            else -> {baufort = "{N/A}"}
        }


        var windDir = "{N/A}"
        when(windDirection?.roundToInt()) {
            in 337..359 -> windDir = "nord"
            in 0..23 -> windDir = "nord"
            in 23..68 -> windDir = "nordøst"
            in 68..113 -> windDir = "øst"
            in 113..158 -> windDir = "sørøst"
            in 158..203 -> windDir = "sør"
            in 203..248 -> windDir = "sørvest"
            in 248..293 -> windDir = "vest"
            in 293..337 -> windDir = "nordvest"
        }
        return "${wind}m/s\n$baufort fra $windDir, vindkast ${windGust}m/s."
    }


    /*
    *   @Return  A detailed string with sea temperature,
    *   current wave and current conditions.
    */
    fun getOceanDescription(): String {
        val waveHeight = oceanData?.getWaveHeight(0)
        val waveDirection = oceanData?.getWaveDirection(0)
        val seaCurrentSpeed = oceanData?.getSeaCurrentSpeed(0)
        val seaTemperature = oceanData?.getSeaTemperature(0)
        val seaCurrentDirection = oceanData?.getSeaCurrentDirection(0)

        var waveHeightStr = waveHeight.toString()
        var seaTemperatureStr = seaTemperature.toString()
        var seaCurrentSpeedStr = seaCurrentSpeed.toString()

        if(waveHeight == null && seaTemperature == null && seaCurrentDirection == null) {return "Ingen data tilgjengelig"}
        if(waveHeight == null) {waveHeightStr = " {N/A} "}
        if(seaTemperature == null) {seaTemperatureStr = " {N/A} "}
        if(seaCurrentSpeed == null) {seaCurrentSpeedStr = " {N/A} "}

        return "Vanntemperatur ${seaTemperatureStr}° " +
                "\nBølgehøyde ${waveHeightStr}m" +
                "\nUndervannsstrøm med en styrke på ${seaCurrentSpeedStr}m/s"
    }


    /*
    *   @Return  A detailed string about rain probability and amount.
    */
    @Suppress("DEPRECATION")
    fun getRainDescription(): String {
        val rain = weatherData?.precipitationForecast(0)
        val rainForeMax = weatherData?.maxPrecipitationForecast(0)
        val rainForeMin = weatherData?.minPrecipitationForecast(0)

        if(rain == null && rainForeMax == null && rainForeMin == null) {
            return "Ingen data tilgjengelig"
        }

        var rainForecast = ""
        if(rain != null && rain > 0.0)
            rainForecast = "\nMaksimal mengde nedbør ${rainForeMax}mm og minimalt ${rainForeMin}mm"
        return "${rain}mm \nSannsynlighet for nedbør ${weatherData?.precipitationProbForecast(0)}%" +
                "$rainForecast"
    }


    /*
    *   @Return  A detailed string about current cloud conditions.
    */
    fun getCloudDescription(): String {
        val cloudCover = weatherData?.getCloudAreaFraction(0)
        if(cloudCover == null) {return "Ingen data tilgjengelig"}
        var toReturn = ""
        when(cloudCover.roundToInt()) {
            in 0..20 -> toReturn = "Minimalt skydekke på $cloudCover%"
            in 20..40 -> toReturn = "Lavt skydekke på $cloudCover%"
            in 40..60 -> toReturn = "Medium skydekke på $cloudCover%"
            in 60..80 -> toReturn = "Høyt skydekke på $cloudCover%"
            in 80..100 -> toReturn = "Totalt skydekke på $cloudCover%"
        }
        val lowClouds = weatherData?.getLowCloudAreaFraction(0)
        val mediumClouds = weatherData?.getMediumCloudAreaFraction(0)
        val highClouds = weatherData?.getHighCloudAreaFraction(0)
        return toReturn.plus("\nLave skyer (≈ 2000m) $lowClouds%" +
                "\nMellomhøye skyer (≈2000m-6000m) $mediumClouds%" +
                "\nHøye skyer  (6000m +) $highClouds%")
    }


    /*
    *   @Return A text that says something about swimming conditions, based on pure intuition.
    */
    fun safeToSwim(): String {
        val seaTemperature = oceanData?.getSeaTemperature(0)
        val seaCurrentSpeed = oceanData?.getSeaCurrentSpeed(0)
        val totalWaveHeight = oceanData?.getWaveHeight(0)

        var tooCold = false
        var tooStrongCurrent = false
        var tooHighWaves = false

        if(seaTemperature == null && seaCurrentSpeed == null && totalWaveHeight == null) {
            return "Ingen data tilgjengelig"
        }

        var temp = ""
        if(seaTemperature != null) {
            if(seaTemperature < 12) { tooCold = true}
            when(seaTemperature.roundToInt()) {
                in -10..0 -> { temp = temp.plus("Badevannet er svært kladt($seaTemperature°).\n") }
                in 0..10 -> { temp = temp.plus("Badevannet er kaldt($seaTemperature°).\n") }
                in 10..12 -> { temp = temp.plus("Badevannet er relativt kaldt($seaTemperature°).\n") }
                in 12..15 -> { temp = temp.plus("Badevannet holder en grei badetemperatur($seaTemperature°).\n") }
                in 15..18 -> { temp = temp.plus("Badevannet holder en behagelig($seaTemperature°).\n") }
                in 18..30 -> { temp = temp.plus("Badevannet er varmt($seaTemperature°)°.\n") }
            }
        }

        var current = ""
        if(seaCurrentSpeed != null) {
            if(seaCurrentSpeed >= 2) { tooStrongCurrent = true }
            when(seaCurrentSpeed.roundToInt()) {
                in 0..1 -> { current = current.plus("Svak undervannsstrøm på $seaCurrentSpeed.\n") }
                in 1..2 -> { current = current.plus("Svak-medium undervannsstrøm på $seaCurrentSpeed.\n") }
                in 2..3 -> { current = current.plus("Medium-sterk undervannsstrøm på $seaCurrentSpeed.\n") }
                in 3..4 -> { current = current.plus("Sterk undervannsstrøm på $seaCurrentSpeed.\n") }
                in 4..100 -> { current = current.plus("Svært sterk undervannsstrøm på $seaCurrentSpeed.\n") }
            }
        }


        var waveHeight = ""
        if(totalWaveHeight != null) {
            if(totalWaveHeight >= 2) { tooHighWaves = true }
            when(totalWaveHeight.roundToInt()) {
                in 0..1 -> { waveHeight = waveHeight.plus("Lave bølger med en høyde på $totalWaveHeight.")}
                in 1..2 -> { waveHeight = waveHeight.plus("Relativt høye bølger med en høyde på $totalWaveHeight.")}
                in 2..15 -> { waveHeight = waveHeight.plus("Svært høye bølger med en høyde på $totalWaveHeight.")}
            }
        }

        var toReturn = "Det er fine badeforhold\n"
        if(tooCold || tooStrongCurrent || tooHighWaves) {
            toReturn = "Bading er ikke anbefalt"
            if(tooCold) { toReturn = toReturn.plus("\n$temp") }
            if(tooStrongCurrent) { toReturn = toReturn.plus("\n$current") }
            if(tooHighWaves) { toReturn = toReturn.plus("\n$waveHeight") }
        }

        if(seaTemperature == null || seaCurrentSpeed == null || totalWaveHeight == null) {
            toReturn = toReturn.plus("Anbefalingen er ikke utfyllende, da ikke alle faktorer kunne tas i betrakning.\nVurder forholdene individuelt.")
        }
        return toReturn
    }
}