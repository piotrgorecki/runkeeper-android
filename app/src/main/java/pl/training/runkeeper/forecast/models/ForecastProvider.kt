package pl.training.runkeeper.forecast.models

import io.reactivex.rxjava3.core.Maybe

interface ForecastProvider {

    fun getForecast(cityName: String): Maybe<Forecast>

}