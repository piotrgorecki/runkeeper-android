package pl.training.runkeeper.forecast.models

import io.reactivex.Observable

interface ForecastService {

    fun getForecast(cityName: String): Observable<Forecast>

}