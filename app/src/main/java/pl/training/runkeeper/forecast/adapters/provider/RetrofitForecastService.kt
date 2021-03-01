package pl.training.runkeeper.forecast.adapters.provider

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.training.runkeeper.forecast.models.Forecast
import pl.training.runkeeper.forecast.models.ForecastService

class RetrofitForecastService(private val forecastProvider: ForecastProvider, private val mapper: (ForecastDto) -> Forecast = ProviderMappers::toDomainModel): ForecastService {

    override fun getForecast(cityName: String): Observable<Forecast> = forecastProvider.getWeather(cityName)
            .map(mapper)
            .toObservable()
            .subscribeOn(Schedulers.io())

}