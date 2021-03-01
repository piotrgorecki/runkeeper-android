package pl.training.runkeeper.forecast

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pl.training.runkeeper.forecast.models.api.WeatherProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ForecastModule {

    @Singleton
    @Provides
    fun weatherProvider(httpClient: OkHttpClient): WeatherProvider = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherProvider::class.java)

}