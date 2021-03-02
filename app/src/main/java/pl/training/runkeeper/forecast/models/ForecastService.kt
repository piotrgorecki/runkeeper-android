package pl.training.runkeeper.forecast.models

import io.reactivex.rxjava3.core.Maybe

interface ForecastService {

    fun getForecast(cityName: String): Maybe<Forecast>

}