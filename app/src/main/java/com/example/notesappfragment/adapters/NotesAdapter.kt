package com.example.notesappfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragment.FragmentNotes
import com.example.notesappfragment.FragmentNotesDirections
import com.example.notesappfragment.dataandviewmodel.Note
import com.example.notesappfragment.databinding.NoteRowBinding

class NotesAdapter(private val fragmentNotes: FragmentNotes): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    private var notes = emptyList<Note>()

    class NotesViewHolder(val binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]

        holder.binding.apply {
            noteTV.text = note.noteText
            noteCV.setOnClickListener {
                val action = FragmentNotesDirections.actionFragmentNotesToEditDeleteFragment(
                    note.pk,
                    note.noteText
                )
                //fragmentNotes.findNavController().navigate(R.id.action_fragmentNotes_to_editDeleteFragment)
                fragmentNotes.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun updateNotes(userNotes: List<Note>) {
        this.notes = userNotes
        notifyDataSetChanged()
    }

}