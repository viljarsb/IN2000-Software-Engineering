package com.example.team39_prosjekt.ui.mainActivityTest

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
class MainActivityTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun test_visibility_viewPager_and_tabsLayout() {

        //Verifying that we are in ActivityMain
        onView(withId(R.id.Main))
            .check(matches(isDisplayed()))

        //Checking that View Pager is displayed.
        onView(withId(R.id.viewPager))
            .check(matches(isDisplayed()))

        //Checking that the Tab Layout is displayed.
        onView(withId(R.id.tabLayout))
            .check(matches(isDisplayed()))
    }


}