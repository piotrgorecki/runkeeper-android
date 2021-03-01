package pl.training.runkeeper.configuration

import dagger.Module
import dagger.Provides
import pl.training.runkeeper.commons.AndroidLogger
import pl.training.runkeeper.commons.Logger
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun logger(): Logger = AndroidLogger()

}