package com.routinebuddy.parent.ui.routine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.routinebuddy.parent.MainActivity
import com.routinebuddy.parent.databinding.FragmentRoutineEditorBinding
import com.routinebuddy.parent.viewmodel.ParentViewModelFactory
import com.routinebuddy.parent.viewmodel.RoutineEditorViewModel

class RoutineEditorFragment : Fragment() {

    private var _binding: FragmentRoutineEditorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoutineEditorViewModel by activityViewModels {
        ParentViewModelFactory((requireActivity() as MainActivity).repository())
    }

    private lateinit var adapter: TaskEditAdapter
    private var routineId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routineId = arguments?.getString(ARG_ROUTINE_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutineEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TaskEditAdapter()
        binding.taskRecycler.adapter = adapter
        binding.taskRecycler.layoutManager = LinearLayoutManager(requireContext())

        val icons = listOf("⭐", "📚", "🪥", "🍽️", "🧸")
        binding.iconDropdown.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, icons)
        )

        viewModel.screenTitle.observe(viewLifecycleOwner) { title ->
            binding.screenTitle.text = title
        }

        viewModel.initialTitle.observe(viewLifecycleOwner) { title ->
            binding.routineTitleInput.setText(title)
        }

        viewModel.initialIcon.observe(viewLifecycleOwner) { icon ->
            binding.iconDropdown.setText(icon ?: "⭐", false)
        }

        viewModel.initialTasks.observe(viewLifecycleOwner) { tasks ->
            adapter.submitList(tasks)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            binding.routineTitleLayout.error = if (error?.contains("title") == true) error else null
            if (!error.isNullOrBlank()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }

        binding.addTaskButton.setOnClickListener {
            val text = binding.newTaskInput.text.toString().trim()
            if (text.isNotBlank()) {
                adapter.addTask(text)
                binding.newTaskInput.setText("")
            }
        }

        binding.saveRoutineButton.setOnClickListener {
            val tasks = adapter.currentTasks().map { it.trim() }.filter { it.isNotBlank() }
            viewModel.save(
                routineId = routineId,
                title = binding.routineTitleInput.text.toString(),
                icon = binding.iconDropdown.text?.toString(),
                taskTitles = tasks
            ) {
                (requireActivity() as MainActivity).closeEditor()
            }
        }

        viewModel.load(routineId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ROUTINE_ID = "routine_id"

        fun newCreateInstance(): RoutineEditorFragment = RoutineEditorFragment()

        fun newEditInstance(routineId: String): RoutineEditorFragment {
            return RoutineEditorFragment().apply {
                arguments = bundleOf(ARG_ROUTINE_ID to routineId)
            }
        }
    }
}
