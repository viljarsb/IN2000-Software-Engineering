package com.example.team39_prosjekt.ui.detailedBeachTests


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import com.example.team39_prosjekt.ui.customClasses.CustomAssertions.Companion.scrollToVerify
import com.example.team39_prosjekt.ui.customClasses.CustomAssertions.Companion.verify
import com.example.team39_prosjekt.ui.customClasses.CustomViewIdAssertion
import org.junit.Rule
import org.junit.Test


class DetailedBeachViewDisplayTests{


    //Setting a test rule. We are in the detailed beach view now.
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_checkThatEveryViewIsInView(){


        fun checkIfGraphIsInOrder(position: Int){
            fun assertIfViewExists(id: Int){
                onView(
                    CustomViewIdAssertion.withIndex(
                        withId(R.id.GraphRec), position)
                )
                    .check(matches(ViewMatchers.hasDescendant(withId(id))))
            }

            assertIfViewExists(R.id.barGraph)
            assertIfViewExists(R.id.lineGraph)
            assertIfViewExists(R.id.ConditionHeader)
            assertIfViewExists(R.id.cond1)
        }

        //Checking if we are in mainActivity
        verify(R.id.Main)

        //Moving into the first recyclerview item, to be able to assert views.
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>
                    (0, ViewActions.click()))

        //Verifying that we are in detailed beach view.
        verify(R.id.detailed_beach)

        //Checking that the beach name is displayed. There was a bug in the app that made this test fail.
        //This test made us able to see that there was a bug that made the page scroll down to the bottom,
        //making "BeachName" and "Temperature" not displayed.
        verify(R.id.BeachName)

        //Checking that "badeforhold" is displayed
        verify(R.id.Swimming)

        //Checking that "Lufttemperatur" is displayed
        verify(R.id.Temperature)

        //Checking that "Vindforhold" is displayed
        verify(R.id.Wind)

        //Checking that "Nedbør" is shown
        scrollToVerify(R.id.Rain)

        //Checking for Graph-Recycler View
        scrollToVerify(R.id.GraphRec)

        checkIfGraphIsInOrder(0)
    }

}