package elfak.mosis.myplaces

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
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
        
        addButton.setOnClickListener{
            val editName : EditText = binding.editName
            val name: String = editName.text.toString()
            val editDescription: EditText = binding.editDescription
            val description: String = editDescription.text.toString()
            myPlacesViewModel.addPlace(name,description)
            findNavController().navigate(R.id.action_fragmentEdit_to_fragmentList)
        }
        binding.editBtnCancel.setOnClickListener {
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


}