package com.example.team39_prosjekt.ui.uiStructures

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.team39_prosjekt.data.BeachLocationForecast
import com.example.team39_prosjekt.data.userDataPersistentStorage.Userdata
import com.example.team39_prosjekt.R
import kotlinx.android.synthetic.main.beach_card.view.*


class BeachListAdapter(val beaches: MutableList<BeachLocationForecast>, val context: Context, val clickListener: (BeachLocationForecast) -> Unit)
    : RecyclerView.Adapter<BeachListAdapter.MyViewHolder>(), Filterable{

    private var displayList = beaches
    var filterFavourites = false

    fun updateData(list : MutableList<BeachLocationForecast>) {
        displayList = list
    }
    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val string = constraint.toString()
                if (string.isEmpty()) {
                    displayList = beaches
                }
                else {
                    val results = mutableListOf<BeachLocationForecast>()
                    beaches.forEach {
                        if(it.getName().toLowerCase().contains(string.toLowerCase())) {
                            results.add(it)
                        }
                    }
                    displayList = results
                }

                if (filterFavourites) {
                    displayList = displayList.filter{
                        Userdata.isFavourite(it.getName(), context as Activity)
                    } as MutableList<BeachLocationForecast>

                }
                val filteredResults = FilterResults()
                filteredResults.values = displayList
                return filteredResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                displayList = results?.values as MutableList<BeachLocationForecast>
                notifyDataSetChanged()
            }
        }
    }
    override fun getItemCount(): Int {
        return displayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.beach_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(displayList[position], clickListener)
        val currentBeach = displayList[position]

        val beachName = currentBeach.getName()
        val oceanTemp = currentBeach.getOceanTemp(0) + "°"
        val airTemp = currentBeach.getTemp(0) + "°"
        val windSpeed = currentBeach.getWind(0) + "m/s"

        holder.name.text = beachName
        holder.airTemp.text = airTemp
        holder.oceanTemp.text = oceanTemp
        holder.wind.text = windSpeed
      
        //Screen Reader Compatibility
        //Name is already read by the screen reader.
        holder.airTemp.contentDescription = "Lufttemperatur: $airTemp celsius"
        holder.oceanTemp.contentDescription = "Vanntemperatur: $oceanTemp celsius"
        holder.wind.contentDescription = "Vindhastighet: $windSpeed, i meter per sekund"
        holder.img_background.contentDescription = "Bilde av $beachName"

        //Checks is beach is favourite, sets icon accordingly
        if (Userdata.isFavourite(currentBeach.getName(), context as Activity)) {
            holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_on)

            //Screen Reader Compatibility:
            holder.favouriteButton.contentDescription = "Favorittstrand"
        } else {
            holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_off)
        }

        //Sets listener to favouriteButton
        holder.favouriteButton.setOnClickListener{
            val favourite = Userdata.toggleFavourite(currentBeach.getName(), context)
            if (favourite) {
                holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                holder.favouriteButton.setImageResource(android.R.drawable.btn_star_big_off)
            }
        }

        //sets background and weather symbol.

        val imageResource = this.context.resources.getIdentifier(currentBeach.getName().toLowerCase()
            .replace(" ", "")
            .replace("å", "a")
            .replace("ø", "o")
            .replace("æ", "ae"),
            "drawable", this.context.packageName)
        Glide.with(holder.card).load(imageResource).into(object : CustomTarget<Drawable?>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                holder.img_background.background = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {} //don't remove
        })

        val iconResource = this.context.resources.getIdentifier(currentBeach.getSymbol(0), "drawable", this.context.packageName)
        Glide.with(holder.card).load(iconResource).into(holder.weatherSymbol)
    }

    class MyViewHolder(cardview: View) : RecyclerView.ViewHolder(cardview)
    {
        val card = cardview
        val name = cardview.beachTitle
        val airTemp = cardview.airTemp
        val oceanTemp = cardview.OceanTemp
        val wind = cardview.wind
        val weatherSymbol = cardview.weatherSymbol
        val img_background = cardview.img_background
        val favouriteButton = cardview.favouriteButton
        fun bind(beach: BeachLocationForecast, clickListener: (BeachLocationForecast) -> Unit)
        {
            card.setOnClickListener { clickListener(beach)}
        }
    }
}
