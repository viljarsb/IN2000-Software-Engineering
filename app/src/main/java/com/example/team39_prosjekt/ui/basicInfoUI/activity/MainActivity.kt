package com.example.team39_prosjekt.ui.basicInfoUI.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.uiStructures.MainFragmentPagerAdapter
import com.example.team39_prosjekt.viewmodels.Viewmodel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listview_fragment.*
import kotlinx.android.synthetic.main.mapview_fragment.*


class MainActivity : AppCompatActivity() {
    private val viewModel: Viewmodel by viewModels()
    private var time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Connects ViewPager with TabLayout, making the tabs visible
        time = System.currentTimeMillis()
        tabLayout.setupWithViewPager(viewPager)
        val mainPagerAdapter = MainFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = mainPagerAdapter
        fetchData()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            //Called when switching tabs
            override fun onTabSelected(tab: TabLayout.Tab) {
                //Hides the keyboard if the user was using the searchbar while switching tabs
                if (currentFocus != null) {
                    currentFocus!!.hideKeyboard()
                }
            }
            //The following functions can't be removed since they need to be overridden
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    //Update data if last update was over one hour ago.
    override fun onResume() {
        super.onResume()
        if((System.currentTimeMillis() - time) >= 3600000)
        {
            updateData()
        }
    }


    //Hides the keyboard
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    //Fetches data.
    private fun fetchData()
    {
        viewModel.fetchLocationData(resources.getStringArray(R.array.locations).toList())
    }

    //Updates data.
    fun updateData()
    {
        progressBarMap.visibility = View.VISIBLE
        metAttributeMap.visibility = View.VISIBLE
        mapView.visibility = View.INVISIBLE

        progressBarList.visibility = View.VISIBLE
        metAttributeList.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE

        time = System.currentTimeMillis()
        Toast.makeText(this, "Oppdaterer data", Toast.LENGTH_LONG).show()
        fetchData()
    }

}
