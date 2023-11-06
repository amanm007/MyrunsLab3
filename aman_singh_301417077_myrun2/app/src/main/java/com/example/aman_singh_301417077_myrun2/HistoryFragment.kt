package com.example.aman_singh_301417077_myrun2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : Fragment() {

    private lateinit var exerciseViewModel: ExerciseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_history)
        val adapter = ExerciseEntryAdapter {
            // Handle click event, show details or map
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        exerciseViewModel = ViewModelProvider(this, ExerciseViewModelFactory((activity?.application as YourApplication).repository)).get(ExerciseViewModel::class.java)

        exerciseViewModel.allEntries.observe(viewLifecycleOwner, { entries ->
            entries?.let { adapter.submitList(it) }
        })

        return view
    }
}