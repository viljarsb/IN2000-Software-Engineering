package com.example.team39_prosjekt.ui.uiStructures

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan

    //Ignore this, just formats some text.
    fun SpannableStringBuilder(text: String, afterChar: String, reduceBy: Float): SpannableStringBuilder? {
        val ssBuilder = SpannableStringBuilder(text)
        if(!text.contains("Ingen data tilgjengelig"))
        {
            val smallSizeText = RelativeSizeSpan(reduceBy)
            ssBuilder.setSpan(smallSizeText, text.indexOf(afterChar), text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return ssBuilder
        }

        ssBuilder.setSpan(ForegroundColorSpan(Color.RED), 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssBuilder
    }