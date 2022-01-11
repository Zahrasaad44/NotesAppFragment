package com.example.notesappfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragment.adapters.NotesAdapter
import com.example.notesappfragment.dataandviewmodel.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentNotes : Fragment() {

    lateinit var viewModel: NotesViewModel
    private val recyclerAdapter = NotesAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        viewModel.getNotes().observe(viewLifecycleOwner, {
                userNotes ->
            recyclerAdapter.updateNotes(userNotes)
        })

        viewModel.fetchNotes()

        val notesRV = view.findViewById<RecyclerView>(R.id.notesRV)
        notesRV.adapter = recyclerAdapter
        notesRV.layoutManager = GridLayoutManager(requireContext(), 2)

        val floatingBtn = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragmentNotes_to_newNoteFragment)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        //Coroutine is used to call the fetchNotes() from our ViewModel after delaying it one second because
        // Firestore takes some time (after updating or deleting)
        CoroutineScope(IO).launch {
            delay(1000)
            viewModel.fetchNotes()
        }
    }

}