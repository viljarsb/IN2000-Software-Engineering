package com.example.team39_prosjekt.ui.detailedInfoUI.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.ui.uiStructures.DetailedBeachFragmentAdapter
import com.example.team39_prosjekt.viewmodels.Viewmodel
import kotlinx.android.synthetic.main.activity_detailed_beach.*

class DetailedBeachActivity : AppCompatActivity() {
    private val viewModel: Viewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_beach)

        tabLayoutDetailed.setupWithViewPager(viewPagerDetailed)
        val DetailedBeachFragmentAdapter =
            DetailedBeachFragmentAdapter(
                supportFragmentManager
            )
        viewPagerDetailed.adapter = DetailedBeachFragmentAdapter
        viewModel.fetchGeneralData(Viewmodel.getDetailed().getName())
    }
}
