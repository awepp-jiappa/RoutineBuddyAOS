package com.routinebuddy.parent.ui.routine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.routinebuddy.parent.MainActivity
import com.routinebuddy.parent.databinding.FragmentRoutineListBinding
import com.routinebuddy.parent.viewmodel.ParentViewModelFactory
import com.routinebuddy.parent.viewmodel.RoutineListViewModel

class RoutineListFragment : Fragment() {

    private var _binding: FragmentRoutineListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoutineListViewModel by activityViewModels {
        ParentViewModelFactory((requireActivity() as MainActivity).repository())
    }

    private lateinit var adapter: RoutineListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutineListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RoutineListAdapter { routine ->
            (requireActivity() as MainActivity).openEditRoutine(routine.id)
        }
        binding.routineRecycler.adapter = adapter
        binding.routineRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.addRoutineButton.setOnClickListener {
            (requireActivity() as MainActivity).openCreateRoutine()
        }

        viewModel.routines.observe(viewLifecycleOwner) { routines ->
            adapter.submitList(routines)
            binding.emptyState.visibility = if (routines.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): RoutineListFragment = RoutineListFragment()
    }
}
