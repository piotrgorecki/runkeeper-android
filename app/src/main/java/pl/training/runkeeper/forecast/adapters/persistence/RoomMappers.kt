package pl.training.runkeeper.forecast.adapters.persistence

import pl.training.runkeeper.forecast.models.DayForecast
import pl.training.runkeeper.forecast.models.Forecast
import java.util.*

object RoomMappers {

    fun toRoomModel(forecast: Forecast) = with(forecast) {
        CityDb(id, toLowerCase(cityName))
    }

    fun toRoomModel(dayForecast: DayForecast, cityId: Long) = with(dayForecast) {
        DayForecastDb(null, date.time, description, maxTemperature, minTemperature, iconUrl, cityId)
    }

    fun toDomainModel(forecastDb: ForecastDb) = with(forecastDb) {
        Forecast(city.id, toLowerCase(city.name), data.map { toDomainModel(it) })
    }

    private fun toDomainModel(dayForecastDb: DayForecastDb) = with(dayForecastDb) {
        DayForecast(id ?: -1, Date(date), description, minTemperature, maxTemperature, iconUrl)
    }

    private fun toLowerCase(text: String) = text.toLowerCase(Locale.ROOT)

}