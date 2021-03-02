package pl.training.runkeeper.forecast.adapters.provider

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastProvider

class RetrofitForecastProvider(private val forecastApi: ForecastApi, private val mapper: (ForecastDto) -> Forecast = ProviderMappers::toDomainModel): ForecastProvider {

    override fun getForecast(cityName: String): Maybe<Forecast> = forecastApi.getWeather(cityName)
            .map(mapper)
            .subscribeOn(Schedulers.io())
            .toMaybe()

}