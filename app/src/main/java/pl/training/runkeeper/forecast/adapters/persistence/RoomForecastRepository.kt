package pl.training.runkeeper.forecast.adapters.persistence

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.training.runkeeper.forecast.adapters.persistence.RoomMappers.toDomainModel
import pl.training.runkeeper.forecast.adapters.persistence.RoomMappers.toRoomModel
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastRepository

class RoomForecastRepository(private val forecastDao: ForecastDao): ForecastRepository {

   override fun save(forecast: Forecast): Maybe<Forecast> {
       return Maybe.fromCallable {
           forecastDao.save(toRoomModel(forecast))
           forecastDao.save(forecast.data.map { toRoomModel(it, forecast.id) })
           forecast
       }
       .subscribeOn(Schedulers.io())
    }

    override fun findByCityName(cityName: String): Maybe<Forecast> = forecastDao.findByCityName(cityName)
        .map { toDomainModel(it) }
        .subscribeOn(Schedulers.io())

}