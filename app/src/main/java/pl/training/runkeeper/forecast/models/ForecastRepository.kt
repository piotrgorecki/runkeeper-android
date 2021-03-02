package pl.training.runkeeper.forecast.models

import io.reactivex.rxjava3.core.Maybe

interface ForecastRepository {

    fun save(forecast: Forecast): Maybe<Forecast>

    fun findByCityName(cityName: String): Maybe<Forecast>

}