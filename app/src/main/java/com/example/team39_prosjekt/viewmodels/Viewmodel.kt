package com.example.team39_prosjekt.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team39_prosjekt.data.BeachLocationForecast
import com.example.team39_prosjekt.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Viewmodel : ViewModel()
{

    //Static variables and functions. Used to transport a beach objekt to a new activity, intent did not work.
    companion object
    {
        private lateinit var detailed: BeachLocationForecast
        fun setDetailed(beach: BeachLocationForecast) { detailed = beach }
        fun getDetailed(): BeachLocationForecast { return detailed }
    }

    //variables that hold live data, and a variable for holding a repo (the viewmodels entry into data module)
    val locationForecasts: MutableLiveData<List<BeachLocationForecast>> by lazy { MutableLiveData<List<BeachLocationForecast>>() }
    val generalDetailed: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>()}
    private val repository = Repository()


    /*
    *   @Param a list of coordinates to fetch data about.
    *   Communicates to the repo to fetch data for a list of locations.
    *
     */
    fun fetchLocationData(locations: List<String>)
    {
    viewModelScope.launch(Dispatchers.IO){
        val beaches = async { repository.fetchLocations(locations)}
            locationForecasts.postValue(beaches.await()) }
    }


    /*
    *   @Param a name of a beach to fetch general info about.
    *   Communicates to the repo to fetch general data about ONE specific beach.
     */
    fun fetchGeneralData(beachName: String)
    {
        viewModelScope.launch(Dispatchers.Main){
            val generalInfo =  async {repository.fetchGeneralData(beachName)}
            generalDetailed.value = generalInfo.await()
        }
    }
}
