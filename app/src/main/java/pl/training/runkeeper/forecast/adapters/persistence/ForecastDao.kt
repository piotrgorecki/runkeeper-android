package pl.training.runkeeper.forecast.adapters.persistence

import androidx.room.*
import io.reactivex.rxjava3.core.Maybe

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cityDb: CityDb)

    @Insert
    fun save(data: List<DayForecastDb>)

    @Transaction
    @Query("select * from CityDb where name = :cityName")
    fun findByCityName(cityName: String): Maybe<ForecastDb>

}