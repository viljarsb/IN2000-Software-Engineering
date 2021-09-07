package com.example.team39_prosjekt.ui.detailedInfoUI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.team39_prosjekt.data.BeachLocationForecast
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.uiStructures.GraphListAdapter
import com.example.team39_prosjekt.ui.uiStructures.SpannableStringBuilder
import com.example.team39_prosjekt.viewmodels.Viewmodel
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_detailed_view.*


class DetailedInfoFragment : Fragment() {
    private val beachObject = Viewmodel.getDetailed()

    companion object {
        fun newInstance() =
            DetailedInfoFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //When the parent activity is done loading, this function is called.
        //Image-loading functions etc. can be called from here
        super.onActivityCreated(savedInstanceState)
        drawData(beachObject)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detailed_view, container, false)
    }

    private fun drawData(beach: BeachLocationForecast) {
        BeachName.text = beach.getName()
        val tempText = "${beach.getTemp(0)}° \nføles som ${beach.getRelativeTemp(0)}°"
        Temperature.text = SpannableStringBuilder(tempText, "\n", 0.7f)
        val oceanText = beach.getOceanDescription()
        OceanTemp.text = SpannableStringBuilder(oceanText, "\n", 0.6f)
        val rainText = beach.getRainDescription()
        Rain.text = SpannableStringBuilder(rainText, "\n", 0.6f)
        val uvText = beach.getUvDescription(resources.getStringArray(R.array.uvIndexes).toList())
        Uv.text = SpannableStringBuilder(uvText, "\n", 0.6f)
        val windText = beach.getWindDescription((resources.getStringArray(R.array.BaufortScale).toList()))
        Wind.text = SpannableStringBuilder(windText, "\n", 0.6f)
        val cloudText = beach.getCloudDescription()
        Cloud.text = SpannableStringBuilder(cloudText, "\n", 0.6f)
        val swimText = beach.safeToSwim()
        Swimming.text = SpannableStringBuilder(swimText, "\n", 0.6f)

        val iconResource = this.context?.resources?.getIdentifier(beach.getSymbol(0), "drawable", this.context?.packageName)
        Glide.with(symbol).load(iconResource).into(symbol)
        val list = createGraphs(beach)
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        GraphRec.layoutManager = layoutManager
        val adapter = GraphListAdapter(list, this.requireActivity())
        GraphRec.adapter = adapter

        //Smoother scrolling. 1 swipe for next graph. Will center the next item after swipe.
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(GraphRec)

    }

    private fun createGraphs(beach: BeachLocationForecast): MutableList<graphInfo> {
        val list = mutableListOf<graphInfo>()
        var lineEntriesOne = mutableListOf<Entry>()
        var LineDataSet: LineDataSet
        var barEntries = mutableListOf<BarEntry>()
        var BarDataSet: BarDataSet

        //Temperature data
        for(i in 0..5) {
            lineEntriesOne.add(Entry(i.toFloat(), beach.getTemp(i).toFloat()))
            barEntries.add(BarEntry(i.toFloat(), beach.getTemp(i).toFloat()))
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Temperaturer")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Temperaturer")
        list.add(graphInfo(LineDataSet, BarDataSet,"airTemp", beach))

        lineEntriesOne.clear()
        barEntries.clear()

        //Wind data
        for(i in 0..5) {
            lineEntriesOne.add(Entry(i.toFloat(), beach.getWind(i).toFloat()))
            barEntries.add(BarEntry(i.toFloat(), beach.getWind(i).toFloat()))
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Vindstyrke")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Vindstyrke")
        list.add(graphInfo(LineDataSet, BarDataSet,"wind", beach))

        lineEntriesOne.clear()
        barEntries.clear()

        //Rain data
        for(i in 0..5) {
            lineEntriesOne.add(Entry(i.toFloat(), beach.getRain(i).toFloat()))
            barEntries.add(BarEntry(i.toFloat(), beach.getRain(i).toFloat()))
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Nedbør")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Nedbør")
        list.add(graphInfo(LineDataSet, BarDataSet,"rain", beach))

        lineEntriesOne.clear()
        barEntries.clear()

        //Cloud data
        for(i in 0..5) {
            lineEntriesOne.add(Entry(i.toFloat(), beach.getCloudCover(i).toFloat()))
            barEntries.add(BarEntry(i.toFloat(), beach.getCloudCover(i).toFloat()))
        }
        LineDataSet = LineDataSet(mutableListOf<Entry>().apply { addAll(lineEntriesOne) }, "Skydekke")
        BarDataSet = BarDataSet(mutableListOf<BarEntry>().apply { addAll(barEntries) }, "Skydekke")
        list.add(graphInfo(LineDataSet, BarDataSet,"cloudCover", beach))

        return list
    }

     data class graphInfo(val p1: LineDataSet, val p2: BarDataSet, val p3: String, val p4: BeachLocationForecast) {
        val LineDataSet = p1
        val barDataSet = p2
        val type = p3
        val beach = p4
     }

}
