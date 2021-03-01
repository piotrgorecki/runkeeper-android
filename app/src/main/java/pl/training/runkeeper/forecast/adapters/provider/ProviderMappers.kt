package pl.training.runkeeper.forecast.adapters.provider

import pl.training.runkeeper.forecast.models.DayForecast
import pl.training.runkeeper.forecast.models.Forecast
import java.util.*

object ProviderMappers {

    fun toDomainModel(forecastDto: ForecastDto) = with(forecastDto) {
        Forecast(city.id, city.name, list.map { toDomainModel(it) })
    }

    private fun toDomainModel(dayForecastDto: DayForecastDto) = with(dayForecastDto) {
        DayForecast(-1, Date(dt * 1_000), weather[0].description, temp.min.toInt(), temp.max.toInt(),
            "https://openweathermap.org/img/wn/${weather[0].icon}.png")
    }

}