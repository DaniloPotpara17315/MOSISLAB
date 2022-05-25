package elfak.mosis.myplaces

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.data.MyPlace
import elfak.mosis.myplaces.databinding.FragmentListBinding
import elfak.mosis.myplaces.model.MyPlacesViewModel


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
        pregled.adapter = ArrayAdapter(requireContext()
            ,android.R.layout.simple_list_item_1
            ,myPlacesViewModel.myPlacesList)
        pregled.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var myPlace : MyPlace = p0?.adapter?.getItem(p2) as MyPlace

                myPlacesViewModel.selected = myPlace
                findNavController().navigate(R.id.action_fragmentList_to_fragmentView)
            }

        })

        pregled.setOnCreateContextMenuListener(object : View.OnCreateContextMenuListener {
            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                val info = menuInfo as AdapterView.AdapterContextMenuInfo
                val myPlace:MyPlace = myPlacesViewModel.myPlacesList[info.position]
                menu?.setHeaderTitle(myPlace.name)
                menu?.add(0,1,1, "View Place")
                menu?.add(0,2,2, "Edit Place")
                menu?.add(0,3,3, "Delete Place")

            }
        })

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "My Places List"

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            1 -> {
                myPlacesViewModel.selected = myPlacesViewModel.myPlacesList[info.position]
                findNavController().navigate(R.id.action_fragmentList_to_fragmentView)
            }
            2 -> {
                myPlacesViewModel.selected = myPlacesViewModel.myPlacesList[info.position]
                findNavController().navigate(R.id.action_fragmentList_to_fragmentEdit)
            }
            3 -> {
                Toast.makeText(context, "Delete Item", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}