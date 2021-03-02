package pl.training.runkeeper.forecast.adapters.persistence

import androidx.room.Embedded
import androidx.room.Relation

data class ForecastDb(@Embedded val city: CityDb, @Relation(parentColumn = "id", entityColumn = "cityId") val data: List<DayForecastDb>)