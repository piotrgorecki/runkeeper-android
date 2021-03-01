package pl.training.runkeeper.forecast.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import pl.training.runkeeper.R
import pl.training.runkeeper.RunKeeperApplication.Companion.applicationGraph
import pl.training.runkeeper.commons.Logger
import pl.training.runkeeper.forecast.models.DayForecast
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastService
import java.util.*
import javax.inject.Inject

class ForecastListFragment : Fragment() {

    @Inject
    lateinit var logger: Logger
    @Inject
    lateinit var forecastService: ForecastService
    private val disposableBag = CompositeDisposable()

    private val forecastData = listOf(
        DayForecast(1, Date(), "Pochmurnie", 4, 8, "https://openweathermap.org/img/wn/10d@2x.png")
    )

    init {
        applicationGraph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecaast_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forecastList = view.findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(activity)
        forecastList.adapter = ForecastListAdapter(forecastData)

        forecastService.getForecast("warsaw")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onWeatherRefreshed) { logger.log(it.toString()) }
            .addTo(disposableBag)
    }

    private fun onWeatherRefreshed(forecast: Forecast) {
        logger.log(forecast.toString())
    }

    override fun onDetach() {
        super.onDetach()
        disposableBag.clear()
    }

}