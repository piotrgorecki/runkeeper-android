package pl.training.runkeeper.forecast.adapters.provider

data class ForecastDto(val city: CityDto, val list: List<DayForecastDto>)