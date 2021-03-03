package pl.training.runkeeper

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import pl.training.runkeeper.commons.SampleBroadcastReceiver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.forecast_tab, R.id.profile_tab))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val intentFilter = IntentFilter()
        intentFilter.addAction("pl.training.runkeeper.action.CUSTOM_ACTION")
        val receiver = SampleBroadcastReceiver()
        registerReceiver(receiver, intentFilter)

        val intent = Intent()
        intent.putExtra("message", "Hello there")
        intent.action = "pl.training.runkeeper.action.CUSTOM_ACTION"
        sendBroadcast(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.show_settings)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}