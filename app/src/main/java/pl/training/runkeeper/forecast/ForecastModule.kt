package pl.training.runkeeper.forecast

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pl.training.runkeeper.forecast.adapters.provider.ForecastProvider
import pl.training.runkeeper.forecast.adapters.provider.RetrofitForecastService
import pl.training.runkeeper.forecast.models.ForecastService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ForecastModule {

    @Singleton
    @Provides
    fun forecastProvider(httpClient: OkHttpClient): ForecastProvider = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ForecastProvider::class.java)

    @Singleton
    @Provides
    fun forecastService(forecastProvider: ForecastProvider): ForecastService = RetrofitForecastService(forecastProvider)

}