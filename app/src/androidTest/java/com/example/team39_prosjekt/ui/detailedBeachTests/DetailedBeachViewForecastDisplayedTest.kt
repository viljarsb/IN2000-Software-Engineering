package com.example.team39_prosjekt.ui.detailedBeachTests

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import com.example.team39_prosjekt.ui.customClasses.CustomAssertions.Companion.verify
import com.example.team39_prosjekt.ui.customClasses.CustomViewIdAssertion.Companion.withIndex
import org.junit.Rule
import org.junit.Test


class DetailedBeachViewForecastDisplayedTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun test_checkThatRecyclerViewIsDisplayed(){

        //This is a nested function. I reuse code a lot here, and this saves lines
        fun checkIfEverySymbolIsInPositionX(position: Int){
            fun assertIfViewExists(id: Int){
                onView(withIndex(withId(R.id.forecastRecycler), position))
                    .check(matches(hasDescendant(withId(id))))
            }

            assertIfViewExists(R.id.time)
            assertIfViewExists(R.id.temp)
            assertIfViewExists(R.id.wind)
            assertIfViewExists(R.id.rain)
            assertIfViewExists(R.id.symbol)

        }

        //Check that we are in main
        verify(R.id.Main)

        //Moving into the first recyclerview item, before moving into forecasts.
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>
                        (0, ViewActions.click()))

        //Verifying that we are in "Været nå"
        verify(R.id.detailed_beach)

        //Moving into forecasts:
        onView(withId(R.id.detailed_beach))
            .perform(swipeLeft())

        //Verifying that we are in the detailed forecast for a specific view.
        verify(R.id.weatherForecastSymbolView)

        //Asserting that recyclerview is there
        verify(R.id.forecastRecycler)

        //Testing to see if this card view (the item in the recycler view at position 0) displays Time()
        //Not 100% sure if this actually tests if a view is displayed. Using isDisplayed() from here didn't work.
        //I have used two nested functions to do this. This is to make it easier to test
        checkIfEverySymbolIsInPositionX(0)



    }
}