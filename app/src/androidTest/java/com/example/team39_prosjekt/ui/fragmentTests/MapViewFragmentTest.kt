package com.example.team39_prosjekt.ui.fragmentTests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//This should be added to test the mapView Fragment.
@RunWith(AndroidJUnit4ClassRunner::class)
class MapViewFragmentTest{

    //Setting a test rule.
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    /*
    * This test is meant to check that we are in mapview when we click the tab "Map"
    * */
    @Test
    fun test_checkIfMapViewFragmentIsDisplayed(){

        //Verifying that we are in MainActivity:
        onView(withId(R.id.Main))
            .check(matches(isDisplayed()))

        /*
        * This does not actually click on the map-tab. It only clicks on the tagLayout itself. I will look at this later
        * */
        onView(withId(R.id.tabLayout))
            .perform(click())
    }

    @Test
    fun test_checkIfSwipingToMapViewWorks(){

        //Verifying that we are in main
        onView(withId(R.id.Main))
            .check(matches(isDisplayed()))

        //Swiping left
        onView(withId(R.id.Main))
            .perform(swipeLeft())
    }
}