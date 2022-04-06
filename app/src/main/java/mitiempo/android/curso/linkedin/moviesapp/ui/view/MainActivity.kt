package mitiempo.android.curso.linkedin.moviesapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import mitiempo.android.curso.linkedin.moviesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//        setSupportActionBar(findViewById(R.id.my_toolbar))

    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu);

        var menuItem: MenuItem = menu!!.findItem(R.id.action_search)
        var searchView: SearchView = menuItem.actionView()

        return super.onCreateOptionsMenu(menu)
    }*/


}