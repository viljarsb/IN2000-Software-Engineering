package com.example.team39_prosjekt.ui.detailedBeachTests

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import com.example.team39_prosjekt.ui.customClasses.CustomAssertions.Companion.scrollToVerify
import com.example.team39_prosjekt.ui.customClasses.CustomAssertions.Companion.verify
import org.junit.Rule
import org.junit.Test


class DetailedBeachViewGeneralTest{


    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_checkIfEveryViewInDetailedGeneralPageIsDisplayed(){

        //Verify that we are in Main
        verify(R.id.Main)

        //Moving into detailedBeachView, the first beach:
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>
                        (6, ViewActions.click()))

        //Verifying that we are in Detailed View:
        verify(R.id.detailed_beach)

        //Moving into general page by swiping left two times.
        onView(withId(R.id.detailed_beach))
            .perform(swipeLeft())

        onView(withId(R.id.weatherForecastSymbolView))
            .perform(swipeLeft())

        //Verifying that we are in general-page:
        verify(R.id.info)

        //Asserting every view in the page. We use the custom function "scrollToVerify", since the page length may vary from beach to beach.
        //The beach description and the header for it should not be scrolled to. This way, we can check if the app automatically jumps to the
        //bottom of the page when it's not supposed to.
        //verify(R.id.BeachName)
        verify(R.id.BeachDescHeader)
        verify(R.id.BeachDesc)
        scrollToVerify(R.id.WaterQualityHeader)
        scrollToVerify(R.id.WaterQuality)
        scrollToVerify(R.id.FacilitiesHeader)
        scrollToVerify(R.id.FacilitiyList)
        scrollToVerify(R.id.TransportHeader)
        scrollToVerify(R.id.TransportDesc)
        scrollToVerify(R.id.osloKomAttr)


    }
}