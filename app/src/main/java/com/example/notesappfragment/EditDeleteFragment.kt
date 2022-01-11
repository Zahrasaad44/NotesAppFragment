package com.example.notesappfragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesappfragment.dataandviewmodel.NotesViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditDeleteFragment : Fragment() {

    lateinit var viewModel: NotesViewModel

    val args: EditDeleteFragmentArgs by navArgs()  //navArgs, to pass data from the Recycler view

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_delete, container, false)

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val editBtn =
            view.findViewById<Button>(R.id.editBtn)  //Note: try doing this with View binding
        val deleteBtn = view.findViewById<Button>(R.id.deleteBtn)
        val editDeleteET = view.findViewById<EditText>(R.id.editDeleteET)

        editDeleteET.setText(args.text)

        editBtn.setOnClickListener {
            viewModel.editNote(args.id, editDeleteET.text.toString())
            Navigation.findNavController(view)
                .navigate(EditDeleteFragmentDirections.actionEditDeleteFragmentToFragmentNotes())
        }

        deleteBtn.setOnClickListener {
            displayDeleteConformation(view)
        }

        return view
    }

    private fun displayDeleteConformation(thisView: View) {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                viewModel.deleteNote(args.id)

                Navigation.findNavController(thisView)
                    .navigate(EditDeleteFragmentDirections.actionEditDeleteFragmentToFragmentNotes())
            })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Delete Confirmation")
        alert.show()
    }

}