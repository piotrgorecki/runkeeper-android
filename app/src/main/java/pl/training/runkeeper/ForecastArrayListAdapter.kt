package pl.training.runkeeper

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastArrayListAdapter(private val forecastData: List<String>) : RecyclerView.Adapter<ForecastArrayListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(TextView(parent.context))

    override fun getItemCount() = forecastData.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.forecastInfo.text = forecastData[position]
    }

    class ViewHolder(val forecastInfo: TextView) : RecyclerView.ViewHolder(forecastInfo)

}