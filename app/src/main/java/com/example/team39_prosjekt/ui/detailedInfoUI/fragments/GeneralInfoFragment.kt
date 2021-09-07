package com.example.team39_prosjekt.ui.detailedInfoUI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import com.example.team39_prosjekt.R
import com.example.team39_prosjekt.viewmodels.Viewmodel
import kotlinx.android.synthetic.main.info_view.*

class GeneralInfoFragment : Fragment() {
    private val viewModel: Viewmodel by activityViewModels()
    companion object {
        fun newInstance() =
            GeneralInfoFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.generalDetailed.observe(viewLifecycleOwner, Observer { generalInfo ->
            drawItems(generalInfo)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_view, container, false)
    }

    private fun drawItems(generalInfo: List<String>)
    {
        BeachName.text = Viewmodel.getDetailed().getName()
        if(generalInfo.size != 0)
        {
            var descBeach = generalInfo.get(0)
            if(descBeach.contains("Vi måler dessverre ikke badevanntemperatur på denne badeplassen."))
            {
                descBeach = descBeach.replace("Vi måler dessverre ikke badevanntemperatur på denne badeplassen.", "")
            }

            if(descBeach.length == 0) { descBeach = "Ingen data tilgjengelig om dette badestedet." }

            var facilities = generalInfo.get(1)
            if(facilities.replace(" ", "").length == 0) {facilities = "Ingen data tilgjengelig om dette badestedets fasiliteter."}

            val transport = generalInfo.get(2)
            if(transport.length == 0) {TransportHeader.visibility = View.GONE; TransportDesc.visibility = View.GONE}
            else {
                TransportDesc.text = viewModel.generalDetailed.value?.get(2)
            }

            var waterQuality = generalInfo.get(3)
            if(waterQuality.replace(" ", "").length == 0) { waterQuality = "Kommunen har ikke målt badevannskvaliteten ved denne stranden" }

            BeachDesc.text = descBeach
            FacilitiyList.text = facilities
            WaterQuality.text = waterQuality
        }
        else if(generalInfo.size == 0)
        {
            info.visibility = View.INVISIBLE
            val errorToast = Toast.makeText(this.context, "Kunne ikke laste inn info..", Toast.LENGTH_LONG)
            errorToast.show()
        }
    }
}
