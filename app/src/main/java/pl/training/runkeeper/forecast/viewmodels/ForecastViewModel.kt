package pl.training.runkeeper.forecast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import pl.training.runkeeper.RunKeeperApplication
import pl.training.runkeeper.RunKeeperApplication.Companion.applicationGraph
import pl.training.runkeeper.commons.Logger
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastRepository
import pl.training.runkeeper.forecast.models.ForecastProvider
import javax.inject.Inject

class ForecastViewModel : ViewModel() {

    @Inject
    lateinit var logger: Logger
    @Inject
    lateinit var forecastProvider: ForecastProvider
    @Inject
    lateinit var forecastRepository: ForecastRepository

    private val disposableBag = CompositeDisposable()
    private val forecastData = MutableLiveData<Forecast>()

    init {
        applicationGraph.inject(this)
    }

    fun getForecast(cityName: String) {
        val cachedForecast = forecastRepository.findByCityName(cityName)
        val forecast = forecastProvider.getForecast(cityName).flatMap(forecastRepository::save)
        Maybe.concat(cachedForecast, forecast)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(forecastData::postValue) { logger.log(it.toString()) }
            .addTo(disposableBag)
    }

    fun forecastData(): LiveData<Forecast> = forecastData

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }

}