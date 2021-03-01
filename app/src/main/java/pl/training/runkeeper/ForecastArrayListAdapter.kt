package pl.training.runkeeper

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastArrayListAdapter(private val forecastData: List<String>) : RecyclerView.Adapter<ForecastArrayListAdapter.ViewHolder>() {

    class ViewHolder(val forecastInfo: TextView) : RecyclerView.ViewHolder(forecastInfo)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(TextView(parent.context))

    override fun getItemCount() = forecastData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.forecastInfo.text = forecastData[position]
    }

}