package pl.training.runkeeper.forecast.models.api

data class CityDto(val id: Long, val name: String, val coord: CoordinatesDto, val country: String)