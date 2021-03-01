package pl.training.runkeeper.forecast.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pl.training.runkeeper.commons.formatDate
import pl.training.runkeeper.commons.formatDegrees
import pl.training.runkeeper.databinding.ItemForecastListBinding
import pl.training.runkeeper.forecast.models.DayForecast

class ForecastListAdapter(private val forecastData: List<DayForecast>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemForecastListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = forecastData.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(forecastData[position])
    }

    class ViewHolder(private val binding: ItemForecastListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(dayForecast: DayForecast) = with(binding) {
            forecastDescription.text = dayForecast.description
            forecastDate.text = formatDate(dayForecast.date)
            forecastMaxTemperature.text = formatDegrees(dayForecast.maxTemperature)
            forecastMinTemperature.text = formatDegrees(dayForecast.minTemperature)
            Picasso.get().load(dayForecast.iconUrl).into(binding.forecastIcon)
        }

    }

}