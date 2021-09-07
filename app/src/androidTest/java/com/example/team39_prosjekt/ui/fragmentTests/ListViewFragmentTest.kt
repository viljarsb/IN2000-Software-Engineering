package com.example.team39_prosjekt.ui.fragmentTests

import androidx.test.espresso.Espresso.onView
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


@RunWith(AndroidJUnit4ClassRunner::class)
class ListViewFragmentTest{


    //Setting a test rule.
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    /*
    * This is a simple test, meant to check that listViewFragment is displayed.
     */
    @Test
    fun test_clickOnListViewFragment(){

        //Verifying that ActivityMain is displaying:
        onView(withId(R.id.Main))
            .check(matches(isDisplayed()))

        //Checking that viewPager is displayed.
        onView(withId(R.id.viewPager))
            .check(matches(isDisplayed()))

    }
}