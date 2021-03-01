package pl.training.runkeeper.forecast.adapters.api

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.training.runkeeper.forecast.adapters.api.ApiMappers.toDomainModel
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastService

class RetrofitForecastService(private val forecastProvider: ForecastProvider): ForecastService {

    override fun getForecast(cityName: String): Observable<Forecast> = forecastProvider.getWeather(cityName)
            .map { toDomainModel(it) }
            .toObservable()
            .subscribeOn(Schedulers.io())

}