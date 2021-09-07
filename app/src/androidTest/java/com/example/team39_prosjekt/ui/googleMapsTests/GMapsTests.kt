package com.example.team39_prosjekt.ui.googleMapsTests

import android.graphics.Point
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import com.example.team39_prosjekt.ui.customClasses.CustomAssertions.Companion.verify
import org.junit.Rule
import org.junit.Test


class GMapsTests{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    private fun clickOnMarker(name: String, device: UiDevice){
        val marker = device.findObject(UiSelector().descriptionContains(name))
        marker.click()
    }


    /*
    * There are several potential issues with this test.
    * 1. If the screen adjusts differently than placing the GMaps infobox in the middle, this test will fail.
    *   - This can happen if GMaps updates their infoview functionality, and we update the Gradle implementation as well.
    * 2. (This is not tested) If there is a beach at the near end of the map, the restrictions placed on the movement of the app
    *    may prevent the application from placing the info window to the middle of the screen.
    *
    * As mentioned further below, the code for clicking on a marker, and for adjusting the screen when clicking on infowindow is
    * copied from links given below.
    * */
    @Test
    fun test_getInDetailedBeachThroughInfoWindow(){

        //Verify that we are in main
        verify(R.id.Main)

        //Move to GoogleMaps:
        onView(withId(R.id.recyclerView))
            .perform(swipeLeft())

        //Verify that we are in Google Maps (which is the map view fragment):
        verify(R.id.mapview_fragment)

        /*
        * Setting up the clicker. We used this StackOverflow post:
        * https://stackoverflow.com/questions/29924564/using-espresso-to-unit-test-google-maps
        *
        * We can't use Espresso for this test, since Espresso treats views in a way that is not compatible with GMaps.
        * This is at least according to the referenced post.
        * */

        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Bygdøy Sjøbad"))
        marker.click()

        /*
        * Since GMaps draws the info window as an image, not a view, we have to manually click on the middle of the screen.
        * We have not figured this out by ourselves. We used this link:
        * https://stackoverflow.com/questions/42505274/android-testing-google-map-info-window-click.
        * The answer there explains how it works.
        * */

        //Waiting for the screen to adjust.
        Thread.sleep(2000)

        //NB! THIS IS COPIED FROM THE POST. WE ARE NOT TAKING CREDIT.
        //Calculating the (x,y) position of the info window, using the screen size:
        val display = activityRule.activity.windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size)
        val screenWidth = size.x
        val screenHeight = size.y
        val x = screenWidth / 2
        val y = (screenHeight*0.43).toInt()

        //Click on the infoWindow. We are using UiAutomator
        device.click(x,y)
        Thread.sleep(2000)


        //This under is not copied from the post.
        //Verify that we are in the detailed beach view.
        verify(R.id.detailed_beach)

    }


    //NB: This test takes several seconds to complete, because UiAutomator loops through the entire list of beaches 10 times.
    @Test
    fun clickOnFirst10Markers(){
        val beaches = activityRule.activity.resources.getStringArray(R.array.locations).toList()

        //Verify that we are in Main
        verify(R.id.Main)

        //Moving into GMaps
        onView(withId(R.id.recyclerView))
            .perform(swipeLeft())

        //Verify that we are in GMaps
        verify(R.id.mapview_fragment)

        val device = UiDevice.getInstance(getInstrumentation())


        /*To be able to click on markers without accidentally pressing down the infowindow that is open,
        * We had to do the same as the last test, manually find a point on the screen to click.
        * We also had to add a delay, so that the test didn't click too quickly.
        *
        * Again, the code beneath this comment is based on the answer in this link:
        * https://stackoverflow.com/questions/42505274/android-testing-google-map-info-window-click
        */
        val display = activityRule.activity.windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size)
        val screenHeight = size.y
        val screenWidth = size.x

        val x = (screenWidth * 0.5).toInt()
        val y = (screenHeight * 0.8).toInt()

        for (i in 0..10){
            val splitted = beaches[i].split("|")
            clickOnMarker(splitted[0], device)
            device.click(x, y)
            Thread.sleep(700)
        }
    }


}