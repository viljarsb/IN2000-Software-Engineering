package com.example.team39_prosjekt.ui.detailedInfoUI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.uiStructures.ForecastListAdapter
import com.example.team39_prosjekt.viewmodels.Viewmodel
import kotlinx.android.synthetic.main.fragment_weather_and_ocean_forecast_view.*

class WeatherAndOceanForecastFragment : Fragment() {
    companion object { fun newInstance() = WeatherAndOceanForecastFragment() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //When the parent activity is done loading, this function is called.
        //Image-loading functions etc. can be called from here
        super.onActivityCreated(savedInstanceState)
        createAndFillRecycler()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_and_ocean_forecast_view, container, false)
    }

    private fun createAndFillRecycler()
    {
        forecastRecycler.layoutManager = LinearLayoutManager(this.context)
        val adapter = ForecastListAdapter(Viewmodel.getDetailed(), this.requireActivity())
        forecastRecycler.adapter = adapter
    }
}
