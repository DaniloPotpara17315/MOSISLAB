package elfak.mosis.myplaces

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import elfak.mosis.myplaces.databinding.ActivityMyPlacesListBinding
import java.lang.Exception

class EditMyPlaceActivity : AppCompatActivity(), View.OnClickListener {
    var editMode = true
    var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_place)

        try{
            val int = intent
            val positionBundle = int.extras
            if(positionBundle!=null){
                position = positionBundle.getInt("position")
            }else{
                editMode=false
            }
        }
        catch (e:Exception){
            editMode=false
        }

        val finishedButton:Button = findViewById<Button>(R.id.editmyplace_finished_button)
        finishedButton.setOnClickListener(this)
        val cancelButton:Button = findViewById<Button>(R.id.editmyplace_cancel_button)
        cancelButton.setOnClickListener(this)

        if(!editMode){
            finishedButton.isEnabled=false;
            finishedButton.setText("Add")
        }
        else if(position>=0){
            finishedButton.setText("Save")
            var place = MyPlacesData.getPlace(position)
            findViewById<EditText>(R.id.editmyplace_name_edit).setText(place.getName())
            findViewById<EditText>(R.id.editmyplace_desc_edit).setText(place.getDesc())
        }


        val nameEditText:EditText = findViewById<EditText>(R.id.editmyplace_name_edit)
        nameEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                finishedButton.isEnabled=true
            }
        })
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit_my_place, menu)
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
            androidx.appcompat.R.id.home ->finish()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(p0: View) {
        when(p0.id){
            R.id.editmyplace_finished_button->{
                if(!editMode) {
                    val etName: EditText = findViewById(R.id.editmyplace_name_edit)
                    val nme: String = etName.text.toString()
                    val etDesc: EditText = findViewById(R.id.editmyplace_desc_edit)
                    val desc: String = etDesc.text.toString()
                    MyPlacesData.addNewPlace(MyPlace(nme, desc))
                }
                else{
                    var place = MyPlacesData.getPlace(position)
                    place.setName((findViewById<EditText>(R.id.editmyplace_name_edit).text.toString()))
                    place.setDesc((findViewById<EditText>(R.id.editmyplace_desc_edit).text.toString()))

                }

                setResult(Activity.RESULT_OK)
                finish()
            }
            R.id.editmyplace_cancel_button->{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }

    }
}