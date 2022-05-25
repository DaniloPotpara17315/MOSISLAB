package elfak.mosis.myplaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.databinding.FragmentViewBinding
import elfak.mosis.myplaces.model.MyPlacesViewModel


class FragmentView : Fragment() {

    private val viewModel : MyPlacesViewModel by activityViewModels()
    private var _binding : FragmentViewBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.nameBox
        name.text = viewModel.selected!!.name
        val description = binding.descriptionBody
        description.text = viewModel.selected!!.name

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "View My Place"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.selected = null
    }
}