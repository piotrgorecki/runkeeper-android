package pl.training.runkeeper

data class Forecast(val id: Long, val cityName: String, val data: List<DayForecast>)