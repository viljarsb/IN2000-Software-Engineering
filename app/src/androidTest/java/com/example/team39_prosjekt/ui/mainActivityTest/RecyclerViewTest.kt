package com.example.team39_prosjekt.ui.mainActivityTest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import com.example.team39_prosjekt.ui.customClasses.CustomMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//This will be implemented later. I just have to check out the tutorials for this.
@RunWith(AndroidJUnit4ClassRunner::class)
class RecyclerViewTest {

    /*
    *Setting a rule instead of always declaring an ActivityScenario since we are always in main.
    *This must either be a @JvmField or use the @get:Rule annotation. The reason being that a rule
    *must be public to be initialized.
     */
    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    /*
    * A working test that uses a custom matcher class found here:
    * https://medium.com/2359media/custom-recyclerview-matcher-and-viewassertion-with-espresso-kotlin-45845c64ab44
    *
    * The idea later on, is to use this kind of custom matcher to other things with the recycler view as well.
    * */
    @Test
    fun countPrograms(){

        //Verify that we are in MainActivity:
        onView(withId(R.id.Main))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView))
            .check(matches(CustomMatchers.withItemCount(21)))
    }

    @Test
    fun test_checkIfRecyclerViewScrolls(){

        //This test will scroll down to the bottom of the page and up again.
        onView(withId(R.id.Main))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(17))
    }


    //We originally had tests here that clicked on a beach in the recycler view. We removed this, since we are already
    //performing this test in "DetailedBeachViewDisplayTest".

}
