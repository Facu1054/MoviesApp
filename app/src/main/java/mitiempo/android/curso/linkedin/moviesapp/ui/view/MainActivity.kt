package mitiempo.android.curso.linkedin.moviesapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import mitiempo.android.curso.linkedin.moviesapp.R
import mitiempo.android.curso.linkedin.moviesapp.databinding.ActivityMainBinding
import mitiempo.android.curso.linkedin.moviesapp.databinding.ActivityMoviesBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment*/
        //val navController = navHostFragment.navController
        val bottomNAv = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNAv.setupWithNavController(navController)
//        setSupportActionBar(findViewById(R.id.my_toolbar))
        /*binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.listMovies -> {
                    // Write your code here
                    Log.i("Tesss","ok")
                    navController.navigate()
                }
                R.id.listtv-> {
                    // Write your code here
                    Log.i("Tesss","ok")

                }
            }
            return@setOnItemSelectedListener true
        }*/
        //binding.bottomNavigation.setupWithNavController(navController)
    }







}