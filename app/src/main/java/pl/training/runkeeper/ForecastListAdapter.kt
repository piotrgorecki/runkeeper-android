package pl.training.runkeeper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ForecastListAdapter(private val forecastData: List<DayForecast>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = forecastData.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(forecastData[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val description: TextView = view.findViewById(R.id.forecast_description)
        private val date: TextView = view.findViewById(R.id.forecast_date)
        private val minTemperature: TextView = view.findViewById(R.id.forecast_max_temperature)
        private val maxTemperature: TextView = view.findViewById(R.id.forecast_min_temperature)
        private val icon: ImageView = view.findViewById(R.id.forecast_icon)

        fun bindView(dayForecast: DayForecast) {
            description.text = dayForecast.description
            date.text = formatDate(dayForecast.date)
            maxTemperature.text = formatDegrees(dayForecast.maxTemperature)
            minTemperature.text = formatDegrees(dayForecast.minTemperature)
            Picasso.get().load(dayForecast.iconUrl).into(icon)
        }

    }

}