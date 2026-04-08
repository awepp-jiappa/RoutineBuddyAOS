package com.routinebuddy.parent.ui.routine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.routinebuddy.parent.data.entity.RoutineSummary
import com.routinebuddy.parent.databinding.ItemRoutineBinding

class RoutineListAdapter(
    private val onClick: (RoutineSummary) -> Unit
) : RecyclerView.Adapter<RoutineListAdapter.RoutineViewHolder>() {

    private val items = mutableListOf<RoutineSummary>()

    fun submitList(routines: List<RoutineSummary>) {
        items.clear()
        items.addAll(routines)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val binding = ItemRoutineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoutineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        val routine = items[position]
        holder.bind(routine)
    }

    override fun getItemCount(): Int = items.size

    inner class RoutineViewHolder(private val binding: ItemRoutineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: RoutineSummary) {
            binding.routineTitle.text = listOfNotNull(routine.icon, routine.title).joinToString(" ")
            binding.taskCount.text = "${routine.taskCount} tasks"
            binding.root.setOnClickListener { onClick(routine) }
        }
    }
}
