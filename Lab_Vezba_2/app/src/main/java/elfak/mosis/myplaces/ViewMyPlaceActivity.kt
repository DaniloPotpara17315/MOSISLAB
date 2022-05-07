package elfak.mosis.myplaces

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import elfak.mosis.myplaces.databinding.ActivityViewMyPlaceBinding
import org.w3c.dom.Text
import java.lang.Exception

class ViewMyPlaceActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityViewMyPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewMyPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        var position = -1
        try{
            var Intent = intent
            var positionBundle = Intent.extras
            if (positionBundle != null) {
                position = positionBundle.getInt("position")
            }
        }
        catch (e:Exception){
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            finish()
        }
    if(position>=0) {
        var place = MyPlacesData.getPlace(position)
        findViewById<TextView>(R.id.viewmyplace_name_text).setText(place.getName())
        findViewById<TextView>(R.id.viewmyplace_desc_text).setText(place.getDesc())

        }

        findViewById<Button>(R.id.viewmyplace_finished_button).setOnClickListener{
            finish()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_view_my_place, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_show_map -> Toast.makeText(this,"Show Map!", Toast.LENGTH_SHORT).show()
            R.id.action_about -> {
                val i: Intent = Intent(this,About::class.java)
                startActivity(i)
            }
            R.id.action_my_places_list -> {
                val i:Intent = Intent(this,MyPlacesList::class.java)
                startActivity(i)
            }
            androidx.appcompat.R.id.home ->finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}