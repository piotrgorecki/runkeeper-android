package pl.training.runkeeper.forecast.adapters.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayForecastDb(@PrimaryKey(autoGenerate = true) var id: Long?, val date: Long, val description: String,
                         @ColumnInfo(name = "max_temperature") val maxTemperature: Int,
                         @ColumnInfo(name = "min_temperature") val minTemperature: Int,
                         @ColumnInfo(name = "icon_url") val iconUrl: String,
                         val cityId: Long)