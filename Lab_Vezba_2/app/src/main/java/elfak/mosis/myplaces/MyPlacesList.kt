package elfak.mosis.myplaces

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.snackbar.Snackbar
import elfak.mosis.myplaces.databinding.ActivityMyPlacesListBinding


class MyPlacesList : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMyPlacesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMyPlacesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var pregled:ListView = findViewById(R.id.my_places_list)
        val arrayAdapter:ArrayAdapter<MyPlace> = ArrayAdapter(this
            ,android.R.layout.simple_list_item_1
            ,MyPlacesData.getMyPlaces())
        pregled.adapter = arrayAdapter
        pregled.setOnItemClickListener{adapterView,view,i,l->
            var positionBundle = Bundle()
            positionBundle.putInt("position",i)
            val I = Intent(this,ViewMyPlaceActivity::class.java)
            I.putExtras(positionBundle)
            startActivity(I)
        }
        pregled.setOnCreateContextMenuListener{contextMenu,view,contextMenuInfo->
            val info:AdapterView.AdapterContextMenuInfo = contextMenuInfo as AdapterView.AdapterContextMenuInfo
            val place:MyPlace = MyPlacesData.getPlace(info.position)
            contextMenu.setHeaderTitle(place.getName())
            contextMenu.add(0,1,1,"View Place")
            contextMenu.add(0,2,2,"Edit Place")
        }
        binding.fab.setOnClickListener { view ->
            val i = Intent(this,EditMyPlaceActivity::class.java)
            startActivityForResult(i,2)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info:AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val positionBundle = Bundle()
        positionBundle.putInt("position",info.position)
        var i: Intent? = null
        if(item.itemId == 1){
            i = Intent(this,ViewMyPlaceActivity::class.java)
            i.putExtras(positionBundle)
            startActivity(i)
        }
        else if(item.itemId == 2){
            i = Intent(this,EditMyPlaceActivity::class.java)
            i.putExtras(positionBundle)
            startActivityForResult(i,2)
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_my_places_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.show_map_item -> Toast.makeText(this,"Show Map!", Toast.LENGTH_SHORT).show()
            R.id.new_place_item -> {
                val i = Intent(this,EditMyPlaceActivity::class.java)
                startActivityForResult(i,1)
            }
            R.id.about_item -> {
                val i = Intent(this,About::class.java)
                startActivity(i)
            }
            androidx.appcompat.R.id.home ->finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2){
            var pregled:ListView = findViewById(R.id.my_places_list)
            val arrayAdapter:ArrayAdapter<MyPlace> = ArrayAdapter(this
                ,android.R.layout.simple_list_item_1
                ,MyPlacesData.getMyPlaces())
            pregled.adapter = arrayAdapter
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}