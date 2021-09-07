package com.example.team39_prosjekt.data.network

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception

    /*
    *   Fetches/Scrapes general information from Oslo kommunes website.
    *   A very volotile function, could stop working in the future, implemented as extra feature.
    *   @Param  The name of the beach that information from is needed.
    *
    */
    suspend fun getGeneralData(beachName: String): MutableList<String>
    {
        var location = beachName.replace(" ", "-")
        .replace("ø", "o")
        .replace("å", "a")

        //Special URL for this beach, dont know why.
        if(location == "Solvikbukta") { location = location.plus("-pa-malmoya") }
        val url = "https://www.oslo.kommune.no/natur-kultur-og-fritid/tur-og-friluftsliv/badeplasser-og-temperaturer/$location/"
        var list = mutableListOf<String>()
        val job = CoroutineScope(Dispatchers.IO).launch {

            //Try to scrape the website.
            try
            {
                val data: Document = Jsoup.connect(url).timeout(5000).get()
                var summary = ".osg-u-padding-top-4.io-tile-introduction.io-tile-common"
                var facilities = ".toggle-xs-closed.io-tile-facts.io-tile-common"
                var parking = ".toggle-xs-closed.io-transport-parking.io-tile-common"
                var waterQuality = ".ok-water-quality"

                val div = data.select(summary)
                val p: Elements = div.select("p")
                summary = ""
                for(i in 0..(p.size-1))
                {
                    if(i < (p.size-1)) { summary = summary.plus(p.get(i).text()).plus("\n\n") }
                    else if(i == (p.size-1)) { summary = summary.plus(p.get(i).text()) }
                }


                val ul: Elements = data.select(facilities)
                val li = ul.select("li")
                facilities = "•"
                li.forEach { facilities = facilities.plus(it.text()).plus("\n\u2022")}
                val div2: Elements = data.select(waterQuality)
                val h = div2.select("span")
                val p2 = div2.select("p")
                val qList = h.text().split(" ")
                val tList = p2.text().split(" ")
                waterQuality = ""
                if(qList.size >= 3 && tList.size >= 3)
                {
                    waterQuality = waterQuality.plus(qList.get(0))
                        .plus(" ${qList.get(1)}")
                        .plus(" ${qList.get(2)}")
                        .plus(" \n${tList.get(0)}")
                        .plus(" ${tList.get(1)}")
                        .plus(" ${tList.get(2)}")
                }
                parking = data.select(parking).select("span").text()
                list.add(summary)
                list.add(facilities.dropLast(1))
                list.add(parking)
                list.add(waterQuality)
            }

            //on fail just log the error.
            catch(E: Exception)
            {
                Log.e("Webscraper error for $location", E.toString())
            }

        }
        job.join()
        return list
}