package com.example.aman_singh_301417077_myrun2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

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

            // Format the dateTime from Long to Date and then to a String format
            val date = Date(entry.dateTime)
            val dateFormat = SimpleDateFormat("MMM d yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val dateString = dateFormat.format(date)
            val timeString = timeFormat.format(date)

            // Assuming activityType is an Int that corresponds to a certain activity
            val activityTypeString = getActivityTypeString(entry.activityType)

            // Format distance and duration
            val distanceString = "${entry.distance} Kms"
            val hours = entry.duration.toInt() / 60
            val minutes = entry.duration.toInt() % 60
            val durationString = "${hours}hrs ${minutes}mins"

            // Now set the text of the TextViews
            exerciseTitleView.text = "Activity Type: $activityTypeString, $timeString, $dateString"
            exerciseTextView.text = "$distanceString, $durationString"
        }

        private fun getActivityTypeString(activityType: Int): String {
            return when (activityType) {
                0 -> "Running"
                1 -> "Walking"
                // Add other cases as necessary
                else -> "Unknown"
            }
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
