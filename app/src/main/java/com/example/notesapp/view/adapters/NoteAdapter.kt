package com.example.notesapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.NoteItemBinding
import com.example.notesapp.model.Note

class NoteAdapter(private var notes : ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view);
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    public fun reloadData(newList : ArrayList<Note>){
        //this.notifyDataSetChanged()
        this.notifyItemInserted(this.notes.size - 1);
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding : NoteItemBinding = NoteItemBinding.bind(itemView);
        fun bind(note : Note){

            binding.modifyStateLayout.visibility = View.GONE;

            itemView.setOnClickListener{
                if(binding.normalStateLayout.visibility == View.VISIBLE){
                    binding.normalStateLayout.visibility = View.GONE;
                    binding.modifyStateLayout.visibility = View.VISIBLE;
                }
            }

            //normal state
            binding.normalStateLayout.setBackgroundColor(note.color);
            binding.normalTitleTextView.text = note.title;
            binding.normalDescriptionTextView.text = note.description;

            //modify state
            binding.modifyStateLayout.setBackgroundColor(note.color);
            binding.modifyTitleEditText.setText(note.title);
            binding.modifyDescriptionEditText.setText(note.description);
            binding.colorSelectorComponent.setSelectedColor(note.color);

            setupButtons()
        }

        private fun setupButtons(){
            binding.saveNoteButton.setOnClickListener{
                binding.normalStateLayout.visibility = View.VISIBLE;
                binding.modifyStateLayout.visibility = View.GONE;
            }

            binding.deleteNoteButton.setOnClickListener{
                binding.normalStateLayout.visibility = View.VISIBLE;
                binding.modifyStateLayout.visibility = View.GONE;
            }

            binding.cancelNoteButton.setOnClickListener{
                binding.normalStateLayout.visibility = View.VISIBLE;
                binding.modifyStateLayout.visibility = View.GONE;
            }
        }
    }
}