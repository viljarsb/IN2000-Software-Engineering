package com.example.team39_prosjekt.ui.basicInfoUI.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team39_prosjekt.data.BeachLocationForecast
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import com.example.team39_prosjekt.ui.detailedInfoUI.activity.DetailedBeachActivity
import com.example.team39_prosjekt.ui.uiStructures.BeachListAdapter
import com.example.team39_prosjekt.viewmodels.Viewmodel
import kotlinx.android.synthetic.main.listview_fragment.*


class ListViewFragment : Fragment() {
    private val viewModel: Viewmodel by activityViewModels()
    private lateinit var adapter: BeachListAdapter
    private var initialSetup = true

    companion object { fun newInstance() = ListViewFragment() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Refreshes data on demand with a swipe up in the recycler view.
        swipeRefreshLayout.setOnRefreshListener {
            val activity: Activity? = activity
            if (activity is MainActivity)
            {
                val myactivity: MainActivity? = activity
                myactivity?.updateData()
            }
            swipeRefreshLayout.isRefreshing = false
        }
        //Observers livedata in the viewmodel, and executes when variable is populated.
        viewModel.locationForecasts.observe(viewLifecycleOwner, Observer { locationForecasts ->

            //If the structures (no adapter, or layout manager) are not set up yet.
            if(initialSetup) {
                createAndFillRecycler(locationForecasts.toMutableList())
                initialSetup = false
            }

            //If structures are already created.
            else if(!initialSetup) {
                updateRecycler()
            }
        })

        //Gives functionality to favourite FilterButton
        favouriteFilterButton.setOnClickListener {
            adapter.filterFavourites = !adapter.filterFavourites //toggle
            if (adapter.filterFavourites) {
                favouriteFilterButton.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                favouriteFilterButton.setImageResource(android.R.drawable.btn_star_big_off)
            }
            //Updates list
            adapter.filter.filter(SearchBox.query)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.listview_fragment, container, false)
    }

    //Notifies the adapter that new data is available (recyclerview will update)
    private fun updateRecycler() {
        adapter.updateData(viewModel.locationForecasts.value!!.toMutableList())
        adapter.notifyDataSetChanged()
        //Shows beaches according to filter
        adapter.filter.filter(SearchBox.query)
        progressBarList.visibility = View.INVISIBLE
        metAttributeList.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    //Sets up the recycler view with a layout manager and an adapter and fills it with initial data.
    private fun createAndFillRecycler(list: MutableList<BeachLocationForecast>) {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = BeachListAdapter(list, this.requireActivity(), { BeachLocationForecast -> beachItemClicked(BeachLocationForecast) })
        recyclerView.adapter = adapter
        progressBarList.visibility = View.INVISIBLE
        metAttributeList.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE


        //Listens for textinput in the search box, will update the recycler view according to text input.
        SearchBox.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    //Beach clicked, start new activity with detailed info about the clicked beach.
    private fun beachItemClicked(beach: BeachLocationForecast)
    {
        //Control that the clicked beach actually has some data to show, if not give a toast with an error.
        //Will only execute if no response for the beach from both of the apis was given for the selected beach.
        if(!beach.isValid())
        {
            Toast.makeText(this.context,"Kan ikke hente detaljert data.", Toast.LENGTH_SHORT).show()
            return
        }

        //  Saves the clicked beach "static" so we can fetch it from the new activity.
        //   Really hard to send nestled objects with parcels, so this seemed like a quick workaround.
        //  Then start new activity and reset the search box.
        Viewmodel.setDetailed(beach)
        val intent = Intent(activity, DetailedBeachActivity::class.java)
        requireActivity().startActivity(intent)
        SearchBox.clearFocus()
        SearchBox.setQuery("", false)
    }

}
