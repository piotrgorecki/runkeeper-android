package pl.training.runkeeper.forecast.models.api

data class DayForecastDto(val dt: Long, val temp: TemperatureDto, val pressure: Float, val humidity: Int, val weather: List<SummaryDto>,
                          val speed: Float, val deg: Int, val clouds: Int, val rain: Float)