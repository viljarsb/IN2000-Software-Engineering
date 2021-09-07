package com.example.team39_prosjekt.ui.basicInfoUI.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.data.BeachLocationForecast
import com.example.team39_prosjekt.ui.detailedInfoUI.activity.DetailedBeachActivity
import com.example.team39_prosjekt.ui.uiStructures.InfoWindowAdapter
import com.example.team39_prosjekt.viewmodels.Viewmodel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.mapview_fragment.*


class MapViewFragment : Fragment(), OnMapReadyCallback {
    companion object { fun newInstance() = MapViewFragment() }

    private var initialSetup = true
    private lateinit var gMap: GoogleMap
    private lateinit var locationArray: Array<String>
    private lateinit var locationForecastsGlobal: List<BeachLocationForecast>
    private val viewModel: Viewmodel by activityViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)
        locationArray = resources.getStringArray(R.array.locations_real)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.mapview_fragment, container, false)
    }

    //Constrains map and set zoom-level etc.
    override fun onMapReady(m: GoogleMap) {
        gMap = m
        //Limit Camera to Oslo
        val osloBorder = LatLngBounds(
            LatLng(59.626977, 10.411806),
            LatLng(59.955998, 10.908517)
        )
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(osloBorder.center, 10f))
        gMap.setLatLngBoundsForCameraTarget(osloBorder)
        gMap.setMinZoomPreference(10.0f)

        viewModel.locationForecasts.observe(this, Observer { locationForecasts ->
            /*
            * Coordinates for the map markers are found in values/coordinates_real.xml
            * Coordinates used for the API is found in values/coordinates
            */
            locationForecastsGlobal = locationForecasts
            if(initialSetup == true)
            {
                createGmap(locationForecastsGlobal)
                initialSetup = false
            }

            else if(initialSetup == false)
            {
                gMap.clear()
                createGmap(locationForecastsGlobal)
            }
        })

        gMap.setOnInfoWindowClickListener(
            {
                for(i in 0..(locationArray.size-1)){
                    val latlng = LatLng(locationArray.get(i).split("|").get(1).toDouble(), locationArray.get(i).split("|").get(2).toDouble())
                    if(latlng.equals(it.position)){
                        Log.d("log","Clicked InfoWindow for marker: "+ locationForecastsGlobal.get(i).getName()+" at "+it.position.toString())
                        Viewmodel.setDetailed(locationForecastsGlobal.get(i))
                        val intent = Intent(activity, DetailedBeachActivity::class.java)
                        requireActivity().startActivity(intent)
                    }
                }
            }
        )
    }


    //Plots data into map, markers with color and clickable interfaces.
    private fun createGmap(locationForecasts: List<BeachLocationForecast>)
    {
        for (i in 0..(locationArray.size-1)) {
            val beach = locationForecasts.get(i)
            val beachLatLon = locationArray.get(i).split("|")
            val beachMap = LatLng(beachLatLon.get(1).toDouble(), beachLatLon.get(2).toDouble())

            //Color markers relative to water temp
            if(beach.getOceanTemp(0) != "{N/A}")
            {
                var temp = beach.getOceanTemp(0).substring(0, beach.getOceanTemp(0).length - 1).toFloat()
                val colorHue: Float
                if(temp <= 0){
                    colorHue = 180f
                }
                else if(temp <= 15){
                    colorHue = 240.0f - (60.0f * temp/15.0f)
                }
                else if(temp <= 25){
                    colorHue = 60.0f - (60.0f * temp/25.0f)
                }
                else{
                    colorHue = 0f
                }
                //Set custom infoWindow
                val infoWindow = InfoWindowAdapter(context)
                gMap.setInfoWindowAdapter(infoWindow)
                val marker = gMap.addMarker(MarkerOptions().position(beachMap)
                    .title(beach.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(colorHue)))
                marker.tag = locationForecasts.get(i)
            }
        }
        mapView.visibility = View.VISIBLE
        progressBarMap.visibility = View.INVISIBLE
        metAttributeMap.visibility = View.INVISIBLE
    }
}
