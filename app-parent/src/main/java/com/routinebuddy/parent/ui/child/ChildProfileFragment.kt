package com.routinebuddy.parent.ui.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.routinebuddy.parent.MainActivity
import com.routinebuddy.parent.databinding.FragmentChildProfileBinding
import com.routinebuddy.parent.viewmodel.ChildProfileViewModel
import com.routinebuddy.parent.viewmodel.ParentViewModelFactory

class ChildProfileFragment : Fragment() {

    private var _binding: FragmentChildProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChildProfileViewModel by activityViewModels {
        ParentViewModelFactory((requireActivity() as MainActivity).repository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChildProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ageGroups = listOf("3-5", "6-8", "9-12")
        binding.ageGroupDropdown.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, ageGroups)
        )

        viewModel.child.observe(viewLifecycleOwner) { child ->
            if (child != null) {
                binding.childNameInput.setText(child.name)
                binding.ageGroupDropdown.setText(child.ageGroup, false)
                (requireActivity() as MainActivity).openRoutineList()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            binding.childNameLayout.error = error
            if (!error.isNullOrBlank()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }

        binding.saveButton.setOnClickListener {
            val age = binding.ageGroupDropdown.text?.toString()?.ifBlank { "6-8" } ?: "6-8"
            viewModel.saveChild(binding.childNameInput.text.toString(), age) {
                (requireActivity() as MainActivity).openRoutineList()
            }
        }

        viewModel.loadChild()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ChildProfileFragment = ChildProfileFragment()
    }
}
