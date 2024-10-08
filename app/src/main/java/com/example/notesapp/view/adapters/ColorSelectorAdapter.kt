package com.example.notesapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.NoteItemBinding
import com.example.notesapp.model.ColorOptions
import com.example.notesapp.view.components.ColorSelectorComponent
import kotlinx.coroutines.selects.select

class ColorSelectorAdapter(private var colors : ArrayList<Int>) : RecyclerView.Adapter<ColorSelectorAdapter.ColorSelectorViewHolder>() {

    private var selectedColor = ColorOptions.colors[0];

    //esta mecanica den onColorChanged funciona en forma de embudo
    //pasa de componente a componente debnido a que no hay conexion directa entre ellos
    private var onColorChanged : () -> Unit = {};

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorSelectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_selector_item, parent, false)
        return ColorSelectorViewHolder(view)

    }

    public fun setOnColorChanged(call : () -> Unit){
        this.onColorChanged = call;
    }

    override fun onBindViewHolder(holder: ColorSelectorViewHolder, position: Int) {
        holder.bind(colors[position], this::setSelectedColor)
        //La funcion recibida por el adapter pasa al viewholder
        holder.setOnColorChanged { onColorChanged() }
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    class ColorSelectorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var colorButton : Button = itemView.findViewById(R.id.colorButton);
        private var onColorChanged : () -> Unit = {};

        fun bind(color : Int, selectedColorFunc : (color : Int) -> Unit){
            colorButton.setOnClickListener{
                selectedColorFunc(color); //Marcamos el color seleccionado
                onColorChanged();
            }
            colorButton.setBackgroundColor(color);
        }

        public fun setOnColorChanged(call : () -> Unit){
            this.onColorChanged = call;
        }
    }
    //Enviar hacia arriba del embudo el color
    public fun setSelectedColor(color : Int){
        selectedColor = color;
    }

    public fun getSelectedColor() : Int{
        return selectedColor;
    }

}