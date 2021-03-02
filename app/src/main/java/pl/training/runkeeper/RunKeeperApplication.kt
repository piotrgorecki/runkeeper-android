package pl.training.runkeeper

import android.app.Application
import pl.training.runkeeper.configuration.ApplicationGraph
import pl.training.runkeeper.configuration.ApplicationModule
import pl.training.runkeeper.configuration.DaggerApplicationGraph

class RunKeeperApplication : Application() {

    companion object {

        lateinit var applicationGraph: ApplicationGraph
            private set

    }

    override fun onCreate() {
        super.onCreate()
        applicationGraph = DaggerApplicationGraph.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}