package pl.training.runkeeper.forecast.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pl.training.runkeeper.R
import pl.training.runkeeper.forecast.models.DayForecast
import pl.training.runkeeper.formatDate
import pl.training.runkeeper.formatDegrees

class ForecastListAdapter(private val forecastData: List<DayForecast>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_forecast_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = forecastData.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(forecastData[position])
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(dayForecast: DayForecast) {
            view.findViewById<TextView>(R.id.forecast_date).text = formatDate(dayForecast.date)
            view.findViewById<TextView>(R.id.forecast_description).text = dayForecast.description
            view.findViewById<TextView>(R.id.forecast_max_temperature).text = formatDegrees(dayForecast.maxTemperature)
            view.findViewById<TextView>(R.id.forecast_min_temperature).text = formatDegrees(dayForecast.minTemperature)
            Picasso.get().load(dayForecast.iconUrl).into(view.findViewById<ImageView>(R.id.forecast_icon))
        }

    }

}