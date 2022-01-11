package com.example.notesappfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notesappfragment.dataandviewmodel.Note
import com.example.notesappfragment.dataandviewmodel.NotesViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class newNoteFragment : Fragment() {

    lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_new_note, container, false)

        val userNoteET = view.findViewById<EditText>(R.id.userNoteET)
        val addBtn = view.findViewById<Button>(R.id.addBtn)

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        addBtn.setOnClickListener {
            if (userNoteET.text.isNotEmpty()) {
                viewModel.addNote(Note("", userNoteET.text.toString()))
                Navigation.findNavController(view)
                    .navigate(R.id.action_newNoteFragment_to_fragmentNotes)
            } else {
                Toast.makeText(requireContext(), "Type something to add a note", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

}