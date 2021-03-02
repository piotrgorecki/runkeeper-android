package pl.training.runkeeper.forecast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pl.training.runkeeper.RunKeeperApplication.Companion.applicationGraph
import pl.training.runkeeper.commons.Logger
import pl.training.runkeeper.commons.UserPreferences
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastProvider
import pl.training.runkeeper.forecast.models.ForecastRepository
import javax.inject.Inject

class ForecastViewModel : ViewModel() {

    @Inject
    lateinit var logger: Logger
    @Inject
    lateinit var forecastProvider: ForecastProvider
    @Inject
    lateinit var forecastRepository: ForecastRepository
    @Inject
    lateinit var userPreferences: UserPreferences

    private val disposableBag = CompositeDisposable()
    private val forecastData = MutableLiveData<Forecast>()
    private val isLoading = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()

    init {
        applicationGraph.inject(this)
        viewModelScope.launch {
            userPreferences.getCity().collect { getForecast(it) }
        }
    }

    fun getForecast(cityName: String) {
        isLoading.postValue(true)
        val cachedForecast = forecastRepository.findByCityName(cityName)
        val forecast = forecastProvider.getForecast(cityName).flatMap(forecastRepository::save)
        Maybe.concat(cachedForecast, forecast)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onForecastRefreshed, this::onForecastRefreshedError)
            .addTo(disposableBag)
    }

    private fun onForecastRefreshed(forecast: Forecast) {
        isLoading.postValue(false)
        forecastData.postValue(forecast)
        viewModelScope.launch { userPreferences.saveCity(forecast.cityName) }
    }

    private fun onForecastRefreshedError(throwable: Throwable) {
        isLoading.postValue(false)
        errorMessage.postValue("Refresh failed")
    }

    fun forecastData(): LiveData<Forecast> = forecastData

    fun isLoading(): LiveData<Boolean> = isLoading

    fun errorMessage(): LiveData<String> = errorMessage

    override fun onCleared() {
        super.onCleared()
        disposableBag.clear()
    }

}