package pl.training.runkeeper.forecast

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pl.training.runkeeper.forecast.adapters.persistence.InMemoryForecastRepository
import pl.training.runkeeper.forecast.adapters.provider.ForecastApi
import pl.training.runkeeper.forecast.adapters.provider.RetrofitForecastProvider
import pl.training.runkeeper.forecast.models.ForecastRepository
import pl.training.runkeeper.forecast.models.ForecastProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ForecastModule {

    @Singleton
    @Provides
    fun forecastApi(httpClient: OkHttpClient): ForecastApi = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ForecastApi::class.java)

    @Singleton
    @Provides
    fun forecastProvider(forecastApi: ForecastApi): ForecastProvider = RetrofitForecastProvider(forecastApi)

    @Singleton
    @Provides
    fun inMemoryForecastRepository(): ForecastRepository = InMemoryForecastRepository()

}