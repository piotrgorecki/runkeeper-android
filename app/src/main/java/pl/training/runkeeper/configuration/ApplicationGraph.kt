package pl.training.runkeeper.configuration

import dagger.Component
import pl.training.runkeeper.forecast.ForecastModule
import pl.training.runkeeper.forecast.viewmodels.ForecastViewModel
import pl.training.runkeeper.forecast.views.ForecastListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ForecastModule::class])
interface ApplicationGraph {

    fun inject(forecastViewModel: ForecastViewModel)

    fun inject(forecastListFragment: ForecastListFragment)

}