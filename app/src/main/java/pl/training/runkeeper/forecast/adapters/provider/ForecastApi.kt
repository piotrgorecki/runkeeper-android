package pl.training.runkeeper.forecast.adapters.provider

import io.reactivex.rxjava3.core.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast/daily?cnt=7&units=metric&APPID=b933866e6489f58987b2898c89f542b8")
    fun getWeather(@Query("q") city: String): Maybe<ForecastDto>

}