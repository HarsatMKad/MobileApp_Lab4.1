package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.databinding.RviewItemBinding
import kotlin.coroutines.coroutineContext

interface CellClickListener{
    fun onCellClickListenet(color: ColorData)
}

data class ColorData(val colorName: String, val colorHex: Int)

class MainActivity : AppCompatActivity(), CellClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this, fetchList(), this)
    }

    private fun fetchList(): ArrayList<ColorData> {
        val ColorList = arrayListOf<ColorData>()
        val color1 = ColorData("Blue", Color.BLUE)
        val color2 = ColorData("Red", Color.RED)
        val color3 = ColorData("Black", Color.BLACK)
        val color4 = ColorData("Yellow", Color.YELLOW)
        val color5 = ColorData("Gray", Color.GRAY)
        val color6 = ColorData("White", Color.WHITE)
        ColorList.add((color1))
        ColorList.add((color2))
        ColorList.add((color3))
        ColorList.add((color4))
        ColorList.add((color5))
        ColorList.add((color6))
        return ColorList
    }

    override fun onCellClickListenet(color: ColorData) {
        Toast.makeText(this, "IT'S " + color.colorName, Toast.LENGTH_SHORT).show()
    }
}

class Adapter(private val context: Context, private val list: ArrayList<ColorData>, private val cellClickListener: CellClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val color: View = view.findViewById(R.id.view)
        val textColor: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.color.setBackgroundColor(data.colorHex)
        holder.textColor.text = data.colorName

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListenet(data)
        }
    }
}








/*
class Adapter(private val cellClickListener : CellClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    val ColorList = ArrayList<ColorData>()
    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = RviewItemBinding.bind(item)
        fun bind(colorData: ColorData){
            binding.view.setBackgroundColor(colorData.colorHex)
            binding.textView.text = colorData.colorName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ColorList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = ColorList[position]

        holder.binding.view.setBackgroundColor(data.colorHex)
        holder.bind(ColorList[position])
        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListenet(ColorList[position])
        }
    }

    fun addColor(){
        val color1 = ColorData("Blue", Color.BLUE)
        val color2 = ColorData("Red", Color.RED)
        val color3 = ColorData("Black", Color.BLACK)
        val color4 = ColorData("Yellow", Color.YELLOW)
        val color5 = ColorData("Gray", Color.GRAY)
        val color6 = ColorData("White", Color.WHITE)

        ColorList.add((color1))
        ColorList.add((color2))
        ColorList.add((color3))
        ColorList.add((color4))
        ColorList.add((color5))
        ColorList.add((color6))
    }
}

class MainActivity : AppCompatActivity(), CellClickListener {

    lateinit var binding: ActivityMainBinding
    private val adapter = Adapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rView.layoutManager = LinearLayoutManager(this@MainActivity)
            rView.adapter = adapter
            adapter.addColor()
        }
    }

    override fun onCellClickListenet(color: ColorData) {
        fun onCellClickListenet(color: ColorData){
            Toast.makeText(this, "IT'S " + color.colorName, Toast.LENGTH_SHORT).show()
        }
    }
}

 */