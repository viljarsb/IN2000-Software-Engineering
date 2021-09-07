package com.example.team39_prosjekt.ui.uiStructures

//Ignore this, just formats the time stamps.
fun timeFormatter(hour: Int?): String
{
    val time = hour.toString()
    if(time.length == 1) { return "0${time.get(0)}:00"}
    else { return "${time.get(0)}${time.get(1)}:00"}
}