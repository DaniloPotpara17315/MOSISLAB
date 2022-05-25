package elfak.mosis.myplaces

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.data.MyPlace
import elfak.mosis.myplaces.databinding.FragmentListBinding
import elfak.mosis.myplaces.model.MyPlacesViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FragmentList : Fragment() {

    private val myPlacesViewModel: MyPlacesViewModel by activityViewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_new_place -> {
                this.findNavController().navigate(R.id.action_fragmentList_to_fragmentEdit)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_my_places_list)
        item.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pregled: ListView = binding.myPlacesList
        val arrayAdapter: ArrayAdapter<MyPlace> = ArrayAdapter(requireContext()
            ,android.R.layout.simple_list_item_1
            ,myPlacesViewModel.myPlacesList)
        pregled.adapter = arrayAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}