package pl.training.runkeeper.forecast.adapters.api

data class ForecastDto(val city: CityDto, val list: List<DayForecastDto>)