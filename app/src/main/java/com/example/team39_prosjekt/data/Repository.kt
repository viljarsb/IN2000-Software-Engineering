package com.example.team39_prosjekt.data

import com.example.team39_prosjekt.data.network.fetchOceanData
import com.example.team39_prosjekt.data.network.fetchWeatherData
import com.example.team39_prosjekt.data.network.getGeneralData
import kotlinx.coroutines.*

class Repository {
    /*
    *   Fetches the location data from the ocean forecast and location forecast api.
    *   @Param A list of locations and names.
    *   @Return A list of BeachLocationForecast objects with data from the api.
    */
    suspend fun fetchLocations(locations: List<String>): List<BeachLocationForecast> {
        val beaches = mutableListOf<BeachLocationForecast>()
        val entryJob: Job
        val subJobs = mutableListOf<Job>()

        //Launches a parent job, and each location gets a job so location can run async.
        entryJob = CoroutineScope(Dispatchers.IO).launch{
            locations.forEach{
                subJobs.add(CoroutineScope(Dispatchers.IO).launch{
                    val info = it.split("|").toList()
                    //Fetches the weather and ocean data in async so they can both run in async, before a beach object is created with both ocean and weather data.
                    val oceanDataResponse = async { fetchOceanData(info.get(1), info.get(2).dropLast(1)) }
                    val weatherDataResponse = async { fetchWeatherData(info.get(1), info.get(2)) }
                    val beachObject = BeachLocationForecast(info.get(0), oceanDataResponse.await(), weatherDataResponse.await())
                    beaches.add(beachObject)
                })
            }
            subJobs.joinAll()
            //Main job waits for all child jobs to finish before quitting.
        }
        //wait for main job to finish before returning all the BeachLocationForecasts in a alf order.
        entryJob.join()
        return beaches.sortedBy { it.getName() }
    }

    /*
    *   Fetches general information about a beach for Oslokommune.no
    *   @Param  A beach name.
    *   @Return A string with general information about a beach.
    */
    suspend fun fetchGeneralData(beachName: String): MutableList<String> {
        var data = mutableListOf<String>()
        val job = CoroutineScope(Dispatchers.IO).launch {
            data = getGeneralData(beachName)
        }

        job.join()
        return data
    }
}

