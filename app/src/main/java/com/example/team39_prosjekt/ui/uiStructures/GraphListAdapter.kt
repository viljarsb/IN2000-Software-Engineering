package com.example.team39_prosjekt.ui.uiStructures

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.detailedInfoUI.fragments.DetailedInfoFragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import kotlinx.android.synthetic.main.graph_frame.view.*

//Adapter for the horizontal recycler view in the detailed page.
//Holds two graphs, and some text.
class GraphListAdapter(val list: MutableList<DetailedInfoFragment.graphInfo>, val context: Context): RecyclerView.Adapter<GraphListAdapter.MyViewHolder>(){

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.graph_frame,
                parent,
                false
            )
        )
    }

    private fun calculateDistance(lowerBound: Int, upperBound: Int): Int
    {
        var counter = 0
        for(i in lowerBound..upperBound)
        {
            counter++
        }
        return counter
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val graphInfo = list.get(position)
        val lineDataSet = graphInfo.LineDataSet
        val barDataSet = graphInfo.barDataSet


        //Display only correct arrows.
         if(position == 0)
         {
             holder.leftArrow.visibility = View.INVISIBLE
         }

         else if(position == list.size-1)
         {
             holder.rightArrow.visibility = View.INVISIBLE
         }


        val lineGraph = LineData(lineDataSet)
        var min: Float = lineGraph.getDataSetByIndex(0).yMin
        var max: Float = lineGraph.getDataSetByIndex(0).yMax
        val barGraph = BarData(barDataSet)
        holder.lineGraph.data = lineGraph
        holder.barGraph.data = barGraph


        /* LineChart styling */

        //General styling
        lineDataSet.lineWidth = 2f
        lineDataSet.circleSize = 4.5f
        lineDataSet.setCircleColor(Color.RED)
        lineDataSet.color = Color.BLACK
        holder.lineGraph.setGridBackgroundColor(Color.TRANSPARENT)
        holder.lineGraph.xAxis.setDrawGridLines(false)
        holder.lineGraph.setDrawGridBackground(true)
        holder.lineGraph.description.isEnabled = false

        //Text
        holder.lineGraph.lineData.setValueTextSize(13f)
        holder.lineGraph.axisLeft.textSize = 14f
        holder.lineGraph.xAxis.textSize = 14f
        holder.lineGraph.legend.textSize = 15f

        //Restricting graph interaction
        holder.lineGraph.setTouchEnabled(false)
        holder.lineGraph.setScaleEnabled(false)
        holder.lineGraph.isDragEnabled = false

        //Axis
        holder.lineGraph.xAxis.position = XAxis.XAxisPosition.BOTTOM
        holder.lineGraph.axisLeft.setDrawGridLines(false)
        holder.lineGraph.axisRight.isEnabled = false
        holder.lineGraph.xAxis.spaceMin = 0.5f
        holder.lineGraph.xAxis.spaceMax = 0.5f
        holder.lineGraph.xAxis.setDrawAxisLine(true)
        holder.lineGraph.axisLeft.isGranularityEnabled = true
        holder.lineGraph.axisLeft.granularity = 1f
        holder.lineGraph.xAxis.labelCount = 5
        holder.lineGraph.axisLeft.axisLineWidth = 1.5f
        holder.lineGraph.axisLeft.axisLineColor = Color.BLACK
        holder.lineGraph.xAxis.axisLineWidth = 1.5f
        holder.lineGraph.xAxis.axisLineColor = Color.BLACK


        /* BarChart styling */

        //General styling
        barDataSet.color = Color.rgb(255, 127, 80)
        barDataSet.barBorderWidth = 1f
        holder.barGraph.setGridBackgroundColor(Color.TRANSPARENT)
        holder.barGraph.xAxis.setDrawGridLines(false)
        holder.barGraph.setDrawGridBackground(true)
        holder.barGraph.legend.textSize = 15f

        //Text
        holder.barGraph.barData.setValueTextSize(13f)
        holder.barGraph.axisLeft.textSize = 14f
        holder.barGraph.xAxis.textSize = 14f

        //Restricting graph interaction
        holder.barGraph.setTouchEnabled(false)
        holder.barGraph.isDragEnabled = false
        holder.barGraph.setScaleEnabled(false)

        //Axis
        holder.barGraph.axisLeft.setDrawGridLines(false)
        holder.barGraph.axisRight.isEnabled = false
        holder.barGraph.xAxis.position = XAxis.XAxisPosition.BOTTOM
        holder.barGraph.description.isEnabled = false
        holder.barGraph.axisLeft.isGranularityEnabled = true
        holder.barGraph.axisLeft.granularity = 1f
        holder.barGraph.xAxis.labelCount = 5
        holder.barGraph.axisLeft.axisLineWidth = 1.5f
        holder.barGraph.axisLeft.axisLineColor = Color.BLACK
        holder.barGraph.xAxis.axisLineWidth = 1.5f
        holder.barGraph.xAxis.axisLineColor = Color.BLACK

        if(graphInfo.type == "airTemp")
        {
            holder.lineGraph.axisLeft.axisMinimum = (min - 3f)
            holder.lineGraph.axisLeft.axisMaximum = (max + 3f)
            holder.lineGraph.axisLeft.valueFormatter = TempYAxisFormatter()
            holder.lineGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.lineGraph.lineData.setValueFormatter(TempValueFormatter())
            holder.lineGraph.axisLeft.labelCount = 8

            if((min - 3f) < 0)
                holder.barGraph.axisLeft.axisMinimum = 0f

            else
                holder.barGraph.axisLeft.axisMinimum = (min - 3f)

            holder.barGraph.axisLeft.axisMaximum = (max + 3f)
            holder.barGraph.axisLeft.valueFormatter = TempYAxisFormatter()
            holder.barGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.barGraph.barData.setValueFormatter(TempValueFormatter())
            holder.barGraph.axisLeft.labelCount = 8

            holder.CurrentCondition.text = "Temperaturer mellom ${timeFormatter(graphInfo.beach.getForecastTime(0)?.hours)}" +
                    "-${timeFormatter(graphInfo.beach.getForecastTime(5)?.hours)}"
            holder.text1.text = "Maksimums temperatur ${max}°\nMinimums temperatur ${min}°\n"
        }

        else if(graphInfo.type == "wind")
        {
            holder.lineGraph.axisLeft.valueFormatter = WindYAxisFormatter()
            holder.lineGraph.axisLeft.axisMaximum = (max + 3f)
            holder.lineGraph.axisLeft.axisMinimum = 0f
            holder.lineGraph.axisLeft.labelCount = 7
            holder.lineGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.lineGraph.lineData.setValueFormatter(WindValueFormatter())

            holder.barGraph.axisLeft.axisMinimum = 0f
            holder.barGraph.axisLeft.axisMaximum = (max + 3f)
            holder.barGraph.axisLeft.labelCount = 7
            holder.barGraph.axisLeft.valueFormatter = WindYAxisFormatter()
            holder.barGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.barGraph.barData.setValueFormatter(WindValueFormatter())

            holder.CurrentCondition.text = "Vindforhold mellom ${timeFormatter(graphInfo.beach.getForecastTime(0)?.hours)}" +
                    "-${timeFormatter(graphInfo.beach.getForecastTime(5)?.hours)}"
            holder.text1.text = "Sterkest vind ${max}m/s\nSvakeste vind ${min}m/s"
        }

        else if(graphInfo.type == "rain")
        {
            holder.lineGraph.axisLeft.valueFormatter = RainYAxisFormatter()
            holder.lineGraph.axisLeft.axisMinimum = (0f)
            holder.lineGraph.axisLeft.labelCount = lineDataSet.yMax.toInt() + 3
            holder.lineGraph.axisLeft.axisMaximum = (max + 3f)
            holder.lineGraph.axisLeft.labelCount = 7
            holder.lineGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.lineGraph.lineData.setValueFormatter(RainValueFormatter())

            holder.barGraph.axisLeft.axisMinimum = 0f
            holder.barGraph.axisLeft.axisMaximum = (max + 3f)
            holder.barGraph.axisLeft.labelCount = 7
            holder.barGraph.axisLeft.valueFormatter = RainYAxisFormatter()
            holder.barGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.barGraph.barData.setValueFormatter(RainValueFormatter())

            holder.CurrentCondition.text = "Nedbør mellom ${timeFormatter(graphInfo.beach.getForecastTime(0)?.hours)}" +
                    "-${timeFormatter(graphInfo.beach.getForecastTime(5)?.hours)}"
            holder.text1.text = "Største mengde nedbør ${max}mm\nMinste mengde nedbør ${min}mm"
        }

        else if(graphInfo.type == "cloudCover")
        {
            holder.lineGraph.axisLeft.valueFormatter = CloudYAxisFormatter()
            holder.lineGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.lineGraph.axisLeft.axisMinimum = (0f)
            holder.lineGraph.axisLeft.axisMaximum = 100f
            holder.lineGraph.lineData.setValueFormatter(CloudValueFormatter())
            holder.lineGraph.axisLeft.labelCount = 6

            holder.barGraph.axisLeft.axisMinimum = 0f
            holder.barGraph.axisLeft.axisMaximum = 100f
            holder.barGraph.axisLeft.labelCount = 6
            holder.barGraph.axisLeft.valueFormatter = CloudYAxisFormatter()
            holder.barGraph.xAxis.valueFormatter = XAxisFormatter()
            holder.barGraph.barData.setValueFormatter(CloudValueFormatter())

            holder.CurrentCondition.text = "Skydekke mellom ${timeFormatter(graphInfo.beach.getForecastTime(0)?.hours)}" +
                    "-${timeFormatter(graphInfo.beach.getForecastTime(5)?.hours)}"
            holder.text1.text = "Skydekke vil være mellom ${max}% og ${min}%"
        }

         holder.changeChartButton.setImageResource(R.drawable.barchart_icon)

        holder.changeChartButton.setOnClickListener {
            if(holder.barGraph.visibility == View.INVISIBLE)
            {
                holder.barGraph.visibility = View.VISIBLE
                holder.lineGraph.visibility = View.INVISIBLE
                holder.changeChartButton.setImageResource(R.drawable.linechart_icon)
            }

            else if(holder.lineGraph.visibility == View.INVISIBLE)
            {
                holder.lineGraph.visibility = View.VISIBLE
                holder.barGraph.visibility = View.INVISIBLE
                holder.changeChartButton.setImageResource(R.drawable.barchart_icon)
            }
        }
     }

    class MyViewHolder(frame: View) : RecyclerView.ViewHolder(frame)
    {
        val lineGraph = frame.lineGraph
        val barGraph = frame.barGraph
        var CurrentCondition = frame.ConditionHeader
        var text1 = frame.cond1
        val changeChartButton = frame.changeChart
        val leftArrow = frame.leftArrow
        val rightArrow = frame.rightArrow
    }
}