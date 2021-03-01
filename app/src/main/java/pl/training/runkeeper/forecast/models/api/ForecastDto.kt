package pl.training.runkeeper.forecast.models.api

data class ForecastDto(val city: CityDto, val list: List<DayForecastDto>)