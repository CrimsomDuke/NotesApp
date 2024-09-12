package com.example.notesapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.NoteItemBinding
import com.example.notesapp.db.FakeDB
import com.example.notesapp.model.Note

class NoteAdapter(private var notes : ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteJustCreated : Boolean = false;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view);
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    public fun reloadData(changePos : Int){
        this.notifyDataSetChanged()
    }

    public fun noteCreated(){
        noteJustCreated = true;
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position], position, { reloadData(position) });
        if(noteJustCreated){
            holder.openModifyState();
            noteJustCreated = false;
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding : NoteItemBinding = NoteItemBinding.bind(itemView);
        private var callOnChange : () -> Unit = {};

        fun bind(note : Note, position: Int, callOnChange : () -> Unit){

            this.callOnChange = callOnChange;

            binding.modifyStateLayout.visibility = View.GONE;

            itemView.setOnClickListener{
                if(binding.normalStateLayout.visibility == View.VISIBLE){
                    openModifyState();
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


            //envia la funcion de cambiar el background al embudo del color choto
            binding.colorSelectorComponent.onColorChanged {
                binding.modifyStateLayout.setBackgroundColor(binding.colorSelectorComponent.getSelectedColor());
            }

            setupButtons(position)
        }

        private fun setupButtons(position: Int){
            binding.saveNoteButton.setOnClickListener{
                if(isNoteEmpty()){
                    //Si esta vacio, para que mantenerlo, duh
                   FakeDB.notes.removeAt(position);
                }else{
                    FakeDB.notes[position].title = binding.modifyTitleEditText.text.toString();
                    FakeDB.notes[position].description = binding.modifyDescriptionEditText.text.toString();
                    FakeDB.notes[position].color = binding.colorSelectorComponent.getSelectedColor();
                }
                callOnChange();
                openNormalState()
            }

            binding.deleteNoteButton.setOnClickListener{
                FakeDB.notes.removeAt(position);
                callOnChange();
                openNormalState()
            }

            binding.cancelNoteButton.setOnClickListener{
                if(isNoteEmpty()){
                    FakeDB.notes.removeAt(position);
                }
                callOnChange();
                openNormalState();
            }
        }

        public fun openNormalState(){
            binding.normalStateLayout.visibility = View.VISIBLE;
            binding.modifyStateLayout.visibility = View.GONE;
        }

        public fun openModifyState(){
            binding.normalStateLayout.visibility = View.GONE;
            binding.modifyStateLayout.visibility = View.VISIBLE;
        }

        private fun isNoteEmpty() : Boolean{
            return binding.modifyTitleEditText.text.toString().isEmpty() && binding.modifyDescriptionEditText.text.toString().isEmpty();
        }
    }
}