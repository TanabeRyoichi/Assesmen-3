package org.d3if4092.ass3


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.d3if4092.ass3.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment)
        val appConfiguration = AppBarConfiguration(setOf(R.id.konversiFragment, R.id.hasilFragment))
        setupActionBarWithNavController(navController, appConfiguration)

        bottomNavigationView.setupWithNavController(navController)

    }

}













