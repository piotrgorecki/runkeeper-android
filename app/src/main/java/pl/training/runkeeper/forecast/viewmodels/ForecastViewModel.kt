package pl.training.runkeeper.forecast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import pl.training.runkeeper.RunKeeperApplication
import pl.training.runkeeper.commons.Logger
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastProvider
import javax.inject.Inject

class ForecastViewModel : ViewModel() {

    private val disposableBag = CompositeDisposable()
    private val forecastData = MutableLiveData<Forecast>()

    @Inject
    lateinit var logger: Logger
    @Inject
    lateinit var forecastProvider: ForecastProvider

    init {
        RunKeeperApplication.applicationGraph.inject(this)
    }

    fun getForecast(cityName: String) {
        forecastProvider.getForecast(cityName)
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