package pl.training.runkeeper.forecast.models

data class Forecast(val id: Long, val cityName: String, val data: List<DayForecast>)