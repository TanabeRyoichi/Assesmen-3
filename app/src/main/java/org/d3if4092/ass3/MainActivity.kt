package org.d3if4092.ass3


import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit



class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment)
        val appConfiguration = AppBarConfiguration(setOf(R.id.konversiFragment, R.id.hasilFragment))
        setupActionBarWithNavController(navController, appConfiguration)

        bottomNavigationView.setupWithNavController(navController)



        val constraints = Constraints.Builder().setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED).build()


        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(BackgroundTask::class.java, 1, TimeUnit.SECONDS)
                .setInputData(Data.Builder().putBoolean("isStart", true).build())
                .setInitialDelay(6000, TimeUnit.MILLISECONDS)
                .build()

        val workManager = WorkManager.getInstance(this)

        workManager.enqueue(periodicWorkRequest)

        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id).observeForever {
            if (it != null) {

                Log.d("periodicWorkRequest", "Status changed to ${it.state.isFinished}")

            }

        }
    }
}













