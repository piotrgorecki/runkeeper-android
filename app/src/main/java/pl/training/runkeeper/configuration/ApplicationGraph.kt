package pl.training.runkeeper.configuration

import dagger.Component
import pl.training.runkeeper.forecast.views.ForecastListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationGraph {

    fun inject(forecastListFragment: ForecastListFragment)

}