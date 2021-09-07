package com.example.team39_prosjekt.ui.customClasses

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class CustomViewIdAssertion {

    companion object {
        fun withIndex(matcher: Matcher<View?>, index: Int): Matcher<View?>? {
            return object : TypeSafeMatcher<View?>() {
                var currentIndex = 0

                override fun describeTo(description: Description) {
                    description.appendText("Item ")
                    description.appendValue(index)
                    matcher.describeTo(description)
                }

                override fun matchesSafely(view: View?): Boolean {
                    return matcher.matches(view) && currentIndex++ == index
                }
            }
        }
    }
}