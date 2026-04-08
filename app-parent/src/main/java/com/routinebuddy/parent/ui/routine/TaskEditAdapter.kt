package com.routinebuddy.parent.ui.routine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.routinebuddy.parent.databinding.ItemTaskEditBinding

class TaskEditAdapter : RecyclerView.Adapter<TaskEditAdapter.TaskEditViewHolder>() {
    private val items = mutableListOf<String>()

    fun submitList(tasks: List<String>) {
        items.clear()
        items.addAll(tasks)
        notifyDataSetChanged()
    }

    fun addTask(task: String) {
        items.add(task)
        notifyItemInserted(items.lastIndex)
    }

    fun currentTasks(): List<String> = items.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskEditViewHolder {
        val binding = ItemTaskEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskEditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskEditViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = items.size

    inner class TaskEditViewHolder(private val binding: ItemTaskEditBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.taskTitle.setText(items[position])
            binding.removeTaskButton.setOnClickListener {
                val adapterPosition = bindingAdapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    items.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
            }
            binding.taskTitle.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val adapterPosition = bindingAdapterPosition
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        items[adapterPosition] = binding.taskTitle.text.toString()
                    }
                }
            }
        }
    }
}
