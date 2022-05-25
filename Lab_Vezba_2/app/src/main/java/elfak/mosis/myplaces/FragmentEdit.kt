package elfak.mosis.myplaces

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.databinding.FragmentEditBinding
import elfak.mosis.myplaces.model.MyPlacesViewModel

class FragmentEdit : Fragment() {

    private val myPlacesViewModel : MyPlacesViewModel by activityViewModels()
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addButton: Button = binding.editBtnAdd
        addButton.isEnabled = false
        val editName : EditText = binding.editName
        val editDescription: EditText = binding.editDescription

        if (myPlacesViewModel.selected!=null) {
            editName.setText(myPlacesViewModel.selected!!.name)
            editDescription.setText(myPlacesViewModel.selected!!.description)
            addButton.isEnabled = true
            addButton.text = getString(R.string.btn_save)
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "Edit My Place"
        }
        else (requireActivity() as AppCompatActivity).supportActionBar?.title = "Add My Place"

        editName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                addButton.isEnabled = editName.text.isNotEmpty()
            }
        })
        addButton.setOnClickListener{
            val name: String = editName.text.toString()
            val description: String = editDescription.text.toString()

            if (myPlacesViewModel.selected!=null) {
                myPlacesViewModel.selected!!.name = name
                myPlacesViewModel.selected!!.description = description
            }
            else  myPlacesViewModel.addPlace(name,description)
            findNavController().navigate(R.id.action_fragmentEdit_to_fragmentList)
        }
        binding.editBtnCancel.setOnClickListener {
            myPlacesViewModel.selected = null
            findNavController().navigate(R.id.action_fragmentEdit_to_fragmentList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_my_places_list -> {
                this.findNavController().navigate(R.id.action_fragmentEdit_to_fragmentList)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_new_place)
        item.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        myPlacesViewModel.selected = null
    }
}