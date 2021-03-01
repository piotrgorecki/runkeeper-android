package pl.training.runkeeper.forecast.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.training.runkeeper.RunKeeperApplication.Companion.applicationGraph
import pl.training.runkeeper.commons.Logger
import pl.training.runkeeper.databinding.FragmentForecastListBinding
import pl.training.runkeeper.forecast.models.DayForecast
import java.util.*
import javax.inject.Inject

class ForecastListFragment : Fragment() {

    private lateinit var binding: FragmentForecastListBinding
    @Inject
    lateinit var logger: Logger

    private val forecastData = listOf(
        DayForecast(1, Date(), "Pochmurnie", 4, 8, "https://openweathermap.org/img/wn/10d@2x.png")
    )

    init {
        applicationGraph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentForecastListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forecastList.layoutManager = LinearLayoutManager(activity)
        binding.forecastList.adapter = ForecastListAdapter(forecastData)
        logger.log("On create...")
    }

}