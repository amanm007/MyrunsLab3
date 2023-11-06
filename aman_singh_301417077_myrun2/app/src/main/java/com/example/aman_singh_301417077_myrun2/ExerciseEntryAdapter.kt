package com.example.aman_singh_301417077_myrun2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ExerciseEntryAdapter(private val onClick: (ExerciseEntry) -> Unit) :
    ListAdapter<ExerciseEntry, ExerciseEntryAdapter.ExerciseEntryViewHolder>(ExerciseEntryDiffCallback) {

    class ExerciseEntryViewHolder(itemView: View, val onClick: (ExerciseEntry) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val exerciseTitleView: TextView = itemView.findViewById(R.id.exercise_text)
        private val exerciseTextView: TextView = itemView.findViewById(R.id.exercise_text)
        private var currentExerciseEntry: ExerciseEntry? = null

        init {
            itemView.setOnClickListener {
                currentExerciseEntry?.let {
                    onClick(it)
                }
            }
        }

        fun bind(entry: ExerciseEntry) {
            currentExerciseEntry = entry
            // Set the text of the TextViews to the appropriate data from the ExerciseEntry
            exerciseTitleView.text = entry.activityType.toString() // Replace with actual data
            exerciseTextView.text = "${entry.distance} km, ${entry.duration} min"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseEntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_entry_item, parent, false)
        return ExerciseEntryViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ExerciseEntryViewHolder, position: Int) {
        val entry = getItem(position)
        holder.bind(entry)
    }
}

object ExerciseEntryDiffCallback : DiffUtil.ItemCallback<ExerciseEntry>() {
    override fun areItemsTheSame(oldItem: ExerciseEntry, newItem: ExerciseEntry): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExerciseEntry, newItem: ExerciseEntry): Boolean {
        return oldItem == newItem
    }
}