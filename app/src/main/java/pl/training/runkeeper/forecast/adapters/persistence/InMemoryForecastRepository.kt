package pl.training.runkeeper.forecast.adapters.persistence

import io.reactivex.rxjava3.core.Maybe
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastRepository
import java.util.*

class InMemoryForecastRepository: ForecastRepository {

    private val data = mutableMapOf<String, Forecast>()

    override fun save(forecast: Forecast): Maybe<Forecast> {
        data[getKey(forecast.cityName)] = forecast
        return Maybe.just(forecast)
    }

    override fun findByCityName(cityName: String): Maybe<Forecast> {
        val key = getKey(cityName)
        return if (data.containsKey(key)) {
            Maybe.just(data[key])
        } else {
            Maybe.empty()
        }
    }

    private fun getKey(cityName: String) = cityName.toLowerCase(Locale.ROOT)

}