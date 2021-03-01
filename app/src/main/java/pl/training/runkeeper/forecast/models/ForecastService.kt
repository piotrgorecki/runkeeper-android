package pl.training.runkeeper.forecast.models

import io.reactivex.rxjava3.core.Observable

interface ForecastService {

    fun getForecast(cityName: String): Observable<Forecast>

}