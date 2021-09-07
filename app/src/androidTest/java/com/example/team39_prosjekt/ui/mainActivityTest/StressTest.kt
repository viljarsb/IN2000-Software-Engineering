package com.example.team39_prosjekt.ui.mainActivityTest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.basicInfoUI.activity.MainActivity
import com.example.team39_prosjekt.ui.customClasses.CustomAssertions.Companion.verify
import org.junit.Rule
import org.junit.Test


class StressTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_clickOnEveryBeach(){

        fun clickOnBeach(index: Int){
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>
                            (index, ViewActions.click()))
            pressBack()
        }

        verify(R.id.Main)

        for(i in 0..20){
            clickOnBeach(i)
        }
    }
}