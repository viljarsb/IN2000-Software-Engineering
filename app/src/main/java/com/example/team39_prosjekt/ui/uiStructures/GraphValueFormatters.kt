package com.example.team39_prosjekt.ui.uiStructures

import com.example.team39_prosjekt.viewmodels.Viewmodel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

//Ignore this, just for formatting the X, Y and value labels in graphs.
@Suppress("DEPRECATION")
class WindYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.toInt()}" + "m/s"
    }
}

@Suppress("DEPRECATION")
class WindValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "m/s"
    }
}


@Suppress("DEPRECATION")
class XAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val time = Viewmodel.getDetailed().getForecastTime(value.toInt())?.hours
        return timeFormatter(time)
    }
}

@Suppress("DEPRECATION")
class TempYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.toInt()}" + "°"
    }
}

@Suppress("DEPRECATION")
class TempValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "°"
    }
}

@Suppress("DEPRECATION")
class RainValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "mm"
    }
}

@Suppress("DEPRECATION")
class RainYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.toInt()}" + "mm"
    }
}

@Suppress("DEPRECATION")
class CloudYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.toInt()}" + "%"
    }
}


@Suppress("DEPRECATION")
class CloudValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "%"
    }
}