package pl.training.runkeeper.forecast.models

import io.reactivex.schedulers.Schedulers
import pl.training.runkeeper.forecast.adapters.api.ApiMappers.toDomainModel
import pl.training.runkeeper.forecast.adapters.api.ForecastProvider

class ForecastService(private val forecastProvider: ForecastProvider) {

    fun getForecast(cityName: String) = forecastProvider.getWeather(cityName)
            .map { toDomainModel(it) }
            .toObservable()
            .subscribeOn(Schedulers.io())

}