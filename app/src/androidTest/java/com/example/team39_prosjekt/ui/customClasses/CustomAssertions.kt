package com.example.team39_prosjekt.ui.customClasses

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers
import java.lang.IllegalStateException

/*
* This is a Custom class that allows for assertions without using "Matches"
* I do not know why you would not use matches(), but this is an alternative way at least.
*
* */


class CustomAssertions{
    companion object{

        //This function, with its private class should be removed, once we are certain it isn't needed.
        fun hasItemCount(count: Int): ViewAssertion {
            return RecyclerViewItemCountAssertion(
                count
            )
        }

        private class RecyclerViewItemCountAssertion(private val count: Int): ViewAssertion{
            override fun check(view: View, noViewFoundException: NoMatchingViewException?){
                if (noViewFoundException != null) throw noViewFoundException
                if (view !is RecyclerView) throw IllegalStateException("The asserted view is not RecyclerView")
                if (view.adapter == null) throw IllegalStateException("No adapter is assigned to the RecyclerView")


                ViewMatchers.assertThat("RecyclerView item count", view.adapter!!.itemCount, CoreMatchers.equalTo(count))
            }
        }

        fun verify(id: Int){
            onView(withId(id))
                .check(matches(isDisplayed()))
        }

        fun scrollToVerify(id: Int){
            onView(withId(id))
                .perform(scrollTo())
                    .check(matches(isDisplayed()))
        }
    }
}