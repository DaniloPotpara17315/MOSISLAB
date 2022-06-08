package elfak.mosis.myplaces

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import elfak.mosis.myplaces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
                if (destination.id == R.id.fragmentEdit || destination.id == R.id.fragmentView) {
                    binding.fab.hide()
                }
                else {
                    binding.fab.show()
                }
            }


        binding.fab.setOnClickListener { view ->
            if (navController.currentDestination?.id == R.id.fragmentHome) {
                navController.navigate(R.id.action_fragmentHome_to_fragmentEdit)
            }
            else if (navController.currentDestination?.id == R.id.fragmentList) {
                navController.navigate(R.id.action_fragmentList_to_fragmentEdit)
            }
            else if (navController.currentDestination?.id==R.id.mapFragment) {
                navController.navigate(R.id.action_map_to_edit)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_show_map -> {

                when(navController.currentDestination?.id) {
                    R.id.fragmentList -> findNavController(R.id.nav_host_fragment_content_main)
                        .navigate(R.id.action_list_to_map)
                    R.id.fragmentHome -> findNavController(R.id.nav_host_fragment_content_main)
                        .navigate(R.id.action_goto_map)
                }
            }
            R.id.action_new_place -> Toast.makeText(this,"New Place!",Toast.LENGTH_SHORT).show()
            R.id.action_my_places_list -> {
                //this.findNavController(R.id.nav_host_fragment_content_main)
                    //.navigate(R.id.action_fragmentHome_to_fragmentList)
            }
            R.id.action_about -> {
                val i:Intent = Intent(this,About::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}