package pl.training.runkeeper.forecast.adapters.persistence

import androidx.room.*

@Entity
data class CityDb(@PrimaryKey val id: Long, val name: String)