package pl.training.runkeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast_list.view.forecast_date as date
import kotlinx.android.synthetic.main.item_forecast_list.view.forecast_description as description
import kotlinx.android.synthetic.main.item_forecast_list.view.forecast_min_temperature as minTemperature
import kotlinx.android.synthetic.main.item_forecast_list.view.forecast_max_temperature as maxTemperature
import kotlinx.android.synthetic.main.item_forecast_list.view.forecast_icon as icon

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
            view.date.text = formatDate(dayForecast.date)
            view.description.text = dayForecast.description
            view.maxTemperature.text = formatDegrees(dayForecast.maxTemperature)
            view.minTemperature.text = formatDegrees(dayForecast.minTemperature)
            Picasso.get().load(dayForecast.iconUrl).into(view.icon)
        }

    }

}