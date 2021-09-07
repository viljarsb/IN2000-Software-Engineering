package com.example.team39_prosjekt.ui.uiStructures

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.team39_prosjekt.ui.basicInfoUI.fragments.ListViewFragment
import com.example.team39_prosjekt.ui.basicInfoUI.fragments.MapViewFragment
import com.example.team39_prosjekt.ui.detailedInfoUI.fragments.WeatherAndOceanForecastFragment
import com.example.team39_prosjekt.ui.detailedInfoUI.fragments.DetailedInfoFragment
import com.example.team39_prosjekt.ui.detailedInfoUI.fragments.GeneralInfoFragment

@Suppress("DEPRECATION")
class MainFragmentPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    //This array decides the label of each tab
    private val titles = arrayOf("Liste", "Kart")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ListViewFragment.newInstance()
            1 -> MapViewFragment.newInstance()
            else -> ListViewFragment.newInstance() //default
        }
    }

    override fun getCount(): Int {
        //Number of fragments in TabLayout
        return titles.size
    }


    //Sets tab label
    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}

@Suppress("DEPRECATION")
class DetailedBeachFragmentAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager)
{
    private val titles = arrayOf("Været nå", "Værmelding", "Generelt")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DetailedInfoFragment.newInstance()
            1 -> WeatherAndOceanForecastFragment.newInstance()
            2 -> GeneralInfoFragment.newInstance()
            else -> DetailedInfoFragment.newInstance() //default
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}