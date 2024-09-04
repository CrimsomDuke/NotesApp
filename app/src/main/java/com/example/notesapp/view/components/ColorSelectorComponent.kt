package com.example.notesapp.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ColorSelecterComponentBinding
import com.example.notesapp.databinding.ColorSelectorItemBinding
import com.example.notesapp.model.ColorOptions
import com.example.notesapp.view.adapters.ColorSelectorAdapter

class ColorSelectorComponent(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private lateinit var binding : ColorSelecterComponentBinding;

    init {
        binding = ColorSelecterComponentBinding.inflate(LayoutInflater.from(context), this, true);
        setupRecyclerView();
    }

    public fun setSelectedColor(color : Int){
        binding.rvColors.adapter.apply {
            (this as ColorSelectorAdapter).setSelectedColor(color);
        }
    }

    public fun onColorChanged(call : () -> Unit){
        this.binding.rvColors.adapter.apply {
            (this as ColorSelectorAdapter).setOnColorChanged(call);
        }
    }

    public fun getSelectedColor() : Int{
        return (binding.rvColors.adapter as ColorSelectorAdapter).getSelectedColor();
    }

    private fun setupRecyclerView(){
        binding.rvColors.adapter = ColorSelectorAdapter(ColorOptions.colors);
        binding.rvColors.layoutManager = LinearLayoutManager(this.context).apply {
            orientation = LinearLayoutManager.HORIZONTAL;
        };
    }
}