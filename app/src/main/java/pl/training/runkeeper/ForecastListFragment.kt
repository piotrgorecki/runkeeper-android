package pl.training.runkeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*
import kotlinx.android.synthetic.main.fragment_forecaast_list.forecast_list as forecastList

class ForecastListFragment : Fragment() {

    private val forecastData = listOf(
        DayForecast(1, Date(), "Pochmurnie", 4, 8, "https://openweathermap.org/img/wn/10d@2x.png")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecaast_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastList.layoutManager = LinearLayoutManager(activity)
        forecastList.adapter = ForecastListAdapter(forecastData)
    }

}