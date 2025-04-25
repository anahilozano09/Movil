package mx.unam.movil.video
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import mx.unam.movil.R
class RecipeAdapter(context: Context,private val list:ArrayList<Modelo>): BaseAdapter() {
    private var inflter:LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return list.size
    }
    override fun getItem(p0: Int): Any? {
        return null
    }
    override fun getItemId(p0: Int): Long {
        return 1
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view=inflter.inflate(R.layout.list_item_row,null)
        val file:TextView= view.findViewById(R.id.title)
        val image:ImageView=view.findViewById(R.id.image_pel)
        file.text=list[p0].nameFile
        image.setImageResource(list[p0].nameImage)
        return view
    }
}