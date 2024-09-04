package com.example.notesapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.ColorOptions
import kotlinx.coroutines.selects.select

class ColorSelectorAdapter(private var colors : ArrayList<Int>) : RecyclerView.Adapter<ColorSelectorAdapter.ColorSelectorViewHolder>() {

    private var selectedColor = ColorOptions.colors[0];

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorSelectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_selector_item, parent, false)
        return ColorSelectorViewHolder(view)

    }

    override fun onBindViewHolder(holder: ColorSelectorViewHolder, position: Int) {
        holder.bind(colors[position], this::setSelectedColor)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    class ColorSelectorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var colorButton : Button = itemView.findViewById(R.id.colorButton);
        fun bind(color : Int, selectedColorFunc : (color : Int) -> Unit){
            colorButton.setOnClickListener{
                selectedColorFunc(color); //Marcamos el color seleccionado
            }
            colorButton.setBackgroundColor(color);
        }
    }

    public fun setSelectedColor(color : Int){
        selectedColor = color;
        notifyItemChanged(colors.indexOf(color));
    }

    public fun getSelectedColor() : Int{
        return selectedColor;
    }

}