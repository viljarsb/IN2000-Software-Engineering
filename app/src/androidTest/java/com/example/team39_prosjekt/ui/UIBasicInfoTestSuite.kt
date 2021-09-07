package com.example.team39_prosjekt.ui

import com.example.team39_prosjekt.ui.detailedBeachTests.DetailedBeachViewDisplayTests
import com.example.team39_prosjekt.ui.detailedBeachTests.DetailedBeachViewForecastDisplayedTest
import com.example.team39_prosjekt.ui.detailedBeachTests.DetailedBeachViewGeneralTest
import com.example.team39_prosjekt.ui.fragmentTests.ListViewFragmentTest
import com.example.team39_prosjekt.ui.fragmentTests.MapViewFragmentTest
import com.example.team39_prosjekt.ui.googleMapsTests.GMapsTests
import com.example.team39_prosjekt.ui.mainActivityTest.MainActivityTest
import com.example.team39_prosjekt.ui.mainActivityTest.RecyclerViewTest
import com.example.team39_prosjekt.ui.mainActivityTest.StressTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


/*
* In here, I will list which tests are to be built, and which are done.
*
* This class can be compared to a hub, where all the test classes can be run.
* Every time a new test class is created, one only has to include the class name
* in here, under the classes that are already included.
*
* To run all the tests, click on the play-button beside the class name, and every test should run.
* */

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    ListViewFragmentTest::class,
    MapViewFragmentTest::class,
    RecyclerViewTest::class,
    DetailedBeachViewDisplayTests::class,
    DetailedBeachViewForecastDisplayedTest::class,
    DetailedBeachViewGeneralTest::class,
    GMapsTests::class,
    StressTest::class
)
class UIBasicInfoTestSuite